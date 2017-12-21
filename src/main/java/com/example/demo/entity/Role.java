package com.example.demo.entity;

public enum Role {
	USER("USER"),
    ADMIN("ADMIN");

    private String value;

    Role(String status) {
        this.value = status;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
