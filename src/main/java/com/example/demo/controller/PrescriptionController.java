package com.example.demo.controller;

import com.example.demo.entity.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.PrescriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping(path = "{prescriptionId}")
    public Prescription getPrescriptionById(@PathVariable("prescriptionId") Long prescriptionId) {
        return prescriptionService.getPrescriptionById(prescriptionId);
    }

    @GetMapping("/patient/{patientId}")
    public List<Prescription> getPrescriptionsByPatientId(@PathVariable("patientId") Long patientId) {
        return prescriptionService.getPrescriptionsByPatientId(patientId);
    }

    @PostMapping
    public void addPrescription(@RequestBody Prescription prescription) {
        prescriptionService.addPrescription(prescription);
    }
}
