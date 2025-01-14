package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.TaskDTO;

import java.util.List;

public interface TaskService {
    TaskDTO createTask(TaskDTO taskDTO);

    TaskDTO updateTask(TaskDTO task, Long taskId);

    TaskDTO getTaskById(Long taskId);

    List<TaskDTO> getAllTasks();

    void deleteTask(Long taskId);

    List<TaskDTO> getTasksForCurrentUser();

    void deleteTaskForCurrentUser(Long taskId);

    TaskDTO updateTaskForCurrentUser(Long taskId, TaskDTO taskDTO);
}
