package org.nailyourinterview.lld.doctors_appointment.strategy;

import org.nailyourinterview.lld.doctors_appointment.dto.DoctorSlot;

import java.util.List;

public interface SlotRankStrategy {
    List<DoctorSlot> rank(List<DoctorSlot> slots);
}
