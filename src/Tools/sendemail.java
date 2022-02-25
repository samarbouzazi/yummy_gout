/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author LENOVO
 */
public class sendemail {
    public static void sendMail(String recepient) throws Exception{
        System.out.println("Preparing to send email");
        Properties properties= new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put ("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.host", "587");
        String Myaccountmail="yummygout@gmail.com";
        String password= "yummy*1999";
        //properties.put("mail.transport.protocol", "smtp");
        Session session = Session.getInstance(properties, new Authenticator() {
        @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Myaccountmail,password);
        }
        });
        Message message= prepareMessage(session,Myaccountmail, recepient);
        
       
        System.err.println("message sent successfuly");
         Transport.send(message);}

    private static Message prepareMessage(Session session, String Myaccountmail, String recepient) {
        try {
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(Myaccountmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("teeest");
            message.setText("hhhhhhhh");
            return message;
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
}
;