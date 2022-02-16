/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Fournisseur;
import Tools.MaCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL PRCISION 3551
 */
public class SFournisseur implements IServices<Fournisseur>{
    Connection cnx= MaCon.getInstance().getCnx();
    @Override
    public void ajouter(Fournisseur f) {
        String sql ="insert into fournisseurs(nomf,prenomf,catf,telf,addf) values(?,?,?,?,?,?) ";
        try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            
            ste.setString(1, f.getNomf());
            ste.setString(2, f.getPrenomf());
            ste.setString(3, f.getCatf());
            ste.setInt(4, f.getTelf());
            ste.setString(5, f.getAddf());
            ste.setInt(6, f.getIdf());
            ste.executeUpdate();
            System.out.println("FOURNISSEUR Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Fournisseur> afficher() {
        List<Fournisseur> Four = new ArrayList<>();
        String sql ="select * from fournisseurs";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Fournisseur f = new Fournisseur();
                f.setIdf(rs.getInt("idf"));
                f.setNomf(rs.getString("nomf"));
                f.setPrenomf(rs.getString("prenomf"));
                f.setCatf(rs.getString("catf"));
                f.setTelf(rs.getInt("telf"));
                f.setAddf(rs.getString("addf"));
                Four.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Four;
    }

    
    public void supprimer(Fournisseur f) {
        String requete = "DELETE FROM fournisseurs WHERE idf=?";
        try {
            
            PreparedStatement pst = MaCon.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,f.getIdf());
            pst.executeUpdate();
            System.out.println("Fournisseur supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
    }
    
    }
   
    
//    public void update(int idf,String nomf,String prenomf,String catf,int telf,String addf) {
//        
//         try {
//            
//            String requete = "UPDATE fournisseurs SET nomf=?,prenomf=?,catf=?,telf=?,addf=? WHERE idf = ?";
//            PreparedStatement pst = MaCon.getInstance().getCnx().prepareStatement(requete);
//            pst.setString(2,nomf);
//            pst.setString(3,prenomf);
//            pst.setString(4,catf);
//            pst.setInt(5, telf);
//            pst.setString(6, addf);
//            pst.executeUpdate();
//            System.out.println("fourniseur modifié");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    public void update(Fournisseur f) {
        
    String sql="update fournisseurs set  nomf=?, prenomf= ?, catf=?, telf=?, addf=? where idf='"+f.getIdf()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, f.getNomf());
            ste.setString(2, f.getPrenomf());
            ste.setString(3, f.getCatf());
            ste.setInt(4, f.getTelf());
            ste.setString(5, f.getAddf());
            
           ste.executeUpdate();
            System.out.println("Fournisseur Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}
}
    

