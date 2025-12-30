package org.nailyourinterview.lld.car_rental;

import org.nailyourinterview.lld.car_rental.enums.*;
import org.nailyourinterview.lld.car_rental.factory.VehicleFactory;
import org.nailyourinterview.lld.car_rental.model.*;
import org.nailyourinterview.lld.car_rental.repository.*;
import org.nailyourinterview.lld.car_rental.service.BookingService;
import org.nailyourinterview.lld.car_rental.strategy.booking.*;
import org.nailyourinterview.lld.car_rental.strategy.payment.*;
import org.nailyourinterview.lld.car_rental.strategy.pricing.*;
import org.nailyourinterview.lld.car_rental.utils.DateTimeParser;

import java.time.LocalDateTime;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        BranchRepository branchRepo = new BranchRepository();
        BookingRepository bookingRepo = new BookingRepository();

        Branch branch1 = new Branch("B1", "New York");
        Branch branch2 = new Branch("B2", "Boston");
        branchRepo.addBranch(branch1);
        branchRepo.addBranch(branch2);

        branch1.addVehicle(VehicleFactory.create(VehicleType.SEDAN, "NY1234", 25, 3.5));
        branch1.addVehicle(VehicleFactory.create(VehicleType.SEDAN, "NY5678", 22, 3));
        branch1.addVehicle(VehicleFactory.create(VehicleType.SUV, "NYB100", 30, 4));

        branch2.addVehicle(VehicleFactory.create(VehicleType.SEDAN, "BO1234", 25, 4));

        User user = new User("U1", "John Doe", "john@example.com");

        LocalDateTime start = DateTimeParser.parse("21 May 7:30 AM 2025");
        LocalDateTime end = DateTimeParser.parse("21 May 12:30 PM 2025");

        BookingService bookingService = BookingService.getInstance(
                branchRepo,
                bookingRepo,
                new LeastBookedVehicleStrategy(),
                new HourlyPricingStrategy()
        );

        System.out.println("--------------");

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started!");
            bookingService.bookVehicle(
                    "B1",
                    VehicleType.SUV,
                    start,
                    end,
                    user,
                    new CreditCardPaymentStrategy(),
                    branch1,
                    branch2,
                    100.0
            );
            System.out.println(Thread.currentThread().getName() + " ended!");
        });

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started!");
            bookingService.bookVehicle(
                    "B1",
                    VehicleType.SUV,
                    start,
                    end,
                    user,
                    new WalletPaymentStrategy(),
                    branch1,
                    branch2,
                    100.0
            );
            System.out.println(Thread.currentThread().getName() + " ended!");
        });

        t1.start();
        t2.start();

        System.out.println("--------------");
    }
}

/*
    Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started!");
            bookingService.bookVehicle(
                    "B1",
                    VehicleType.SUV,
                    start,
                    end,
                    user,
                    new CreditCardPaymentStrategy(),
                    branch1,
                    branch2,
                    100.0
            );
            System.out.println(Thread.currentThread().getName() + " ended!");
        });

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started!");
            bookingService.bookVehicle(
                    "B1",
                    VehicleType.SUV,
                    start,
                    end,
                    user,
                    new WalletPaymentStrategy(),
                    branch1,
                    branch2,
                    100.0
            );
            System.out.println(Thread.currentThread().getName() + " ended!");
        });

        t1.start();
        t2.start();
 */