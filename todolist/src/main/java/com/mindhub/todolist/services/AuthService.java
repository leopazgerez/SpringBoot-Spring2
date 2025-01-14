package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.LoginUser;
import com.mindhub.todolist.dtos.SignupUser;

public interface AuthService {
    public void signup(SignupUser signupUser);

//    public String login(LoginUser loginRequest);
}
