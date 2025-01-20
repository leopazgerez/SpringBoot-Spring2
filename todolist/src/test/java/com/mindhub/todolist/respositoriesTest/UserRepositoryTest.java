package com.mindhub.todolist.respositoriesTest;

import com.mindhub.todolist.models.User;
import com.mindhub.todolist.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        // Crear usuario para las pruebas
        user = new User();
        user.setEmail("test@example.com");
        user.setUserName("testUser");
        user.setPassword("password123"); // Asumiendo que el campo password es requerido

        // Persistir el usuario y hacer flush
        user = entityManager.persist(user);
        entityManager.flush();
    }

    @Test
    public void testExistsByEmail() {
        // When
        boolean exists = userRepository.existsByEmail("test@example.com");

        // Then
        assertTrue(exists);
    }

    @Test
    public void testExistsByEmail_WhenEmailDoesNotExist() {
        // When
        boolean exists = userRepository.existsByEmail("nonexistent@example.com");

        // Then
        assertFalse(exists);
    }

    @Test
    public void testExistsByUserName() {
        // When
        boolean exists = userRepository.existsByUserName("testUser");

        // Then
        assertTrue(exists);
    }

    @Test
    public void testExistsByUserName_WhenUserNameDoesNotExist() {
        // When
        boolean exists = userRepository.existsByUserName("nonexistentUser");

        // Then
        assertFalse(exists);
    }

    @Test
    public void testFindByEmail() {
        // When
        Optional<User> foundUser = userRepository.findByEmail("test@example.com");

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals("test@example.com", foundUser.get().getEmail());
        assertEquals("testUser", foundUser.get().getUserName());
    }

    @Test
    public void testFindByEmail_WhenEmailDoesNotExist() {
        // When
        Optional<User> foundUser = userRepository.findByEmail("nonexistent@example.com");

        // Then
        assertTrue(foundUser.isEmpty());
    }

    @Test
    public void testFindByUserName() {
        // When
        Optional<User> foundUser = userRepository.findByUserName("testUser");

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals("testUser", foundUser.get().getUserName());
        assertEquals("test@example.com", foundUser.get().getEmail());
    }

    @Test
    public void testFindByUserName_WhenUserNameDoesNotExist() {
        // When
        Optional<User> foundUser = userRepository.findByUserName("nonexistentUser");

        // Then
        assertTrue(foundUser.isEmpty());
    }
}