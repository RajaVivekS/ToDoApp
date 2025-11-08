package com.rajavivek.springboot.ToDoApp.controller.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rajavivek.springboot.ToDoApp.entity.ToDo;
import com.rajavivek.springboot.ToDoApp.service.ToDoService;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class ToDoController {
	private ToDoService toDoService;
	
	
	
	
	public ToDoController(ToDoService toDoService) {
		super();
		this.toDoService = toDoService;
	}




	@RequestMapping("list-todos")
	public String listToDo(ModelMap model) {
		
		String username = getLoggedInUsername(model);
		
		List<ToDo> todos = toDoService.findByUsername(username);
		
		model.put("todos",todos);
		return "listToDo";
	}




	
	
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showAddTodo(ModelMap model) {
		String username = getLoggedInUsername(model);
		ToDo todo = new ToDo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	} 
	
	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addTodo( ModelMap model, @Valid @ModelAttribute("todo") ToDo todo, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("todo", todo);
			return "todo";
		}
		String username = getLoggedInUsername(model);
		toDoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos";
	}
	
	
	@RequestMapping(value="delete-todo")
	public String deleteTodo(@RequestParam int id) {
		toDoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo", method = RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id, ModelMap model) {
		ToDo todo = toDoService.findById(id);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String updateTodo( ModelMap model, @Valid @ModelAttribute("todo") ToDo todo, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("todo", todo);
			return "todo";
		}
		toDoService.updateToDo(todo);
		return "redirect:list-todos";
	}
	
	
	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
}
