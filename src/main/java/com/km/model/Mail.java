/*
 * 
 */
package com.km.model;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// TODO: Auto-generated Javadoc
/**
 * The Class Mail.
 * chứa các phương thức liên quan đến chức năng gửi mail
 */
public class Mail {
	
	/**
	 * Send mail.
	 *
	 * @param to the to
	 * @param messenger the messenger
	 * @return true, if successful
	 */
	public boolean sendMail(String to, String messenger) {
		boolean result = false;
		final String from = "xosokienthiet.tailoc@gmail.com";// email nguoi gui
		final String password = "amtrgxynlgnwkmti"; // mk

		// properties: Khai bao cac thuoc tinh
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");// SMTP host
		props.put("mail.smtp.port", "587");// TLS 587; SSL 465
		props.put("mail.smtp.auth", "true");// phai login
		props.put("mail.smtp.starttls.enable", "true");

		// create Authenticator
		Authenticator auth = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from, password);
			}
		};

		// Phien lam viec
		Session session = Session.getInstance(props, auth);
		// create a messenger
		MimeMessage msg = new MimeMessage(session);

		try {
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.setFrom(from);// nguoi gui
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));// nguoi nhan
			// tieu de
			msg.setSubject("Mật khẩu tài khoản Xô số kiến thiết");
			// qd ngay gui
			msg.setSentDate(new Date());
			// qd email nhan phan hoi
			msg.setReplyTo(InternetAddress.parse(to, false));
			// noi dung
			msg.setContent("<html><body><p >Mật khẩu tài khoản của bạn là: <span style='color:red;'>"+messenger+"</span></p></body></html>",
					"text/html; charset=UTF-8");

			// gui email
			Transport.send(msg);
			System.out.println("gui thu thanh cong");
			result=true;

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println("gui thu That bai");
			result=false;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Send unlock accountk mail.
	 *
	 * @param to the to
	 * @param messenger the messenger
	 * @return true, if successful
	 */
	public boolean sendUnlockAccountkMail(String to, String messenger) {
		boolean result = false;
		final String from = "xosokienthiet.tailoc@gmail.com";// email nguoi gui
		final String password = "amtrgxynlgnwkmti"; // mk

		// properties: Khai bao cac thuoc tinh
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");// SMTP host
		props.put("mail.smtp.port", "587");// TLS 587; SSL 465
		props.put("mail.smtp.auth", "true");// phai login
		props.put("mail.smtp.starttls.enable", "true");

		// create Authenticator
		Authenticator auth = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from, password);
			}
		};

		// Phien lam viec
		Session session = Session.getInstance(props, auth);
		// create a messenger
		MimeMessage msg = new MimeMessage(session);

		try {
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.setFrom(from);// nguoi gui
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));// nguoi nhan
			// tieu de
			msg.setSubject("Khôi phục Tài khoản");
			// qd ngay gui
			msg.setSentDate(new Date());
			// qd email nhan phan hoi
			msg.setReplyTo(InternetAddress.parse(to, false));
			// noi dung
			msg.setContent("<html><body><p>Tài khoản của bạn đã được khôi phục. Mật khẩu mới của bạn là: <span style='color:red;'>"+messenger+"</span></p></body></html>",
					"text/html; charset=UTF-8");

			// gui email
			Transport.send(msg);
			System.out.println("gui thu thanh cong");
			result=true;

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println("gui thu That bai");
			result=false;
			e.printStackTrace();
		}
		return result;
	}

}
