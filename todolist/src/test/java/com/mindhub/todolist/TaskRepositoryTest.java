package com.mindhub.todolist;

import com.mindhub.todolist.dtos.TaskDTO;
import com.mindhub.todolist.models.Task;
import com.mindhub.todolist.enums.TaskStatus;
import com.mindhub.todolist.models.User;
import com.mindhub.todolist.repositories.TaskRepository;
import com.mindhub.todolist.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class TaskRepositoryTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskService taskService;
    private Task task;
    private TaskDTO taskDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Task();
        task.setTitle("Sample Task");
        task.setDescription("This is a sample task");
        task.setStatus(TaskStatus.PENDING);
        // Suponiendo que ya tienes un objeto `User` para setear en la tarea.
        task.setUser(new User()); // Ajusta esta línea con el objeto `User` adecuado
        taskDTO = new TaskDTO();
        taskDTO.setId(1L);
    }

    @Test
    public void testFindByIdAndUserId() {
        when(taskRepository.findByIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.of(task));

        Optional<Task> foundTask = taskRepository.findByIdAndUserId(1L, 1L);

        assertEquals(true, foundTask.isPresent());
        assertEquals(1L, foundTask.get().getId());
        assertEquals(1L, foundTask.get().getUser().getId());
        assertEquals("Sample Task", foundTask.get().getTitle());
        assertEquals("This is a sample task", foundTask.get().getDescription());
        assertEquals(TaskStatus.PENDING, foundTask.get().getStatus());
    }

    @Test
    public void testFindByUserId() {
        when(taskRepository.findByUserId(anyLong())).thenReturn(List.of(taskDTO));

        List<TaskDTO> tasks = taskRepository.findByUserId(1L);

        assertEquals(1, tasks.size());
    }
}
