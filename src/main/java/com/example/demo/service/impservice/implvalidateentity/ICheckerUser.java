package com.example.demo.service.impservice.implvalidateentity;
import org.springframework.stereotype.Component;
import com.example.demo.model.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.validateentity.CheckerUser;
@Component  
public class ICheckerUser implements CheckerUser {
    @Override
    public void CheckCanOrder(Long userId) {
            if(userId == null || userId <= 0) {
                throw new UserNotFoundException("User with id: " + userId + " not found.");
            }
    }
    
}
