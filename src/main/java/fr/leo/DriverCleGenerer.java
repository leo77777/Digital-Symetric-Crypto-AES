package fr.leo;

import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKey;

public class DriverCleGenerer {

	/*
	 * Il existe de familles de cryptographie :
	 * 	la cryptographie symetrique : on utilise une clé pour chifrer et pour 
	 * 									dechifrer on utilise la meme clé
	 * 									Donc c'est la meme clé qui est utilisée pour chifer et pour déchifré
	 * 									
	 *  la cryptographie asymetrique :  on utilise une clé pour chifrer et pour 
	 *  								dechifrer on utilise une clé différente
	 *  			Ici on a une clé publique et une clé privée
	 *  			On crypte avec la clé publique et on décrypte avec la clé privée !
	 *  			Du coup la clé publique on peut la partager avec plusieurs applications, 
	 *  			 donc ils peuvent crypter les données qu'ils vont m'envoyer,
	 *  			 et puis aprés je suis le seul à être capable à décrypter, à déchifrer le contenu !
	 *  
	 *  Parmis les familles d'algoritmes les plus connus, il y a :	
	 *  	AES (Advanced Encryption Standard) pour la crypto symetrique
	 *  	RAS ( )  pour la crypto asymetrique	 *  
	 */
	
	public static void main(String[] args) throws Exception {
		CryptoUtilImpl cryptoUtilImpl = new CryptoUtilImpl();
		
		//		CRYPTAGE
		String data = "My Data .....";
		
		// clé secrete générée
		SecretKey secretKey = cryptoUtilImpl.generateSecretKey();
		byte[] secretKeyBytes = secretKey.getEncoded();		
		System.out.println( new String(secretKeyBytes) ); // î`©n@?áœ3d}Ät
		System.out.println(Arrays.toString(secretKeyBytes));  //[-18, 96, -87, 110, 64, -112, -31, 3, -100, 5,
															  // 51, 100, 125, -60, 116, 28]
												  
		// Cette clé générée, il faudrait elle meme
		// encore l'encoder en base64, afin qu'elle ne soit pas lisible
		// par tout le monde !
		String encodedSecretKey = Base64.getEncoder().encodeToString(secretKeyBytes); 
		System.out.println(encodedSecretKey); // 7mCpbkCQ4QOcBTNkfcR0HA==

		String encryptedData = cryptoUtilImpl.encrypteAES(data.getBytes(), secretKey);
		System.out.println(encryptedData);  // UkZLcaCOy9XadNhEJqQeew==
		
		//		DECRYPTAGE
		byte[] decryptedBytes = cryptoUtilImpl.decrypteAES(encryptedData, secretKey);
		System.out.println(new String(decryptedBytes)); // My Data .....
	}

}
