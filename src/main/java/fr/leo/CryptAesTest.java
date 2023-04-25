package fr.leo;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CryptAesTest {

	public static void main(String[] args) throws Exception {
		
		String data = "This is my message";
		/*
		 * La clé secrete doit respecter une certaine taille : sinon une exception est levée
		 * 		128 bits<=>16 caractères
		 * 		192 bits<=>24 caractères
		 * 		256 bits<=>32 caractères
		 */
		String secret = "azerty_azerty_az"; // clé secrete, clé privée ! 
		
		Cipher cipher = Cipher.getInstance("AES");
		SecretKey secretKey = new SecretKeySpec(secret.getBytes(), 0, secret.length(), "AES"); // 0 : position du 1er caractere de la clé
		cipher.init(Cipher.ENCRYPT_MODE, secretKey); // ici on précise que on veut encrypter ( pas décrypter !)
		byte[] encrytedData = cipher.doFinal(data.getBytes()); // ici, on crypte !
		System.out.println(new String(encrytedData)); // i7lCh£Nrt”Vd†OzJ±¸]ö?6šjÖð«×‡×
		
		// Maintenant, on l'encode au format texte Base64
		byte[] encodedEncryptedData = Base64.getEncoder().encode(encrytedData);
		System.out.println(new String(encodedEncryptedData));  // aTdsQ2ijTgJydJRWZIZPekqxuF32Pzaaatbwq9cGh9c=
		
		String encodedEncryptedDataString = Base64.getEncoder().encodeToString(encrytedData); // encodage format string !
		System.out.println(encodedEncryptedDataString); // aTdsQ2ijTgJydJRWZIZPekqxuF32Pzaaatbwq9cGh9c=
		
		/*
		 * Ci dessous, le message, et le message encodé
		 */
		System.out.println();
		System.out.println(data); // This is my message
		System.out.println(encodedEncryptedDataString);	// aTdsQ2ijTgJydJRWZIZPekqxuF32Pzaaatbwq9cGh9c=	
	}
}
