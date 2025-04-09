package com.deltav.document.service;

import com.deltav.document.entity.User;
import java.util.List;

public interface UserService {
    
    User saveUser(User user);
    
    User getUserById(Long id);
    
    User getUserByUsername(String username);
    
    List<User> getAllUsers();
    
    User updateUser(User user);
    
    void deleteUser(Long id);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
} 