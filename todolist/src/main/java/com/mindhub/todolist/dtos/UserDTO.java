package com.mindhub.todolist.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mindhub.todolist.models.Task;
import com.mindhub.todolist.models.User;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;
    private String name, email, password;
    private List<Task> tasks;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.tasks = user.getTasks();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}