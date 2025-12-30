package org.nailyourinterview.lld.doctors_appointment.service;

import lombok.AllArgsConstructor;
import org.nailyourinterview.lld.doctors_appointment.exception.DoctorNotFoundException;
import org.nailyourinterview.lld.doctors_appointment.model.Doctor;
import org.nailyourinterview.lld.doctors_appointment.enums.Specialization;
import org.nailyourinterview.lld.doctors_appointment.repository.DoctorRepository;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository repo;

    public Doctor register(String name, Specialization spec, double rating) {
        Doctor doctor = new Doctor(name, spec, rating);
        repo.save(doctor);
        return doctor;
    }

    public void declareAvailability(UUID doctorId, List<String> slots) {
        Doctor doc = repo.findById(doctorId);
        if (doc == null) throw new DoctorNotFoundException("Doctor not found");
        for (String slot : slots) doc.getAvailability().put(slot, true);
    }
}
