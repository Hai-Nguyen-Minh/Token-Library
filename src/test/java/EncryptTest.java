import com.ecoservice.ecm.crypto.FileCrypto;
import com.ecoservice.ecm.crypto.FileCryptoImpl;
import org.junit.Test;

import java.util.Base64;

public class EncryptTest {

    @Test
    public void encrypt1() throws Exception {
        String fileName = "test.txt";
        String content = "dcm";
        FileCrypto fileCrypto = FileCryptoImpl.getInstance("fdb3tFR+trmO/jl70g7y5Q==");
        byte[] encrypt = fileCrypto.encrypt(content.getBytes(), fileName);
        System.out.println(Base64.getEncoder().encodeToString(encrypt));

    }
}
