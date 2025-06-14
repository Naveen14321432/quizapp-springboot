package com.example.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.dto.AnswerDto;
import com.example.quiz.service.AnswerService;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {
	
	private final AnswerService answerService;
	
	public AnswerController(AnswerService answerService) {
		this.answerService = answerService;
	}
	
	@PostMapping("/submit")
	public ResponseEntity<AnswerDto> submitAnswer(@RequestBody AnswerDto answerDto){
		AnswerDto saved = answerService.saveAnswer(answerDto);
		return ResponseEntity.ok(saved);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<AnswerDto>> getAnswerByUser(@PathVariable Long userId){
		List<AnswerDto> answers = answerService.getAnswersByUserId(userId);
		return ResponseEntity.ok(answers);
	}
	
	@GetMapping("/question/{questionId}")
	public ResponseEntity<List<AnswerDto>> getAnswerByQuestion(@PathVariable Long questionId){
		List<AnswerDto> answers = answerService.getAnswersByQuestionId(questionId);
		return ResponseEntity.ok(answers);
	}
	
	@GetMapping("/answer/{answerId}")
	public ResponseEntity<AnswerDto> getAnswer(@PathVariable Long answerId){
		AnswerDto answer = answerService.getAnswerById(answerId);
		return ResponseEntity.ok(answer);
	}
	
	@DeleteMapping("/{answerId}")
	public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId){
		answerService.deleteAnswer(answerId);
		return ResponseEntity.noContent().build();
	}
	
}
