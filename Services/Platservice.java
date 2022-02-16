/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.categorie;
import entities.plat;
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
public class Platservice implements Iservice<plat>{
 Connection cnx= Maconnexion.getInstance().getCnx();
    @Override
    public void ajouter(plat p) {
         try {
            String sql="insert into plat(Idplat,Descplat,Nomplat,image,favorie,idcat) values('"+p.getIdplat()+"','"+p.getDescplat()+"','"+p.getNomplat()+"','"+p.getimage()+"','"+p.getFavorie()+"','"+p.getIdcat()+"')";
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println(" plat Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<plat> afficher() {
         List<plat> plat = new ArrayList<>();
        String sql ="select * from plat";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                plat p = new plat();
                p.setIdplat(rs.getInt("Idplat"));
                p.setDescplat(rs.getString("Descplat"));
                p.setNomplat(rs.getString("Nomplat"));
                p.setimage(rs.getString("image"));
                p.setFavorie(rs.getInt("favorie"));
                p.setIdcat(rs.getInt("idcat"));
                plat.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return plat;

    }

    @Override
    public void supprimer(plat p) {
          String sql="delete from plat where Idplat = '"+p.getIdplat()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println("plat supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(plat p) {
         String sql="update plat set  Descplat=?, Nomplat= ?, image=? ,favorie=? ,idcat=?  where Idplat='"+p.getIdplat()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, p.getDescplat());
            ste.setString(2, p.getNomplat());
            ste.setString(3, p.getimage());
            ste.setInt(4, p.getFavorie()); 
            ste.setInt(5, p.getIdcat());
                
           ste.executeUpdate();
            System.out.println("plat Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  

    
    
}
