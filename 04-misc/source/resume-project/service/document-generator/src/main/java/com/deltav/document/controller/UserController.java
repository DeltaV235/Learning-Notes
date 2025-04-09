package com.deltav.document.controller;

import com.deltav.document.dto.ApiResponse;
import com.deltav.document.entity.User;
import com.deltav.document.service.UserService;
import io.micrometer.core.instrument.Counter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Counter requestCounter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<User> createUser(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ApiResponse.error(400, "用户名已存在");
        }
        if (userService.existsByEmail(user.getEmail())) {
            return ApiResponse.error(400, "邮箱已存在");
        }
        return ApiResponse.success(userService.saveUser(user));
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable Long id) {
        try {
            return ApiResponse.success(userService.getUserById(id));
        } catch (RuntimeException e) {
            return ApiResponse.error(404, "用户不存在");
        }
    }

    @GetMapping
    public ApiResponse<List<User>> getAllUsers() {
        requestCounter.increment();
        return ApiResponse.success(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User existingUser = userService.getUserById(id);
            user.setId(id);
            return ApiResponse.success(userService.updateUser(user));
        } catch (RuntimeException e) {
            return ApiResponse.error(404, "用户不存在");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.getUserById(id);
            userService.deleteUser(id);
            return ApiResponse.success(null);
        } catch (RuntimeException e) {
            return ApiResponse.error(404, "用户不存在");
        }
    }
} 