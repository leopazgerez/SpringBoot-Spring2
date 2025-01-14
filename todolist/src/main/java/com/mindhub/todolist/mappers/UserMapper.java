package com.mindhub.todolist.mappers;

import com.mindhub.todolist.dtos.SignupUser;
import com.mindhub.todolist.dtos.UserDTO;
import com.mindhub.todolist.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO mapToResponseDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getUserName());
        userDTO.setId(user.getId());
        return userDTO;
    }

    public User mapToEntity(SignupUser signupUser, String encodedPass) {
        User user = new User();
        user.setEmail(signupUser.email());
        user.setPassword(encodedPass);
        user.setUserName(signupUser.username());
        user.setRoleType(signupUser.roleTypeId());
        return user;
    }

    public User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return user;
    }
    UserMapper(){}
}
