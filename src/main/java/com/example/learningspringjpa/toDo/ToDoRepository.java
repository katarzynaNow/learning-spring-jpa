package com.example.learningspringjpa.toDo;

import com.example.learningspringjpa.toDo.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
}
