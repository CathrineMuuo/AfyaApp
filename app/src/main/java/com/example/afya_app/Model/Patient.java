package com.example.afya_app.Model;

public class Patient {
    private String name;
    private String email;

    public Patient(String name) {
        // Empty constructor needed for Firestore
    }

    public Patient(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
