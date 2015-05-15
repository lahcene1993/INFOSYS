package com.web.entity;

public enum WebRole {
    CLIENT, MANAGER, ADMIN;
    
    @Override
    public String toString() {
        return this.name();
    }
}
