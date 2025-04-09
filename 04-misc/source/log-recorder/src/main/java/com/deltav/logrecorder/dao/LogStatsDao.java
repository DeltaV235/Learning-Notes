package com.deltav.logrecorder.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class LogStatsDao {
    private static final Logger logger = LoggerFactory.getLogger(LogStatsDao.class);
    private final DataSource dataSource;

    @Autowired
    public LogStatsDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String INSERT_STATS_SQL = """
        INSERT INTO log_stats (user_id, operation_type, operation_count, stats_time, created_at)
        SELECT 
            l.user_id,
            l.operation_type,
            COUNT(*) as operation_count,
            ? as stats_time,
            NOW() as created_at
        FROM logs l
        WHERE l.timestamp BETWEEN ? AND ?
        GROUP BY l.user_id, l.operation_type
        """;

    public int saveLogStats(LocalDateTime startTime, LocalDateTime endTime) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                int affectedRows = executeInsertStats(conn, startTime, endTime);
                conn.commit();
                logger.info("统计数据保存成功，插入 {} 条记录", affectedRows);
                return affectedRows;
            } catch (SQLException e) {
                conn.rollback();
                logger.error("保存统计数据失败", e);
                throw e;
            }
        }
    }

    private int executeInsertStats(Connection conn, LocalDateTime startTime, LocalDateTime endTime) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(INSERT_STATS_SQL)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(endTime));
            pstmt.setTimestamp(2, Timestamp.valueOf(startTime));
            pstmt.setTimestamp(3, Timestamp.valueOf(endTime));
            return pstmt.executeUpdate();
        }
    }
} 