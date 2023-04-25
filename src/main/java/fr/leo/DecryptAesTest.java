package fr.leo;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DecryptAesTest {

	public static void main(String[] args) throws Exception {
		/*
		 * Maintenant, on fait l'opération inverse.
		 * Il faut absolument connaitre le secret !
		 * Comme on est en crypto symetrique,
		 * 	avec la meme clé on crypte et on décrypte
		 */							
		String receivedEncryptedData = "aTdsQ2ijTgJydJRWZIZPekqxuF32Pzaaatbwq9cGh9c=";
		// ci dessous, on décode, car on avait ajouté une couche, on avait encodé en base64 !
		byte[] decodeEncryptedData = Base64.getDecoder().decode("aTdsQ2ijTgJydJRWZIZPekqxuF32Pzaaatbwq9cGh9c=");
				
		String mySecret ="azerty_azerty_az";
		SecretKey secretKey = new SecretKeySpec(mySecret.getBytes(), 0 , mySecret.length(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey); // on précise que on veut decrypter !
		
		byte[] decryptedBytes = cipher.doFinal(decodeEncryptedData); // on décrypte !
		System.out.println(new String(decryptedBytes)); // => This is my message
		
		/*
		 * "AES" est l'algo le plus utilisé et le plus solide !
		 * C'est le plus recommandé pour l'algo symétrique !
		 */
	}
}
