package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.LoginUser;
import com.mindhub.todolist.dtos.SignupUser;
import org.apache.coyote.BadRequestException;

public interface AuthService {
    public void signup(SignupUser signupUser) throws BadRequestException;

//    public String login(LoginUser loginRequest);
}
