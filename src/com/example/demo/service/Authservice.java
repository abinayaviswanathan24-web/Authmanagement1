package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Authentity;
import com.example.demo.repository.Authrepo;
import com.example.demo.jwt.JWTservice;

@Service
public class Authservice {

    @Autowired
    private Authrepo authrepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    @Autowired
    private JWTservice jwtService;

    // Register
    public Authentity register(Authentity auth) {

        // Hash password
        auth.setPassword(passwordEncoder.encode(auth.getPassword()));

        // Save user
        Authentity savedUser = authrepo.save(auth);

        // Send registration mail
        mailService.sendMail(
                auth.getEmail(),
                "Registration Successful",
                "Welcome! Your registration was successful."
        );

        return savedUser;
    }

    // Login
    public String login(String email, String password) {

        // Find user by email
        Authentity user = authrepo.findByEmail(email);

        if (user == null) {
            return "User not found";
        }

        // Check password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Invalid password";
        }

        // Generate JWT token
        return jwtService.generateToken(email);
    }
}