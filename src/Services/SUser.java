/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Fournisseur;
import Entities.User;
import Tools.MaCon;
import static com.sun.org.apache.bcel.internal.classfile.Utility.toHexString;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import org.json.simple.JSONObject;

/**
 *
 * @author DELL PRCISION 3551
 */
public class SUser {
    Connection cnx = MaCon.getInstance().getCnx();
    private Statement ste;

public void ajouter(User u) {
        SControl sc=new SControl();
        
        String sql ="insert into user(nomu,prenomu,cinu,telu,emailu,password,role) values(?,?,?,?,?,?,?) ";
        try {
            if( sc.Controlechar(u)){
        if (sc.existe(u)==0 ){
            
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, u.getNomu());
            ste.setString(2, u.getPrenomu());
            ste.setInt(3, u.getCinu());
            ste.setInt(4, u.getTelu());
            ste.setString(5, u.getEmailu());
            ste.setString(6, u.getPassword());
            ste.setString(7, u.getRole());
            
            ste.executeUpdate();
            System.out.println("Compte Ajouté"); 
            
        }
        else
        {
            System.out.println("Compte déjà existe"); 
        }
        }
        else
        {
            System.out.println("Compte invalide"); 
        
        } 
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}
public List<User> afficher() {
        List<User> usr = new ArrayList<>();
        String sql ="select * from user";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                User u = new User();
            u.setIdu( rs.getInt("idu"));
            u.setNomu(rs.getString("nomu"));
            u.setPrenomu(rs.getString("prenomu"));
            u.setCinu(rs.getInt("cinu"));
            u.setTelu(rs.getInt("telu"));
            u.setEmailu(rs.getString("emailu"));
            u.setPassword(rs.getString("password"));
            u.setRole(rs.getString("role"));
                usr.add(u);
//                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return usr;
    }

public ObservableList<User> RECHERCHE(int idu ) {
         ObservableList<User> usr = FXCollections.observableArrayList();
        String req = "SELECT * FROM user where idu LIKE '" + idu + "%'  ";
       

        ObservableList<User> list=FXCollections.observableArrayList();
        try {
           Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){
               
              User u=new User(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4),rst.getInt(5),rst.getString(6),rst.getString(7),rst.getString(8));
               list.add(u);
           }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
}

public void update(User u) {

        try {
            String req="UPDATE user SET `nomu`='"+u.getNomu()+"',`prenomu`='"+u.getPrenomu() +"',`cinu`='"+u.getCinu()+"',`telu`='"+u.getTelu()+"',`emailu`='"+u.getEmailu()+"',`password`='"+u.getPassword()+"',`role`='"+u.getRole()+"' WHERE `idu`='"+u.getIdu()+"'";
            Statement st=cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Compte modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

public void deletef(int idu) {
        
         
        try {
            String req="DELETE FROM user WHERE idu="+idu+";";
            
            Statement st=cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Compte supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
public List<User> chercherav(String facteur){
          String req="SELECT * FROM user WHERE (nomu LIKE ? or prenomu LIKE ? or emailu LIKE ? or role LIKE ? )";
            MaCon myCNX = MaCon.getInstance();
            String ch="%"+facteur+"%";
            ArrayList<User> myList= new ArrayList();
        try {
            Statement st = myCNX.getCnx()
                    .createStatement();
            PreparedStatement pst = myCNX.getCnx().prepareStatement(req);
            pst.setString(1, ch);
            pst.setString(2, ch);
            pst.setString(3, ch);
            pst.setString(4, ch);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                User u = new User();
                u.setIdu(rs.getInt(1));
                u.setNomu(rs.getString(2));
                u.setPrenomu(rs.getString(3));
                u.setCinu(rs.getInt(4));
                u.setTelu(rs.getInt(5));
                u.setEmailu(rs.getString(6));
                u.setPassword(rs.getString(7));
                u.setRole(rs.getString(8));
                
                
                myList.add(u);
                //System.out.println("founisseur trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
public int getnbreUSR() {
        String req = "select count(*) from user";
        int a=-1;
        try {
           Statement ste = cnx.createStatement();
            ResultSet rst = ste.executeQuery(req);
   
           while(rst.next()){
              a= rst.getInt(1);
           }
           return a;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }
}