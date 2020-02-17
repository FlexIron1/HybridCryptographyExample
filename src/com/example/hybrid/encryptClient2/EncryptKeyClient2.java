package com.example.hybrid.encryptClient2;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;

class EncryptKeyClient2 {

    private Cipher cipher;

    EncryptKeyClient2(PublicKey key, File originalKeyFile, File encryptedKeyFile, String cipherAlgorithm) throws IOException,
            GeneralSecurityException {
        this.cipher = Cipher.getInstance(cipherAlgorithm);
        encryptFile(getFileInBytes(originalKeyFile), encryptedKeyFile, key);
    }

    private void encryptFile(byte[] input, File output, PublicKey key) throws IOException,
            GeneralSecurityException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        writeToFile(output, this.cipher.doFinal(input));
    }

    private void writeToFile(File output, byte[] toWrite) throws IOException {
        output.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(output);
        fos.write(toWrite);
        fos.flush();
        fos.close();
        System.out.println("Ключ был успешно зашифрован и сохранен в:" + output.getPath());
    }

    private byte[] getFileInBytes(File f) throws IOException {
        FileInputStream fis = new FileInputStream(f);
        byte[] fBytes = new byte[(int) f.length()];
        fis.read(fBytes);
        fis.close();
        return fBytes;
    }
}
