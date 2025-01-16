package com.example.demo.service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Prescription;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Appointment with ID " + id + " does not exist."));
    }

    public List<Appointment> getAppointmentByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public void createAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public void updateAppointmentStatus(Long appointmentId, String status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalStateException("Appointment with ID " + appointmentId + " does not exist."));
        appointment.setStatus(status);
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new IllegalStateException("Appointment with ID " + id + " does not exist.");
        }
        appointmentRepository.deleteById(id);
    }
}

