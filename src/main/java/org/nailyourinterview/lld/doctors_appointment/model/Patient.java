package org.nailyourinterview.lld.doctors_appointment.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

@Getter
public class Patient {
    private final UUID id;
    @Setter
    private String name;

    public Patient(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
