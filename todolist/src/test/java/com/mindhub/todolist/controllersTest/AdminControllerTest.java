package com.mindhub.todolist.controllersTest;

import com.mindhub.todolist.config.JwtAuthenticationFilter;
import com.mindhub.todolist.config.JwtUtils;
import com.mindhub.todolist.config.SecurityConfig;
import com.mindhub.todolist.controllers.AdminController;
import com.mindhub.todolist.dtos.TaskDTO;
import com.mindhub.todolist.enums.TaskStatus;
import com.mindhub.todolist.services.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({AdminController.class, SecurityConfig.class})
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @MockitoBean
    private JwtUtils jwtUtils;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetAllTasks() throws Exception {
        TaskDTO task = new TaskDTO();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("Description of Test Task");
        task.setStatus(TaskStatus.PENDING);
        List<TaskDTO> tasks = Collections.singletonList(task);
        Mockito.when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/admin/tasks/getAllTasks")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteTask() throws Exception {
        Long taskId = 1L;

        Mockito.doNothing().when(taskService).deleteTask(taskId);

        mockMvc.perform(delete("/admin/tasks/{taskId}", taskId))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTask() throws Exception {
        Long taskId = 1L;
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(taskId);
        taskDTO.setTitle("Updated Task");
        taskDTO.setDescription("Updated Description");
        taskDTO.setStatus(TaskStatus.COMPLETED);

        Mockito.when(taskService.updateTask(Mockito.any(TaskDTO.class), Mockito.eq(taskId))).thenReturn(taskDTO);

        mockMvc.perform(put("/admin/tasks/{taskId}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Updated Task\",\"description\":\"Updated Description\",\"status\":\"COMPLETED\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateTaskForUser() throws Exception {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1L);
        taskDTO.setTitle("New Task");
        taskDTO.setDescription("New Description");
        taskDTO.setStatus(TaskStatus.PENDING);

        Mockito.when(taskService.createTask(Mockito.any(TaskDTO.class))).thenReturn(taskDTO);

        mockMvc.perform(post("/admin/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"New Task\",\"description\":\"New Description\",\"status\":\"PENDING\"}"))
                .andExpect(status().isOk());
    }
}
