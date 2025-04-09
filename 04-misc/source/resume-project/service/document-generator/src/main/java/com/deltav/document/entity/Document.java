package com.deltav.document.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "document")
public class Document {

    @Id
    @Column(name = "doc_id", length = 10, nullable = false)
    private String docId;
    
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Lob
    @Column(name = "doc_binary", columnDefinition = "MEDIUMBLOB")
    private byte[] docBinary;
} 