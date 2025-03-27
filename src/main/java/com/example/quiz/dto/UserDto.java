package com.example.quiz.dto;

import com.example.quiz.model.Role;

public class UserDto {
	private Long id;
	private String username;
	private String email;
	private String name;
	private Role role;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public UserDto(Long id, String username, String email, String name, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.name = name;
		this.role = role;
	}
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
