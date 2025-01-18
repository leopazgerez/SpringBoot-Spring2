package com.mindhub.todolist.controllers;

import com.mindhub.todolist.dtos.TaskDTO;
import com.mindhub.todolist.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    TaskService taskService;

    @Operation(summary = "Get all tasks", description = "Retrieves a list of all tasks in the system. Requires admin privileges.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class)))
    })
    @GetMapping("/tasks/getAllTasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Delete a task by ID", description = "Deletes a task by its unique ID. Requires admin privileges.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @Operation(summary = "Update a task by ID", description = "Updates an existing task by its unique ID. Requires admin privileges.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class))),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input data")
    })

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO) {
        TaskDTO updatedTask = taskService.updateTask(taskDTO, taskId);
        return ResponseEntity.ok(updatedTask);
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> createTaskForUser(@RequestBody TaskDTO taskDTO) {
        TaskDTO updatedTask = taskService.createTask(taskDTO);
        return ResponseEntity.ok(updatedTask);
    }
}
