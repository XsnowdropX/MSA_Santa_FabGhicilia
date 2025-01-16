package com.example.demo.service;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients()
    {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalStateException("Patient with ID " + patientId + " does not exist."));
    }

    public Patient login(String email, String password) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email must not be null or empty.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password must not be null or empty.");
        }

        System.out.println("Attempting login with email: " + email + ", password: " + password);

        return patientRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> {
                    System.out.println("No patient found with email: " + email + " and password: " + password);
                    return new IllegalStateException("Invalid email or password.");
                });
    }

    public void registerNewPatient(Patient patient) {
        Optional<Patient> existingPatient = patientRepository.findPatientByEmail(patient.getEmail());
        if (existingPatient.isPresent()) {
            throw new IllegalStateException("Email is already taken.");
        }

        patientRepository.save(patient);
    }

    public void addNewPatient(Patient patient) {
        Optional<Patient> patientOptional = patientRepository.findPatientByEmail(patient.getEmail());
        if(patientOptional.isPresent())
        {
            throw new IllegalStateException("email already taken");
        }
        patientRepository.save(patient);
    }

    public void deletePatient(Long patientId) {
        boolean exists = patientRepository.existsById(patientId);
        if(!exists)
        {
            throw new IllegalStateException("patient with id " + patientId + " does not exist");
        }
        patientRepository.deleteById(patientId);
    }

    @Transactional
    public void updatePacient(Long patientId, String first_name, String last_name, String email) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalStateException("patient with id " + patientId + " does not exist"));
        if(first_name != null &&
            first_name.length()>0 &&
            !Objects.equals(patient.getFirst_name(), first_name)) {
            patient.setFirst_name(first_name);
        }

        if(last_name != null &&
                last_name.length()>0 &&
                !Objects.equals(patient.getLast_name(), last_name)) {
            patient.setFirst_name(last_name);
        }

        if(email != null &&
                email.length()>0 &&
                !Objects.equals(patient.getEmail(), email)) {
            Optional<Patient> patientOptional = patientRepository.findPatientByEmail(email);
            if(patientOptional.isPresent())
            {
                throw new IllegalStateException("email already taken");
            }
            patient.setEmail(email);
        }
    }
}
