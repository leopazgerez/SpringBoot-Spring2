package com.mindhub.todolist.services.implementation;

import com.mindhub.todolist.dtos.UserDTO;
import com.mindhub.todolist.exceptions.EmailAlreadyExistException;
import com.mindhub.todolist.exceptions.TaskNotFoundException;
import com.mindhub.todolist.exceptions.UserNotFoundException;
import com.mindhub.todolist.mappers.UserMapper;
import com.mindhub.todolist.models.User;
import com.mindhub.todolist.repositories.UserRepository;
import com.mindhub.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.userMapper = mapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyExistException("Email already in use");
        }

        if (userRepository.existsByUserName(userDTO.getName())) {
            throw new UserNotFoundException("Username already in use");
        }
        User user = new User();
        user.setUserName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        User savedUser = userRepository.save(user);
        return userMapper.mapToResponseDTO(savedUser);
    }

    @Override
    public UserDTO findUserById(Long id) {
        return userRepository.findById(id).map(userMapper::mapToResponseDTO).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    //Actualizo los datos del usuario que encuentro
    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User finduser = getCurrentUser();
        finduser.setEmail(userDTO.getEmail());
        finduser.setUserName(userDTO.getName());
        User updatedUser = userRepository.save(finduser);
        return userMapper.mapToResponseDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }


    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
