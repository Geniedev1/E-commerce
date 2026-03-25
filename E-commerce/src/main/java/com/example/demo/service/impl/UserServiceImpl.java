package com.example.demo.service.impl;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AdminCreateUserRequest;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserStatus;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FirstOrderService;
import com.example.demo.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FirstOrderService firstOrderService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, 
                          FirstOrderService firstOrderService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.firstOrderService = firstOrderService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @CacheEvict(value = {"users", "usersByRole"}, allEntries = true)
    public void registerUser(AuthRequest authRequest) {
        if (userRepository.existsByEmail(authRequest.getEmail())) {
            throw new BadRequestException("User with email: " + authRequest.getEmail() + " already exists.");
        }
        User user = UserMapper.AuthtoEntity(authRequest);
        user.setStatus(UserStatus.PENDING);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User usersave = userRepository.save(user);
        firstOrderService.firstOrder(usersave);
    }

    @Override
    @CacheEvict(value = {"users", "usersByRole"}, allEntries = true)
    public void createUserByAdmin(AdminCreateUserRequest adminCreateUserRequest) {
        if (userRepository.existsByEmail(adminCreateUserRequest.getEmail())) {
            throw new BadRequestException("User with email: " + adminCreateUserRequest.getEmail() + " already exists.");
        }
        User user = UserMapper.AdminCreateUserRequesttoEntity(adminCreateUserRequest);
        userRepository.save(user);
    }

    @Override
    @Cacheable(value = "users")
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(UserMapper::toDTO).toList();
    }

    @Override
    @Cacheable(value = "usersByRole", key = "#role")
    public List<UserDTO> getByRole(Role role) {
        return userRepository.findByRole(role).stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public void addMail(String email, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found."));
        if (user.getEmail() != null) {
            throw new BadRequestException("User with email: " + email + " already exists.");
        }
    }

    @Override
    public void checkUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User with id: " + userId + " not found.");
        }
    }

    @Override
    @CacheEvict(value = {"users", "usersByRole"}, allEntries = true)
    public void activateUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found."));
        user.activate();
        userRepository.save(user);
    }
}
