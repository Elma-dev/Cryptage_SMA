package dev.elma.agentsRSA;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Client extends Agent {
    @Override
    protected void setup() {
        //get the secret key in string format
        String publicKey = (String) getArguments()[0];
        byte[] decode = Base64.getDecoder().decode(publicKey);


        try {
            KeyFactory keyFactory=KeyFactory.getInstance("RSA");
            PublicKey publicKeyGenerate = keyFactory.generatePublic(new X509EncodedKeySpec(decode));

            Cipher cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,publicKeyGenerate);
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
