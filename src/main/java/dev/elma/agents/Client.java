package dev.elma.agents;

import jade.core.Agent;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyFactory;

public class Client extends Agent {
    @Override
    protected void setup() {
        String secretKey = (String) getArguments()[0];
        System.out.println(secretKey);
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey key = keyGenerator.generateKey();
            byte[] encoded = key.getEncoded();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
