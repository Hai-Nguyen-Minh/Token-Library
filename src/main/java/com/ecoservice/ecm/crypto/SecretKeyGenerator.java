package com.ecoservice.ecm.crypto;

import javax.crypto.SecretKey;
import java.util.List;

public interface SecretKeyGenerator {
    SecretKey keyGenerator() throws Exception;

    List<String> getAllKeys() throws Exception;
}
