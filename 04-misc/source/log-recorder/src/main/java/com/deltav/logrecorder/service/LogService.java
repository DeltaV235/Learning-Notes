package com.deltav.logrecorder.service;

import com.deltav.logrecorder.dao.LogDao;
import com.deltav.logrecorder.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogService {

    private final LogDao logDao;

    @Autowired
    public LogService(LogDao logDao) {
        this.logDao = logDao;
    }

    @Transactional
    public List<Log> saveLogs(List<Log> logs) {
        try {
            logDao.batchInsertLogs(logs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Log> queryUserLogs(String userId, LocalDateTime startTime, LocalDateTime endTime) {
        try {
            return logDao.queryUserLogs(userId, startTime, endTime);
        } catch (SQLException e) {
            throw new RuntimeException("查询用户日志失败", e);
        }
    }
} 