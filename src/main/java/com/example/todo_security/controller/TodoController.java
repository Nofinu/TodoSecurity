package com.example.todo_security.controller;

import com.example.todo_security.exception.NotFoundException;
import com.example.todo_security.model.Todo;
import com.example.todo_security.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
//@PreAuthorize("hasRole('USER')")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("")
    public ResponseEntity<List<Todo>> getAllTodo (){
        return new ResponseEntity<>(todoService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Todo> createTodo (@RequestBody Todo todo){
        if(todo != null){
            Todo todoSave = todoService.save(todo);
            return new ResponseEntity<>(todoSave,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> findTodo (@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(todoService.findById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo (@PathVariable int id, @RequestBody Todo todo) throws NotFoundException {
        return new ResponseEntity<>(todoService.update(todo,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo (@PathVariable int id) throws NotFoundException {
        if(todoService.delete(id)){
            return new ResponseEntity<>("todo supprimé",HttpStatus.OK);
        }
        return new ResponseEntity<>("error durring deletion",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/{id}/{status}")
    public ResponseEntity<Todo> updateStatus (@PathVariable int id,@PathVariable String status) throws NotFoundException {
        if(status.equals("complete")){
            return new ResponseEntity<>(todoService.updateStatus(id,true),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(todoService.updateStatus(id,false),HttpStatus.OK);
        }
    }
}
