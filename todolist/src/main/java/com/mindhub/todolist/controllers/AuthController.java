package com.mindhub.todolist.controllers;

import com.mindhub.todolist.config.JwtUtils;
import com.mindhub.todolist.dtos.LoginUser;
import com.mindhub.todolist.dtos.SignupUser;
import com.mindhub.todolist.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Operation(summary = "Register a new user", description = "Registers a new user with the provided information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully")
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupUser signupUser) throws BadRequestException {
        authService.signup(signupUser);
        return ResponseEntity.ok("User registered successfully");
    }

    @Operation(summary = "Login a user", description = "Logs in a user with the provided email and password. Returns a JWT token upon successful login.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful - JWT token in response body"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUser loginUser) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.email(),
                        loginUser.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication.getName());
        return ResponseEntity.ok(jwt);
    }
}

