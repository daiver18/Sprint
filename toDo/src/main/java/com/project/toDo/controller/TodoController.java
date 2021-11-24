package com.project.toDo.controller;

import java.util.List;

import com.project.toDo.model.TodoModel;
import com.project.toDo.respositories.TodoRepositorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TodoController {
    @Autowired
    TodoRepositorie todoRepositorie;
    @GetMapping("/getAll")
    public String getAll() {
        return "Get All";
    }
    @GetMapping("/getAllTask")
    public List<TodoModel> getAllTask() {
        return todoRepositorie.findAll();
    }

}
