# How to Achieve Security in Multi-Agent Systems
## Message Cryptage with Advanced Encryption Standard AES
### AES 
* AES is a symmetric cipher which means, the same key used to encrypt data is necessary to decrypt that data.
* AES keys are a string of bits (1s and 0s) and can take three different sizes: **128**, **192**, or **256** bits.
### How does it work?
* **Encryption**

As we mentioned AES can have keys of three sizes, 128, 192, and 256 bits, and that the longer the key, the stronger the encryption.
To understand this we must know that when AES encrypts, if applies the same algorithm encryption algorithm for a certain number of rounds, each round using a different sub-key generated from our initial key in a process called key expansion.

![image](https://user-images.githubusercontent.com/67378945/232121121-469abbb1-ed94-4970-a1bc-f857beec8fd4.png)

* **Decryption**

We only need to reverse the encryption procedure to decrypt a text.

### How To Apply it in SMA with Java?
* Encryption
1. firstly we should generate a secrete key and share it with all agents, in my case i put it in this file [AES.txt](https://github.com/Elma-dev/Cryptage_SMA/blob/master/AES.txt).
2. in the agent who will encrypt the message we need to transform the key to be able of using with AES algorithm

    `SecretKey secretKey=new SecretKeySpec(secretKeyString.getBytes(),"AES");`
3. create cipher object with AES algorithm and chose the mode **Crypt OR Decrypt**

    ```
    Cipher cipher=Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE,secretKey);
    ```
4. crypt Message
    ```
    //crypt message
    byte[] cryptMessage = cipher.doFinal("Hello Server Are you Here?".getBytes());
    //byte to string encoded
    String cryptMessageStr = Base64.getEncoder().encodeToString(cryptMessage);
    ```
5. Send Message to Agents
* Decryption
1. create a AES secret key from the original key receiving from the key sharing like the 2 part in Encryption.
2. receive Message Encrypted
3. decoded with Base64
4. create cipher object with decryption mode
5. decrypted message 
```
        //secretKey
        String secretKeyString=(String) getArguments()[0];
        //create AES Secret Key
        SecretKey secretKeyAES=new SecretKeySpec(secretKeyString.getBytes(),"AES");
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                //receive message crypted
                ACLMessage receiveMessage = receive();
                if(receiveMessage!=null){
                    String content = receiveMessage.getContent();
                    //decoded Messsage
                    byte[] decode = Base64.getDecoder().decode(content);
                    try {
                        //create cipher with DecryptMode
                        Cipher cipher=Cipher.getInstance("AES");
                        cipher.init(Cipher.DECRYPT_MODE,secretKeyAES);
                        //decrypted message
                        byte[] bytes = cipher.doFinal(decode);
                        System.out.println(new String(bytes));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
```

# Message Cryptage with RSA
![image](https://user-images.githubusercontent.com/67378945/232170802-2ea15b29-9489-4f24-847a-d9618e5ba871.png)

### How To Apply it in SMA with Java?
1. the server create two secret Keys (private & public) [RSAKeys.txt](https://github.com/Elma-dev/Cryptage_SMA/blob/master/RSAKeys).
2. share the public key with other agents.
3. the others crypte the content with the public key.
4. the server decrypted the content with the private key.  

