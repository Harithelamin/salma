package com.salma.repository;

import com.salma.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    boolean existsById(Integer id);
    boolean existsByPersonalId(String personalId);
    boolean existsByEmail( String email);
    Patient findByPersonalId(String personalId);
}
