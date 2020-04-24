package com.salma.repository;

import com.salma.model.Donor;
import com.salma.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    boolean existsByPersonalId(String personalId);
    boolean existsByEmail( String email);
    boolean existsByPatient(Patient patient);
    Donor findByPatient(Patient patient);
}
