package com.example.demo.patient;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Patient {
    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    private Long id;
    private String first_name;
    private String last_name;
    private LocalDate dob;
    private String email;
    private Long health_id;
    @Transient
    private Integer age;

    public Patient() {
    }

    public Patient(Long id,
                   String first_name,
                   String last_name,
                   LocalDate dob,
                   String email,
                   Long health_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.email = email;
        this.health_id = health_id;
    }

    public Patient(String first_name,
                   String last_name,
                   LocalDate dob,
                   String email,
                   Long health_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.email = email;
        this.health_id = health_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getHealth_id() {
        return health_id;
    }

    public void setHealth_id(Long health_id) {
        this.health_id = health_id;
    }

    public Integer getAge()
    {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age)
    {
        this.age=age;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", health_id=" + health_id +
                '}';
    }
}
