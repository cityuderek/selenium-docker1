package com.vinsguru.util;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.opentelemetry.api.internal.StringUtils;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailHelper {
	public static void main(String[] args) {
	    Config.initialize();
	    
		sendEmail(Config.get("report.to"), "TMA selenium", "ffff", "test-output\\emailable-report.html");
	}
	
	public static void sendEmail(String to, String subject, String content, String file) {
	
	    final String username = Config.get("smtp.username");
	    final String password = Config.get("smtp.password");
	
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", true);
	    props.put("mail.smtp.starttls.enable", true);
	    props.put("mail.smtp.host", Config.get("smtp.host"));
	    props.put("mail.smtp.port", Config.get("smtp.port"));
	
	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(username, password);
	                }
	            });
	
	    try {
	
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(Config.get("smtp.from")));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(to));
	        message.setSubject(subject);
	        message.setText(content);
	
	        if(!StringUtils.isNullOrEmpty(file)) {
		        MimeBodyPart messageBodyPart = new MimeBodyPart();
		        Multipart multipart = new MimeMultipart();
	//	        String file = "path of file to be attached";
	//	        String fileName = "attachmentName";
		        DataSource source = new FileDataSource(file);
		        messageBodyPart.setDataHandler(new DataHandler(source));
	//	        messageBodyPart.setFileName(fileName);
		        multipart.addBodyPart(messageBodyPart);
		        message.setContent(multipart);
	        }
	
	
	        System.out.println("Sending");
	        Transport.send(message);
	        System.out.println("Sent");
	
	    } catch (MessagingException e) {
	        System.out.println("sendEmail error; to=" + to + ", subject=" + subject + ", content=" + content + ", file=" + file);
	        e.printStackTrace();
	    }
	  }

}
