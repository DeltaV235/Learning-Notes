package com.deltav.document.service.impl;

import com.deltav.document.entity.Document;
import com.deltav.document.repository.DocumentRepository;
import com.deltav.document.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Document getDocumentById(String docId) {
        return documentRepository.findById(docId)
                .orElseThrow(() -> new RuntimeException("文档未找到，ID: " + docId));
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public List<Document> getDocumentsByUserId(Integer userId) {
        return documentRepository.findByUserId(userId);
    }

    @Override
    public Document updateDocument(Document document) {
        // 确保文档存在
        if (!documentRepository.existsById(document.getDocId())) {
            throw new RuntimeException("无法更新，文档不存在，ID: " + document.getDocId());
        }
        return documentRepository.save(document);
    }

    @Override
    public void deleteDocument(String docId) {
        // 确保文档存在
        if (!documentRepository.existsById(docId)) {
            throw new RuntimeException("无法删除，文档不存在，ID: " + docId);
        }
        documentRepository.deleteById(docId);
    }

    @Override
    public boolean existsById(String docId) {
        return documentRepository.existsById(docId);
    }

    @Override
    public boolean existsByUserId(Integer userId) {
        return documentRepository.existsByUserId(userId);
    }
} 