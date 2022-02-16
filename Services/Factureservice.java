/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.facture;
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
public class Factureservice implements Iservice<facture> {
 Connection cnx= Maconnexion.getInstance().getCnx();
    @Override
    public void ajouter(facture f) {
        try {
            String sql="insert into facture(Idfac,Idplat,Idclient) values('"+f.getIdfac()+"','"+f.getIdplat()+"','"+f.getIdclient()+"')";
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println(" facture Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<facture> afficher() {
         List<facture> facture = new ArrayList<>();
        String sql ="select * from facture";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                facture f = new facture();
                f.setIdfac(rs.getInt("Idfac"));
                f.setIdplat(rs.getInt("Idplat"));
                f.setIdclient(rs.getInt("Idclient"));
               
                facture.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return facture;
    }

    @Override
    public void supprimer(facture f) {
     String sql="delete from facture where Idfac = '"+f.getIdfac()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println("facture supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(facture f) {
        
         String sql="update facture set  Idplat=?, Idclient= ?  where Idfac='"+f.getIdfac()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setInt(1, f.getIdplat());
            ste.setInt(2, f.getIdclient());
             ste.executeUpdate();
            System.out.println("facture Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

          }
    
}
