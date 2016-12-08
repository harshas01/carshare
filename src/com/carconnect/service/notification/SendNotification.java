/**
 * 
 */
package com.carconnect.service.notification;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author 
 *
 */
public class SendNotification {

	
	 public void sendNotification(String body, String subject, String toEmail)
	    {
			System.out.println("Starting Process on: ");
			ReadConfig readConfig = new ReadConfig();
			final String senderEmailId = readConfig.getValueForKey("senderEmailId");
			final String senderPassword = readConfig.getValueForKey("senderPassword");
			String fromEmail = readConfig.getValueForKey("fromEmail");
//			String toEmail = readConfig.getValueForKey("toEmail");
			String emailSubject=subject;//readConfig.getValueForKey("subject");
			StringBuilder emailBody=new StringBuilder();
//			emailBody.append(readConfig.getValueForKey("body"))
			emailBody.append(body);
			System.out.println("toEmail: "+toEmail);
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
	
			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(senderEmailId,senderPassword);
					}
				});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(fromEmail));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(toEmail));
				message.setSubject(emailSubject);
				message.setText(emailBody.toString());

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	    }

}
