/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import org.json.simple.*;
import Entities.User;
import Tools.MaCon;
//import static com.sun.org.apache.bcel.internal.classfile.Utility.toHexString;
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigInteger; 
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.util.UUID;
import org.json.simple.JSONObject;


/**
 *
 * @author marwen
 */
public class SUser {
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    { 
        // Static getInstance method is called with hashing SHA 
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
        // digest() method called 
        // to calculate message digest of an input 
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8)); 
    }
    
        public static String toHexString(byte[] hash)
        {
            // Convert byte array into signum representation 
            BigInteger number = new BigInteger(1, hash); 

            // Convert message digest into hex value 
            StringBuilder hexString = new StringBuilder(number.toString(16)); 

            // Pad with leading zeros
            while (hexString.length() < 32) 
            { 
                hexString.insert(0, '0'); 
            } 

            return hexString.toString();  }
    
    
    
    Connection con = MaCon.getInstance().getCnx();
    private Statement ste;

    public SUser() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ajouterUser(User user) throws SQLException {
     try {
        String req = "INSERT INTO USERS (email, roles , password, cin, username) VALUES (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, user.getEmail());
            st.setString(2, user.getRoles());
            st.setString(3,user.getPassword() );
            st.setInt(4, user.getCin());
         st.setString(5, user.getUsername());
           st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
     public void Sendcode(int id_user,String code) throws SQLException{
     String req="INSERT INTO codemail (id_user,code) VALUES('"+id_user+"','"+code+"');";
     ste.executeUpdate(req);
        System.out.println("Code generated");
     
 }
    public int checkcode (int id_user,String code) throws SQLException{
         int nb = 0;

        String req1 = "SELECT count(code) FROM `codemail` WHERE id_user";
        PreparedStatement preparedStatement = con.prepareStatement(req1);

        ResultSet result = preparedStatement.executeQuery();
        if (result.first()) {
            nb = result.getInt(1);
        }

        return nb;
        
    }
    
     public void modifierUser(User user) throws SQLException {
        String sql = "UPDATE users SET `email`=?,`roles`=?,`password`=?,`cin`=?, `username`=? "
              +" WHERE id=" + user.getId();
        PreparedStatement ste;
        String pass="";
        try{pass=toHexString(getSHA( user.getPassword()));}
        catch(NoSuchAlgorithmException e) { 
            System.out.println("Exception thrown for incorrect algorithm: " + e); 
        } 
        try {
            ste = con.prepareStatement(sql);
            
            ste.setString(1, user.getEmail());
            ste.setString(2,"'['"+user.getRoles()+"']'");
            ste.setString(3, pass);        
            ste.setInt(4, user.getCin());
            ste.setString(5, user.getUsername());
            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de l'utilisateur : " + user.getUsername() + " a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     
       public void supprimerUser(User user) {

        try {
            String req = "DELETE FROM `users` WHERE `users`.`id` = ?";
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1, user.getId());
            ste.executeUpdate();
            System.out.println("Utilisateur supprimé");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
       
        public List<User> readAll() throws SQLException {
        List<User> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from users ");
        User user = null;
        while (res.next()) {
            user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));          
            list.add(user);
            System.out.println("all" + list);
        }
        return list;
    }
        public String findcodebyid(int id_user)throws SQLException {
        String req = "SELECT code from codemail where id_user='" + id_user + "'";
        ResultSet res = ste.executeQuery(req);
        String code = null;

        while (res.next()) {
            code = res.getString("code");

        }

        return code;
    }
        public String getUserRole(int id_user) throws SQLException {
        String req = "SELECT roles from users where id='" + id_user + "'";
        ResultSet res = ste.executeQuery(req);
        String role = null;

        while (res.next()) {
            
             String jsonString  = res.getString("roles");
           
//            JSONObject obj = new JSONObject(jsonString);
//            String pageName = obj.getJSONObject("pageInfo").getString("pageName");

        }

        return role;
    }

         public List<User> readAllUserByRole(String role) throws SQLException {
        List<User> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from users WHERE roles='" + role + "'");
        User user = null;
        while (res.next()) {
            user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));        
            list.add(user);
        }
        System.out.println("all" + list);

        return list;
    }
         
        public User authentifier(String email, String pwd) throws SQLException {
        String req = "select * from users WHERE email=? and password=?";
        String pass="";
        try{pass=toHexString(getSHA(pwd));}
        catch(NoSuchAlgorithmException e) { 
            System.out.println("Exception thrown for incorrect algorithm: " + e); 
        } 
        PreparedStatement steprep = con.prepareStatement(req);
        steprep.setString(1, email);
        steprep.setString(2, pass);

        ResultSet res = steprep.executeQuery();

        User user = null;
        if (res.next()) {
            user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));       
        }

        return user;
    }
      
        
         public List<User> TrierParUserName() throws SQLException {
        List<User> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from users ORDER BY username ASC");
        User user = null;
        while (res.next()) {
            user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));       
            list.add(user);
        }
        System.out.println(list);
        return list;
    }

         
         public List<User> TrierParCIN() throws SQLException {
        List<User> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from users ORDER BY cin ASC");
        User user = null;
        while (res.next()) {
            user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));       
            list.add(user);
        }
        System.out.println(list);
        return list;
    }
         
            public User getUserById(int id_user) throws SQLException {
        User user = new User();
      
            String req = "SELECT * from users where id='" + id_user + "'";
            ResultSet res = ste.executeQuery(req);
            while (res.next()) {
                          user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));        
            }
                    System.out.println(user);
                    return user;

            }
            
            
    public Integer getUserByUserName(String username) throws SQLException{
         User user = new User();

        String req = "SELECT * from users where username='" + username + "'";
        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));
        }
        System.out.println(user);
        return user.getId();

    }
    public int getIdbymail(String mail) {
        try {
            PreparedStatement st = con.prepareStatement("select * from users where email=?");
            st.setString(1, mail);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;

    }
     public void setNewMotPass(int idUser ,String pass){
       PreparedStatement st;
        
        try{pass=toHexString(getSHA( pass));}
        catch(NoSuchAlgorithmException e) { 
            System.out.println("Exception thrown for incorrect algorithm: " + e); 
        } 
        try {
            String req = "UPDATE `users` SET `password` ='" + pass + "' WHERE `users`.`id` = "+idUser;
            st = con.prepareStatement(req);
            st.executeUpdate(req);
             System.out.println(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
        
    }
     public String getPassbyId(int id) {
        try {
                Connection con = MaCon.getInstance().getCnx();

            PreparedStatement st = con.prepareStatement("select * from users where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
      }
     public boolean Login( String email, String password){
     
         boolean flag=true; ;
        try {
            Statement st= con.createStatement();
            ResultSet rs = st.executeQuery("Select * from users where email='"+email+"' and password ='"+password+"' ");
            if ( rs.next()){
               flag = true;
            }else {
                    flag = false ;
            }
        } catch(SQLException ex) {
                System.out.println(ex);
                }
                return flag ;
     }
           
            
    
}
