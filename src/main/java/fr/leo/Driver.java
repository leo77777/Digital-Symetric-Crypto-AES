package fr.leo;

import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKey;

public class Driver {

	/*
	 * Il existe deux familles de cryptographie :
	 * 	la cryptographie symetrique : on utilise une clé pour chifrer et pour 
	 * 									dechifrer on utilise la meme clé
	 * 									Donc c'est la meme clé qui est utilisée pour chifer et pour déchifré
	 * 									
	 *  la cryptographie asymetrique :  on utilise une clé pour chifrer et pour 
	 *  								dechifrer on utilise une clé différente
	 *  			Ici on a une clé publique et une clé privée
	 *  			On crypte avec la clé publique et on décrypte avec la clé privée !
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
		byte[] encodedSecretKey = secretKey.getEncoded(); // ici on l'encode afin de pouvoir aficher la clé en String		
		System.out.println( new String(encodedSecretKey) ); // Æö#š“=H¯©¡ Á®x <= à chaque lancement on a un résultat différent ! On a 16 caractères !
		System.out.println(Arrays.toString(encodedSecretKey)); // [-58, -10, 35, -102, 15, -109, 61, 72, -81, -87, -95, 32, -63, -82, 26, 120]
																	   // on peut voir que on a 16 caractères, cad 128 bits 

	
	}
}
