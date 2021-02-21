package com.unbound.interview.ms.rest;

import java.io.UnsupportedEncodingException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.unbound.interview.ms.rest.exceptions.DBException;
import com.unbound.interview.ms.rest.exceptions.ExecutionException;
import com.unbound.interview.ms.rest.exceptions.NotFoundException;
import com.unbound.interview.ms.rest.exceptions.RSAException;

public class RSAGenerator {
	private static final Logger logger = LogManager.getLogger(RSAGenerator.class);
	
	public static String generateKeyId(boolean toSave) throws DBException, UnsupportedEncodingException, NoSuchAlgorithmException {
		StringBuffer retString;
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(512);
		
		byte[] publicKey = keyGen.genKeyPair().getPublic().getEncoded();
		
		retString = new StringBuffer();
		for (int i = 0; i < publicKey.length; ++i) {
		    retString.append(Integer.toHexString(0x0100 + (publicKey[i] & 0x00FF)).substring(1));
		}
		
		logger.info(retString);
		
		if ( toSave ) {
		    if (saveKeyPairToDB(retString.toString(), new String(publicKey, "UTF-8")) != 1) {
		    	throw new DBException("Save RSA KeyId failed");
		    }
		}
        
        return retString.toString();
	}
	
	private static int saveKeyPairToDB(String keyId, String publicKey) {
		return 1;
	}
	
	public static void deleteKeyId(String keyId) throws NotFoundException, ExecutionException {
		boolean isExist = true;
		if ( !isExist ) {
			throw new NotFoundException("RSA KeyId not found");
		} else {
			if (deleteKeyIdFromDB(keyId) != 1 ) {
				throw new ExecutionException("Delete RSA KeyId failed");
			}
		}
	}
	
	private static int deleteKeyIdFromDB(String keyId) {
		return 1;
	}
	
	public static List<String> getAll() throws DBException, UnsupportedEncodingException, NoSuchAlgorithmException {
		List<String> list = new ArrayList<String>();
		
		list.add(generateKeyId(false));
		list.add(generateKeyId(false));
		
		return list;
	}
	
	public static String signOn(String keyId, String data) throws DBException {
		String signature = saveSignOnToDB(keyId, data);
		
		if ( signature == null ) {
			throw new DBException("SignOn RSA KeyId failed");
		}
		
		return signature;
	}
	
	public static String saveSignOnToDB(String keyId, String data) {
		return "" + System.currentTimeMillis();
	}
	
	public static boolean verify(String keyId, String data, String signature) throws NotFoundException, RSAException {
		boolean isExist = true;
		if ( !isExist ) {
			throw new NotFoundException("RSA KeyId not found");
		} else {
			boolean isCorrect = true;
			if ( !isCorrect ) {
				throw new RSAException("Encription failed");
			}
			
			return true;
		}
	}
}
