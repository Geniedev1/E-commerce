package com.example.demo.service;
import com.example.demo.model.User;
import java.util.List;
import com.example.demo.model.Role;
import com.example.demo.dto.UserDTO;
public interface UserService {
   public UserDTO create(UserDTO userDTO);
   public List<UserDTO> getAll(); 
   public List<UserDTO> getByRole(Role role);
   public void checkUser(Long userId);
   public void activateUser(Long userId);
}