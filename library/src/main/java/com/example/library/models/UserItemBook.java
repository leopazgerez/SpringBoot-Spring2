package com.example.library.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

@Entity
public class UserItemBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
