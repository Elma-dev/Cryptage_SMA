package dev.elma.agentsRSA;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.PublicKey;
import java.util.Base64;

public class Client extends Agent {
    @Override
    protected void setup() {
        //get the secret key in string format
        PublicKey publicKey = (PublicKey) getArguments()[0];

        try {

            Cipher cipher=Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
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
