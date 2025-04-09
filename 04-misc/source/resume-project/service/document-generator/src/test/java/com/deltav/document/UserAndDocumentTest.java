package com.deltav.document;

import com.deltav.document.entity.Document;
import com.deltav.document.entity.User;
import com.deltav.document.service.DocumentService;
import com.deltav.document.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserAndDocumentTest {

    private static final long MAX_FILE_SIZE = 16 * 1024 * 1024; // 16MB (MEDIUMBLOB 限制)

    @Autowired
    private UserService userService;

    @Autowired
    private DocumentService documentService;

    @Test
    public void testCreateUser() {
        // 创建随机用户数据
        User user = new User();
        String randomUsername = "user_" + UUID.randomUUID().toString().substring(0, 8);
        user.setUsername(randomUsername);
        user.setPassword("password123"); // 实际应用中应该加密
        user.setEmail(randomUsername + "@example.com");
        user.setActive(true);

        // 保存用户
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser.getId());
        assertEquals(randomUsername, savedUser.getUsername());
        assertTrue(savedUser.isActive());
    }

    @Test
    public void testCreateDocument() throws IOException {
        // 尝试从数据库获取用户
        List<User> users = userService.getAllUsers();
        Long userId;
        
        if (users.isEmpty()) {
            // 如果没有用户，创建一个新用户
            User user = new User();
            String randomUsername = "doc_user_" + UUID.randomUUID().toString().substring(0, 8);
            user.setUsername(randomUsername);
            user.setPassword("password123");
            user.setEmail(randomUsername + "@example.com");
            user.setActive(true);
            User savedUser = userService.saveUser(user);
            userId = savedUser.getId();
        } else {
            // 使用第一个用户
            userId = users.get(0).getId();
        }

        // 读取本地PDF文件
        File pdfFile = ResourceUtils.getFile("classpath:pdf/RabbitMQ.pdf");
        long fileSize = pdfFile.length();
        assertTrue(fileSize <= MAX_FILE_SIZE, 
                String.format("测试文件大小 (%d bytes) 超过了最大限制 (%d bytes)", fileSize, MAX_FILE_SIZE));
        
        byte[] pdfContent = Files.readAllBytes(pdfFile.toPath());

        // 创建文档记录
        Document document = new Document();
        // 生成10位的文档ID
        String docId = String.format("%010d", System.currentTimeMillis() % 10000000000L);
        document.setDocId(docId);
        document.setUserId(userId);
        document.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        document.setDocBinary(pdfContent);

        // 保存文档
        Document savedDocument = documentService.saveDocument(document);
        assertNotNull(savedDocument.getDocId());
        assertEquals(10, savedDocument.getDocId().length(), "文档ID长度必须为10");
        assertEquals(userId, savedDocument.getUserId());
        assertArrayEquals(pdfContent, savedDocument.getDocBinary());
    }
} 