package com.example.todo_security.repository;

import com.example.todo_security.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends CrudRepository<User,Integer> {
    public User findByUsername (String username);
}
