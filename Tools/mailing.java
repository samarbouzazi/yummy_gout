/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;
import java.io.File;
import java.io.IOException;
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
 * @author HP
 */
public class mailing {
    
    public  void sendMail() throws AddressException, MessagingException, IOException{
    Properties property = new Properties();
    
   property.put("mail.smtp.auth",true);
   property.put("mail.smtp.starttls.enable",true);
   property.put("mail.smtpsmtp.EnableSsl",true);
   property.put("mail.smtp.host","smtp.gmail.com");
   property.put("mail.smtp.port","25");
   property.put("mail.transport.protocl","smtp");
   
     Session session = Session.getInstance(property, new Authenticator() {
            
        @Override
        protected PasswordAuthentication getPasswordAuthentication (){
        
        return new PasswordAuthentication("yummygout@gmail.com","yummy*1999");
        }

        });
     // craeation of an empty object hen you fill the content with whatever you want 
        Message message = new MimeMessage(session);
          message.setSubject("Réclamation");
          
          Address addressTo = new InternetAddress("marwa.memmi@esprit.tn");
          message.setRecipient(Message.RecipientType.TO,addressTo);
          
          MimeMultipart emailContent = new MimeMultipart();
          
          //attachment.attachFile(new File("C:\\Users\\hp\\Documents\\NetBeansProjects\\FirstCrud\\src\\Files\\pdf.pdf"));

            
          //First The HTML Part 
          MimeBodyPart messageBodyPart = new MimeBodyPart();
          String htmlText = "<h1> une réclamation a été ajouter </h1><img src=\"cid:image\">";
          messageBodyPart.setContent(htmlText,"text/html");
                    
//           // second part the image 
//           MimeBodyPart fileAttach = new MimeBodyPart();
//            fileAttach.attachFile(new File("C:\\Users\\HP\\Desktop\\photo.png"));
//          
          //adding the image and the html Part 
          emailContent.addBodyPart(messageBodyPart);
         // emailContent.addBodyPart(fileAttach);

          //multipart.addBodyPart(attachment);
          
          //putting everything together in our Email Content 
          message.setContent(emailContent);
          // Sending the email
          Transport.send(message);
		  }
}