package com.deltav.questionservice.repository;

import com.deltav.questionservice.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long questionId);
    List<Answer> findByQuestionIdOrderBySequenceAsc(Long questionId);
} 