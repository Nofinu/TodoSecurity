package com.example.todo_security.service.Impl;

import com.example.todo_security.model.User;
import com.example.todo_security.model.UserDetailImpl;
import com.example.todo_security.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRespository userRespository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRespository.findByUsername(username);
        return UserDetailImpl.build(user);
    }
}
