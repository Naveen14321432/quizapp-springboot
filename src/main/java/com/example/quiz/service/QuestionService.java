package com.example.quiz.service;

import java.util.List;

import com.example.quiz.dto.QuestionDto;

public interface QuestionService {
	List<QuestionDto> getAllQuestions();
	QuestionDto getQuestionById(Long id);
	QuestionDto createQuestion(QuestionDto question);
}
