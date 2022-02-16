/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.Maconnexion;

/**
 *
 * @author chaim
 */
public class categorieservice implements Iservice<categorie> {
  Connection cnx= Maconnexion.getInstance().getCnx();
    @Override
    public void ajouter(categorie c) {
       try {
            String sql="insert into categorie(idcat,Nomcat,Image) values('"+c.getidcat()+"','"+c.getNomcat()+"','"+c.getImage()+"')";
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println(" categorie Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
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
                c.setidcat(rs.getInt("idcat"));
                c.setNomcat(rs.getString("Nomcat"));
                c.setImage(rs.getString("Image"));
                categorie.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categorie;

    }

    @Override
    public void supprimer(categorie c) {
 
        String sql="delete from categorie where idcat = '"+c.getidcat()+"'";
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
        String sql="update categorie set  Nomcat=?, Image= ? where idcat='"+c.getidcat()+"'";
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

   
    
