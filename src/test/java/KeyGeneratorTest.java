import com.ecoservice.ecm.crypto.SecretKeyGenerator;
import com.ecoservice.ecm.crypto.SecretKeyGeneratorImpl;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class KeyGeneratorTest {
    @Test
    public void keyGenerator1() throws Exception {
        SecretKeyGenerator secretKeyGenerator = SecretKeyGeneratorImpl.getInstance();
        SecretKey secretKey = secretKeyGenerator.keyGenerator();
        System.out.println(secretKey);
    }

    @Test
    public void keyGenerator2() throws Exception {
        SecretKeyGenerator secretKeyGenerator = SecretKeyGeneratorImpl.getInstance();
        List<String> secretKeys = secretKeyGenerator.getAllKeys();
        System.out.println(secretKeys);
        assertEquals("fdb3tFR+trmO/jl70g7y5Q==", secretKeys.get(0));
    }
}
