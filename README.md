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
1. firstly we should generate a secrete key and share it with all agents, in my case i put it in this file [AES.txt](https://github.com/Elma-dev/Cryptage_SMA/blob/master/AES.txt).
2. in the agent who will encrypt the message we need to transform the key to be able of using with AES algorithm




