package com.salma.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Table(name="APP_DONOR")
public class Donor {

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

    @NotEmpty
    @Column(name = "PERSONAL_ID", unique = true, nullable = false)
    private String personalId;


    @Temporal(TemporalType.DATE)
    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;


    @NotEmpty
    @Column(unique=true, nullable=false)
    private String name;

    @NotEmpty
    private String gender;   // form:radiobutton - radio button

    @NotEmpty
    private String donorRelation;


    @NotEmpty
    @Column(unique=true, nullable=false)
    private String mobileNumber;

    @NotEmpty
    @Column(nullable=false)
    private String email;

    //@Temporal(TemporalType.DATE)
    //@NotNull
    //@Past
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfJoinCenter;

    @NotEmpty
    private String residence;  //Khartoum, Bahri, Umdurman.

    @NotEmpty
    @Column(nullable=false)
    private String address; //Khartoum, Bahri, Umdurman.// form:textarea - textarea       old // form:select - form:option - dropdown - single select//country

    @Column
    private boolean insurance; // yes, no.  //  newsletter

    @Column
    private String insuranceDetails;

    @Column
    private boolean useInStatic; // yes, no.  //  newsletter

    @Column
    private Date dateOfLeaving;

    @Column
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

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personId) {
        this.personalId = personId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDonorRelation() {
        return donorRelation;
    }

    public void setDonorRelation(String donorRelation) {
        this.donorRelation = donorRelation;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfJoinCenter() {
        return dateOfJoinCenter;
    }

    public void setDateOfJoinCenter(Date dateOfJoinCenter) {
        this.dateOfJoinCenter = dateOfJoinCenter;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public String getInsuranceDetails() {
        return insuranceDetails;
    }

    public void setInsuranceDetails(String insuranceDetails) {
        this.insuranceDetails = insuranceDetails;
    }

    public boolean isUseInStatic() {
        return useInStatic;
    }

    public void setUseInStatic(boolean useInStatic) {
        this.useInStatic = useInStatic;
    }

    public Date getDateOfLeaving() {
        return dateOfLeaving;
    }

    public void setDateOfLeaving(Date dateOfLeaving) {
        this.dateOfLeaving = dateOfLeaving;
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
        return "Donor{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", updatedOn=" + updatedOn +
                ", updatedBy='" + updatedBy + '\'' +
                ", personId='" + personalId + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", donorRelation='" + donorRelation + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", dateOfJoinCenter=" + dateOfJoinCenter +
                ", residence='" + residence + '\'' +
                ", address='" + address + '\'' +
                ", insurance=" + insurance +
                ", insuranceDetails='" + insuranceDetails + '\'' +
                ", useInStatic=" + useInStatic +
                ", dateOfLeaving=" + dateOfLeaving +
                ", comment='" + comment + '\'' +
                ", patient=" + patient +
                '}';
    }
}

