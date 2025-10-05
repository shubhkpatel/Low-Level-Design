package org.nailyourinterview.lld.rate_limiter;

import org.nailyourinterview.lld.rate_limiter.enums.UserTier;
import org.nailyourinterview.lld.rate_limiter.model.User;
import org.nailyourinterview.lld.rate_limiter.service.RateLimiterService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    // call allowreq func 20 times simultaneously
    static void checkConcurrency(RateLimiterService rateLimiterService) throws InterruptedException {
        User freeUser1 = new User("user1", UserTier.FREE);

        int threads = 20; // simulate 20 concurrent requests
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        CyclicBarrier barrier = new CyclicBarrier(threads);
        CountDownLatch latch = new CountDownLatch(threads);

        for (int i = 1; i <= threads; i++) {
            final int reqNum = i;
            executor.submit(() -> {
                try {
                    // all threads wait here until barrier is full
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                boolean allowed = rateLimiterService.allowRequest(freeUser1);
                System.out.println(Thread.currentThread().getName() +
                        " | Request " + reqNum + " for FreeUser1: " + (allowed ? "ALLOWED" : "BLOCKED"));

                latch.countDown();
            });
        }

        latch.await(); // wait for all threads to finish
        executor.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiterService rateLimiterService = new RateLimiterService();

        User freeUser = new User("user1", UserTier.FREE); // 10 req in 60 sec
        User premiumUser = new User("user2", UserTier.PREMIUM); // 100 req in 60 sec

//        System.out.println("=== Free User Requests ===");
//        for (int i = 1; i <= 15; i++) {
//            boolean allowed = rateLimiterService.allowRequest(freeUser);
//            System.out.println("Request " + i + " for Free User: " + (allowed ? "ALLOWED" : "BLOCKED"));
//            Thread.sleep(100); // simulate delay between requests
//        }
//
//        System.out.println("\n=== Premium User Requests ===");
//        for (int i = 1; i <= 120; i++) {
//            boolean allowed = rateLimiterService.allowRequest(premiumUser);
//            System.out.println("Request " + i + " for Premium User: " + (allowed ? "ALLOWED" : "BLOCKED"));
//            Thread.sleep(100);
//        }

        checkConcurrency(rateLimiterService);
    }
}