package org.nailyourinterview.lld.car_rental.service;

import lombok.Setter;
import org.nailyourinterview.lld.car_rental.enums.*;
import org.nailyourinterview.lld.car_rental.model.*;
import org.nailyourinterview.lld.car_rental.repository.*;
import org.nailyourinterview.lld.car_rental.strategy.booking.BookingStrategy;
import org.nailyourinterview.lld.car_rental.strategy.payment.PaymentStrategy;
import org.nailyourinterview.lld.car_rental.strategy.pricing.PricingStrategy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookingService {
    private static volatile BookingService instance;

    private final BranchRepository branchRepo;
    private final BookingRepository bookingRepo;

    @Setter
    private BookingStrategy bookingStrategy;
    @Setter
    private PricingStrategy pricingStrategy;

    private BookingService(BranchRepository branchRepo,
                           BookingRepository bookingRepo,
                           BookingStrategy bookingStrategy,
                           PricingStrategy pricingStrategy) {
        this.branchRepo = branchRepo;
        this.bookingRepo = bookingRepo;
        this.bookingStrategy = bookingStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public static BookingService getInstance(BranchRepository branchRepo,
                                             BookingRepository bookingRepo,
                                             BookingStrategy bookingStrategy,
                                             PricingStrategy pricingStrategy) {
        if (instance == null) {
            synchronized (BookingService.class) {
                if (instance == null) {
                    instance = new BookingService(branchRepo, bookingRepo, bookingStrategy, pricingStrategy);
                }
            }
        }
        return instance;
    }

    public Optional<Booking> bookVehicle(String branchId,
                                         VehicleType vehicleType,
                                         LocalDateTime start,
                                         LocalDateTime end,
                                         User user,
                                         PaymentStrategy paymentStrategy,
                                         Branch pickUpBranch,
                                         Branch dropBranch,
                                         double distanceKm) {

        Branch branch = branchRepo.getBranch(branchId);
        if (branch == null) {
            System.out.println("Branch not found");
            return Optional.empty();
        }

        List<Vehicle> activeVehicles = branch.getVehiclesByType(vehicleType).stream()
                .filter(v -> v.getStatus() == VehicleStatus.AVAILABLE)
                .filter(v -> !v.getIsBooked().get())
                .toList();

        if (activeVehicles.isEmpty()) {
            System.out.println("No active " + vehicleType.name() + " vehicles available.");
            return Optional.empty();
        }

        // Booking strategy tries to createBooking and return a vehicle with atomic isBooked set
        Vehicle vehicle = bookingStrategy.bookVehicle(activeVehicles);

        if (vehicle == null) {
            System.out.println("No vehicle could be booked.");
            return Optional.empty();
        }

        double amount = pricingStrategy.calculatePrice(vehicle, start, end, distanceKm);

        Booking booking = Booking.builder()
                .bookingId(UUID.randomUUID().toString())
                .user(user)
                .vehicle(vehicle)
                .pickupBranch(pickUpBranch)
                .dropBranch(dropBranch)
                .startTime(start)
                .endTime(end)
                .amount(amount)
                .build();

        PaymentProcessor processor = new PaymentProcessor(paymentStrategy);
        if (!processor.pay(booking)) {
            System.out.println("Payment failed");
            // Rollback booking if payment fails
            vehicle.getIsBooked().set(false);
            return Optional.empty();
        }

        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepo.addBooking(booking);

        vehicle.incrementBookingCount();
        vehicle.setStatus(VehicleStatus.BOOKED);

        System.out.println(booking);

        return Optional.of(booking);
    }

    public void returnVehicle(String bookingId) {
        Optional<Booking> bookingOpt = bookingRepo.getBookingById(bookingId);
        if (bookingOpt.isEmpty()) {
            throw new RuntimeException("Booking not found");
        }

        Booking booking = bookingOpt.get();

        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new RuntimeException("Vehicle is not currently booked");
        }

        booking.setStatus(BookingStatus.COMPLETED);
        booking.getVehicle().getIsBooked().set(false);

        Branch dropBranch = booking.getDropBranch();
        dropBranch.addVehicle(booking.getVehicle());
        System.out.println("Vehicle returned to branch " + dropBranch.getCity() + ": " + booking.getVehicle().getLicensePlate());
    }
}