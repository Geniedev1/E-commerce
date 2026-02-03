package com.example.demo.mapper;
import com.example.demo.model.User;
import com.example.demo.model.UserStatus;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.AdminCreateUserReponse;
import com.example.demo.dto.AdminCreateUserRequest;
import com.example.demo.dto.AuthRequest;
public class UserMapper {
       public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setStatus(user.getStatus());
        return userDTO;
    }
     public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setStatus(userDTO.getStatus());
        user.setRole(userDTO.getRole());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setPassword(userDTO. getPassword());
        return user;
 }
 public static User AuthtoEntity(AuthRequest authRequest) {
        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(authRequest.getPassword());
        return user;
    }
public static User AdminCreateUserRequesttoEntity(AdminCreateUserRequest adminCreateUserRequest) {
        User user = new User();
        user.setEmail(adminCreateUserRequest.getEmail());
        user.setPassword(adminCreateUserRequest.getPassword());
        user.setRole(adminCreateUserRequest.getRole());
        user.setStatus(adminCreateUserRequest.getStatus());
        return user;
    }
}
