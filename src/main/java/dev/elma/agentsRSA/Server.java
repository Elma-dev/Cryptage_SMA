package dev.elma.agentsRSA;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;

public class Server extends Agent {
    @Override
    protected void setup()  {
        PrivateKey privateKey =(PrivateKey) getArguments()[0];

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receiveMessage = receive();
                if(receiveMessage!=null){
                    String content = receiveMessage.getContent();
                    //decoded Messsage
                    byte[] decode = Base64.getDecoder().decode(content);
                    try {
                        Cipher cipher=Cipher.getInstance("AES");
                        cipher.init(Cipher.DECRYPT_MODE,privateKey);
                        //decrypted message
                        byte[] bytes = cipher.doFinal(decode);
                        System.out.println(new String(bytes));

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