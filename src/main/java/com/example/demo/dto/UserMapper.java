package com.example.demo.dto;
import com.example.demo.model.User;
import com.example.demo.dto.UserResponse;
public class UserMapper {
    public static UserResponse toDTO(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setStatus(user.getStatus());
        return dto;
    }
}
