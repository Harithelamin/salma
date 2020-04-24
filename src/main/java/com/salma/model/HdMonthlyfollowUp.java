package com.salma.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="APP_HDMONTHLYFOLLOWUP")
public class HdMonthlyfollowUp {

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

    //@DateTimeFormat(pattern="dd/mm/yyyy")
    //@Temporal(TemporalType.DATE)
    //@NotNull
    //@Past
    private String monthOfFollowUp;
    @NotNull
    private Double sBP;  // (mmHg)
    @NotNull
    private Double dBP; //(mmHg)
    @NotNull
    private int   uRR; //(%)
    @NotNull
    private Double hemoglobin; // (g/dL)
    @NotNull
    private Double ferritin; // (ng/mL)
    @NotNull
    private int   tSAT; //(%)
    @NotNull
    private Double calcium; // (mg/dL)
    @NotNull
    private Double pO4; //  (mg/dL)
    @NotNull
    private Double pTH; //(pg/mL)
    @NotNull
    private Double alb; //(g/dL)
    @NotNull
    private Double aLT;// (u/L)
    @NotNull
    private String accessInfection;// (Yes/No)
    private ArrayList hospitalization;// (days)
    @NotNull
    private Double erythropoietinDose; // (unit/wk)
    @NotNull
    private Double parenteralIronDose; //(mg/wk)

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

    public String getMonthOfFollowUp() {
        return monthOfFollowUp;
    }

    public void setMonthOfFollowUp(String monthOfFollowUp) {
        this.monthOfFollowUp = monthOfFollowUp;
    }

    public Double getsBP() {
        return sBP;
    }

    public void setsBP(Double sBP) {
        this.sBP = sBP;
    }

    public Double getdBP() {
        return dBP;
    }

    public void setdBP(Double dBP) {
        this.dBP = dBP;
    }

    public int getuRR() {
        return uRR;
    }

    public void setuRR(int uRR) {
        this.uRR = uRR;
    }

    public Double getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(Double hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public Double getFerritin() {
        return ferritin;
    }

    public void setFerritin(Double ferritin) {
        this.ferritin = ferritin;
    }

    public int gettSAT() {
        return tSAT;
    }

    public void settSAT(int tSAT) {
        this.tSAT = tSAT;
    }

    public Double getCalcium() {
        return calcium;
    }

    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    public Double getpO4() {
        return pO4;
    }

    public void setpO4(Double pO4) {
        this.pO4 = pO4;
    }

    public Double getpTH() {
        return pTH;
    }

    public void setpTH(Double pTH) {
        this.pTH = pTH;
    }

    public Double getAlb() {
        return alb;
    }

    public void setAlb(Double alb) {
        this.alb = alb;
    }

    public Double getaLT() {
        return aLT;
    }

    public void setaLT(Double aLT) {
        this.aLT = aLT;
    }

    public String getAccessInfection() {
        return accessInfection;
    }

    public void setAccessInfection(String accessInfection) {
        this.accessInfection = accessInfection;
    }

    public ArrayList getHospitalization() {
        return hospitalization;
    }

    public void setHospitalization(ArrayList hospitalization) {
        this.hospitalization = hospitalization;
    }

    public Double getErythropoietinDose() {
        return erythropoietinDose;
    }

    public void setErythropoietinDose(Double erythropoietinDose) {
        this.erythropoietinDose = erythropoietinDose;
    }

    public Double getParenteralIronDose() {
        return parenteralIronDose;
    }

    public void setParenteralIronDose(Double parenteralIronDose) {
        this.parenteralIronDose = parenteralIronDose;
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
}

