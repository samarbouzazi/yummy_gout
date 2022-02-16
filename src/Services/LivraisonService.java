/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Livraison;
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
 * @author LENOVO
 */
public class LivraisonService implements IService<Livraison>{
    Connection cnx= MaConnexion.getInstance().getCnx();
    
    @Override
    public void ajouter(Livraison y) {
        String sql_1 ="SELECT COUNT(*) FROM livraison;";
        String sql ="INSERT INTO `livraison`(`date`, `Idfac`, `Idliv`) VALUES(CURRENT_TIMESTAMP,?,?) ";
        try {
            Statement ste_1= cnx.createStatement();
            ResultSet resultat= ste_1.executeQuery(sql_1);
            int nombreLignes = resultat.getRow(); 
            if (nombreLignes != 0){
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setInt(1, y.getId_facture());
            ste.setInt(2, y.getId_Livreur());           
            ste.executeUpdate();
            System.out.println("Livraison Ajoutée");}
            else {
             System.out.println("Livraison existe");   
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
    

    @Override
    public List<Livraison> afficher() {
        List<Livraison> Livraison = new ArrayList<>();
        String sql ="SELECT * FROM livraison ";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Livraison L = new Livraison();
                L.setId_Livraison(rs.getInt("id_livraison"));
                L.setDate(rs.getDate("date")); 
                L.setId_facture(rs.getInt("Idfac"));  
                L.setId_Livreur(rs.getInt("Idliv"));                             
                Livraison.add(L);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Livraison;
    }

    @Override
    public void modifier(Livraison y) {
        String sql="update Livraison set Idfac=?, Idliv=? where Id_livraison='"+y.getId_Livraison()+"'";
        String sql_1 ="SELECT COUNT(*) FROM livraison;";
            try {
            Statement ste_1= cnx.createStatement();
            ResultSet resultat= ste_1.executeQuery(sql_1);
            int nombreLignes = resultat.getRow(); 
            if (nombreLignes != 0){
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setInt(1, y.getId_facture());
            ste.setInt(2, y.getId_Livreur());           
            ste.executeUpdate();
            System.out.println("Livraison Modifiée");}
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Livraison y) {
        String sql="delete from livraison where id_livraison = '"+y.getId_Livraison()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println("livraison supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

