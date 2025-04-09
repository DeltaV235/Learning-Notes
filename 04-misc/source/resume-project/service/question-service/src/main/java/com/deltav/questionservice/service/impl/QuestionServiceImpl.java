package com.deltav.questionservice.service.impl;

import com.deltav.questionservice.dto.QuestionWithAnswersDTO;
import com.deltav.questionservice.entity.Answer;
import com.deltav.questionservice.entity.Question;
import com.deltav.questionservice.repository.AnswerRepository;
import com.deltav.questionservice.repository.QuestionRepository;
import com.deltav.questionservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    @CacheEvict(value = "questionsWithAnswers", allEntries = true)
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    @CacheEvict(value = "questionsWithAnswers", allEntries = true)
    public Question updateQuestion(Long id, Question questionDetails) {
        Question question = getQuestionById(id);
        return questionRepository.save(Question.builder()
                .questionId(question.getQuestionId())
                .content(questionDetails.getContent())
                .sequence(questionDetails.getSequence())
                .createdAt(question.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build());
    }

    @Override
    @CacheEvict(value = "questionsWithAnswers", allEntries = true)
    public void deleteQuestion(Long id) {
        Question question = getQuestionById(id);
        questionRepository.delete(question);
    }

    @Override
    @Cacheable(value = "questionsWithAnswers", key = "'all'")
    public List<QuestionWithAnswersDTO> getAllQuestionsWithAnswers() {
        List<Question> questions = questionRepository.findAllByOrderBySequenceAsc();
        return questions.stream()
                .map(question -> {
                    List<Answer> answers = answerRepository.findByQuestionIdOrderBySequenceAsc(question.getQuestionId());
                    return QuestionWithAnswersDTO.from(question, answers);
                })
                .collect(Collectors.toList());
    }
} 