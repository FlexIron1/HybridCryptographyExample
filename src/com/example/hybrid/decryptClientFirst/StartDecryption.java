package com.example.hybrid.decryptClientFirst;

import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class StartDecryption {
	
	private PrivateKey getPrivate(String filename, String algorithm) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance(algorithm);
		return kf.generatePrivate(spec);
	}

	private SecretKeySpec getSecretKey(String filename, String algorithm) throws IOException{
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		return new SecretKeySpec(keyBytes, algorithm);
	}
	
	public static void main(String[] args) throws Exception{
		StartDecryption startEnc = new StartDecryption();
		
		File encryptedKeyReceived = new File("EncryptedFiles/encryptedSecretKey2");
		File decryptedKeyFile = new File("DecryptedFiles/SecretKey2");
		new DecryptKey(startEnc.getPrivate("KeyPair/privateKey_Client1", "RSA"), encryptedKeyReceived, decryptedKeyFile, "RSA");
		
		File encryptedFileReceived = new File("EncryptedFiles/encryptedFile2");
		File decryptedFile = new File("DecryptedFiles/messageForTheFirstCustomer");
		new DecryptDataClient2(encryptedFileReceived, decryptedFile, startEnc.getSecretKey("DecryptedFiles/SecretKey2", "AES"), "AES");
	}
}
