package com.example.onekey;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.spec.SecretKeySpec;

public class GenerateSymmetricKey {
	private SecretKeySpec secretKey;

	public GenerateSymmetricKey(int length, String algorithm) {
		SecureRandom rnd = new SecureRandom();
		byte [] key = new byte [length];
		rnd.nextBytes(key);
		this.secretKey = new SecretKeySpec(key, algorithm);
	}
	
	public SecretKeySpec getKey(){
		return this.secretKey;
	}

	public void writeToFile(String path, byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(f);
		fos.write(key);
		fos.flush();
		fos.close();
	}
	
	public static void main(String[] args) throws IOException {
		GenerateSymmetricKey genSK = new GenerateSymmetricKey(16, "AES");
		genSK.writeToFile("OneKey/secretKey", genSK.getKey().getEncoded());
		GenerateSymmetricKey genSK2 = new GenerateSymmetricKey(16, "AES");
		genSK.writeToFile("OneKey/secretKey2", genSK2.getKey().getEncoded());
	}
}
