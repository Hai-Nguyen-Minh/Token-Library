package com.ecoservice.ecm.crypto;

public interface FileCrypto {
    byte[] encrypt(byte[] content, String fileName) throws Exception;
    byte[] decrypt(String fileName) throws Exception;

}
