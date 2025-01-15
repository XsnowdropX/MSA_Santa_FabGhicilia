package com.example.demo.config;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PatientConfig {

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository repository)
    {
        return args -> {
            Patient jane = new Patient(
                    "Jane",
                    "Doe",
                    LocalDate.of(2000, Month.JANUARY, 01),
                    "jane.doe@gmail.com",
                    123L);

            Patient joe = new Patient(
                    "Joe",
                    "Doe",
                    LocalDate.of(1970, Month.DECEMBER, 31),
                    "joe.doe@gmail.com",
                    123L);

            repository.saveAll(
                    List.of(jane, joe)
            );
        };
    }
}
