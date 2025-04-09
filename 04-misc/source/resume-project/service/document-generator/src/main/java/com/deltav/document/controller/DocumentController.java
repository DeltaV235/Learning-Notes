package com.deltav.document.controller;

import com.deltav.document.dto.ApiResponse;
import com.deltav.document.entity.Document;
import com.deltav.document.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    public ApiResponse<Document> createDocument(@RequestBody Document document) {
        return ApiResponse.success(documentService.saveDocument(document));
    }

    @GetMapping("/{docId}")
    public ApiResponse<Document> getDocument(@PathVariable String docId) {
        return ApiResponse.success(documentService.getDocumentById(docId));
    }

    @GetMapping
    public ApiResponse<List<Document>> getAllDocuments() {
        return ApiResponse.success(documentService.getAllDocuments());
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<Document>> getDocumentsByUserId(@PathVariable Integer userId) {
        return ApiResponse.success(documentService.getDocumentsByUserId(userId));
    }

    @PutMapping
    public ApiResponse<Document> updateDocument(@RequestBody Document document) {
        return ApiResponse.success(documentService.updateDocument(document));
    }

    @DeleteMapping("/{docId}")
    public ApiResponse<Void> deleteDocument(@PathVariable String docId) {
        documentService.deleteDocument(docId);
        return ApiResponse.success(null);
    }

    @GetMapping("/exists/{docId}")
    public ApiResponse<Boolean> existsById(@PathVariable String docId) {
        return ApiResponse.success(documentService.existsById(docId));
    }

    @GetMapping("/user/{userId}/exists")
    public ApiResponse<Boolean> existsByUserId(@PathVariable Integer userId) {
        return ApiResponse.success(documentService.existsByUserId(userId));
    }
} 