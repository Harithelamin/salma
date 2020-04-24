package com.salma.repository;

import com.salma.model.HdMonthlyfollowUp;
import com.salma.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HdMonthlyFollowUpRepository extends JpaRepository<HdMonthlyfollowUp, Long> {
    boolean existsByPatientAndMonthOfFollowUp(Patient patient, String monthOfFollowUp);
    List<HdMonthlyfollowUp> findByPatientOrderByMonthOfFollowUp(Patient patient);

}
