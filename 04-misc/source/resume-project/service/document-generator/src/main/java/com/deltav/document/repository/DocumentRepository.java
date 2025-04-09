package com.deltav.document.repository;

import com.deltav.document.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {
    
    // 根据用户ID查找文档
    List<Document> findByUserId(Integer userId);
    
    // 检查用户是否有文档
    boolean existsByUserId(Integer userId);
} 