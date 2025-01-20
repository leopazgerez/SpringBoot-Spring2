package com.mindhub.todolist.services.implementation;

import com.mindhub.todolist.dtos.LoginUser;
import com.mindhub.todolist.dtos.SignupUser;
import com.mindhub.todolist.exceptions.EmailAlreadyExistException;
import com.mindhub.todolist.exceptions.UserNameAlreadyExistException;
import com.mindhub.todolist.mappers.UserMapper;
import com.mindhub.todolist.models.User;
import com.mindhub.todolist.repositories.UserRepository;
import com.mindhub.todolist.services.AuthService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void signup(SignupUser signupUser) throws BadRequestException {
        if (userRepository.existsByEmail(signupUser.email())) {
            throw new EmailAlreadyExistException("Email already exists");
        }
        if (userRepository.existsByUserName(signupUser.username())) {
            throw new UserNameAlreadyExistException("Username already exists");
        }
        if (signupUser.email().isEmpty() | (signupUser.password().isEmpty() | signupUser.password().length() < 4) | signupUser.username().isEmpty()) {
            throw new BadRequestException();
        }
        String encryptPass = passwordEncoder.encode(signupUser.password());
        User user = userMapper.mapToEntity(signupUser, encryptPass);
        userRepository.save(user);
    }

//    @Override
//    public String login(LoginUser loginRequest) {
//
//        return "";
//    }
}
