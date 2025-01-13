package com.mindhub.todolist.models;

import com.mindhub.todolist.enums.RoleType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String userName, password;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(Long roleTypeId) {
        this.roleType = roleTypeId == 1 ? RoleType.USER : RoleType.ADMIN;
    }
}
