/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.SUser;
import Tools.CurrentSession;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author DELL PRCISION 3551
 */
public class JavamailUtil {
    SUser US=new SUser();

    public  void sendMail (String recepient) throws Exception {
        System.out.println("Preparing to send:");
        Properties properties = new Properties();

        String myAccountEmail = "samar.bouzezi@esprit.tn";
        String password = "191JFT3562@.";

        properties.put("com.hof.email.starttime", "20170519094544");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.connectiontimeout", "60000");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.timeout", "60000");
        properties.put("mail.transport.protocol", "smtp");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recepient);

        Transport.send(message);
        System.out.println("Message send successfully");
    }

    private  Message prepareMessage(Session session, String myAccountEmail, String recepient) throws SQLException {
        try {
            String a =US.findcodebyid(CurrentSession.userm.getId());
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("your reset code");
            message.setText("your code is  "+a);
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(JavamailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
