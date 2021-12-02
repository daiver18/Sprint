package com.project.toDo.service;

import com.project.toDo.respositories.TodoRepositorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    TodoRepositorie todoRepositorie;
}
