/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Stock;
import Tools.MaCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL PRCISION 3551
 */
public class SStock implements IService<Stock>{
    
    Connection cnx = MaCon.getInstance().getCnx();

    @Override
    public void ajouter(Stock s) {
         try{
            String sql="insert into stocks(noms,date_ajout_s,date_fin_s,prix_s,qt_s)"+"Values(?,?,?,?,?)";
            PreparedStatement ste=cnx.prepareStatement(sql);
            ste.setString(1, s.getNoms());
            ste.setString(2, s.getDate_ajout_s());
            ste.setString(3, s.getDate_fin_s());
            ste.setFloat(4,s.getPrix_s());
            ste.setInt(5,s.getQt_s());
            ste.executeUpdate();
            System.out.println("Produit ajouté");
        }catch(SQLException ex){
             System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Stock> afficher() {
        
        ObservableList<Stock> ols = FXCollections.observableArrayList();
        String sql ="select * from stocks";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
        while (rs.next()){
             Stock s= new Stock();
             s.setIds(rs.getInt("ids"));
             s.setNoms(rs.getString("noms"));
             s.setDate_ajout_s(rs.getString("date_ajout_s"));
             s.setDate_fin_s(rs.getString("date_fin_s"));
             s.setPrix_s(rs.getFloat("prix_s"));
             s.setQt_s(rs.getInt("qt_s"));
             ols.add(s);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return (ols);
    }
    
    
   public void deleteEvent(Stock s){
        String requete = "DELETE FROM stocks WHERE ids=?";
        try {
            
            PreparedStatement pst = MaCon.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,s.getIds());
            pst.executeUpdate();
            System.out.println("Produit supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
    }
    
    }

    

   
   public void updateFormation(int ids,String noms,String date_ajout_s,String date_fin_s,float prix_s,int qt_s) {
         try {
            String requete = "UPDATE stocks SET noms=?,date_ajout_s=?,date_fin_s=?,prix_s=?,qt_s=? WHERE ids=?";
            PreparedStatement pst = MaCon.getInstance().getCnx().prepareStatement(requete);
            pst.setString(2,noms);
            pst.setString(3, date_ajout_s);
            pst.setString(4, date_fin_s);
            pst.setFloat(5, prix_s);
            pst.setInt(6, qt_s);
            pst.executeUpdate();
            System.out.println("Produit modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
   public void update(Stock s) {
        
    String sql="update stocks set  noms=?, date_ajout_s= ?, date_fin_s=?, prix_s=?, qt_s=? where ids='"+s.getIds()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, s.getNoms());
            ste.setString(2, s.getDate_ajout_s());
            ste.setString(3, s.getDate_fin_s());
            ste.setFloat(4, s.getPrix_s());
            ste.setInt(5, s.getQt_s());
            
           ste.executeUpdate();
            System.out.println("Produit Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}
}
