package com.mindhub.todolist.mappers;

import com.mindhub.todolist.dtos.UserDTO;
import com.mindhub.todolist.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO mapToDTO(User user) {
        return new UserDTO(user);
    }

    public UserDTO mapToResponseDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getUserName());
        userDTO.setId(user.getId());
        return userDTO;
    }

    public User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
