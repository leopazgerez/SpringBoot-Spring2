package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO user);

    UserDTO findUserById(Long id);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);
}
