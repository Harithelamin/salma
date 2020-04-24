package com.salma.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="APP_HD_BASELINE")
public class HdBaseLine {

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


    @Temporal(TemporalType.DATE)
    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOffirstHdEver;

    private String hbv; // Negative, Positive
    private String hcv; // Negative, Positive
    private String hiv; // Negative, Positive
    private String vascularAccess; //AVF, AVG, Permcath, Temporal.


    private ArrayList hdDays;

    private Integer numberOfHdSessions;  // // 1,2,3,4,5. // form:radiobuttons - radio button  //number

    @Column
    private String comment;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof HdBaseLine))
            return false;
        HdBaseLine other = (HdBaseLine) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


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

    public Date getDateOffirstHdEver() {
        return dateOffirstHdEver;
    }

    public void setDateOffirstHdEver(Date dateOffirstHdEver) {
        this.dateOffirstHdEver = dateOffirstHdEver;
    }

    public String getHbv() {
        return hbv;
    }

    public void setHbv(String hbv) {
        this.hbv = hbv;
    }

    public String getHcv() {
        return hcv;
    }

    public void setHcv(String hcv) {
        this.hcv = hcv;
    }

    public String getHiv() {
        return hiv;
    }

    public void setHiv(String hiv) {
        this.hiv = hiv;
    }

    public String getVascularAccess() {
        return vascularAccess;
    }

    public void setVascularAccess(String vascularAccess) {
        this.vascularAccess = vascularAccess;
    }


    public ArrayList getHdDays() {
        return hdDays;
    }

    public void setHdDays(ArrayList hdDays) {
        this.hdDays = hdDays;
    }

    public Integer getNumberOfHdSessions() {
        return numberOfHdSessions;
    }

    public void setNumberOfHdSessions(Integer numberOfHdSessions) {
        this.numberOfHdSessions = numberOfHdSessions;
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
        return "HdBaseLine{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", updatedOn=" + updatedOn +
                ", updatedBy='" + updatedBy + '\'' +
                ", dateOffirstHdEver=" + dateOffirstHdEver +
                ", hbv='" + hbv + '\'' +
                ", hcv='" + hcv + '\'' +
                ", hiv='" + hiv + '\'' +
                ", vascularAccess='" + vascularAccess + '\'' +
                ", hdDays='" + hdDays + '\'' +
                ", numberOfHdSessions=" + numberOfHdSessions +
                ", comment='" + comment + '\'' +
                ", patient=" + patient +
                '}';
    }
}


