package com.example.demo.service.impservice;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.validateentity.CheckerUser;
import com.example.demo.model.Role;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.exception.UserNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class IUserService implements UserService {
    private List<User> users;
    private CheckerUser checkerUser;
    public IUserService(CheckerUser checkerUser) {
        this.checkerUser = checkerUser;
        this.users = new java.util.ArrayList<>();
    }
    public User create(CreateUserRequest requestUser) {        
        User user = new User(requestUser.getId(), requestUser.getName(), requestUser.getEmail(),requestUser.getRole(),requestUser.getCreatedAt(),requestUser.getStatus());
        users.add(user);
        return user;
    }   
    public List<User> getAll() {
       return users;
    }   
    public List<User> getByRole(String role) {
        return users.stream()
                .filter(user -> user.getRole() != null && user.getRole().getAllRoles().contains(role))
                .toList();
    }   
    public User addMail(User user, String mail) {
        if(user == null) {
            throw new UserNotFoundException(user.getId().toString());
        }
        if(mail == null || mail.isEmpty()) {
            throw new IllegalArgumentException("Mail cannot be null or empty");
        }
        user.setEmail(mail);
        return user;
    }
    @Override
    public void checkUser(String userId) {
        boolean exists = users.stream()
                .anyMatch(user -> user.getId().toString().equals(userId));
        if(!exists) {
            throw new UserNotFoundException(userId);
        }   
    }
    @Override
    public void activeUser(Long userId)
    {
       checkerUser.CheckCanOrder(userId);
        for(User user : users)
        {
            if(user.getId().toString().equals(Long.toString(userId))) {
                if(user.getStatus().equals("ACTIVATE")) {
                    throw new com.example.demo.exception.UserAlreadyActivateException(userId);
                }  
                user.setStatus("ACTIVATE");
                return;
            }
        }
       throw new UserNotFoundException(userId.toString());
    }

}
