package com.example.demo.controller;

import com.example.demo.entity.Patient;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            Patient patient = patientService.login(email, password);
            return ResponseEntity.ok(patient);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    @PostMapping("/register")
    public void registerPatient(@RequestBody Patient patient) {
        patientService.registerNewPatient(patient);
    }

    @GetMapping
    public List<Patient> getPatients()
    {
        return patientService.getPatients();
    }

    @GetMapping(path = "{patientId}")
    public Patient getPatientById(@PathVariable("patientId") Long patientId) {
        return patientService.getPatientById(patientId);
    }

    @PostMapping
    public void registerNewPatient(@RequestBody Patient patient)
    {
        patientService.addNewPatient(patient);
    }

    @DeleteMapping(path = "{patientId}")
    public void deletePatient(@PathVariable("patientId") Long patientId)
    {
        patientService.deletePatient(patientId);
    }

    @PutMapping(path="{patientId}")
    public void updatePatient(
            @PathVariable("patientId") Long patientId,
            @RequestParam(required=false) String first_name,
            @RequestParam(required=false) String last_name,
            @RequestParam(required=false) String email){
        patientService.updatePacient(patientId, first_name, last_name, email);
    }

}
