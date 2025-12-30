package org.nailyourinterview.lld.car_rental.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.nailyourinterview.lld.car_rental.enums.BookingStatus;
import org.nailyourinterview.lld.car_rental.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Setter
@Builder
public class Booking {
    private String bookingId;
    private User user;
    private Vehicle vehicle;
    private Branch pickupBranch;
    private Branch dropBranch;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingStatus status = BookingStatus.CREATED;
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    private double amount;

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM h:mm a yyyy", Locale.ENGLISH);
        return "\n" +
                "Booking ID: " + bookingId + "\n" +
                "User: " + (user != null ? user.getName() : "N/A") + "\n" +
                "Pickup Time: " + startTime.format(formatter) + "\n" +
                "Drop Time: " + endTime.format(formatter) + "\n" +
                "Pickup Location: " + (pickupBranch != null ? pickupBranch.getCity() : "N/A") + "\n" +
                "Drop Location: " + (dropBranch != null ? dropBranch.getCity() : "N/A") + "\n" +
                "Vehicle Type: " + (vehicle != null ? vehicle.getType() : "N/A") + "\n" +
                "Vehicle Number Plate: " + (vehicle != null ? vehicle.getLicensePlate() : "N/A") + "\n" +
                "Amount: $" + String.format("%.2f", amount) + "\n" +
                "Status: " + status + "\n";
    }
}