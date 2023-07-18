package com.ecoservice.ecm.exception;

public enum ExceptionEnum {
    LOGIN_EXCEPTION ("Please login by your PIN to use this service!!!");

    private final String name;
    ExceptionEnum(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
