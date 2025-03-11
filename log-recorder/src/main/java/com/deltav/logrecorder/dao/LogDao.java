package com.deltav.logrecorder.dao;

import com.deltav.logrecorder.model.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LogDao {
    private static final Logger logger = LoggerFactory.getLogger(LogDao.class);
    private final DataSource dataSource;

    private static final String QUERY_LOGS_SQL = """
            SELECT id, user_id, operation_type, level, message, timestamp, source, trace_id
            FROM logs
            WHERE user_id = ? AND timestamp BETWEEN ? AND ?
            ORDER BY timestamp ASC
            """;

    private static final String INSERT_LOG_SQL = """
            INSERT INTO logs (user_id, operation_type, level, message, timestamp, source, trace_id)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    @Autowired
    public LogDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Log> queryUserLogs(String userId, LocalDateTime startTime, LocalDateTime endTime) throws SQLException {
        List<Log> logs = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(QUERY_LOGS_SQL)) {

            pstmt.setString(1, userId);
            pstmt.setTimestamp(2, Timestamp.valueOf(startTime));
            pstmt.setTimestamp(3, Timestamp.valueOf(endTime));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Log log = new Log();
                    log.setId(rs.getLong("id"));
                    log.setUserId(rs.getString("user_id"));
                    log.setOperationType(rs.getString("operation_type"));
                    log.setLevel(rs.getString("level"));
                    log.setMessage(rs.getString("message"));
                    log.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
                    log.setSource(rs.getString("source"));
                    log.setTraceId(rs.getString("trace_id"));
                    logs.add(log);
                }
            }
        } catch (SQLException e) {
            logger.error("查询用户日志失败", e);
            throw e;
        }

        return logs;
    }

    public int batchInsertLogs(List<Log> logs) throws SQLException {
        if (logs == null || logs.isEmpty()) {
            return 0;
        }

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmt = conn.prepareStatement(INSERT_LOG_SQL)) {
                int batchSize = 1000; // 每批处理的记录数
                int totalCount = 0;

                for (int i = 0; i < logs.size(); i++) {
                    Log log = logs.get(i);
                    setLogParameters(pstmt, log);
                    pstmt.addBatch();

                    // 当达到批处理大小时执行
                    if ((i + 1) % batchSize == 0 || i == logs.size() - 1) {
                        int[] results = pstmt.executeBatch();
                        for (int result : results) {
                            totalCount += result;
                        }
                        pstmt.clearBatch();
                    }
                }

                conn.commit();
                logger.info("批量插入日志完成，共插入 {} 条记录", totalCount);
                return totalCount;
            } catch (SQLException e) {
                conn.rollback();
                logger.error("批量插入日志失败", e);
                throw e;
            }
        }
    }

    /**
     * 设置日志参数
     */
    private void setLogParameters(PreparedStatement pstmt, Log log) throws SQLException {
        pstmt.setString(1, log.getUserId());
        pstmt.setString(2, log.getOperationType());
        pstmt.setString(3, log.getLevel());
        pstmt.setString(4, log.getMessage());
        pstmt.setTimestamp(5, Timestamp.valueOf(log.getTimestamp()));
        pstmt.setString(6, log.getSource());
        pstmt.setString(7, log.getTraceId());
    }
}