package com.example.library.services;

import com.example.library.models.User;

public interface UserService {

    void createUser();

    User getUser();

    User updateUser();

    void deleteUser();

}
