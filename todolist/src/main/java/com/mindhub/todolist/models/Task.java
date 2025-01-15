package com.mindhub.todolist.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mindhub.todolist.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue
    private long id;
    private String title, description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    // Le indica a jackson que no debe ser serializada. Evita recursividad. No es lo recomendable para proyectos grandes. Lo mejor es usar dtos
    @JsonBackReference
    @ManyToOne()
    private User user;


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
