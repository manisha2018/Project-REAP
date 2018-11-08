package com.projectreap.ProjectReap.enums;

public enum Role {
    ADMIN("admin"),
    USER("user");

   String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
