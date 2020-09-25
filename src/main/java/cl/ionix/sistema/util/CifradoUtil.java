package cl.ionix.sistema.util;


import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Component
public class CifradoUtil {

    public static final String ALGORITMO_CIFRADO = "DES";


    public String cifrar(String data, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,  BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {


        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITMO_CIFRADO);
        DESKeySpec keySpec = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
        SecretKey ks = keyFactory.generateSecret(keySpec);

        byte[]  cleartext = data.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance(ALGORITMO_CIFRADO);
        cipher.init(Cipher.ENCRYPT_MODE, ks);
        return Base64.getEncoder().encodeToString(cipher.doFinal(cleartext));


    }
}
