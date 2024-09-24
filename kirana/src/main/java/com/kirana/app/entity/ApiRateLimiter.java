//package com.kirana.app.entity;
//
//import com.google.common.util.concurrent.RateLimiter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ApiRateLimiter {
//
//    private final RateLimiter rateLimiter = RateLimiter.create(10); // 10 requests per minute
//
//    public boolean tryAcquire() {
//        return rateLimiter.tryAcquire();
//    }
//}
//
