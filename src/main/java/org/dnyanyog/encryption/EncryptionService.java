package org.dnyanyog.encryption;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class EncryptionService
{
	public static final String SECRET_KEY="5F270B070EF2F0BAB8123A810368B0E4";
	public static final String ALGORITHM="AES";
	
	public static SecretKey secretKey;
	public static Cipher cipher;

	static 
	{
		secretKey=new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8),ALGORITHM);//create secret key
		
		try
		{
			cipher=Cipher.getInstance(ALGORITHM);//cipher instance responsible to encrypt and decrypt
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);//initialize cipher with secret key
		}catch(InvalidKeyException e)
		{
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		} catch (NoSuchPaddingException e) 
		{
			e.printStackTrace();
		}
		
	}
	public String encrypt(String data) throws Exception
	{
		
		byte[] encryptedData=cipher.doFinal(data.getBytes());//encrypt data into bytes
		return Base64.getEncoder().encodeToString(encryptedData);//encode byte data into string
		
	}
	public static String decrypt(String encryptedData) throws Exception
	{
		byte[] decryptedData=cipher.doFinal(Base64.getDecoder().decode(encryptedData));//decrypt data into bytes
		return new String(decryptedData,StandardCharsets.UTF_8);//convert decrypted data into string
	}
	public static SecretKey generateAesKey()
	{
		try
		{
			KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
			keyGenerator.init(256);
			return keyGenerator.generateKey();
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException("Error generating AES key",e);
		}
	}
	public boolean encrypt(boolean equals) {
		// TODO Auto-generated method stub
		return false;
	}

}
