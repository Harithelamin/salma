package com.salma.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="APP_TRANSPLANT")
public class Transplant implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date createdOn;

    @Column
    private String createdBy;

    @Column
    private Date updatedOn;

    @Column
    private String updatedBy;


    //@Temporal(TemporalType.DATE)
    //@NotNull
    ///@Past
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfTransplant;

    @NotEmpty
    private String kidney;//right kidney, left kidney;
    @NotEmpty
    private String site;//left iliac fossa, right iliac fossa.

    @NotNull
    private Double hlaA;
    @NotNull
    private Double hlaB;
    @NotNull
    private Double hlaC;
    @NotEmpty
    private String induction; // options noen, no;
    @NotEmpty
    private String cni;  // options CSA, TAC;
    @NotEmpty
    private String antMetabolite;// options Azathioprine, MMF
    @NotEmpty
    private String graftFunction; //option immediate, primary non-function, delayed.
    @NotEmpty
    private String rejection; //None, Steroid sensitive;
    private String comment;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getDateOfTransplant() {
        return dateOfTransplant;
    }

    public void setDateOfTransplant(Date dateOfTransplant) {
        this.dateOfTransplant = dateOfTransplant;
    }

    public String getKidney() {
        return kidney;
    }

    public void setKidney(String kidney) {
        this.kidney = kidney;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getHlaA() {
        return hlaA;
    }

    public void setHlaA(Double hlaA) {
        this.hlaA = hlaA;
    }

    public Double getHlaB() {
        return hlaB;
    }

    public void setHlaB(Double hlaB) {
        this.hlaB = hlaB;
    }

    public Double getHlaC() {
        return hlaC;
    }

    public void setHlaC(Double hlaC) {
        this.hlaC = hlaC;
    }

    public String getInduction() {
        return induction;
    }

    public void setInduction(String induction) {
        this.induction = induction;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getAntMetabolite() {
        return antMetabolite;
    }

    public void setAntMetabolite(String antMetabolite) {
        this.antMetabolite = antMetabolite;
    }

    public String getGraftFunction() {
        return graftFunction;
    }

    public void setGraftFunction(String graftFunction) {
        this.graftFunction = graftFunction;
    }

    public String getRejection() {
        return rejection;
    }

    public void setRejection(String rejection) {
        this.rejection = rejection;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Transplant{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", updatedOn=" + updatedOn +
                ", updatedBy='" + updatedBy + '\'' +
                ", dateOfTransplant=" + dateOfTransplant +
                ", kidney='" + kidney + '\'' +
                ", site='" + site + '\'' +
                ", hlaA=" + hlaA +
                ", hlaB=" + hlaB +
                ", hlaC=" + hlaC +
                ", induction='" + induction + '\'' +
                ", cni='" + cni + '\'' +
                ", antMetabolite='" + antMetabolite + '\'' +
                ", graftFunction='" + graftFunction + '\'' +
                ", rejection='" + rejection + '\'' +
                ", comment='" + comment + '\'' +
                ", patient=" + patient +
                '}';
    }
}

