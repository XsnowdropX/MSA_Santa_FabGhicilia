package com.example.demo.config;

import com.example.demo.entity.Doctor;
import com.example.demo.entity.Medicine;
import com.example.demo.entity.Patient;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    CommandLineRunner initDatabase(
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            MedicineRepository medicineRepository
    ) {
        return args -> {
            // Pre-populate Patients
            Patient patient1 = new Patient(
                    "John",
                    "Doe",
                    LocalDate.of(1990, 1, 1),
                    "john.doe@example.com",
                    123L,
                    "password123"
            );

            Patient patient2 = new Patient(
                    "Jane",
                    "Smith",
                    LocalDate.of(2000, 5, 15),
                    "jane.smith@example.com",
                    456L,
                    "password456"
            );

            patientRepository.saveAll(List.of(patient1, patient2));

            // Pre-populate Doctors
            Doctor doctor1 = new Doctor(
                    "Alice",
                    "Brown",
                    "Cardiology",
                    List.of("Monday 9:00-11:00", "Wednesday 14:00-16:00")
            );

            Doctor doctor2 = new Doctor(
                    "Bob",
                    "Taylor",
                    "Neurology",
                    List.of("Tuesday 10:00-12:00", "Thursday 15:00-17:00")
            );

            doctorRepository.saveAll(List.of(doctor1, doctor2));

            // Pre-populate Medicines
            Medicine medicine1 = new Medicine(
                    "Paracetamol",
                    "500mg",
                    3
            );

            Medicine medicine2 = new Medicine(
                    "Ibuprofen",
                    "200mg",
                    2
            );

            Medicine medicine3 = new Medicine(
                    "Amoxicillin",
                    "250mg",
                    3
            );

            medicineRepository.saveAll(List.of(medicine1, medicine2, medicine3));
        };
    }
}