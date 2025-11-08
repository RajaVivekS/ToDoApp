package com.rajavivek.springboot.ToDoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajavivek.springboot.ToDoApp.entity.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

	List<ToDo> findByUsername(String username);

}
