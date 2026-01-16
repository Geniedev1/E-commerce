package com.example.demo.service;
import com.example.demo.model.User;
import java.util.List;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.model.Role;
public interface UserService {
   public User create(CreateUserRequest requestUser);
   public List<User> getAll(); 
   public List<User> getByRole(String role);
   public void checkUser(String userId);
   public void activeUser(Long userId);
}