package com.example.quiz.service.impl;

import com.example.quiz.dto.AnswerDto;
import com.example.quiz.model.Answer;
import com.example.quiz.model.Question;
import com.example.quiz.model.User;
import com.example.quiz.repositories.AnswerRepository;
import com.example.quiz.repositories.QuestionRepository;
import com.example.quiz.repositories.UserRepository;
import com.example.quiz.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public AnswerDto saveAnswer(AnswerDto answerDto) {
        User user = userRepository.findById(answerDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Question question = questionRepository.findById(answerDto.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Answer answer = new Answer();
        answer.setUser(user);
        answer.setQuestions(question);
        answer.setSelectedOption(answerDto.getSelectedOption());
        answer.setCorrect(answerDto.isCorrect());

        Answer savedAnswer = answerRepository.save(answer);

        return new AnswerDto(savedAnswer.getId(), user.getId(), question.getId(), savedAnswer.getSelectedOption(), savedAnswer.isCorrect());
    }

    @Override
    public List<AnswerDto> getAnswersByUserId(Long userId) {
        List<Answer> answers = answerRepository.findByUserId(userId);
        return answers.stream()
                .map(answer -> new AnswerDto(answer.getId(), answer.getUser().getId(), answer.getQuestions().getId(), answer.getSelectedOption(), answer.isCorrect()))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnswerDto> getAnswersByQuestionId(Long questionId) {
        List<Answer> answers = answerRepository.findByQuestions_Id(questionId);
        return answers.stream()
                .map(answer -> new AnswerDto(answer.getId(), answer.getUser().getId(), answer.getQuestions().getId(), answer.getSelectedOption(), answer.isCorrect()))
                .collect(Collectors.toList());
    }

    @Override
    public AnswerDto getAnswerById(Long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found"));

        return new AnswerDto(answer.getId(), answer.getUser().getId(), answer.getQuestions().getId(), answer.getSelectedOption(), answer.isCorrect());
    }

    @Override
    public void deleteAnswer(Long answerId) {
        answerRepository.deleteById(answerId);
    }
}
