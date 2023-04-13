package dev.elma.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class Server extends Agent {
    @Override
    protected void setup() {
        String secretKeyString=(String) getArguments()[0];

        SecretKey secretKeyAES=new SecretKeySpec(secretKeyString.getBytes(StandardCharsets.UTF_8),"AES");
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receiveMessage = receive();
                if(receiveMessage!=null){
                    String content = receiveMessage.getContent();
                    try {
                        Cipher cipher=Cipher.getInstance("AES");
                        cipher.init(Cipher.DECRYPT_MODE,secretKeyAES);
                        String decryptedMsg = cipher.doFinal(content.getBytes()).toString();
                        System.out.println(decryptedMsg);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                    block();
            }
        });
    }
}
