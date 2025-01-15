package com.example.demo.service;

import com.example.demo.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Prescription;


import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public List<Prescription> getPrescriptionsByPatientId(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId);
    }

    public Prescription getPrescriptionById(Long prescriptionId) {
        return prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new IllegalStateException("Prescription with ID " + prescriptionId + " does not exist."));
    }

    public void addPrescription(Prescription prescription) {
        if (prescription.getPatient() == null || prescription.getDoctor() == null || prescription.getMedicines() == null) {
            throw new IllegalArgumentException("Patient, Doctor, and Medicines must be provided.");
        }

        prescriptionRepository.save(prescription);
    }

}
