/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

<<<<<<< HEAD
import entities.categorie;
=======
import Entities.Fournisseur;
import Entities.Stock;
import Entities.User;
import Tools.MaCon;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
>>>>>>> 8845519aac4386f17f05bd0a9b2fb7ace06bb4ce

/**
 *
 * @author chaim
 */
public class Scontrol {
    
<<<<<<< HEAD
      public static boolean Controlechar(categorie c) {
		String str = (c.getNomcat()).toLowerCase();
=======
    Connection cnx = MaCon.getInstance().getCnx();
    
    
   public static boolean Controlechar(Stock f) {
		String str = (f.getNoms()).toLowerCase();
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
    
    
=======
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
    
    
    
   
>>>>>>> 8845519aac4386f17f05bd0a9b2fb7ace06bb4ce
}
