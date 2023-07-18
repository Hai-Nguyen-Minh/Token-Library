package com.ecoservice.ecm.lib;

import com.ecoservice.ecm.crypto.FileCrypto;
import com.ecoservice.ecm.crypto.FileCryptoImpl;
import com.ecoservice.ecm.crypto.SecretKeyGenerator;
import com.ecoservice.ecm.crypto.SecretKeyGeneratorImpl;
import com.ecoservice.ecm.exception.LoginException;
import com.ecoservice.ecm.security.SecurityEncryption;
import com.ecoservice.ecm.security.SecurityEncryptionImpl;

import javax.crypto.SecretKey;
import java.util.List;

public class TokenLibImpl implements TokenLib {
    private final SecretKeyGenerator secretKeyGenerator;
    private final SecurityEncryption securityEncryption;
    public static TokenLibImpl INSTANCE;

    public static TokenLibImpl getInstance(
            SecretKeyGenerator secretKeyGenerator,
            SecurityEncryption securityEncryption
    ) {
        if (INSTANCE == null) {
            return new TokenLibImpl(secretKeyGenerator, securityEncryption);
        }
        return INSTANCE;
    }

    public static TokenLibImpl getInstance() {
        if (INSTANCE == null) {
            SecurityEncryption securityEncryption = SecurityEncryptionImpl.getInstance();
            SecretKeyGenerator secretKeyGenerator = SecretKeyGeneratorImpl.getInstance();
            return new TokenLibImpl(secretKeyGenerator, securityEncryption);
        }
        return INSTANCE;
    }

    public TokenLibImpl(SecretKeyGenerator secretKeyGenerator, SecurityEncryption securityEncryption) {
        this.secretKeyGenerator = secretKeyGenerator;
        this.securityEncryption = securityEncryption;
    }

    @Override
    public boolean login(char[] password) {
        return securityEncryption.login(password);
    }

    @Override
    public List<String> getAllKeys() throws Exception {
        if (!securityEncryption.isLogin()) {
            throw new LoginException();
        }
        return secretKeyGenerator.getAllKeys();
    }

    @Override
    public SecretKey keyGenerate() throws Exception {
        if (!securityEncryption.isLogin()) {
            throw new LoginException();
        }
        return secretKeyGenerator.keyGenerator();
    }

    @Override
    public byte[] encrypt(byte[] content, String fileName, String secretKey) throws Exception {
        if (!securityEncryption.isLogin()) {
            throw new LoginException();
        }
        FileCrypto fileCrypto = FileCryptoImpl.getInstance(secretKey);
        return fileCrypto.encrypt(content, fileName);
    }

    @Override
    public byte[] decrypt(String fileName, String secretKey) throws Exception {
        if (!securityEncryption.isLogin()) {
            throw new LoginException();
        }
        FileCrypto fileCrypto = FileCryptoImpl.getInstance(secretKey);
        return fileCrypto.decrypt(fileName);
    }

    @Override
    public void logout() {
        securityEncryption.logout();
    }
}
