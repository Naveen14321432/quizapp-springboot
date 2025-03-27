package com.example.quiz.dto;

public class AnswerDto {
	private Long id;
    private Long userId;
    private Long questionId;
    private String selectedOption;
    private boolean isCorrect;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	public AnswerDto(Long id, Long userId, Long questionId, String selectedOption, boolean isCorrect) {
		super();
		this.id = id;
		this.userId = userId;
		this.questionId = questionId;
		this.selectedOption = selectedOption;
		this.isCorrect = isCorrect;
	}
	
	public AnswerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
