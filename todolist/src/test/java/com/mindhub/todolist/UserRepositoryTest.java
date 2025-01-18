package com.mindhub.todolist;
import com.mindhub.todolist.models.User;
import com.mindhub.todolist.repositories.UserRepository;
import com.mindhub.todolist.services.implementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService; // Suponiendo que tienes un servicio que usa el repositorio

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setEmail("test@example.com");
        user.setUserName("testUser");
    }

    @Test
    public void testExistsByEmail() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        boolean exists = userRepository.existsByEmail("test@example.com");

        assertEquals(true, exists);
    }

    @Test
    public void testExistsByUserName() {
        when(userRepository.existsByUserName(anyString())).thenReturn(true);

        boolean exists = userRepository.existsByUserName("testUser");

        assertEquals(true, exists);
    }

    @Test
    public void testFindByEmail() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        Optional<User> foundUser = userRepository.findByEmail("test@example.com");

        assertEquals(true, foundUser.isPresent());
        assertEquals("test@example.com", foundUser.get().getEmail());
    }

    @Test
    public void testFindByUserName() {
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));

        Optional<User> foundUser = userRepository.findByUserName("testUser");

        assertEquals(true, foundUser.isPresent());
        assertEquals("testUser", foundUser.get().getUserName());
    }
}

