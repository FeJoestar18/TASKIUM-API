package com.taskium.project.Domain.Enums;

public enum RoleName {

    ADMIN("admin"),
    USER("user"),
    MANAGER("manager");

    private String role;

    RoleName(String role) {
        this.role = role;
    }

}