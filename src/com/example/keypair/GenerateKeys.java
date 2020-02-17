package com.example.keypair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

public class GenerateKeys {
    private KeyPairGenerator keyGen;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    private GenerateKeys(int keyLength) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keyLength);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, IOException {
        GenerateKeys clientOne;
        GenerateKeys clientTwo;

        clientOne = new GenerateKeys(1024);
        clientOne.createKeys();
        clientOne.writeToFile("KeyPair/publicKey_Client1", clientOne.getPublicKey().getEncoded());
        clientOne.writeToFile("KeyPair/privateKey_Client1", clientOne.getPrivateKey().getEncoded());

        clientTwo = new GenerateKeys(1024);
        clientTwo.createKeys();
        clientTwo.writeToFile("KeyPair/publicKey_Client2", clientTwo.getPublicKey().getEncoded());
        clientTwo.writeToFile("KeyPair/privateKey_Client2", clientTwo.getPrivateKey().getEncoded());
    }

    private void createKeys() {
        KeyPair pair = this.keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    private PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    private PublicKey getPublicKey() {
        return this.publicKey;
    }

    private void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }
}
