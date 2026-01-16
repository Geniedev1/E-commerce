package com.example.demo.model;
import java.util.List;
import com.example.demo.exception.StatusUserIncorretException;
public abstract class BaseEntity {
    private List<String> statuses = List.of("ACTIVATE", "INACTIVATE", "BLOCKED","PENDING");
    private Long id;
    private String createdAt;
    private Role role;
    private String status;

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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        if (!statuses.contains(status)) {
            throw new StatusUserIncorretException(status);
        }
        this.status = status;
    }
    public BaseEntity(String role, Long id, String createdAt,String status) {
        Role roleObj = new Role(role);
        this.role = roleObj;
        this.id = id;
        this.createdAt = createdAt;
        if (!statuses.contains(status)) {
            throw new StatusUserIncorretException(status);
        }
        this.status = status;
    }
    public abstract boolean work();
}