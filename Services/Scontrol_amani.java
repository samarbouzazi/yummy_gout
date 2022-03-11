/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Branche;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Tools.MaConnexion;

/**
 *
 * @author LENOVO
 */
public class Scontrol_amani {
    Connection cnx= MaConnexion.getInstance().getCnx();
      public static boolean Controlechar(Branche b) {
		String str = (b.getNomBranche()).toLowerCase();
                if (str.length() == 0)
                    return false;
		char[] charArray = str.toCharArray();
                
		for (int i = 0; i < charArray.length; i++) {
			char ch = charArray[i];
			if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
				return false;
			}
		}
		return true;
	}
     public boolean isNumeric(String text) {
             if (text == null || text.trim().equals("")) {
                 return false;
             }
             for (int iCount = 0; iCount < text.length(); iCount++) {
                 if (!Character.isDigit(text.charAt(iCount))) {
                    return false;
               }
           }
            return true;
        }

}

    

    

