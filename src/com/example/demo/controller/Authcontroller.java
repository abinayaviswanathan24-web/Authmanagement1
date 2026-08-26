package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Authentity;
import com.example.demo.service.Authservice;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class Authcontroller {

    @Autowired
    private Authservice authservice;

    // Register
    @PostMapping("/register")
    public Authentity register(@RequestBody Authentity auth) {
        return authservice.register(auth);
    }

    // Login
    @PostMapping("/login")
    public String login(@RequestBody Authentity auth) {
        return authservice.login(
                auth.getEmail(),
                auth.getPassword()
        );
    }

    // Test API
    @GetMapping("/test")
    public String test() {
        return "JWT Authentication is working!";
    }
}