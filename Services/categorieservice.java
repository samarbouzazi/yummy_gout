/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.categorie;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import Tools.MaConnexion;

/**
 *
 * @author chaim
 */
public class categorieservice implements Iservice_1<categorie> {
  Connection cnx= MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(categorie c) {
       try {
          
           if (existe(c) ==0) {
            String sql="insert into categorie(idcatt,Nomcat,Image) values('"+c.getidcatt()+"','"+c.getNomcat()+"','"+c.getImage()+"')";
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println(" categorie Ajoutée");}
            else { System.out.println("categorie existe deja");}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public int existe(categorie c) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from categorie WHERE Nomcat = '" + c.getNomcat().toLowerCase() +"'");
        int size = 0;
        rs.next();
        size=rs.getInt(1);
        return size;
    }
    
    @Override
    public List<categorie> afficher() {
        List<categorie> categorie = new ArrayList<>();
        String sql ="select * from categorie";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                categorie c = new categorie();
                c.setidcatt(rs.getInt("idcatt"));
                c.setNomcat(rs.getString("Nomcat"));
                c.setImage(rs.getString("Image"));
                categorie.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categorie;

    }
    
    public ObservableList<categorie> Tric() {
     
  
        
          String req = "SELECT idcatt,Nomcat,Image FROM categorie order by Nomcat DESC";

        ObservableList<categorie> list=FXCollections.observableArrayList();
        try {
           Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){
               
              categorie c=new categorie(rst.getInt(1),rst.getString(2),rst.getString(3));
               list.add(c);
           }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list ;
       
     }


    @Override
    public void supprimer(categorie c) {
 
        String sql="delete from categorie where idcatt = '"+c.getidcatt()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println("categorie supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  

    @Override
    public void modifier(categorie c) {
        String sql="update categorie set  Nomcat=?, Image= ? where idcatt='"+c.getidcatt()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, c.getNomcat());
            ste.setString(2, c.getImage());
            
           ste.executeUpdate();
            System.out.println("categorie Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
   
}
    
    

   
    
