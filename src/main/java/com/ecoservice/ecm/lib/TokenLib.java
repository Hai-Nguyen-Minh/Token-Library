package com.ecoservice.ecm.lib;

import javax.crypto.SecretKey;
import java.util.List;

public interface TokenLib {
    boolean login(char[] password);
    List<String> getAllKeys() throws Exception;
    SecretKey keyGenerate() throws Exception;
    byte[] encrypt(byte[] content, String fileName, String secretKey) throws Exception;
    byte[] decrypt(String fileName, String secretKey) throws Exception;
    void logout();
}
