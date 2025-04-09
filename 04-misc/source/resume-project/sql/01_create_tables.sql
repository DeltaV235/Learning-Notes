-- 创建问题表
CREATE TABLE IF NOT EXISTS questions (
    question_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(200) NOT NULL,
    sequence INT NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
);

-- 创建答案表
CREATE TABLE IF NOT EXISTS answers (
    answer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(200) NOT NULL,
    sequence INT NOT NULL,
    fk_question_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
); 