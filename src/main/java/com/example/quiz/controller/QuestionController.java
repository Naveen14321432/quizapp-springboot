package com.example.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.dto.QuestionDto;
import com.example.quiz.service.QuestionService;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
	private final QuestionService questionService;
	
	public QuestionController(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<QuestionDto>> getAllQs() {
	    List<QuestionDto> questions = questionService.getAllQuestions();
	    return ResponseEntity.ok(questions);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<QuestionDto> getQid(@PathVariable Long id){
		QuestionDto question = questionService.getQuestionById(id);
		if(question != null) {
			return ResponseEntity.ok(question);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addQuestion")
	public ResponseEntity<QuestionDto> createQ(@RequestBody QuestionDto questionDto){
		QuestionDto createdQ = questionService.createQuestion(questionDto);
		return ResponseEntity.ok(createdQ);
	}
}
