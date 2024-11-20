package com.example.demo.patient;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
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
