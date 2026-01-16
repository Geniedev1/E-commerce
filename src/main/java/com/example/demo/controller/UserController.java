package com.example.demo.controller;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UserMapper;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
@RestController
public class UserController {
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
@PostMapping("/users")
@ResponseStatus(HttpStatus.CREATED)
public UserResponse createUser(@Valid @RequestBody CreateUserRequest requestUser) {
    
    User user = userService.create(requestUser);
    return UserMapper.toDTO(user);
  }
@PostMapping("/users/{id}/activate")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void activateUser(@PathVariable Long id) {
    userService.activeUser(id);
  }
}
