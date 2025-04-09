package com.deltav.questionservice.service;

import com.deltav.questionservice.entity.Question;
import com.deltav.questionservice.dto.QuestionWithAnswersDTO;
import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);
    Question getQuestionById(Long id);
    List<Question> getAllQuestions();
    Question updateQuestion(Long id, Question question);
    void deleteQuestion(Long id);
    List<QuestionWithAnswersDTO> getAllQuestionsWithAnswers();
} 