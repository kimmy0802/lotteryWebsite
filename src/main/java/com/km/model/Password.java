/*
 * 
 */
package com.km.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// TODO: Auto-generated Javadoc
/**
 * The Class Password.
 */
public class Password {
	
	/**
	 * Check password.
	 *
	 * @param p the p
	 * @return true, if successful
	 */
	public boolean checkPassword(String p) {
		if(p==null) return false;
		String regex="\\w{8,20}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher=pattern.matcher(p);		
		
		return matcher.matches();
	}
	
	// Tạo Mật khẩu mới ngẫu nhiên
	
	/**
	 * Creates the password.
	 *
	 * @return the string
	 */
	public  String createPassword() {
		String result="";
		String s="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";// lenghth: 62
		for(int i=0; i<8; i++) {
			Random generator = new Random();
			int value = generator.nextInt(62);
			result+=s.charAt(value);
		}
		return result;
	}
	
	
	//---------------------------------------- Mã hóa mật khẩu
	/**
	 * Gets the md 5.
	 *
	 * @param input the input
	 * @return the md 5
	 */
	// md5
		public  String getMd5(String input) {
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
		public  String getSHA1(String s) {
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
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Password p=new Password();
				System.out.println(p.getSHA1("12345"));
				String s = "12345678";
				System.out.println("Your HashCode Generated by MD5 is: " + p.getMd5(s));
				System.out.println("check passwor:" +p.checkPassword("qqq11 1111jj"));
				
	}

}
