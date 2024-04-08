/*
 * 
 */
package com.km.model;

import java.math.BigInteger;
import java.security.MessageDigest;
//import java.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// TODO: Auto-generated Javadoc
/**
 * The Class Encryption.
 */
public class Encryption {

	/**
	 * Gets the md 5.
	 *
	 * @param input the input
	 * @return the md 5
	 */
	// md5
	public static String getMd5(String input) {
		String salt = "678gjhgr5yedj8";
		input=input+salt;

		try {

			// Static getInstance method is called with hashing MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// digest() method is called to calculate message digest
			// of an input digest() return array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Gets the sha1.
	 *
	 * @param s the s
	 * @return the sha1
	 */
	// sha-1: -> thường được sử dụng
	public static String getSHA1(String s) {
		String salt = "678gjhgr5yedj8";
		String result = null;

		s = s + salt;
		try {
			byte[] dataBytes = s.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(md.digest(dataBytes));
		} catch (Exception e) {
			System.out.println("xãy ra lỗi trong quá trình mã hóa");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Checks if is true account.
	 *
	 * @param phonenumber the phonenumber
	 * @return true, if is true account
	 */
	public static boolean isTrueAccount( String phonenumber) {

		String regex="^[\\d]{10}$";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(phonenumber);
		return matcher.matches();
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isTrueAccount("0123456787"));
		System.out.println(isTrueAccount("Kimy122.kimmy@gmail.com"));
		System.out.println(isTrueAccount("011111111111111111"));
		System.out.println(isTrueAccount("0.0.0.0.0."));
		System.out.println(isTrueAccount("Kimy122-.kimmy@gmail.com"));

	}

}
