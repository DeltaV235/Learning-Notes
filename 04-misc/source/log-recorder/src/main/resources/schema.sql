-- 日志表
CREATE TABLE IF NOT EXISTS logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL,
    operation_type VARCHAR(50) NOT NULL,
    level VARCHAR(20) NOT NULL,
    message TEXT NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    source VARCHAR(100),
    trace_id VARCHAR(100),
    INDEX idx_timestamp (timestamp),
    INDEX idx_user_id_type (user_id, operation_type)
);

-- 统计表
CREATE TABLE IF NOT EXISTS log_stats (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL,
    operation_type VARCHAR(50) NOT NULL,
    operation_count BIGINT NOT NULL,
    stats_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    INDEX idx_stats_time (stats_time),
    INDEX idx_user_id_type (user_id, operation_type)
); 