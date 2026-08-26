package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Authentity;

public interface Authrepo extends JpaRepository<Authentity, String> {

    Authentity findByEmail(String email);
}