package org.nailyourinterview.lld.doctors_appointment.service;

import lombok.AllArgsConstructor;
import org.nailyourinterview.lld.doctors_appointment.exception.PatientNotFoundException;
import org.nailyourinterview.lld.doctors_appointment.model.Patient;
import org.nailyourinterview.lld.doctors_appointment.repository.PatientRepository;

import java.util.UUID;

@AllArgsConstructor
public class PatientService {
    private final PatientRepository repo;

    public Patient register(String name) {
        Patient p = new Patient(name);
        repo.save(p);
        return p;
    }

    public Patient findById(UUID id){
        Patient patient = repo.findById(id);
        if (patient == null) throw new PatientNotFoundException("Patient not found");
        return patient;
    }
}
