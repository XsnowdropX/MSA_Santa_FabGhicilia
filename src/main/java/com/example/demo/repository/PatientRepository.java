package com.example.demo.repository;

import com.example.demo.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository
        extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.email = ?1")
    Optional<Patient> findPatientByEmail(String email);

    @Query("SELECT p FROM Patient p WHERE p.email = :email AND p.password = :password")
    Optional<Patient> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
