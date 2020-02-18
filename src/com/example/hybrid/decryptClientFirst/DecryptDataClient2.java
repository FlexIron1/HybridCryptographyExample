package com.example.hybrid.decryptClientFirst;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class DecryptDataClient2 {
	private Cipher cipher;

	public DecryptDataClient2(File encryptedFileReceived, File decryptedFile, SecretKeySpec secretKey, String algorithm) throws IOException, GeneralSecurityException {
		this.cipher = Cipher.getInstance(algorithm);
		decryptFile(getFileInBytes(encryptedFileReceived), decryptedFile, secretKey);
	}
	
	public void decryptFile(byte[] input, File output, SecretKeySpec key) throws IOException, GeneralSecurityException {
		this.cipher.init(Cipher.DECRYPT_MODE, key);
		writeToFile(output, this.cipher.doFinal(input));
    }
	
	private void writeToFile(File output, byte[] toWrite) throws IOException{
		output.getParentFile().mkdirs();
		FileOutputStream fos = new FileOutputStream(output);
		fos.write(toWrite);
		fos.flush();
		fos.close();
		System.out.println("Файл был успешно расшифрован. Вы можете просмотреть его в:" + output.getPath());
	}
	
	public byte[] getFileInBytes(File f) throws IOException{
		FileInputStream fis = new FileInputStream(f);
		byte[] fBytes = new byte[(int) f.length()];
		fis.read(fBytes);
		fis.close();
		return fBytes;
	}

}
