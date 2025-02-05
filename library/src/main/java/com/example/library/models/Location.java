package com.example.library.models;

import jakarta.persistence.*;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String section;
    private String shelve;
    @OneToOne(mappedBy = "user")
    private User user;
    public Long getId() {
        return id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getShelve() {
        return shelve;
    }

    public void setShelve(String shelve) {
        this.shelve = shelve;
    }
}
