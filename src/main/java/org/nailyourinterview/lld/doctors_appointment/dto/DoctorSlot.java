package org.nailyourinterview.lld.doctors_appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.nailyourinterview.lld.doctors_appointment.model.Doctor;

@Getter
@AllArgsConstructor
public class DoctorSlot {
    private final Doctor doctor;
    private final String slot;
}

/*
doctor1 : "10:00"
doctor2 : "11:00"
doctor1 : "13:00"
 */
