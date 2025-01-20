package com.mindhub.todolist.respositoriesTest;

import com.mindhub.todolist.dtos.TaskDTO;
import com.mindhub.todolist.models.Task;
import com.mindhub.todolist.enums.TaskStatus;
import com.mindhub.todolist.models.User;
import com.mindhub.todolist.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TaskRepository taskRepository;

    private Task task;
    private User user;

    @BeforeEach
    public void setUp() {
        // Crear y persistir un usuario
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user = entityManager.persist(user);

        // Crear y persistir una tarea
        task = new Task();
        task.setTitle("Sample Task");
        task.setDescription("This is a sample task");
        task.setStatus(TaskStatus.PENDING);
        task.setUser(user);
        task = entityManager.persist(task);

        // Asegurarse de que los cambios se flush-een a la base de datos
        entityManager.flush();
    }

    @Test
    public void testFindByIdAndUserId() {
        // When
        Optional<Task> foundTask = taskRepository.findByIdAndUserId(task.getId(), user.getId());

        // Then
        assertTrue(foundTask.isPresent());
        assertEquals(task.getId(), foundTask.get().getId());
        assertEquals(user.getId(), foundTask.get().getUser().getId());
        assertEquals("Sample Task", foundTask.get().getTitle());
        assertEquals("This is a sample task", foundTask.get().getDescription());
        assertEquals(TaskStatus.PENDING, foundTask.get().getStatus());
    }

    @Test
    public void testFindByIdAndUserIdWhenNotFound() {
        // When
        Optional<Task> foundTask = taskRepository.findByIdAndUserId(999L, 999L);

        // Then
        assertTrue(foundTask.isEmpty());
    }

    @Test
    public void testFindByUserId() {
        // When
        List<TaskDTO> tasks = taskRepository.findByUserId(user.getId());

        // Then
        assertEquals(1, tasks.size());
        TaskDTO taskDTO = tasks.get(0);
        assertEquals(task.getId(), taskDTO.getId());
        assertEquals("Sample Task", taskDTO.getTitle());
    }

    @Test
    public void testFindByUserIdWhenNoTasks() {
        // When
        List<TaskDTO> tasks = taskRepository.findByUserId(999L);

        // Then
        assertTrue(tasks.isEmpty());
    }
}