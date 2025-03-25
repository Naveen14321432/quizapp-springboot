package com.example.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quiz.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
