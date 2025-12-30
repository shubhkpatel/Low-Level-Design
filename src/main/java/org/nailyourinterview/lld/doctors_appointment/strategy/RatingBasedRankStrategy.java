package org.nailyourinterview.lld.doctors_appointment.strategy;

import org.nailyourinterview.lld.doctors_appointment.dto.DoctorSlot;

import java.util.List;

public class RatingBasedRankStrategy implements SlotRankStrategy {
    @Override
    public List<DoctorSlot> rank(List<DoctorSlot> slots) {
        slots.sort((a, b) -> Double.compare(b.getDoctor().getRating(), a.getDoctor().getRating()));
        return slots;
    }
}