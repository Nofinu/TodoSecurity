package com.example.todo_security.service;

import com.example.todo_security.exception.NotFoundException;
import com.example.todo_security.model.Todo;
import com.example.todo_security.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Todo save (Todo todo){
        return todoRepository.save(todo);
    }
    public Todo update (Todo todo,int id) throws NotFoundException {
        Todo todoFind = findById(id);
        todoFind.setDescription(todo.getDescription());
        todoFind.setTitle(todo.getTitle());
        return todoRepository.save(todoFind);
    }

    public boolean delete (int id) throws NotFoundException {
        Todo todo = findById(id);
        todoRepository.delete(todo);
        return true;
    }

    public Todo updateStatus(int id,boolean status) throws NotFoundException {
        Todo todo = findById(id);
        todo.setComplete(status);
        return todoRepository.save(todo);
    }

    public Todo findById (int id) throws NotFoundException {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if(todoOptional.isPresent()){
            return todoOptional.get();
        }
        throw new NotFoundException();
    }

    public List<Todo> findAll (){
        return (List<Todo>) todoRepository.findAll();
    }
}
