package com.deltav.logrecorder.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "log_stats")
public class LogStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "operation_type", nullable = false)
    private String operationType;

    @Column(name = "operation_count", nullable = false)
    private Long operationCount;

    @Column(name = "stats_time", nullable = false)
    private LocalDateTime statsTime;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
} 