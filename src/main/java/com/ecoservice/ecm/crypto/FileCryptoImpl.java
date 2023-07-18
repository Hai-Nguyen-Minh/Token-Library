package com.ecoservice.ecm.crypto;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FileCryptoImpl implements FileCrypto{
    public static final String ENCRYPT_FOLDER = "encrypt/";
    public static final String CIPHER_ALGO = "AES/CBC/PKCS5Padding";
    public static FileCryptoImpl INSTANCE;
    private final SecretKey secretKey;
    private final Cipher cipher;

    public static FileCryptoImpl getInstance(String secretKey) throws Exception {
        if (INSTANCE == null) {
            System.out.println("Secret key : " + secretKey);
            return new FileCryptoImpl(secretKey, CIPHER_ALGO);
        }
        return INSTANCE;
    }
    public FileCryptoImpl(String secretKey, String cipher) throws Exception {
        byte[] keyDecode = Base64.getDecoder().decode(secretKey.getBytes());
        this.secretKey = new SecretKeySpec(keyDecode, "AES");
        this.cipher = Cipher.getInstance(cipher);
    }

    @Override
    public byte[] encrypt(byte[] content, String fileName) throws Exception {
        File file = new File(ENCRYPT_FOLDER + fileName);
        file.getParentFile().mkdir();
        file.createNewFile();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] iv = cipher.getIV();

        try (
                FileOutputStream fileOut = new FileOutputStream(file, false);
                CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)
        ) {
            fileOut.write(iv);
            cipherOut.write(content);
        }
        int i = 0;
        byte[] encryptedData = cipher.getIV();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            while (fileInputStream.read() != -1) {
                char c = (char) fileInputStream.read();
                encryptedData[i] = (byte) c;
                i++;
            }
        }
        System.out.println(Base64.getEncoder().encodeToString(encryptedData));
        return encryptedData;
    }

    @Override
    public byte[] decrypt(String fileName) throws Exception {
        File file = new File(ENCRYPT_FOLDER + fileName);
        String content;

        try (FileInputStream fileIn = new FileInputStream(file)) {
            byte[] fileIv = new byte[16];
            fileIn.read(fileIv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileIv));

            try (
                    CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
                    InputStreamReader inputReader = new InputStreamReader(cipherIn);
                    BufferedReader reader = new BufferedReader(inputReader)
            ) {

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                content = sb.toString();
            }

        }
        file.delete();
        System.out.println(new String(content.getBytes(), StandardCharsets.UTF_8));
        return content.getBytes();
    }

}
