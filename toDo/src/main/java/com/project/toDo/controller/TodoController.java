package com.project.toDo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.toDo.excepcion.RecursoNoEncontrado;
import com.project.toDo.model.TodoModel;
import com.project.toDo.respositories.TodoRepositorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class TodoController {
    @Autowired
    TodoRepositorie todoRepositorie;

    @GetMapping("/tasks")
    public List<TodoModel> getAll() {
        return todoRepositorie.findAll();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TodoModel> getById(@PathVariable(value = "id") String id)
            throws RecursoNoEncontrado {
        TodoModel todoModel = todoRepositorie.findById(id)
                .orElseThrow(() -> new RecursoNoEncontrado("Task not found on :: " + id));
        return ResponseEntity.ok().body(todoModel);
    }

    @GetMapping("/tasks/createdBy/{createdBy}")
    public List<TodoModel> getBycreator(@PathVariable(value = "createdBy") String createdBy) {
        return todoRepositorie.findBycreatedBy(createdBy);
    }

    @PostMapping("/task")
    public TodoModel createTask(@RequestBody TodoModel newTodo) {
        return todoRepositorie.save(newTodo);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<TodoModel> updateTask(@PathVariable(value = "id") String id,
            @RequestBody TodoModel todoDetails) throws RecursoNoEncontrado {
        TodoModel todo = todoRepositorie.findById(id)
                .orElseThrow(() -> new RecursoNoEncontrado("task no encontrado con el id :: " + id));

        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setDone(todoDetails.getDone());
        todo.setCreatedBy(todoDetails.getCreatedBy());
        final TodoModel updatedtask = todoRepositorie.save(todo);
        return ResponseEntity.ok(updatedtask);
    }

    @DeleteMapping("/task/{id}")
    public Map<String, Boolean> deleteTask(@PathVariable(value = "id") String id)
    throws RecursoNoEncontrado {
        TodoModel todo = todoRepositorie.findById(id).orElseThrow(() -> new RecursoNoEncontrado("task no encontrado con el id :: " + id));

        todoRepositorie.delete(todo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        return response;
    }

}
