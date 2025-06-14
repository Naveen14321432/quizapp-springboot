package com.example.quiz.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.quiz.dto.QuestionDto;
import com.example.quiz.model.Question;
import com.example.quiz.repositories.AnswerRepository;
import com.example.quiz.repositories.QuestionRepository;
import com.example.quiz.service.QuestionService;

import jakarta.transaction.Transactional;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	private final QuestionRepository questionRepository;
	
	private final AnswerRepository answerRepository;
	
	public QuestionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository) {
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
	}
	
	public List<QuestionDto> getAllQuestions() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   // System.out.println("Authorities: " + auth.getAuthorities());

	    boolean isAdmin = auth.getAuthorities().stream()
	                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

	    return questionRepository.findAll().stream()
	            .map(q -> {
	                QuestionDto dto = convertToDto(q);
	                if (!isAdmin) {
	                    dto.setCorrectAnswer(null); // hide answer for USER
	                }
	                return dto;
	            }).collect(Collectors.toList());
	}

	
	@Override
	public QuestionDto getQuestionById(Long id) {
	    Optional<Question> q = questionRepository.findById(id);

	    if (q.isPresent()) {
	        QuestionDto dto = convertToDto(q.get());

	        // Get current authentication
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	        // Check if the user is not admin, then hide correct answer
	        boolean isAdmin = auth.getAuthorities().stream()
	                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

	        if (!isAdmin) {
	            dto.setCorrectAnswer(null);
	        }

	        return dto;
	    }

	    return null;
	}

	@Override
	public QuestionDto createQuestion(QuestionDto q) {
		Question question = convertToEntity(q);
		Question savedQuestion = questionRepository.save(question);
		return convertToDto(savedQuestion);
	}
	
	@Override
	public QuestionDto updateQuestion(QuestionDto questionDto) {
		Optional<Question> existing = questionRepository.findById(questionDto.getId());
		if(existing.isPresent()) {
			Question question = existing.get();
			question.setQuestion(questionDto.getQuestion());
	        question.setOptionA(questionDto.getOptionA());
	        question.setOptionB(questionDto.getOptionB());
	        question.setOptionC(questionDto.getOptionC());
	        question.setOptionD(questionDto.getOptionD());
	        question.setCorrectAnswer(questionDto.getCorrectAnswer());
	        
	        Question updated = questionRepository.save(question);
	        return convertToDto(updated);
		} else {
			throw new RuntimeException("Question not found with ID : " + questionDto.getId());
		}
	}
	
	@Override
	@Transactional
	public void deleteQuestion(Long id) {
		if(!questionRepository.existsById(id)) {
			throw new RuntimeException("Question not found with ID : " + id);
		} else {
			answerRepository.deleteByQuestions_Id(id);
			questionRepository.deleteById(id);
		}
	}
	
	public QuestionDto convertToDto(Question question) {
		return new QuestionDto(question.getId(), question.getQuestion(), question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD(), question.getCorrectAnswer());
	}
	
	public Question convertToEntity(QuestionDto questionDto) {
		return new Question(questionDto.getId(), questionDto.getQuestion(), questionDto.getOptionA(), questionDto.getOptionB(), questionDto.getOptionC(), questionDto.getOptionD(), questionDto.getCorrectAnswer());
	}

}
