/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.categorie;
import entities.panier;
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
public class Panierservice implements Iservice<panier>{
Connection cnx= Maconnexion.getInstance().getCnx();
    @Override
    public void ajouter(panier pas) {
         try {
            String sql="insert into panier(Idpanier,Idplat,quantite) values('"+pas.getIdpanier()+"','"+pas.getIdplat()+"','"+pas.getQuantite()+"')";
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println(" Panier Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @Override
    public List<panier> afficher() {
  List<panier> panier = new ArrayList<>();
        String sql ="select * from panier";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
               panier pas = new panier();
                pas.setIdpanier(rs.getInt("Idpanier"));
                pas.setIdplat(rs.getInt("Idplat"));
                pas.setQuantite(rs.getInt("quantite"));
                panier.add(pas);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return panier;        
    }

    @Override
    public void supprimer(panier pas) {
       String sql="delete from panier where Idpanier = '"+pas.getIdpanier()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println(" panier supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(panier pas) {
         String sql="update panier set  Idplat=?, quantite= ? where Idpanier='"+pas.getIdpanier()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setInt(1, pas.getIdplat());
            ste.setInt(2, pas.getQuantite());
            
           ste.executeUpdate();
            System.out.println("panier Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
