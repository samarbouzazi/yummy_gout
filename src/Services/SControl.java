/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

<<<<<<< HEAD
import entities.Branche;
=======
import Entities.Fournisseur;
import Entities.Stock;
import Entities.User;
import Tools.MaCon;
>>>>>>> 8845519aac4386f17f05bd0a9b2fb7ace06bb4ce
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import tools.MaConnexion;

/**
 *
 * @author LENOVO
 */
<<<<<<< HEAD
public class Scontrol {
    Connection cnx= MaConnexion.getInstance().getCnx();
      public static boolean Controlechar(Branche b) {
		String str = (b.getNomBranche()).toLowerCase();
=======
public class SControl {
    
    Connection cnx = MaCon.getInstance().getCnx();
    
    
   public static boolean Controlechar(Stock f) {
		String str = (f.getNoms()).toLowerCase();
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
    public int existe(Fournisseur f) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from fournisseurs WHERE addf = '" + f.getAddf()+"'");
        int size = 0;
        rs.next();
        size=rs.getInt(1);
        return size;
    }
    //  public static void showNotif(String text, String text2) {
//        Notifications notificationBuilder = Notifications.create()
//                .title(text)
//                .text(text2)
//                .graphic(null)
//                .hideAfter(Duration.ofSeconds(10))
//                
//                .onAction(new EventHandler<ActionEvent>() {
//                    public void handle(ActionEvent event) {
//
//                    }
//                });
//
//        notificationBuilder.darkStyle();
//        notificationBuilder.showConfirm();
//    }
    public static boolean Controle(Fournisseur f) {
		String str = (f.getNomf()).toLowerCase();
>>>>>>> 8845519aac4386f17f05bd0a9b2fb7ace06bb4ce
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
<<<<<<< HEAD
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
     public int exist(Branche y) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from branche WHERE Emplacement = '" + y.getEmplacement() +"'");
        int size = 0;
        rs.next();
        size=rs.getInt(1);
        return size;
    }
=======
    
    
    
   
>>>>>>> 8845519aac4386f17f05bd0a9b2fb7ace06bb4ce
}

    

    

