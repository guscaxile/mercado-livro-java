package com.mercado.java.mercadolivrojava.enums;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    CUSTOMER("ROLE_CUSTOMER");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
