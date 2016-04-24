package com.awqotd.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailClass 
{
	private Properties properties;
	private Session session;
	Authenticator auth;
	public MailClass()
	{
		init();		
	}
	public void init()
	{
		properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.user", "aqotdmailer@gmail.com");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.port", "587");
		auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("aqotdmailer@gmail.com", "Adaptive1234");
            }
		};
		session = Session.getDefaultInstance(properties,auth);
	}
	public void createAndSendMsg(String from,List<String> to, String subject, String msg_content)
	{
		MimeMessage message = new MimeMessage(session);
		try 
		{
			message.setFrom(new InternetAddress(from));
			for (String to_add: to)
			{
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to_add));
			}
			message.setSubject(subject);
			message.setContent(msg_content, "text/html");
			Transport.send(message);
		}
		catch (AddressException e) 
		{
			e.printStackTrace();
		}
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}	
	}
	public static void main(String[] args) 
	{
		String from = "aqotdmailer@gmail.com";
		String subject = "Test Mail Adaptive Web";
		String msg_content = "<html><body><h3>If you can see this then AJITHS quiz of the day works!!</h3>"
				+ "<form>First name:<br><input type=\"text\" name=\"firstname\"><br>Last name:"
				+ "<br><input type=\"text\" name=\"lastname\"></form></body></html>";
		List<String> list = new ArrayList<String>();
		list.add("Anandh.Ravikumar@asu.edu");
		list.add("mmalaiar@asu.edu");
		list.add("pnatara8@asu.edu");
		list.add("avimalch@asu.edu");
		new MailClass().createAndSendMsg(from, list, subject, msg_content);
	}
}