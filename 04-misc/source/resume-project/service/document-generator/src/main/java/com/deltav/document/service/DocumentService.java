package com.deltav.document.service;

import com.deltav.document.entity.Document;

import java.util.List;

public interface DocumentService {
    
    // 创建新文档
    Document saveDocument(Document document);
    
    // 根据ID获取文档
    Document getDocumentById(String docId);
    
    // 获取所有文档
    List<Document> getAllDocuments();
    
    // 根据用户ID获取文档
    List<Document> getDocumentsByUserId(Integer userId);
    
    // 更新文档
    Document updateDocument(Document document);
    
    // 删除文档
    void deleteDocument(String docId);
    
    // 检查文档是否存在
    boolean existsById(String docId);
    
    // 检查用户是否有文档
    boolean existsByUserId(Integer userId);
} 