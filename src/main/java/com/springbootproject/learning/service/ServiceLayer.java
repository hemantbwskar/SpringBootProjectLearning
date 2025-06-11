package com.springbootproject.learning.service;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceLayer {

    public ResponseEntity<Map<String, String>>  getResponse(){
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello World");
        return ResponseEntity.ok(response);
    }

    @Async("taskExecutor")
    public void createOrder() throws InterruptedException {
        Thread.sleep(5000L);
        System.out.println("Order created successfully");
    }

    @Async("taskExecutor")
    public void createNotification() throws InterruptedException {
        Thread.sleep(5000L);
        System.out.println("Notification created successfully");
    }

    @Async("taskExecutor")
    public void createTransport() throws InterruptedException {
        Thread.sleep(5000L);
        System.out.println("transport created successfully");
    }
}
