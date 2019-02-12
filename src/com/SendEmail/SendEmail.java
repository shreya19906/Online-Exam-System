package com.SendEmail;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail extends HttpServlet {

   public static void send(String receiver,String subject,String messageText)
      throws ServletException, IOException {
    
	   String to=receiver;;
	   String sub=subject;
	   String msg=messageText;
	   final String user="pcpriyachauhan933@gmail.com";
	   String pass="9828922355";
	   Properties props=new Properties();
	   
	   props.put("mail.smtp.host", "smtp.gmail.com");
	   props.put("mail.smtp.port", "587");
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");
	   
	   Session session=Session.getInstance(props,new javax.mail.Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication()
		   {
			   return new PasswordAuthentication(user,pass);
		   }
	   });
	   
	   try {
		   
		   MimeMessage message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(user));
		   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		   message.setSubject(sub);
		   message.setText(msg);
		   BodyPart msgBodyPart=new MimeBodyPart();
		   msgBodyPart.setText(msg);
		   MimeMultipart multipart=new MimeMultipart();
		   multipart.addBodyPart(msgBodyPart);
		   
		   msgBodyPart=new MimeBodyPart();
		   String flename="C:\\Users\\SHREYA\\Desktop\\certificate1.png";
		   DataSource source=new FileDataSource(flename);
		   msgBodyPart.setDataHandler(new DataHandler(source));
		   msgBodyPart.setFileName(flename);
		   multipart.addBodyPart(msgBodyPart);
		   message.setContent(multipart);
		   
		   Transport.send(message);
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
  
   
} 