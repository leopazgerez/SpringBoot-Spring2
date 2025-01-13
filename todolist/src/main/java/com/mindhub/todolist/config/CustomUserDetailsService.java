package com.mindhub.todolist.config;

import com.mindhub.todolist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Carga la info de usuario, como su nombre y credenciales
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String UserEmail) throws UsernameNotFoundException {
        com.mindhub.todolist.models.User userEntity = userRepository.findByEmail(UserEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with UserEmail: " + UserEmail));
        return new User(userEntity.getUserName(), userEntity.getPassword(), AuthorityUtils.createAuthorityList(userEntity.getRoleType().toString()));
    }
}
