package com.deltav.logrecorder.controller;

import com.deltav.logrecorder.model.Log;
import com.deltav.logrecorder.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Log>> saveLogs(@RequestBody List<Log> logs) {
        List<Log> savedLogs = logService.saveLogs(logs);
        return ResponseEntity.ok(savedLogs);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Log>> queryUserLogs(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        List<Log> logs = logService.queryUserLogs(userId, startTime, endTime);
        return ResponseEntity.ok(logs);
    }
} 