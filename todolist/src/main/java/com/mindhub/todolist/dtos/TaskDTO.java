package com.mindhub.todolist.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mindhub.todolist.enums.TaskStatus;
import com.mindhub.todolist.models.Task;
import com.mindhub.todolist.models.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {
    private Long id;
    private String title, description;
    private TaskStatus status;
    private User user;

    public TaskDTO() {
    }

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.user = task.getUser();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
