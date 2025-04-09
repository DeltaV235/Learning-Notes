package com.deltav.questionservice.controller;

import com.deltav.questionservice.dto.QuestionWithAnswersDTO;
import com.deltav.questionservice.metrics.MetricsUtil;
import com.deltav.questionservice.service.QuestionService;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final MetricsUtil metricsUtil;

    @GetMapping("/with-answers")
    public ResponseEntity<List<QuestionWithAnswersDTO>> getAllQuestionsWithAnswers() {
        log.info("开始获取所有问题和答案");
        Timer.Sample sample = metricsUtil.startTimer();

        try {
            List<QuestionWithAnswersDTO> questions = questionService.getAllQuestionsWithAnswers();
            log.info("成功获取所有问题和答案，共 {} 个问题", questions.size());
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            log.error("获取问题和答案时发生错误", e);
            throw e;
        } finally {
            metricsUtil.stopTimer(sample, "get_all_questions_with_answers");
        }
    }
} 