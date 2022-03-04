/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 *
 * @author HP
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MessagingException, AddressException, IOException {
        mailing m = new mailing();
        m.sendMail();
    }
    
}
