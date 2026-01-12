package com.example.demo.model;
public class User extends BaseEntity{
    private String name;
    private String email;
    @Override
    public boolean work() {
        return true;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public User(Long id,String name, String email, String role, String createdAt) {
        super(role, id, createdAt);
        this.name = name;
        this.email = email;
    }   
}