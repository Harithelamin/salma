package com.salma.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="APP_TrFollowUp")
public class TrFollowUp implements Serializable {
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
    // @Past
    private Date dateOfFollowUp;
    private String currentStates;  // Alive and well, Death, Graft failure
    private String priorTrasplant; //no, yes



    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deathDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date graftLossDate;
    private String graftLossCause; //optihon   Chronic allograft dysfunction, Primary non-function, Death with functioning graft, FSGS


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lossDate;
    private int survival; //0 to 200;
    private String deathCouse;//Cardiovascular, Infection, Cardiovascular, Gastrointestinal, Malignancy, Liver disease, Infection related to antirejection treatment, Others, No data.
    private String ckdCaouse; //ADPKD, KAKUT, Famialial disorder, Uncertain, Obstructive uropathy,Glomerulonephritis, HTN, DM, SLE/vasculitis ,other

    private int dialysisDuration; // 1 to 200;
    private String biopsy; //yes, no.
    private int biopsyMonth; //1 to 100



    private String biopsyReport;//ACR/AMR, Coagulative necrosis, FSGS, IFTA, Inadequate, Interstitial nephritis, No data,

    @NotEmpty
    private String hisBlood; //yes, no
    private String hisPregnancy; //yes, no;
    private String hisDm; //yes no
    private String hisIhd;// yes, no
    private String hishbv;// yes, no
    private String hishcv;// yes, no
    private String sickle; //yes, no





    private String other;// p, negagive
    private String histb;// yes, no
    private String mantoux; //posotive, negative.
    @NotEmpty
    private ArrayList surgeon; //doctor name.



    private String djs;// removed by cystoscopy, attached to catheter.
    private int hospitalStay;// 1 to 200.
    private Double creatinine; //0.0  to 5.o;



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

    public Date getDateOfFollowUp() {
        return dateOfFollowUp;
    }

    public void setDateOfFollowUp(Date dateOfFollowUp) {
        this.dateOfFollowUp = dateOfFollowUp;
    }

    public String getCurrentStates() {
        return currentStates;
    }

    public void setCurrentStates(String currentStates) {
        this.currentStates = currentStates;
    }

    public String getPriorTrasplant() {
        return priorTrasplant;
    }

    public void setPriorTrasplant(String priorTrasplant) {
        this.priorTrasplant = priorTrasplant;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public Date getGraftLossDate() {
        return graftLossDate;
    }

    public void setGraftLossDate(Date graftLossDate) {
        this.graftLossDate = graftLossDate;
    }

    public String getGraftLossCause() {
        return graftLossCause;
    }

    public void setGraftLossCause(String graftLossCause) {
        this.graftLossCause = graftLossCause;
    }

    public Date getLossDate() {
        return lossDate;
    }

    public void setLossDate(Date lossDate) {
        this.lossDate = lossDate;
    }

    public int getSurvival() {
        return survival;
    }

    public void setSurvival(int survival) {
        this.survival = survival;
    }

    public String getDeathCouse() {
        return deathCouse;
    }

    public void setDeathCouse(String deathCouse) {
        this.deathCouse = deathCouse;
    }

    public String getCkdCaouse() {
        return ckdCaouse;
    }

    public void setCkdCaouse(String ckdCaouse) {
        this.ckdCaouse = ckdCaouse;
    }

    public int getDialysisDuration() {
        return dialysisDuration;
    }

    public void setDialysisDuration(int dialysisDuration) {
        this.dialysisDuration = dialysisDuration;
    }

    public String getBiopsy() {
        return biopsy;
    }

    public void setBiopsy(String biopsy) {
        this.biopsy = biopsy;
    }

    public int getBiopsyMonth() {
        return biopsyMonth;
    }

    public void setBiopsyMonth(int biopsyMonth) {
        this.biopsyMonth = biopsyMonth;
    }

    public String getBiopsyReport() {
        return biopsyReport;
    }

    public void setBiopsyReport(String biopsyReport) {
        this.biopsyReport = biopsyReport;
    }

    public String getHisBlood() {
        return hisBlood;
    }

    public void setHisBlood(String hisBlood) {
        this.hisBlood = hisBlood;
    }

    public String getHisPregnancy() {
        return hisPregnancy;
    }

    public void setHisPregnancy(String hisPregnancy) {
        this.hisPregnancy = hisPregnancy;
    }

    public String getHisDm() {
        return hisDm;
    }

    public void setHisDm(String hsiDm) {
        this.hisDm = hsiDm;
    }

    public String getHisIhd() {
        return hisIhd;
    }

    public void setHisIhd(String hisIhd) {
        this.hisIhd = hisIhd;
    }

    public String getHishbv() {
        return hishbv;
    }

    public void setHishbv(String hishbv) {
        this.hishbv = hishbv;
    }

    public String getHishcv() {
        return hishcv;
    }

    public void setHishcv(String hishcv) {
        this.hishcv = hishcv;
    }

    public String getSickle() {
        return sickle;
    }

    public void setSickle(String sickle) {
        this.sickle = sickle;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getHistb() {
        return histb;
    }

    public void setHistb(String histb) {
        this.histb = histb;
    }

    public String getMantoux() {
        return mantoux;
    }

    public void setMantoux(String mantoux) {
        this.mantoux = mantoux;
    }


    public ArrayList getSurgeon() {
        return surgeon;
    }

    public void setSurgeon(ArrayList surgeon) {
        this.surgeon = surgeon;
    }

    public String getDjs() {
        return djs;
    }

    public void setDjs(String djs) {
        this.djs = djs;
    }

    public int getHospitalStay() {
        return hospitalStay;
    }

    public void setHospitalStay(int hospitalStay) {
        this.hospitalStay = hospitalStay;
    }

    public Double getCreatinine() {
        return creatinine;
    }

    public void setCreatinine(Double creatinine) {
        this.creatinine = creatinine;
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
        return "TrFollowUp{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", updatedOn=" + updatedOn +
                ", updatedBy='" + updatedBy + '\'' +
                ", dateOfFollowUp=" + dateOfFollowUp +
                ", currentStates='" + currentStates + '\'' +
                ", priorTrasplant='" + priorTrasplant + '\'' +
                ", deathDate=" + deathDate +
                ", graftLossDate=" + graftLossDate +
                ", graftLossCause='" + graftLossCause + '\'' +
                ", lossDate=" + lossDate +
                ", survival=" + survival +
                ", deathCouse='" + deathCouse + '\'' +
                ", ckdCaouse='" + ckdCaouse + '\'' +
                ", dialysisDuration=" + dialysisDuration +
                ", biopsy='" + biopsy + '\'' +
                ", biopsyMonth=" + biopsyMonth +
                ", biopsyReport='" + biopsyReport + '\'' +
                ", hisBlood='" + hisBlood + '\'' +
                ", hisPregnancy='" + hisPregnancy + '\'' +
                ", hisDm='" + hisDm + '\'' +
                ", hisIhd='" + hisIhd + '\'' +
                ", hishbv='" + hishbv + '\'' +
                ", hishcv='" + hishcv + '\'' +
                ", sickle='" + sickle + '\'' +
                ", other='" + other + '\'' +
                ", histb='" + histb + '\'' +
                ", mantoux='" + mantoux + '\'' +
                ", surgeon='" + surgeon + '\'' +
                ", djs='" + djs + '\'' +
                ", hospitalStay=" + hospitalStay +
                ", creatinine=" + creatinine +
                ", comment='" + comment + '\'' +
                ", patient=" + patient +
                '}';
    }
}


