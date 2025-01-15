package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table
public class Doctor {
    @Id
    @SequenceGenerator(
            name = "doctor_sequence",
            sequenceName = "doctor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "doctor_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String specialization;

    @ElementCollection
    private List<String> availableHours;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String specialization, List<String> availableHours) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.availableHours = availableHours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<String> getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(List<String> availableHours) {
        this.availableHours = availableHours;
    }
}
