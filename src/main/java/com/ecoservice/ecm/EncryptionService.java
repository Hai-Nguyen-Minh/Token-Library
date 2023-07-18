package com.ecoservice.ecm;

import com.ecoservice.ecm.lib.TokenLib;
import com.ecoservice.ecm.lib.TokenLibImpl;

import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

public class EncryptionService {
    public static void main(String[] args) throws Exception {
        TokenLib tokenLib = TokenLibImpl.getInstance();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        printUsage();
        int answer;
        do {
            System.out.print("Enter your choice: ");
            answer = Integer.parseInt(reader.readLine());
            switch (answer) {
                case 1: {
                    System.out.print("Enter the PIN: ");
                    String pass = reader.readLine();
                    boolean login = tokenLib.login(pass.toCharArray());
                    if (login) {
                        System.out.println("Login ok!!!");
                    }
                    else {
                        System.err.println("Login fail!!!");
                    }
                    break;
                }
                case 2: {
                    try {
                        List<String> keys = tokenLib.getAllKeys();
                        System.out.println("Found " + keys.size() + " key(s)");
                        keys.forEach(key -> {
                            System.out.println("keyId: " + key);
                        });
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 3: {
                    try {
                        SecretKey secretKey = tokenLib.keyGenerate();
                        System.out.println("New key added: " + Base64.getEncoder().encodeToString(secretKey.getEncoded()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 4: {
                    System.out.print("Enter the KeyID: ");
                    String key = reader.readLine();
                    byte[] content = "Hello!!!".getBytes();
                    String fileName = "test.txt";
                    try {
                        tokenLib.encrypt(content, fileName, key);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 5: {
                    System.out.print("Enter the KeyID: ");
                    String key = reader.readLine();
                    String fileName = "test.txt";
                    try {
                        tokenLib.decrypt(fileName, key);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 6: {
                    tokenLib.logout();
                    break;
                }
                default:
            }
        }
        while (answer > 0 && answer < 11);
        System.exit(0);
    }

    private static void printUsage() {
        System.out.println("Welcome EcoTokenLib functions");
        System.out.println("There are functions we support");
        System.out.println("1. login");
        System.out.println("2. get all key ids");
        System.out.println("3. gen keypair");
        System.out.println("4. encrypt");
        System.out.println("5. decrypt");
        System.out.println("6. logout");
        System.out.println("else exit");
    }
}