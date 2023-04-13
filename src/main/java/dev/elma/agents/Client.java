package dev.elma.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.util.Base64;

public class Client extends Agent {
    @Override
    protected void setup() {
        //get the secret key in string format
        String secretKeyString = (String) getArguments()[0];

        try {
            //generate secret Key to using with aes
            SecretKey secretKey=new SecretKeySpec(secretKeyString.getBytes(),"AES");
            //create cipher object for crypt and decrypt
            Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            //crypt message
            byte[] cryptMessage = cipher.doFinal("Hello Server Are you Here?".getBytes());
            //byte to string encoded
            String cryptMessageStr = Base64.getEncoder().encodeToString(cryptMessage);
            //send message to srv:
            ACLMessage message=new ACLMessage(ACLMessage.INFORM);
            message.addReceiver(new AID("server",AID.ISLOCALNAME));
            message.setContent(cryptMessageStr);
            send(message);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
