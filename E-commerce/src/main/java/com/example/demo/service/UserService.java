package com.example.demo.service;
import com.example.demo.model.User;
import java.util.List;
import com.example.demo.dto.AdminCreateUserReponse;
import com.example.demo.dto.AdminCreateUserRequest;
import com.example.demo.model.Role;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.UserDTO;

public interface UserService {
    void registerUser(AuthRequest authRequest);
    void createUserByAdmin(AdminCreateUserRequest adminCreateUserRequest);
    List<UserDTO> getAll();
    List<UserDTO> getByRole(Role role);
    void checkUser(Long userId);
    void activateUser(Long userId);
}