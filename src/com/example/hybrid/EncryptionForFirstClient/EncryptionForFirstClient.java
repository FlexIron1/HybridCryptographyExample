package com.example.hybrid.EncryptionForFirstClient;

import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class EncryptionForFirstClient {

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
	
	public static void main(String[] args) throws Exception{
		EncryptionForFirstClient startEnc = new EncryptionForFirstClient();
		
		File originalKeyFile = new File("OneKey/secretKey2");
		File encryptedKeyFile = new File("EncryptedFiles/encryptedSecretKey2");
		new EncryptKeyClient2(startEnc.getPublic("KeyPair/publicKey_Client1", "RSA"), originalKeyFile, encryptedKeyFile, "RSA");
		
		File originalFile = new File("message for 1 client .txt");
		File encryptedFile = new File("EncryptedFiles/encryptedFile2");
		new EncryptDataClient2(originalFile, encryptedFile, startEnc.getSecretKey("OneKey/secretKey2", "AES"), "AES");
	}
}
