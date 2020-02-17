package com.example.hybrid.encryptClient1;

import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class StartEncryption {

	public PublicKey getPublic(String filename, String algorithm) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance(algorithm);
		return kf.generatePublic(spec);
	}
	
	public SecretKeySpec getSecretKey(String filename, String algorithm) throws IOException{
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		return new SecretKeySpec(keyBytes, algorithm);
	}
	
	public static void main(String[] args) throws IOException, GeneralSecurityException, Exception{
		StartEncryption startEnc = new StartEncryption();
		
		File originalKeyFile = new File("OneKey/secretKey");
		File encryptedKeyFile = new File("EncryptedFiles/encryptedSecretKey");
		new EncryptKey(startEnc.getPublic("KeyPair/publicKey_Client2", "RSA"), originalKeyFile, encryptedKeyFile, "RSA");
		
		File originalFile = new File("word.txt");
		File encryptedFile = new File("EncryptedFiles/encryptedFile");
		new EncryptData(originalFile, encryptedFile, startEnc.getSecretKey("OneKey/secretKey", "AES"), "AES");
	}
}
