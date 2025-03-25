package com.example.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quiz.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

}
