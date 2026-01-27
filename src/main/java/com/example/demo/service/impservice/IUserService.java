package com.example.demo.service.impservice;
import com.example.demo.model.User;
import com.example.demo.resposity.UserRepository;
import com.example.demo.service.FirstOrderService;
import com.example.demo.service.UserService;
import com.example.demo.service.validateentity.CheckerUser;
import com.example.demo.model.Role;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.UserMapper;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.UserAlreadyException;
import java.util.ArrayList;
import com.example.demo.exception.MailalreadySetException;
@Service
public class IUserService implements UserService {
    private UserRepository userRepository;
    private CheckerUser checkerUser;
    private FirstOrderService firstOrderService;

    public IUserService(UserRepository userRepository, CheckerUser checkerUser, FirstOrderService firstOrderService) {
        this.userRepository = userRepository;   
        this.checkerUser = checkerUser;
        this.firstOrderService = firstOrderService;
    }
    @Override
    public UserDTO create(UserDTO userDTO) {        
        if(userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyException("User with email: " + userDTO.getEmail() + " already exists.");
        }
        User user = UserMapper.toEntity(userDTO);
         User usersave = userRepository.save(user);
         userDTO.setId(usersave.getId());
        firstOrderService.firstOrder(usersave);
        return UserMapper.toDTO(usersave);
    }   
    @Override
    public List<UserDTO> getAll() {
       return userRepository.findAll().stream().map(user -> UserMapper.toDTO(user)).toList();
    }   
    @Override
    public List<UserDTO> getByRole(Role role) {
        return userRepository.findByRole(role).stream()
                .filter(user -> user.getRole() != null && user.getRole().toString().equals(role))
                .map(user -> UserMapper.toDTO(user))
                .toList();
    }   
    public void addMail(String email,Long userId) {
        User user = userRepository.findById(userId).
        orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found."));
        if(user.getEmail()!=null)
        {
            throw new MailalreadySetException("User with email: " + email + " already exists.");
        }
    }
    @Override
    public void checkUser(Long userId) {
         if(!userRepository.existsById(userId)) {
             throw new UserNotFoundException("User with id: " + userId + " not found.");
         }  
    }
    @Override
    public void activateUser(Long userId)
    {
       checkerUser.CheckCanOrder(userId);
        User user = userRepository.findById(userId).
        orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found."));
        user.activate();
        userRepository.save(user);
    }
    
}
