package com.backendguru.mvc_demo.model.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Profile {
    private String name;
    private String email;

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
