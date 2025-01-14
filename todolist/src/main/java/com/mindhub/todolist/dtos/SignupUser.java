package com.mindhub.todolist.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record SignupUser(@JsonProperty("username")
                         String username,
                         @JsonProperty("password")
                         String password,
                         @JsonProperty("email")
                         String email,
                         @JsonProperty("roleTypeId")
                         Long roleTypeId) {
}
