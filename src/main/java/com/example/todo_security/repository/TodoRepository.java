package com.example.todo_security.repository;

import com.example.todo_security.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo,Integer> {
}
