package com.example.demo.controller;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdminCreateUserRequest;
import com.example.demo.dto.ApiResponse;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    UserService userService;
    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/{id}/activate")
    public ApiResponse<Void> activateUser(@PathVariable Long id) {
        userService.activateUser(id);
        return new ApiResponse<>("success", "User activated", null);
    }

    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> createUserByAdmin(@RequestBody AdminCreateUserRequest adminCreateUserRequest) {
        userService.createUserByAdmin(adminCreateUserRequest);
        return new ApiResponse<>("success", "User created", null);
    }
}