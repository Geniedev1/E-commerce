package com.example.demo.service.impservice;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.model.Role;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.exception.UserNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class IUserService implements UserService {
    private List<User> users;
    public IUserService() {
        this.users = new java.util.ArrayList<>();
    }
    public User create(CreateUserRequest requestUser) {        
        if(requestUser.getName() == null || requestUser.getEmail() == null) {
            throw new UserNotFoundException(requestUser.getId().toString());
        }
        User user = new User(requestUser.getId(), requestUser.getName(), requestUser.getEmail(),requestUser.getRole(),requestUser.getCreatedAt());
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
}
