package com.example.hybrid.decryptClient2;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

public class DecryptKey {
    private Cipher cipher;

    DecryptKey(PrivateKey privateKey, File encryptedKeyReceived, File decryptedKeyFile, String algorithm) throws IOException, GeneralSecurityException {
        this.cipher = Cipher.getInstance(algorithm);
        decryptFile(getFileInBytes(encryptedKeyReceived), decryptedKeyFile, privateKey);
    }

    private void decryptFile(byte[] input, File output, PrivateKey key) throws IOException, GeneralSecurityException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        writeToFile(output, this.cipher.doFinal(input));
    }

    private void writeToFile(File output, byte[] toWrite) throws IOException {
        output.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(output);
        fos.write(toWrite);
        fos.flush();
        fos.close();
    }

    private byte[] getFileInBytes(File f) throws IOException {
        FileInputStream fis = new FileInputStream(f);
        byte[] fbytes = new byte[(int) f.length()];
        fis.read(fbytes);
        fis.close();
        return fbytes;
    }
}
