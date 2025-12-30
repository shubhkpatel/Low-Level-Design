package org.nailyourinterview.lld.doctors_appointment.model;

import lombok.Getter;
import lombok.Setter;
import org.nailyourinterview.lld.doctors_appointment.enums.Specialization;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class Doctor {
    private final UUID id;
    @Setter
    private String name;
    @Setter
    private Specialization specialization;
    private final Map<String, Boolean> availability = new HashMap<>(); // slot -> isAvailable
    @Setter
    private double rating;

    public Doctor(String name, Specialization specialization, double rating) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.specialization = specialization;
        this.rating = rating;
    }
}
