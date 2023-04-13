package dev.elma.cryptFunc;

import java.io.File;
import java.io.FileWriter;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class GenerateRSAKeys {
    public static void main(String[] args) throws Exception {
        KeyPair keyPair = CryptageMethods.generateRSAKey();
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();
        String aPristring = Base64.getEncoder().encodeToString(aPrivate.getEncoded());
        String aPubString = Base64.getEncoder().encodeToString(aPublic.getEncoded());
        System.out.println("=====Private Key=======");
        System.out.println(aPristring);
        System.out.println("=====Public Key=======");
        System.out.println(aPubString);

        FileWriter rsaKeys=new FileWriter("RSAKeys");
        rsaKeys.write(aPristring+"\n"+aPubString);
        rsaKeys.close();


    }
}
