package com.salma.repository;

import com.salma.model.Patient;
import com.salma.model.TrFollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrFollowUpRepository extends JpaRepository<TrFollowUp, Long> {
    List<TrFollowUp> findByPatientOrderByDateOfFollowUp(Patient patient);
}
