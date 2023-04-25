package fr.leo;

import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKey;

public class DriverCleGenerer {

	/*
	 * Il existe de familles de cryptographie :
	 * 	la cryptographie symetrique : on utilise une cl� pour chifrer et pour 
	 * 									dechifrer on utilise la meme cl�
	 * 									Donc c'est la meme cl� qui est utilis�e pour chifer et pour d�chifr�
	 * 									
	 *  la cryptographie asymetrique :  on utilise une cl� pour chifrer et pour 
	 *  								dechifrer on utilise une cl� diff�rente
	 *  			Ici on a une cl� publique et une cl� priv�e
	 *  			On crypte avec la cl� publique et on d�crypte avec la cl� priv�e !
	 *  			Du coup la cl� publique on peut la partager avec plusieurs applications, 
	 *  			 donc ils peuvent crypter les donn�es qu'ils vont m'envoyer,
	 *  			 et puis apr�s je suis le seul � �tre capable � d�crypter, � d�chifrer le contenu !
	 *  
	 *  Parmis les familles d'algoritmes les plus connus, il y a :	
	 *  	AES (Advanced Encryption Standard) pour la crypto symetrique
	 *  	RAS ( )  pour la crypto asymetrique	 *  
	 */
	
	public static void main(String[] args) throws Exception {
		CryptoUtilImpl cryptoUtilImpl = new CryptoUtilImpl();
		
		//		CRYPTAGE
		String data = "My Data .....";
		
		// cl� secrete g�n�r�e
		SecretKey secretKey = cryptoUtilImpl.generateSecretKey();
		byte[] secretKeyBytes = secretKey.getEncoded();		
		System.out.println( new String(secretKeyBytes) ); // �`�n@?��3d}�t
		System.out.println(Arrays.toString(secretKeyBytes));  //[-18, 96, -87, 110, 64, -112, -31, 3, -100, 5,
															  // 51, 100, 125, -60, 116, 28]
												  
		// Cette cl� g�n�r�e, il faudrait elle meme
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
