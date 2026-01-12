package com.example.demo.dto;
public class CreateUserRequest {
    private String name;
    private String email;
    private String role;
    private Long id;
    private String createdAt;
    public CreateUserRequest() {
    }
    public CreateUserRequest(String name, String email, String role, Long id, String createdAt) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.id = id;
        this.createdAt = createdAt;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role;
    }
    public Long getId() {
        return id;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
}
