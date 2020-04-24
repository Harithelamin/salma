package com.salma.controller;


import com.salma.config.MessageResponse;
import com.salma.config.ResourceNotFoundException;
import com.salma.model.*;
import com.salma.repository.*;
import com.salma.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    PatientRepository patients;

    @Autowired
    HdMonthlyFollowUpRepository hdMonthlyFollowUpRepository;

    @Autowired
    PatientDocumentRepository patientDocumentRepository;

    @Autowired
    HdBaseLineRepository hdBaseLineRepository;

    @Autowired
    DonorRepository donorRepository;

    @Autowired
    TransplantRepository transplantRepository;

    @Autowired
    TrFollowUpRepository trFollowUpRepository;

    @Autowired
    RoleRepository roles;

    @Autowired
    PasswordEncoder passwordEncoder;

    //To get user principle
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    //Patients
    @GetMapping("/getPatientsList")
    public List< Patient > getPatientsList() {
        return patients.findAll();
    }

    @GetMapping("/getPatientByPersonalId/{personalId}")
    public ResponseEntity<Optional<Patient>> getPatientByPersonalId(
            @PathVariable(value = "personalId") String personalId) throws ResourceNotFoundException {

        Optional<Patient> patient = Optional.ofNullable(patients.findByPersonalId(personalId));
        patient.orElseThrow(() -> new ResourceNotFoundException("Patient not found :: " + personalId));
        return ResponseEntity.ok().body(patient);
    }




    @PostMapping("/registerPatient")
    @PutMapping
    @Transactional
    public ResponseEntity  registerPatient(@Valid @RequestBody Patient data) throws ResourceNotFoundException {
        //
        //check if patient is new, or old
        if (data.getId()!=null){
            Patient currentPatient = patients.findByPersonalId(data.getPersonalId());
            currentPatient.setUpdatedOn(new Date());
            currentPatient.setStatus("New");
            currentPatient.setPersonalId(data.getPersonalId());
            currentPatient.setName(data.getName());
            currentPatient.setEmail(data.getEmail());
            currentPatient.setDateOfBirth(data.getDateOfBirth());
            currentPatient.setPassword(data.getPassword());
            currentPatient.setConfirmPassword(data.getConfirmPassword());
            currentPatient.setGender(data.getGender());
            currentPatient.setMobileNumber(data.getMobileNumber());
            currentPatient.setDateOfJoinCenter(new Date());
            currentPatient.setResidence(data.getResidence());
            currentPatient.setAddress(data.getAddress());
            currentPatient.setInsurance(data.isInsurance());
            currentPatient.setInsuranceDetails(data.getInsuranceDetails());
            currentPatient.setUseInStatic(data.isUseInStatic());
            currentPatient.setComment(data.getComment());






            patients.saveAndFlush(currentPatient);
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Patient is updated successfully!"));
        }
        if (patients.existsByPersonalId(data.getPersonalId())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Personal Id is already registered!"));
        }

        if (patients.existsByEmail(data.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email is already in registered!"));
        }
        if (data.getPassword()!= data.getPassword()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Passwords do not match!"));
        }
        if (data.getGender()== "") {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Select patient's gender please!"));
        }
        // Create new patient's account
        Patient patient =new Patient();
        patient.setCreatedBy(data.getCreatedBy());
        patient.setCreatedOn(new Date());
        patient.setStatus("New");
        patient.setPersonalId(data.getPersonalId());
        patient.setName(data.getName());
        patient.setEmail(data.getEmail());
        patient.setDateOfBirth(data.getDateOfBirth());
        patient.setPassword(data.getPassword());
        patient.setConfirmPassword(data.getConfirmPassword());
        patient.setGender(data.getGender());
        patient.setMobileNumber(data.getMobileNumber());
        patient.setDateOfJoinCenter(new Date());
        patient.setResidence(data.getResidence());
        patient.setAddress(data.getAddress());
        patient.setInsurance(data.isInsurance());
        patient.setInsuranceDetails(data.getInsuranceDetails());
        patient.setUseInStatic(data.isUseInStatic());
        patient.setComment(data.getComment());
        patients.save(patient);
        return ok(new MessageResponse("Patient registered successfully!"));
    }


    @PutMapping("/patient/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable(value = "id") long patientId,
                                                 @Valid @RequestBody Patient patientDetails) throws ResourceNotFoundException {
        Patient patient = patients.findById((int) patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));

        patient.setUpdatedOn(new Date());
        final Patient updatePatient = patients.save(patientDetails);
        return ok(updatePatient);
    }

    //Hd Baseline

    @PostMapping("/registerHdBaseLine")
    public ResponseEntity  registerHdBaseLine(@Valid @RequestBody HdBaseLine data) {
        //check if Hd BaseLine is new, or old
        if (data.getId()!=null){
            HdBaseLine currentHdBaseLine = hdBaseLineRepository.findByPatient(data.getPatient());
            currentHdBaseLine.setUpdatedBy(data.getUpdatedBy());
            currentHdBaseLine.setUpdatedOn(new Date());
            currentHdBaseLine.setPatient(data.getPatient());
            currentHdBaseLine.setDateOffirstHdEver(data.getDateOffirstHdEver());
            currentHdBaseLine.setHbv(data.getHbv());
            currentHdBaseLine.setHcv(data.getHcv());
            currentHdBaseLine.setHiv(data.getHiv());
            currentHdBaseLine.setVascularAccess(data.getVascularAccess());
            currentHdBaseLine.setNumberOfHdSessions(data.getNumberOfHdSessions());
            currentHdBaseLine.setHdDays(data.getHdDays());
            currentHdBaseLine.setComment(data.getComment());
            hdBaseLineRepository.save(currentHdBaseLine);
            return ResponseEntity.ok(new MessageResponse("Patient's HD BaseLine updated successfully!"));
        }

        if (hdBaseLineRepository.existsByPatient(data.getPatient())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Patient's Baseline is already registered!"));
        }

        HdBaseLine hdBaseLine= new HdBaseLine();

        // Create new Hd BaseLine
        hdBaseLine.setCreatedBy(data.getCreatedBy());
        hdBaseLine.setCreatedOn(new Date());
        hdBaseLine.setPatient(data.getPatient());
        hdBaseLine.setDateOffirstHdEver(data.getDateOffirstHdEver());
        hdBaseLine.setHbv(data.getHbv());
        hdBaseLine.setHcv(data.getHcv());
        hdBaseLine.setHiv(data.getHiv());
        hdBaseLine.setVascularAccess(data.getVascularAccess());
        hdBaseLine.setNumberOfHdSessions(data.getNumberOfHdSessions());
        hdBaseLine.setHdDays(data.getHdDays());
        hdBaseLine.setComment(data.getComment());


        hdBaseLineRepository.save(hdBaseLine);
        return ok(new MessageResponse("Patient's HD BaseLine registered successfully!"));
    }

    @GetMapping("/getHdBaseLinesList")
    public List< HdBaseLine > getHdBaseLinesList() {
        return hdBaseLineRepository.findAll();
    }

    @GetMapping("/getHdBaseLineByPersonalId/{personalId}")
    public ResponseEntity < HdBaseLine > getHdBaseLineByPersonalId(
            @PathVariable(value = "personalId") String personalId) throws ResourceNotFoundException {
        Patient patient = patients.findByPersonalId(personalId);
        HdBaseLine hdBaseLine = hdBaseLineRepository.findByPatient(patient);
        return ResponseEntity.ok().body(hdBaseLine);
    }

    //Hd Monthly Followup

    @PostMapping("/registerHdMonthlyfollowUp")
    public ResponseEntity  registerHdMonthlyfollowUp(@Valid @RequestBody HdMonthlyfollowUp data) {
        if (hdMonthlyFollowUpRepository.existsByPatientAndMonthOfFollowUp(data.getPatient(),data.getMonthOfFollowUp())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Patient's Monthly Follow up for this month    is already registered!"));
        }
        // Create new Hd Monthly Followup
        HdMonthlyfollowUp hdMonthlyfollowUp =new HdMonthlyfollowUp();
        hdMonthlyfollowUp.setCreatedBy(data.getCreatedBy());
        hdMonthlyfollowUp.setCreatedOn(new Date());
        hdMonthlyfollowUp.setPatient(data.getPatient());
        hdMonthlyfollowUp.getPatient().setStatus("Active");
        hdMonthlyfollowUp.setMonthOfFollowUp(data.getMonthOfFollowUp());
        hdMonthlyfollowUp.setsBP(data.getsBP());
        hdMonthlyfollowUp.setdBP(data.getdBP());
        hdMonthlyfollowUp.setuRR(data.getuRR());
        hdMonthlyfollowUp.setHemoglobin(data.getHemoglobin());
        hdMonthlyfollowUp.setFerritin(data.getFerritin());
        hdMonthlyfollowUp.settSAT(data.gettSAT());
        hdMonthlyfollowUp.setCalcium(data.getCalcium());
        hdMonthlyfollowUp.setpO4(data.getpO4());
        hdMonthlyfollowUp.setpTH(data.getpTH());
        hdMonthlyfollowUp.setAlb(data.getAlb());
        hdMonthlyfollowUp.setaLT(data.getaLT());
        hdMonthlyfollowUp.setAccessInfection(data.getAccessInfection());
        hdMonthlyfollowUp.setHospitalization(data.getHospitalization());
        hdMonthlyfollowUp.setErythropoietinDose(data.getErythropoietinDose());
        hdMonthlyfollowUp.setParenteralIronDose(data.getParenteralIronDose());
        hdMonthlyfollowUp.setComment(data.getComment());
        hdMonthlyFollowUpRepository.save(hdMonthlyfollowUp);
        return ok(new MessageResponse("Patient's Monthly Follow Up registered successfully!"));
    }

    @GetMapping("/getHdMonthlyFollowUpsList")
    public List< HdMonthlyfollowUp > getHdMonthlyFollowUpsList() {
        return hdMonthlyFollowUpRepository.findAll();
    }

    @GetMapping("/getHdMonthlyfollowUpByPersonalId/{personalId}")
    public List<HdMonthlyfollowUp> getHdMonthlyfollowUpByPersonalId(@PathVariable String personalId)
    {
        Patient patient=patients.findByPersonalId(personalId);
        return (hdMonthlyFollowUpRepository.findByPatientOrderByMonthOfFollowUp(patient));
    }

    //Donor
    @GetMapping("/getDonorsList")
    public List<Donor> getDonorsList() {
        return donorRepository.findAll();
    }

    @GetMapping("/getDonorByPersonalId/{personalId}")
    public ResponseEntity<Donor> getDonorByPersonalId(
            @PathVariable(value = "personalId") String personalId) throws ResourceNotFoundException {
        Patient patient = patients.findByPersonalId(personalId);
        Donor donor = donorRepository.findByPatient(patient);
        return ResponseEntity.ok().body(donor);
    }


    @PostMapping("/registerDonor")
    public ResponseEntity  registerDonor(@Valid @RequestBody Donor data) {
        //check if patient is new, or old
        if (data.getId()!=null){
            Donor currentDonor = donorRepository.findByPatient(data.getPatient());
            currentDonor.setUpdatedBy(data.getUpdatedBy());
            currentDonor.setUpdatedOn(new Date());
            currentDonor.setPatient(data.getPatient());
            currentDonor.setPersonalId(data.getPersonalId());
            currentDonor.setDateOfBirth(data.getDateOfBirth());
            currentDonor.setName(data.getName());
            currentDonor.setGender(data.getGender());
            currentDonor.setDonorRelation(data.getDonorRelation());
            currentDonor.setMobileNumber(data.getMobileNumber());
            currentDonor.setEmail(data.getEmail());
            currentDonor.setResidence(data.getResidence());
            currentDonor.setAddress(data.getAddress());
            currentDonor.setInsurance(data.isInsurance());
            currentDonor.setInsuranceDetails(data.getInsuranceDetails());
            currentDonor.setUseInStatic(data.isUseInStatic());
            currentDonor.setDateOfLeaving(data.getDateOfLeaving());
            currentDonor.setComment(data.getComment());
            donorRepository.save(currentDonor);
            return ResponseEntity.ok(new MessageResponse("Donor updated successfully!"));
        }



        if (donorRepository.existsByPersonalId(data.getPersonalId())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Personal Id is already registered!"));
        }

        if (donorRepository.existsByEmail(data.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email is already in registered!"));
        }

        // Create new donor's account
        Donor donor= new Donor();
        donor.setCreatedBy(data.getCreatedBy());
        donor.setCreatedOn(new Date());
        donor.setPatient(data.getPatient());
        donor.setPersonalId(data.getPersonalId());
        donor.setDateOfBirth(data.getDateOfBirth());
        donor.setName(data.getName());
        donor.setGender(data.getGender());
        donor.setDonorRelation(data.getDonorRelation());
        donor.setMobileNumber(data.getMobileNumber());
        donor.setEmail(data.getEmail());
        donor.setDateOfJoinCenter(new Date());
        donor.setResidence(data.getResidence());
        donor.setAddress(data.getAddress());
        donor.setInsurance(data.isInsurance());
        donor.setInsuranceDetails(data.getInsuranceDetails());
        donor.setUseInStatic(data.isUseInStatic());
        donor.setDateOfLeaving(data.getDateOfLeaving());
        donor.setComment(data.getComment());
        donorRepository.save(donor);
        return ok(new MessageResponse("Donor registered successfully!"));
    }


    @PutMapping("/donor/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable(value = "id") long donorId,
                                                 @Valid @RequestBody Donor donorDetails) throws ResourceNotFoundException {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found for this id :: " + donorId));

        donor.setUpdatedOn(new Date());
        final Donor updateDonor = donorRepository.save(donorDetails);
        return ok(donorDetails);
    }


    //Transplant

    @PostMapping("/registerTransplant")
    public ResponseEntity  registerTransplant(@Valid @RequestBody Transplant data) {
        //check if patient is new, or old
        if (data.getId()!=null){
            Transplant currentTransplant = transplantRepository.findByPatient(data.getPatient());
            currentTransplant.setUpdatedBy(data.getUpdatedBy());
            currentTransplant.setUpdatedOn(new Date());
            currentTransplant.setPatient(data.getPatient());
            currentTransplant.getPatient().setStatus("Transplanted");
            currentTransplant.setKidney(data.getKidney());
            currentTransplant.setSite(data.getSite());
            currentTransplant.setHlaA(data.getHlaA());
            currentTransplant.setHlaB(data.getHlaB());
            currentTransplant.setHlaC(data.getHlaC());
            currentTransplant.setInduction(data.getInduction());
            currentTransplant.setCni(data.getCni());
            currentTransplant.setAntMetabolite(data.getAntMetabolite());
            currentTransplant.setGraftFunction(data.getGraftFunction());
            currentTransplant.setRejection(data.getRejection());
            currentTransplant.setComment(data.getComment());
            transplantRepository.save(currentTransplant);
            return ResponseEntity.ok(new MessageResponse("Patient's Transplant updated successfully!"));
        }


        if (transplantRepository.existsByPatient(data.getPatient())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Patient's Transplant is already registered!"));
        }

        // Create new Transplant
        Transplant transplant = new Transplant();
        transplant.setCreatedBy(data.getCreatedBy());
        transplant.setCreatedOn(new Date());
        transplant.setPatient(data.getPatient());
        transplant.getPatient().setStatus("Transplanted");
        transplant.setDateOfTransplant(new Date());
        transplant.setKidney(data.getKidney());
        transplant.setSite(data.getSite());
        transplant.setHlaA(data.getHlaA());
        transplant.setHlaB(data.getHlaB());
        transplant.setHlaC(data.getHlaC());
        transplant.setInduction(data.getInduction());
        transplant.setCni(data.getCni());
        transplant.setAntMetabolite(data.getAntMetabolite());
        transplant.setGraftFunction(data.getGraftFunction());
        transplant.setRejection(data.getRejection());
        transplant.setComment(data.getComment());
        transplantRepository.save(transplant);
        return ok(new MessageResponse("Patient's Transplant registered successfully!"));
    }

    @GetMapping("/getTransplantList")
    public List< Transplant > getTransplantList() {
        return transplantRepository.findAll();
    }

    @GetMapping("/getTransplantByPersonalId/{personalId}")
    public ResponseEntity < Transplant > getTransplantByPersonalId(
            @PathVariable(value = "personalId") String personalId) throws ResourceNotFoundException {
        Patient patient = patients.findByPersonalId(personalId);
        Transplant transplant = transplantRepository.findByPatient(patient);
        return ResponseEntity.ok().body(transplant);
    }

    //TrFollowUp

    @PostMapping("/registerTrFollowUp")
    public ResponseEntity  registerTrFollowUp(@Valid @RequestBody TrFollowUp data) {

        // Create new TrFollowUp
        TrFollowUp trFollowUp= new TrFollowUp();
        trFollowUp.setCreatedBy(data.getCreatedBy());
        trFollowUp.setCreatedOn(new Date());
        trFollowUp.setPatient(data.getPatient());
        trFollowUp.setDateOfFollowUp(new Date());
        trFollowUp.setCurrentStates(data.getCurrentStates());
        trFollowUp.setPriorTrasplant(data.getPriorTrasplant());
        trFollowUp.setDeathDate(data.getDeathDate());
        trFollowUp.setGraftLossDate(data.getGraftLossDate());
        trFollowUp.setGraftLossCause(data.getGraftLossCause());
        trFollowUp.setGraftLossDate(data.getGraftLossDate());
        trFollowUp.setSurvival(data.getSurvival());
        trFollowUp.setDeathCouse(data.getDeathCouse());
        trFollowUp.setCkdCaouse(data.getCkdCaouse());
        trFollowUp.setDialysisDuration(data.getDialysisDuration());
        trFollowUp.setBiopsy(data.getBiopsy());
        trFollowUp.setBiopsyMonth(data.getBiopsyMonth());
        trFollowUp.setBiopsyReport(data.getBiopsyReport());
        trFollowUp.setHisBlood(data.getHisBlood());
        trFollowUp.setHisPregnancy(data.getHisPregnancy());
        trFollowUp.setHisDm(data.getHisDm());
        trFollowUp.setHisIhd(data.getHisIhd());
        trFollowUp.setHishbv(data.getHishbv());
        trFollowUp.setHishcv(data.getHishcv());
        trFollowUp.setSickle(data.getSickle());
        trFollowUp.setOther(data.getOther());
        trFollowUp.setHistb(data.getHistb());
        trFollowUp.setMantoux(data.getMantoux());
        trFollowUp.setSurgeon(data.getSurgeon());
        trFollowUp.setDjs(data.getDjs());
        trFollowUp.setHospitalStay(data.getHospitalStay());
        trFollowUp.setCreatinine(data.getCreatinine());
        trFollowUp.setComment(data.getComment());
        trFollowUpRepository.save(trFollowUp);
        return ok(new MessageResponse("Patient's TrFollowUp registered successfully!"));
    }

    @GetMapping("/getTrFollowUpsList")
    public List< TrFollowUp > getTrFollowUpsList() {
        return trFollowUpRepository.findAll();
    }

    @GetMapping("/getTrFollowUpByPersonalId/{personalId}")
    public List<TrFollowUp> getTrFollowUpByPersonalId(@PathVariable String personalId)
    {
        Patient patient=patients.findByPersonalId(personalId);
        return (trFollowUpRepository.findByPatientOrderByDateOfFollowUp(patient));
    }


}
