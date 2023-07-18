package com.ecoservice.ecm.security;

public interface SecurityEncryption {
    boolean login(char[] password);

    void logout();

    boolean isLogin();
}
