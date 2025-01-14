package com.mindhub.todolist.mappers;

import com.mindhub.todolist.dtos.TaskDTO;
import com.mindhub.todolist.models.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDTO mapToDTO(Task task) {
        return new TaskDTO(task);
    }

    public TaskDTO mapToDTOResponse(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        return taskDTO;
    }

    public Task mapToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setTitle(taskDTO.getTitle());
        task.setStatus(taskDTO.getStatus());
        return task;
    }

    TaskMapper() {
    }
}
