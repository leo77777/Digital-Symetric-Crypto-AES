package fr.leo;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DecryptAesTest {

	public static void main(String[] args) throws Exception {
		/*
		 * Maintenant, on fait l'op�ration inverse.
		 * Il faut absolument connaitre le secret !
		 * Comme on est en crypto symetrique,
		 * 	avec la meme cl� on crypte et on d�crypte
		 */							
		String receivedEncryptedData = "aTdsQ2ijTgJydJRWZIZPekqxuF32Pzaaatbwq9cGh9c=";
		// ci dessous, on d�code, car on avait ajout� une couche, on avait encod� en base64 !
		byte[] decodeEncryptedData = Base64.getDecoder().decode("aTdsQ2ijTgJydJRWZIZPekqxuF32Pzaaatbwq9cGh9c=");
				
		String mySecret ="azerty_azerty_az";
		SecretKey secretKey = new SecretKeySpec(mySecret.getBytes(), 0 , mySecret.length(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey); // on pr�cise que on veut decrypter !
		
		byte[] decryptedBytes = cipher.doFinal(decodeEncryptedData); // on d�crypte !
		System.out.println(new String(decryptedBytes)); // => This is my message
		
		/*
		 * "AES" est l'algo le plus utilis� et le plus solide !
		 * C'est le plus recommand� pour l'algo sym�trique !
		 */
	}
}
