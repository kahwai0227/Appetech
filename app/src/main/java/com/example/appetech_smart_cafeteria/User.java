package com.example.appetech_smart_cafeteria;

public class User {
    public String username;
    public String id;
    public String role;
    public String email;
    public String password;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String id, String role, String email, String password) {
        this.username = username;
        this.id = id;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getId() { return id; }
    public String getRole() { return role; }
    public String getEmail() {return email; }
    public String getPassword() {
        return password;
    }
}
