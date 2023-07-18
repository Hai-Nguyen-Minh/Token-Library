import com.ecoservice.ecm.crypto.FileCrypto;
import com.ecoservice.ecm.crypto.FileCryptoImpl;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static junit.framework.TestCase.assertEquals;

public class DecryptTest {
    @Test
    public void decrypt1() throws Exception {
        String fileName = "test.txt";
        FileCrypto fileCrypto = FileCryptoImpl.getInstance("fdb3tFR+trmO/jl70g7y5Q==");
        byte[] encrypt = fileCrypto.encrypt("dcm".getBytes(), fileName);
        System.out.println(Base64.getEncoder().encodeToString(encrypt));
        byte[] content = fileCrypto.decrypt(fileName);
        System.out.println(Base64.getEncoder().encodeToString(content));
        System.out.println(new String(content, StandardCharsets.UTF_8));
        assertEquals("dcm", new String(content, StandardCharsets.UTF_8));
    }
}
