package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Medicine {
    @Id
    @SequenceGenerator(
            name = "medicine_sequence",
            sequenceName = "medicine_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medicine_sequence"
    )
    private Long id;

    private String name;
    private String dosage; // E.g., "500mg"
    private int timesPerDay; // E.g., 3 for "3 times per day"

    public Medicine() {
    }

    public Medicine(String name, String dosage, int timesPerDay) {
        this.name = name;
        this.dosage = dosage;
        this.timesPerDay = timesPerDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getTimesPerDay() {
        return timesPerDay;
    }

    public void setTimesPerDay(int timesPerDay) {
        this.timesPerDay = timesPerDay;
    }
}