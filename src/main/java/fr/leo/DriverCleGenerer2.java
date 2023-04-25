package fr.leo;

import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKey;

public class DriverCleGenerer2 {
	
	public static void main(String[] args) throws Exception {
		CryptoUtilImpl cryptoUtilImpl = new CryptoUtilImpl();
		
		//		CRYPTAGE
		String data = "My Data .....";		
		
		SecretKey secretKey2 = cryptoUtilImpl.generateSecretKey("azerty_azerty_az"); //<= MODIFICATION !
		byte[] secretKeyBytes = secretKey2.getEncoded();		
		System.out.println( new String(secretKeyBytes) ); // azerty_azerty_az
		System.out.println(Arrays.toString(secretKeyBytes));   // [97, 122, 101, 114, 116, 121, 95, 97, 122,
															   // 101, 114, 116, 121, 95, 97, 122]
		
		String encodedSecretKey = Base64.getEncoder().encodeToString(secretKeyBytes); 
		System.out.println(encodedSecretKey); // YXplcnR5X2F6ZXJ0eV9heg==

		String encryptedData = cryptoUtilImpl.encrypteAES(data.getBytes(), secretKey2);
		System.out.println(encryptedData); // GsItoZZCEZql4taikMjlSw==
		
		//		DECRYPTAGE
		byte[] decryptedBytes = cryptoUtilImpl.decrypteAES(encryptedData, secretKey2);
		System.out.println(new String(decryptedBytes));  //My Data .....
	}
}
