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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task newTask = new Task();
        User user = getCurrentUser();
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

    public List<TaskDTO> getTasksForCurrentUser() {
        User currentUser = getCurrentUser();
        return taskRepository.findByUserId(currentUser.getId());
    }

    public void deleteTaskForCurrentUser(Long taskId) {

        User currentUser = getCurrentUser();
        Task task = taskRepository.findByIdAndUserId(taskId, currentUser.getId())
                .orElseThrow(() -> new RuntimeException("Task not found or not authorized"));
        taskRepository.delete(task);
    }

    public TaskDTO updateTaskForCurrentUser(Long taskId, TaskDTO taskDTO) {
        User currentUser = getCurrentUser();
        Task task = taskRepository.findByIdAndUserId(taskId, currentUser.getId())
                .orElseThrow(() -> new RuntimeException("Task not found or not authorized"));
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        taskRepository.save(task);
        return taskMapper.mapToDTOResponse(task);
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
