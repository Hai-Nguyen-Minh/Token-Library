package com.ecoservice.ecm.crypto;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class SecretKeyGeneratorImpl implements SecretKeyGenerator {
    public static final String KEY_FILE = "key/key.txt";
    public static SecretKeyGeneratorImpl INSTANCE;

    public static SecretKeyGeneratorImpl getInstance() {
        if (INSTANCE == null) {
            return new SecretKeyGeneratorImpl();
        }
        return INSTANCE;
    }

    @Override
    public SecretKey keyGenerator() throws Exception {
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
        String key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("Secret key : " + key);
        File file = new File(KEY_FILE);
        file.getParentFile().mkdir();
        file.createNewFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, true)) {
            fileOutputStream.write(key.getBytes());
            fileOutputStream.write("\n".getBytes());
        }
        return secretKey;
    }

    @Override
    public List<String> getAllKeys() throws Exception {
        List<String> keyIds = new ArrayList<>();
        File file = new File(KEY_FILE);
        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                keyIds.add(line);
            }
        }
        return keyIds;
    }
}
