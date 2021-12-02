package com.project.toDo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.toDo.excepcion.RecursoNoEncontrado;
import com.project.toDo.model.UserModel;
import com.project.toDo.respositories.UserRepositorie;

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

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepositorie userRep;

    @GetMapping("/users")
    public List<UserModel> getAll() {
        return userRep.findAll();
    }

    @PostMapping("/users")
    public UserModel createUser(@RequestBody UserModel newUser) {
        return userRep.save(newUser);

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable(value = "id") String id,
            @RequestBody UserModel userDetails) throws RecursoNoEncontrado {
        UserModel user = userRep.findById(id)
                .orElseThrow(() -> new RecursoNoEncontrado("user no encontrado con el id :: " + id));

        user.setName(userDetails.getName());
        final UserModel updatedUser = userRep.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") String id)
    throws RecursoNoEncontrado {
        UserModel user = userRep.findById(id).orElseThrow(() -> new RecursoNoEncontrado("user no encontrado con el id :: " + id));

        userRep.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        return response;
    }
}
