package com.project.toDo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class TodoModel {
    @Id
    private String id;
    private String title;

    public TodoModel(String id, String title) {
        this.id = id;
        this.title = title;
 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TodoModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

}
