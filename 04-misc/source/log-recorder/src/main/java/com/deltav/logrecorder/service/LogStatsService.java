package com.deltav.logrecorder.service;

import com.deltav.logrecorder.dao.LogStatsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
public class LogStatsService {
    private static final Logger logger = LoggerFactory.getLogger(LogStatsService.class);
    private final DataSource dataSource;

    private static volatile boolean isRunning = false;
    private static final Object lock = new Object();
    private final LogStatsDao logStatsDao;

    @Autowired
    public LogStatsService(DataSource dataSource, LogStatsDao logStatsDao) {
        this.dataSource = dataSource;
        this.logStatsDao = logStatsDao;
    }

    @Scheduled(fixedRate = 600000) // 每10分钟执行一次
    public void generateHourlyStats() {
        if (isRunning) {
            return;
        }

        synchronized (lock) {
            if (isRunning) {
                return;
            }
            try {
                isRunning = true;
                executeStats();
            } finally {
                isRunning = false;
            }
        }
    }

    private void executeStats() {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusHours(1);

        try {
            logStatsDao.saveLogStats(startTime, endTime);
        } catch (SQLException e) {
            logger.error("统计数据保存失败");
            throw new RuntimeException(e);
        }
    }

}