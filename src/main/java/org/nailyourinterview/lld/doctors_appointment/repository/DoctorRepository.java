package org.nailyourinterview.lld.doctors_appointment.repository;

import org.nailyourinterview.lld.doctors_appointment.model.Doctor;
import org.nailyourinterview.lld.doctors_appointment.enums.Specialization;

import java.util.*;

public class DoctorRepository {
    private final Map<UUID, Doctor> doctorMap = new HashMap<>();

    public void save(Doctor doctor) {
        doctorMap.put(doctor.getId(), doctor);
    }

    public Doctor findById(UUID id) {
        return doctorMap.get(id);
    }

    public List<Doctor> findBySpecialization(Specialization specialization) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor doc : doctorMap.values()) {
            if (doc.getSpecialization() == specialization) result.add(doc);
        }
        return result;
    }
}
