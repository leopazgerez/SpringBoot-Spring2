package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.UserDTO;
import org.springframework.security.core.Authentication;

public interface UserService {
    UserDTO createUser(UserDTO user);

    UserDTO findUserById(Long id);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(Long id);
}
