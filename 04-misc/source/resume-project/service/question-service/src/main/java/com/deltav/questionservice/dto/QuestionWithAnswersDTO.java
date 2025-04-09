package com.deltav.questionservice.dto;

import com.deltav.questionservice.entity.Answer;
import com.deltav.questionservice.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWithAnswersDTO {
    private Long questionId;
    private String content;
    private Integer sequence;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<AnswerDTO> answers;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerDTO {
        private Long answerId;
        private String content;
        private Integer sequence;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    public static QuestionWithAnswersDTO from(Question question, List<Answer> answers) {
        return QuestionWithAnswersDTO.builder()
                .questionId(question.getQuestionId())
                .content(question.getContent())
                .sequence(question.getSequence())
                .createdAt(question.getCreatedAt())
                .updatedAt(question.getUpdatedAt())
                .answers(answers.stream()
                        .map(answer -> AnswerDTO.builder()
                                .answerId(answer.getAnswerId())
                                .content(answer.getContent())
                                .sequence(answer.getSequence())
                                .createdAt(answer.getCreatedAt())
                                .updatedAt(answer.getUpdatedAt())
                                .build())
                        .toList())
                .build();
    }
} 