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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MaConnexion;

/**
 *
 * @author LENOVO
 */
public class LivraisonService implements IService<Livraison>{
    Connection cnx= MaConnexion.getInstance().getCnx();   
    public int exist(Livraison y) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from Livraison WHERE Idfac = '" + y.getId_facture() +"' and Idliv='"+y.getId_Livreur()+"'");
        int size = 0;
        rs.next();
        size=rs.getInt(1);
        return size;
    }
    @Override
    public void ajouter(Livraison y) {
        String sql ="INSERT INTO `livraison`(`date`, `Idfac`, `Idliv`) VALUES(CURRENT_TIMESTAMP,?,?) ";
        try {          
            if (exist(y)== 0){
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
            
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setInt(1, y.getId_facture());
            ste.setInt(2, y.getId_Livreur());           
            ste.executeUpdate();
            System.out.println("Livraison Modifiée");
            
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
    public ObservableList<Livraison> chercherav(String chaine){
          String req="SELECT * FROM livraison WHERE (id_livraison LIKE ? or date LIKE ? or Idfac LIKE ? or Idp LIKE ? )";            
            String ch="%"+chaine+"%";
            ObservableList<Livraison> myList=FXCollections.observableArrayList();
        try {
            Statement ste = cnx.createStatement();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, ch);
            ps.setString(2, ch);
            ps.setString(3, ch);
            ps.setString(4, ch);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Livraison y = new Livraison ();
                y.setId_Livraison(rs.getInt(1));
                y.setDate(rs.getDate(2));
                y.setId_facture(rs.getInt(3));
                y.setId_Livreur(rs.getInt(4));                
                myList.add(y);
                System.out.println("Livraison trouvée! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
    public ObservableList<Livraison> TriLivraison() {       
          String req = "SELECT `id_livraison`, `date`, `Idfac`, `Idp` FROM `livraison` Group by `Idp` order by `date` DESC";

        ObservableList<Livraison> list=FXCollections.observableArrayList();
        try {
           Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){
               
              Livraison l=new Livraison(rst.getInt(1),rst.getDate(2),rst.getInt(3),rst.getInt(4));
               list.add(l);
           }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
       
     }
}

