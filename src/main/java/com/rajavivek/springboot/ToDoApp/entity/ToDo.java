package com.rajavivek.springboot.ToDoApp.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ToDo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	
	@Size(min=10, message="Please enter atleast 10 characters")
	private String description;
	private LocalDate targetDate;
	private boolean done;
	
	
	@Override
	public String toString() {
		return "ToDo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", done=" + done + "]";
	}
	
	
}
