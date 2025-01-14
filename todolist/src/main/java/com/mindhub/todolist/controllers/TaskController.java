package com.mindhub.todolist.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import com.mindhub.todolist.dtos.TaskDTO;
import com.mindhub.todolist.services.TaskService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

//    private final TaskService taskService;
//
//    public TaskController(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
//    @Operation(summary = "Create a new task", description = "Creates a new task based on the provided TaskDTO object.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Task successfully created",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = TaskDTO.class))),
//    })
//    @PostMapping()
//    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
//        return new ResponseEntity<>(taskService.createTask(taskDTO), HttpStatus.CREATED);
//    }
//
//
//    @Operation(summary = "Update an existing task", description = "Updates an existing task by its ID.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Task successfully updated",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = TaskDTO.class))),
//            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
//    })
//
//    @PutMapping("/{id}")
//    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
//        TaskDTO updatedTask = taskService.updateTask(taskDTO, id);
//        return ResponseEntity.ok(updatedTask);
//    }
//
//    @Operation(summary = "Delete a task", description = "Deletes a task by its unique ID.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204", description = "Task successfully deleted"),
//            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
//    })
//    @DeleteMapping("/{taskId}")
//    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
//        taskService.deleteTask(taskId);
//        return ResponseEntity.noContent().build();
//    }
//
//    @Operation(summary = "Get a task by ID", description = "Fetches a task's details by its unique ID.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Task found",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = TaskDTO.class))),
//            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
//    })
//    @GetMapping("/{id}")
//    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
//        return ResponseEntity.ok(taskService.getTaskById(id));
//    }
//
//    @Operation(summary = "Get all tasks", description = "Retrieves a list of all tasks.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "List of tasks found",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = TaskDTO.class))),
//    })
//    @GetMapping()
//    public ResponseEntity<List<TaskDTO>> getAllTask() {
//        return ResponseEntity.ok(taskService.getAllTasks());
//    }

}
