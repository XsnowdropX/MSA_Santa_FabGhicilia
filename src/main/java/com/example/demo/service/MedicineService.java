package com.example.demo.service;

import com.example.demo.entity.Medicine;
import com.example.demo.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<Medicine> getMedicines() {
        return medicineRepository.findAll();
    }

    public Medicine getMedicineById(Long medicineId) {
        return medicineRepository.findById(medicineId)
                .orElseThrow(() -> new IllegalStateException("Medicine with ID " + medicineId + " does not exist."));
    }

    public void addMedicine(Medicine medicine) {
        medicineRepository.save(medicine);
    }

    public void deleteMedicine(Long medicineId) {
        if (!medicineRepository.existsById(medicineId)) {
            throw new IllegalStateException("Medicine with ID " + medicineId + " does not exist.");
        }
        medicineRepository.deleteById(medicineId);
    }
}
