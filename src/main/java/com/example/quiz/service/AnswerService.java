package com.example.quiz.service;

import java.util.List;

import com.example.quiz.dto.AnswerDto;

public interface AnswerService {
	AnswerDto saveAnswer(AnswerDto answerDto);
	List<AnswerDto> getAnswersByUserId(Long userId);
	List<AnswerDto> getAnswersByQuestionId(Long questionId);
	AnswerDto getAnswerById(Long answerId);
	void deleteAnswer(Long id);
}
