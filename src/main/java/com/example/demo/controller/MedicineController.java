package com.example.demo.controller;

import com.example.demo.entity.Medicine;
import com.example.demo.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping
    public List<Medicine> getMedicines() {
        return medicineService.getMedicines();
    }

    @GetMapping(path = "{medicineId}")
    public Medicine getMedicineById(@PathVariable("medicineId") Long medicineId) {
        return medicineService.getMedicineById(medicineId);
    }


    @PostMapping
    public void addMedicine(@RequestBody Medicine medicine) {
        medicineService.addMedicine(medicine);
    }

    @DeleteMapping(path = "{medicineId}")
    public void deleteMedicine(@PathVariable("medicineId") Long medicineId) {
        medicineService.deleteMedicine(medicineId);
    }
}
