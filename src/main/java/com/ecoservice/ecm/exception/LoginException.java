package com.ecoservice.ecm.exception;

public class LoginException extends Exception{
    public LoginException() {
        super(ExceptionEnum.LOGIN_EXCEPTION.getName());
    }
}
