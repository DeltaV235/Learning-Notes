package com.deltav.document.repository;

import com.deltav.document.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsername(String username);
    
    User findByEmail(String email);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
} 