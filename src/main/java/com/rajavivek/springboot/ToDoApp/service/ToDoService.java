package com.rajavivek.springboot.ToDoApp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.rajavivek.springboot.ToDoApp.entity.ToDo;

import jakarta.validation.Valid;

@Service
public class ToDoService {

	private static List<ToDo> todos = new ArrayList<>();
	private static int todosCount=0;

	static {
		todos.add(new ToDo(++todosCount, "Raja", "Learn AI", LocalDate.now().plusYears(1), false));
		todos.add(new ToDo(++todosCount, "Raja", "Learn Frontend", LocalDate.now().plusYears(1), false));
		todos.add(new ToDo(++todosCount, "Raja", "Learn Backend", LocalDate.now().plusYears(1), false));
	}
	
	public List<ToDo> findByUsername (String username){
		
		Predicate<? super ToDo> predicate = (todo) -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean isDone) {
		
		ToDo todo = new ToDo(++todosCount, username, description, targetDate, isDone);
		todos.add(todo);
	}

	public void deleteById(int id) {
		
		Predicate<? super ToDo> predicate = (todo) -> todo.getId()==id;
		todos.removeIf(predicate);
		
	}

	public ToDo findById(int id) {
		Predicate<? super ToDo> predicate = (todo) -> todo.getId()==id;
		return todos.stream().filter(predicate).findFirst().get();
	}

	

	public void updateToDo(@Valid ToDo todo) {
		deleteById(todo.getId());
		todos.add(todo);
		
	}

}
