package com.example.quiz.service;

import java.util.List;

import com.example.quiz.dto.UserDto;

public interface UserService {
	List<UserDto> getAllUsers();
	UserDto getUserById(Long id);
	UserDto createUser(UserDto user);
}
