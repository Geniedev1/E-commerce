package com.example.demo.model;
public abstract class BaseEntity {
    private Long id;
    private String createdAt;
    private Role role;

    public Role getRole() {
        return role;
    }
    public void setRole(String role) {
        Role roleObj = new Role(role);
        this.role = roleObj;
    }
    public Long getId() {
        return id;
    }   
    public void setId(Long id) {
        this.id = id;
    }   
    public String getCreatedAt() {
        return createdAt;
    }   
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public BaseEntity(String role, Long id, String createdAt) {
        Role roleObj = new Role(role);
        this.role = roleObj;
        this.id = id;
        this.createdAt = createdAt;
    }
    public abstract boolean work();
}