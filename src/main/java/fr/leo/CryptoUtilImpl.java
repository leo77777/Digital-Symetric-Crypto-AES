package fr.leo;

import java.util.Base64;
import java.util.Formatter;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Hex;

public class CryptoUtilImpl {
	
	/*
	 * ENCODAGE
	 */	
	public String encodeToBase64(byte[] data) {
		return Base64.getEncoder().encodeToString(data);				
	}	
	public byte[] decodeFromBase64(String dataBase64) {
		return Base64.getDecoder().decode(dataBase64.getBytes());	
	}	
	public String encodeToBase64URL(byte[] data) {
		return Base64.getUrlEncoder().encodeToString(data);				
	}	
	public byte[] decodeFromBase64URL(String dataBase64URL) {
		return Base64.getUrlDecoder().decode(dataBase64URL.getBytes());	
	}	
	/*
	 * Solution avec jaxB !
	 */
	public String encodeToHex(byte[] data) {
		return DatatypeConverter.printHexBinary(data); // Avex Jaxb !
	}	
	/*
	 * Solution avec la librairie Apache que on a mis dans le pom.xml :
	 *  "commons-codec" !
	 */
	public String encodeToHexApacheCodec(byte[] data) {
		return Hex.encodeHexString(data);
	}	
	/*
	 * Solution perso
	 */
	public String encodeToHexNative(byte[] data) {
		Formatter formatter = new Formatter();
		for (byte b : data) {
			formatter.format("%02x", b);
		}
		return formatter.toString();
	}
	
	/*
	 * CRYPTAGE SYMETRIQUE AES
	 */
	// Cryptage Version sans le type "SecretKey" en parametre 
	public String encrypteAES(byte[] data, String secret) throws Exception  {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKey secretKey = new SecretKeySpec(secret.getBytes(), 0, secret.length(), "AES"); // 0 : position du 1er caractere de la clé
		cipher.init(Cipher.ENCRYPT_MODE, secretKey); // ici on précise que on veut encrypter ( pas décrypter !)
		byte[] encrytedData = cipher.doFinal(data); // ici, on crypte !  => i7lCh£Nrt”Vd†OzJ±¸]ö?6šjÖð«×‡×
		String encodedEncryptedData = Base64.getEncoder().encodeToString(encrytedData); // Maintenant, on l'encode au format texte
		return encodedEncryptedData;
	}
	// Cryptage Version avec le tpye "SecretKey" en parametre 
	public String encrypteAES(byte[] data, SecretKey secretKey) throws Exception  {
		Cipher cipher = Cipher.getInstance("AES");
		// SecretKey secretKey = new SecretKeySpec(secret.getBytes(), 0, secret.length(), "AES"); // 0 : position du 1er caractere de la clé
		cipher.init(Cipher.ENCRYPT_MODE, secretKey); // ici on précise que on veut encrypter ( pas décrypter !)
		byte[] encrytedData = cipher.doFinal(data); // ici, on crypte !  => i7lCh£Nrt”Vd†OzJ±¸]ö?6šjÖð«×‡×
		String encodedEncryptedData = Base64.getEncoder().encodeToString(encrytedData); // Maintenant, on l'encode au format texte
		return encodedEncryptedData;
	}
	
	// DeCryptage Version sans le type "SecretKey" en parametre 
	public byte[] decrypteAES(String encodedEncryptedData, String secret) throws Exception  {		
		byte[] decodeEncryptedData = Base64.getDecoder().decode(encodedEncryptedData);			
		SecretKey secretKey = new SecretKeySpec(secret.getBytes(), 0 , secret.length(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey); 	// on précise que on veut decrypter !		
		byte[] decryptedBytes = cipher.doFinal(decodeEncryptedData); // on décrypte !
		return decryptedBytes;
	}	
	// DeCryptage Version avec le tpye "SecretKey" en parametre 
	public byte[] decrypteAES(String encodedEncryptedData, SecretKey secretKey) throws Exception  {		
		byte[] decodeEncryptedData = Base64.getDecoder().decode(encodedEncryptedData);			
		// SecretKey secretKey = new SecretKeySpec(secret.getBytes(), 0 , secret.length(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey); 	// on précise que on veut decrypter !		
		byte[] decryptedBytes = cipher.doFinal(decodeEncryptedData); // on décrypte !
		return decryptedBytes;
	}
	
	//	GENERER UNE CLE SECRETE
	public SecretKey generateSecretKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128); // on l'initialise à 128 bits. Ici on peut augmenter => 192 ou 256 !
		return keyGenerator.generateKey();
	}
	
	// GENERER SA PROPRE CLE SECRETE
	public SecretKey generateSecretKey(String secret) throws Exception {
		SecretKey secretKey = new SecretKeySpec(secret.getBytes(), 0, secret.length(),"AES");
		return secretKey;
	}
	
}
