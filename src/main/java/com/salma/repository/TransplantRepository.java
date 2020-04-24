package com.salma.repository;

import com.salma.model.Patient;
import com.salma.model.Transplant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransplantRepository extends JpaRepository<Transplant, String> {
    boolean existsByPatient(Patient patient);
    Transplant findByPatient(Patient patient);
}
