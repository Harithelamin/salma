package com.salma.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="APP_PATIENT")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    @Past(message="Correct The BirthDate please!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String name;

    @Column
    @NotNull(message = "Select The Patient Gender Please")
    private String gender;   // form:radiobutton - radio button

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String mobileNumber;

    @NotEmpty
    @Column(nullable = false)
    private String email;

    @Column
    private String password; // form:input - password       old

    //will not save in db
    @Transient
    private String confirmPassword;  // form:input - password    old

    //auto data today by system
    private Date dateOfJoinCenter;

    @Column
    @NotNull(message = "Select The Patient residence Please")
    private String residence;  //Khartoum, Bahri, Umdurman.

    @NotEmpty
    @Column(nullable = false)
    private String address; //Khartoum, Bahri, Umdurman.// form:textarea - textarea       old // form:select - form:option - dropdown - single select//country

    @Column
    private boolean insurance; // yes, no.  //  newsletter

    @Column
    private String insuranceDetails;

    @Column
    private String status;// New, Active, Transferred, Transplanted, Recovered, Died, No data. // form:select - multiple=true - dropdown - multiple select   //skill

    @Column
    private boolean useInStatic; // yes, no.  //  newsletter

    @Column
    private String comment;

    @Column
    private Date dateOfLeaving;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PatientDocument> patientDocuments = new HashSet<PatientDocument>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<HdBaseLine> hdBaseLines = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<HdMonthlyfollowUp> hdMonthlyfollowUps = new HashSet<HdMonthlyfollowUp>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Donor> donors = new HashSet<Donor>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Transplant> transplants = new HashSet<Transplant>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TrFollowUp> trFollowUps = new HashSet<TrFollowUp>();

    public Patient(){}
    public Patient(Date createdOn, String createdBy, Date updatedOn, String updatedBy, @NotEmpty String personalId, @NotNull @Past(message = "Correct The BirthDate please!") Date dateOfBirth, @NotEmpty String name, @NotNull(message = "Select The Patient Gender Please") String gender, @NotEmpty String mobileNumber, @NotEmpty String email, String password, String confirmPassword, Date dateOfJoinCenter, @NotNull(message = "Select The Patient residence Please") String residence, @NotEmpty String address, boolean insurance, String insuranceDetails, String status, boolean useInStatic, String comment, Date dateOfLeaving, Set<PatientDocument> patientDocuments, Set<HdBaseLine> hdBaseLines, Set<HdMonthlyfollowUp> hdMonthlyfollowUps, Set<Donor> donors, Set<Transplant> transplants, Set<TrFollowUp> trFollowUps) {
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
        this.personalId = personalId;
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.dateOfJoinCenter = dateOfJoinCenter;
        this.residence = residence;
        this.address = address;
        this.insurance = insurance;
        this.insuranceDetails = insuranceDetails;
        this.status = status;
        this.useInStatic = useInStatic;
        this.comment = comment;
        this.dateOfLeaving = dateOfLeaving;
        this.patientDocuments = patientDocuments;
        this.hdBaseLines = hdBaseLines;
        this.hdMonthlyfollowUps = hdMonthlyfollowUps;
        this.donors = donors;
        this.transplants = transplants;
        this.trFollowUps = trFollowUps;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((personalId == null) ? 0 : personalId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Patient))
            return false;
        Patient other = (Patient) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (personalId == null) {
            if (other.personalId != null)
                return false;
        } else if (!personalId.equals(other.personalId))
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

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isUseInStatic() {
        return useInStatic;
    }

    public void setUseInStatic(boolean useInStatic) {
        this.useInStatic = useInStatic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateOfLeaving() {
        return dateOfLeaving;
    }

    public void setDateOfLeaving(Date dateOfLeaving) {
        this.dateOfLeaving = dateOfLeaving;
    }

    public Set<PatientDocument> getPatientDocuments() {
        return patientDocuments;
    }

    public void setPatientDocuments(Set<PatientDocument> patientDocuments) {
        this.patientDocuments = patientDocuments;
    }

    public Set<HdBaseLine> getHdBaseLines() {
        return hdBaseLines;
    }

    public void setHdBaseLines(Set<HdBaseLine> hdBaseLines) {
        this.hdBaseLines = hdBaseLines;
    }

    public Set<HdMonthlyfollowUp> getHdMonthlyfollowUps() {
        return hdMonthlyfollowUps;
    }

    public void setHdMonthlyfollowUps(Set<HdMonthlyfollowUp> hdMonthlyfollowUps) {
        this.hdMonthlyfollowUps = hdMonthlyfollowUps;
    }

    public Set<Donor> getDonors() {
        return donors;
    }

    public void setDonors(Set<Donor> donors) {
        this.donors = donors;
    }

    public Set<Transplant> getTransplants() {
        return transplants;
    }

    public void setTransplants(Set<Transplant> transplants) {
        this.transplants = transplants;
    }

    public Set<TrFollowUp> getTrFollowUps() {
        return trFollowUps;
    }

    public void setTrFollowUps(Set<TrFollowUp> trFollowUps) {
        this.trFollowUps = trFollowUps;
    }
}
