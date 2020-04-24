package com.salma.repository;

import com.salma.model.HdBaseLine;
import com.salma.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HdBaseLineRepository extends JpaRepository<HdBaseLine, String> {
    boolean existsByPatient(Patient patient);
    HdBaseLine findByPatient(Patient patient);
}
