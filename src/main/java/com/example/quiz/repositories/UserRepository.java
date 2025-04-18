package com.example.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
