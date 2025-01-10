package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.TaskDTO;

public interface TaskService {
    TaskDTO createTask(TaskDTO task, Long userId);

    TaskDTO updateTask(TaskDTO task, Long userId);

    void deleteTask(Long taskId);

}
