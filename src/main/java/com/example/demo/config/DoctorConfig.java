package com.example.demo.config;

import com.example.demo.entity.Doctor;
import com.example.demo.repository.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DoctorConfig {

    @Bean
    CommandLineRunner doctorCommandLineRunner(DoctorRepository doctorRepository) {
        return args -> {
            Doctor drSmith = new Doctor(
                    "John",
                    "Smith",
                    "Cardiology",
                    List.of("Monday 9:00-11:00", "Wednesday 14:00-16:00")
            );

            Doctor drBrown = new Doctor(
                    "Sarah",
                    "Brown",
                    "Pediatrics",
                    List.of("Tuesday 10:00-12:00", "Thursday 15:00-17:00")
            );

            Doctor drWhite = new Doctor(
                    "Emily",
                    "White",
                    "Orthopedics",
                    List.of("Friday 8:00-10:00", "Saturday 12:00-14:00")
            );

            // Save the sample doctors to the database
            doctorRepository.saveAll(List.of(drSmith, drBrown, drWhite));
        };
    }
}

