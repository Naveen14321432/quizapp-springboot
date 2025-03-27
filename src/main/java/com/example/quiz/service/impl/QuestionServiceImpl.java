package com.example.quiz.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.quiz.dto.QuestionDto;
import com.example.quiz.model.Question;
import com.example.quiz.repositories.QuestionRepository;
import com.example.quiz.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	private final QuestionRepository questionRepository;
	//Constuctor-based DI
	public QuestionServiceImpl(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}
	
	@Override
	public List<QuestionDto> getAllQuestions(){
		return questionRepository.findAll()
				.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public QuestionDto getQuestionById(Long id) {
		Optional<Question> q = questionRepository.findById(id);
		return q.map(this::convertToDto).orElse(null);
	}
	
	
	@Override
	public QuestionDto createQuestion(QuestionDto q) {
		Question question = convertToEntity(q);
		Question savedQuestion = questionRepository.save(question);
		return convertToDto(savedQuestion);
	}
	
	
	
	public QuestionDto convertToDto(Question question) {
		return new QuestionDto(question.getId(), question.getQuestion(), question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD());
	}
	
	public Question convertToEntity(QuestionDto questionDto) {
		return new Question(questionDto.getId(), questionDto.getQuestion(), questionDto.getOptionA(), questionDto.getOptionB(), questionDto.getOptionC(), questionDto.getOptionD(), null);
	}

}
