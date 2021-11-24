package com.project.toDo.respositories;

import java.util.List;

import com.project.toDo.model.TodoModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepositorie extends MongoRepository<TodoModel, String> {
}
