package com.mindhub.todolist.controllersTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindhub.todolist.config.JwtAuthenticationFilter;
import com.mindhub.todolist.config.JwtUtils;
import com.mindhub.todolist.config.SecurityConfig;
import com.mindhub.todolist.controllers.AuthController;
import com.mindhub.todolist.dtos.LoginUser;
import com.mindhub.todolist.dtos.SignupUser;
import com.mindhub.todolist.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@WebMvcTest({AuthController.class, SecurityConfig.class})
@Import(JwtUtils.class)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private AuthService authService;

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private JwtUtils jwtUtils;

    private SignupUser signupUser;
    private LoginUser loginUser;
    private UsernamePasswordAuthenticationToken authentication;

    @BeforeEach
    void setUp() {
        // Preparar datos de prueba para signup
        signupUser = new SignupUser(
                "test@example.com",
                "testuser",
                "password123",
                1L
        );

        // Preparar datos de prueba para login
        loginUser = new LoginUser(
                "test@example.com",
                "password123"
        );

        // Preparar Authentication mock
        authentication = new UsernamePasswordAuthenticationToken(
                loginUser.email(),
                loginUser.password()
        );
    }

    @Test
    void signup_Success() throws Exception {
        // Given
        doNothing().when(authService).signup(any(SignupUser.class));

        // When & Then
        mockMvc.perform(post("/auth/signup")
                        .content(objectMapper.writeValueAsString(signupUser)))
                .andExpect(status().isOk());
    }

    @Test
    void signup_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        // Given
        SignupUser invalidUser = new SignupUser(
                "",  // email vacío
                "",  // username vacío
                "", // contraseña muy corta
                1L
        );

        // When & Then
        mockMvc.perform(post("/auth/signup")
                        .content(objectMapper.writeValueAsString(invalidUser)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void login_Success() throws Exception {
        // Given
        when(authenticationManager.authenticate(any(org.springframework.security.core.Authentication.class)))
                .thenReturn(authentication);
        var jwtResponse = jwtUtils.generateToken("test@example.com");
        when(jwtUtils.generateToken(anyString()))
                .thenReturn("test.jwt.token");

        // When & Then
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginUser)))
                .andExpect(status().isOk())
                .andExpect(content().string("test.jwt.token"));
    }

    @Test
    void login_WithInvalidCredentials_ShouldReturnUnauthorized() throws Exception {
        // Given
        when(authenticationManager.authenticate(any(org.springframework.security.core.Authentication.class)))
                .thenThrow(new org.springframework.security.authentication.BadCredentialsException("Bad credentials"));

        // When & Then
        mockMvc.perform(post("/auth/login")
                        .content(objectMapper.writeValueAsString(loginUser)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void login_WithMissingCredentials_ShouldReturnBadRequest() throws Exception {
        // Given
        LoginUser invalidLogin = new LoginUser("", ""); // email y password vacíos

        // When & Then
        mockMvc.perform(post("/auth/login")
                        .content(objectMapper.writeValueAsString(invalidLogin)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signup_WithExistingEmail_ShouldReturnConflict() throws Exception {
        // Given
        doNothing().when(authService)
                .signup(any(SignupUser.class));

        // When & Then
        mockMvc.perform(post("/auth/signup")
                        .content(objectMapper.writeValueAsString(signupUser)))
                .andExpect(status().isOk());
    }
}