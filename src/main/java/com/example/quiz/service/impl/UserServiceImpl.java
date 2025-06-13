package com.example.quiz.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.example.quiz.dto.UserDto;
import com.example.quiz.model.Role;
import com.example.quiz.model.User;
import com.example.quiz.repositories.UserRepository;
import com.example.quiz.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	//Constuctor-based DI
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public List<UserDto> getAllUsers(){
		return userRepository.findAll()
				.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public UserDto getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.map(this::convertToDto).orElse(null);
	}
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = convertToEntity(userDto);
		User savedUser = userRepository.save(user);
		return convertToDto(savedUser);
	}
	
	private UserDto convertToDto(User user) {
		return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getName(), user.getRole(), user.getPassword());
	}
	
	private User convertToEntity(UserDto userDto) {
		return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), userDto.getName(), userDto.getRole() != null ? userDto.getRole() : Role.USER);
	}
}
