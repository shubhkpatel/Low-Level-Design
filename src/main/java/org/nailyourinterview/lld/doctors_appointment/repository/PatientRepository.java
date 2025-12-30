package org.nailyourinterview.lld.doctors_appointment.repository;

import org.nailyourinterview.lld.doctors_appointment.model.Patient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PatientRepository {
    private final Map<UUID, Patient> patientMap = new HashMap<>();

    public void save(Patient patient) {
        patientMap.put(patient.getId(), patient);
    }

    public Patient findById(UUID id) {
        return patientMap.get(id);
    }
}