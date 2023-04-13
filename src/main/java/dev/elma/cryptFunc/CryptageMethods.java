package dev.elma.cryptFunc;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class CryptageMethods {
    public static KeyPair generateRSAKey() throws Exception{
        //generate ras keys (private and public)
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        rsa.initialize(512);
        KeyPair keyPair = rsa.generateKeyPair();
        return keyPair;
    }
}
