/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entités.personnel;
import entités.reclamationn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

/**
 *
 * @author HP
 */
public class reclamationService implements IService <reclamationn> {
Connection cnx= MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(reclamationn p) {
    String sql ="insert into reclamationn( idchef,descriptionrec,daterec,idp) values(?,?,CURRENT_TIMESTAMP,?) ";
        try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setInt(1, p.getIdchef());
            ste.setString(2, p.getDescription());
            ste.setInt(3, p.getIdp());
            ste.executeUpdate();
            System.out.println(" Reclamation Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<reclamationn> afficher() {
List<reclamationn> reclamations = new ArrayList<>();
        String sql ="select * from reclamationn";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                reclamationn r = new reclamationn();
                r.setIdrec(rs.getInt("idrec"));
                r.setIdchef(rs.getInt("idchef"));
                r.setDescription(rs.getString("descriptionrec"));
                r.setDaterec(rs.getDate("daterec"));
                r.setIdp(rs.getInt("Idp"));
                reclamations.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }

    @Override
    public void supprimer(reclamationn r) {
String sql =" DELETE FROM reclamationn WHERE idrec = '"+r.getIdrec()+"'" ;
        try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("reclamation supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(reclamationn r) {
String sql="update reclamationn set descriptionrec=?  where idrec='"+r.getIdrec()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);           
                ste.setString(1,r.getDescription());
                ste.executeUpdate();
            System.out.println("reclamation Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
