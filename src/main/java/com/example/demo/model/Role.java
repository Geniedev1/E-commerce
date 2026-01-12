package com.example.demo.model;
import java.util.List;
public class Role {
    private String role;
    List<String> roles = List.of("Admin", "User", "Manager");
    public List<String> getAllRoles() {
        return roles;
    }
    public Role(String role){
        for(String r: roles){
            if(r.equals(role)){
                this.role = role;
                return;
            }
        }   
        throw new IllegalArgumentException("Invalid role: " + role);
    }
    public String getRole() {
        return role;
    }
    
}