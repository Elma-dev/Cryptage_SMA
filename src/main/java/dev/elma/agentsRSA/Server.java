package dev.elma.agentsRSA;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Server extends Agent {
    @Override
    protected void setup()  {
        String privateKey =(String) getArguments()[0];

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receiveMessage = receive();
                if(receiveMessage!=null){

                    String content = receiveMessage.getContent();
                    //decoded Messsage
                    byte[] decode = Base64.getDecoder().decode(content);
                    try {
                        byte[] decodeKeyPrivate = Base64.getDecoder().decode(privateKey);
                        KeyFactory rsa = KeyFactory.getInstance("RSA");
                        PrivateKey privateKeyGen = rsa.generatePrivate(new PKCS8EncodedKeySpec(decodeKeyPrivate));

                        Cipher cipher=Cipher.getInstance("RSA");
                        cipher.init(Cipher.DECRYPT_MODE,privateKeyGen);
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
