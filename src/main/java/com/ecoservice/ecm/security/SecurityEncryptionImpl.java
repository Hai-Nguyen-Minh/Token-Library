package com.ecoservice.ecm.security;

import java.util.Arrays;

public class SecurityEncryptionImpl implements SecurityEncryption {
    public static volatile boolean isLogin = false;
    public static final char[] PIN = "123456".toCharArray();

    public static SecurityEncryptionImpl INSTANCE;

    public static SecurityEncryptionImpl getInstance() {
        if (INSTANCE == null) {
            return new SecurityEncryptionImpl();
        }
        return INSTANCE;
    }

    @Override
    public boolean login(char[] password) {
        if (Arrays.equals(password, PIN)) {
            return isLogin = true;
        }
        return isLogin = false;
    }

    @Override
    public void logout() {
        isLogin = false;
    }

    @Override
    public boolean isLogin() {
        return isLogin;
    }
}
