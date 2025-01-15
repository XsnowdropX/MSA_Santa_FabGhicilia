package com.example.demo.config;

import com.example.demo.entity.Medicine;
import com.example.demo.repository.MedicineRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicineConfig {

    @Bean
    CommandLineRunner medicineCommandLineRunner(MedicineRepository medicineRepository) {
        return args -> {
            Medicine paracetamol = new Medicine("Paracetamol", "500mg", 3);
            Medicine ibuprofen = new Medicine("Ibuprofen", "200mg", 2);
            Medicine amoxicillin = new Medicine("Amoxicillin", "250mg", 3);

            medicineRepository.saveAll(List.of(paracetamol, ibuprofen, amoxicillin));
        };
    }
}
