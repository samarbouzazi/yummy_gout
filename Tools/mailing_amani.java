/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Services.LivraisonService;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author LENOVO
 */
public class mailing_amani {

    public void sendMail() throws AddressException, MessagingException, IOException, SQLException{
        LivraisonService ls=new LivraisonService();
        List<String>liste=ls.getlistlivreur();
    Properties property = new Properties();   
   property.put("mail.smtp.auth",true);
   property.put("mail.smtp.starttls.enable",true);
   property.put("mail.smtpsmtp.EnableSsl",true);
   property.put("mail.smtp.host","smtp.gmail.com");
   property.put("mail.smtp.port","587");
   property.put("mail.transport.protocl","smtp");
   
     Session session = Session.getInstance(property, new Authenticator() {
            
        @Override
        protected PasswordAuthentication getPasswordAuthentication (){
        
        return new PasswordAuthentication("yummygout@gmail.com","yummy*1999");
        }

        });
     // craeation of an empty object hen you fill the content with whatever you want 
        Message message = new MimeMessage(session);
          message.setSubject("Livraison");
          for(int i=0; i<liste.size(); i++){
          Address addressTo = new InternetAddress(liste.get(i));
          message.setRecipient(Message.RecipientType.TO,addressTo);
          String ch=ls.bodymail(liste.get(i));
          MimeMultipart emailContent = new MimeMultipart();          
          //attachment.attachFile(new File("C:\\Users\\tchet\\Documents\\NetBeansProjects\\FirstCrud\\src\\Files\\pdf.pdf"));           
          //First The HTML Part 
          MimeBodyPart messageBodyPart = new MimeBodyPart();
          String htmlText = ch;
          messageBodyPart.setContent(htmlText,"text/html");
                    
           // second part the image 
           MimeBodyPart fileAttach = new MimeBodyPart();
           fileAttach.attachFile(new File("C:\\Users\\Smayra\\Pictures\\class.png"));
          
          //adding the image and the html Part 
          emailContent.addBodyPart(messageBodyPart);
          emailContent.addBodyPart(fileAttach);

          //multipart.addBodyPart(attachment);
          
          //putting everything together in our Email Content 
          message.setContent(emailContent);
          // Sending the email
          Transport.send(message);
		  }}
    
}
