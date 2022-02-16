/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entités.personnel;
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
public class PersonnelService implements IService <personnel>{
Connection cnx= MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(personnel p) {
      String sql ="insert into personnel(Nomp,Prenom,Mail,Tel,Salaire,Specialite) values(?,?,?,?,?,?) ";
        try {
            PreparedStatement ste =cnx.prepareStatement(sql);
           
            ste.setString(1, p.getNomp());
            ste.setString(2, p.getPrenom());
            ste.setString(3, p.getMail());
            ste.setInt(4, p.getTel());
            ste.setInt(5, p.getSalaire());
            ste.setString(6, p.getSpecialite());
            ste.executeUpdate();
            System.out.println("Personnel Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<personnel> afficher() {
 List<personnel> personnels = new ArrayList<>();
        String sql ="select * from personnel";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                personnel p = new personnel();            //j'ai utilise un constructeur par defaut
                p.setIdp(rs.getInt("idp"));
                p.setNomp(rs.getString("nomp"));
                p.setPrenom(rs.getString("prenom"));
                p.setMail(rs.getString("mail"));
                p.setTel(rs.getInt("tel"));
                p.setSalaire(rs.getInt("salaire"));
                p.setSpecialite(rs.getString("Specialite"));
                personnels.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnels;
    }

    @Override
    public void supprimer(personnel p) {
String sql =" DELETE FROM personnel WHERE Idp = '"+p.getIdp()+"'" ;
        try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Personnel supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(personnel p) {
 String sql="update personnel set Nomp=?, Prenom=?,  Mail= ?, Tel= ?, Salaire=?, Specialite=? where Idp='"+p.getIdp()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);           
                ste.setString(1,p.getNomp());
                ste.setString(2,p.getPrenom());
                ste.setString(3,p.getMail());
                ste.setInt(4,p.getTel());
                ste.setInt(5,p.getSalaire());
                ste.setString(6,p.getSpecialite());
                ste.executeUpdate();
            System.out.println("personnel Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

    
