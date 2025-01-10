package com.mindhub.todolist.services.implementation;

import com.mindhub.todolist.dtos.TaskDTO;
import com.mindhub.todolist.exceptions.TaskNotFoundException;
import com.mindhub.todolist.exceptions.UserNotFoundException;
import com.mindhub.todolist.mappers.TaskMapper;
import com.mindhub.todolist.models.Task;
import com.mindhub.todolist.models.User;
import com.mindhub.todolist.repositories.TaskRepository;
import com.mindhub.todolist.repositories.UserRepository;
import com.mindhub.todolist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task newTask = new Task();
        User user = userRepository.findById(taskDTO.getUser().getId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        newTask.setStatus(taskDTO.getStatus());
        newTask.setDescription(taskDTO.getDescription());
        newTask.setTitle(taskDTO.getTitle());
        newTask.setUser(user);
        Task savedTask = taskRepository.save(newTask);
        return taskMapper.mapToDTOResponse(savedTask);
    }

    @Override
    public TaskDTO updateTask(TaskDTO taskDTO, Long taskId) {
        return taskRepository.findById(taskId).map(existingTask -> {
                    existingTask.setDescription(taskDTO.getDescription());
                    existingTask.setTitle(taskDTO.getTitle());
                    existingTask.setStatus(taskDTO.getStatus());
                    Task updatedTask = taskRepository.save(existingTask);
                    return taskMapper.mapToDTOResponse(updatedTask);
                }).
                orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    @Override
    public TaskDTO getTaskById(Long taskId) {
        return taskRepository.findById(taskId).map(taskMapper::mapToDTOResponse).
                orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::mapToDTOResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskRepository.delete(task);
    }
}
