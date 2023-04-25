package fr.leo;

import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKey;

public class Driver {

	/*
	 * Il existe deux familles de cryptographie :
	 * 	la cryptographie symetrique : on utilise une cl� pour chifrer et pour 
	 * 									dechifrer on utilise la meme cl�
	 * 									Donc c'est la meme cl� qui est utilis�e pour chifer et pour d�chifr�
	 * 									
	 *  la cryptographie asymetrique :  on utilise une cl� pour chifrer et pour 
	 *  								dechifrer on utilise une cl� diff�rente
	 *  			Ici on a une cl� publique et une cl� priv�e
	 *  			On crypte avec la cl� publique et on d�crypte avec la cl� priv�e !
	 *  
	 *  Parmis les familles d'algoritmes les plus connus, il y a :	
	 *  	AES (Advanced Encryption Standard) pour la crypto symetrique
	 *  	RAS ( )  pour la crypto asymetrique	 *  
	 */
	
	public static void main(String[] args) throws Exception {
		
		CryptoUtilImpl cryptoUtilImpl = new CryptoUtilImpl();	
		String data = "My Data .....";		
		String secret = "azerty_azerty_az";
		
		//		CRYPTAGE :
		String encryptedData = cryptoUtilImpl.encrypteAES(data.getBytes(), secret);
		System.out.println(encryptedData); // GsItoZZCEZql4taikMjlSw==		
		//		DECRYPTAGE :
		byte[] decryptedBytes = cryptoUtilImpl.decrypteAES(encryptedData, secret);
		System.out.println(new String(decryptedBytes)); // My Data .....
		
		
		//     GENERATION D'UNE CLE SECRETE :
		SecretKey secretKey = cryptoUtilImpl.generateSecretKey();
		byte[] encodedSecretKey = secretKey.getEncoded(); // ici on l'encode afin de pouvoir aficher la cl� en String		
		System.out.println( new String(encodedSecretKey) ); // ��#��=H��� ��x <= � chaque lancement on a un r�sultat diff�rent ! On a 16 caract�res !
		System.out.println(Arrays.toString(encodedSecretKey)); // [-58, -10, 35, -102, 15, -109, 61, 72, -81, -87, -95, 32, -63, -82, 26, 120]
																	   // on peut voir que on a 16 caract�res, cad 128 bits 

	
	}
}
