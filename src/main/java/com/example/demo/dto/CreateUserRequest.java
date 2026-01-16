package com.example.demo.dto;
import jakarta.validation.constraints.NotBlank;
public class CreateUserRequest {
    private String name;
    private String email;
    private String role;
    private Long id;
    private String createdAt;
    private String status;
    public CreateUserRequest() {
    }
    public CreateUserRequest(String name, String email, String role, Long id, String createdAt, String status) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
    }
    @NotBlank( message = "Name is mandatory" )
    public String getName() {
        return name;
    }
    @NotBlank( message = "Email is mandatory" )
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
