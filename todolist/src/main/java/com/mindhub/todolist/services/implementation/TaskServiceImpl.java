package com.mindhub.todolist.services.implementation;

import com.mindhub.todolist.dtos.TaskDTO;
import com.mindhub.todolist.repositories.UserRepository;
import com.mindhub.todolist.services.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;

    public TaskServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public TaskDTO createTask(TaskDTO task, Long userId) {
        return null;
    }

    @Override
    public TaskDTO updateTask(TaskDTO task, Long userId) {
        return null;
    }

    @Override
    public void deleteTask(Long taskId) {

    }
}
