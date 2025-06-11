package com.springbootproject.learning.controller;

import com.springbootproject.learning.model.UserInfo;
import com.springbootproject.learning.service.ServiceLayer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controller {

    //use Constructor Injection for ServiceLayer
    private final ServiceLayer serviceLayer;

    public Controller(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfo> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserInfo currentUser = (UserInfo) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping(value = "/getResponse", produces = "application/json")
    public ResponseEntity<Map<String, String>> getResponse() throws InterruptedException {
        serviceLayer.createOrder();
        serviceLayer.createNotification();
        serviceLayer.createTransport();
        return serviceLayer.getResponse();
    }
}
