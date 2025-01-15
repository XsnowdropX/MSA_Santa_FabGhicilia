package com.example.demo.config;

import com.example.demo.entity.Doctor;
import com.example.demo.entity.Medicine;
import com.example.demo.entity.Patient;
import com.example.demo.entity.Prescription;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.PrescriptionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class PrescriptionConfig {

    @Bean
    CommandLineRunner prescriptionCommandLineRunner(
            PrescriptionRepository prescriptionRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            MedicineRepository medicineRepository) {
        return args -> {
            // Fetch existing patients, doctors, and medicines
            List<Patient> patients = patientRepository.findAll();
            List<Doctor> doctors = doctorRepository.findAll();
            List<Medicine> medicines = medicineRepository.findAll();

            if (!patients.isEmpty() && !doctors.isEmpty() && !medicines.isEmpty()) {
                Patient jane = patients.get(0); // First patient
                Patient joe = patients.get(1);  // Second patient

                Doctor drSmith = doctors.get(0); // First doctor
                Doctor drBrown = doctors.get(1); // Second doctor

                // Assign a subset of medicines to the prescriptions
                List<Medicine> prescribedMedicinesForJane = medicines.subList(0, 2); // First two medicines
                List<Medicine> prescribedMedicinesForJoe = medicines.subList(1, 3); // Second and third medicines

                // Create sample prescriptions
                Prescription prescription1 = new Prescription(
                        LocalDate.of(2025, 1, 15),
                        jane,
                        drSmith,
                        prescribedMedicinesForJane
                );

                Prescription prescription2 = new Prescription(
                        LocalDate.of(2025, 1, 16),
                        joe,
                        drBrown,
                        prescribedMedicinesForJoe
                );

                // Save the prescriptions
                prescriptionRepository.saveAll(List.of(prescription1, prescription2));
            }
        };
    }
}