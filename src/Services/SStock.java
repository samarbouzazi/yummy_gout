/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entités.Stock;
import tools.MaConnexion;
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
    
    Connection cnx = MaConnexion.getInstance().getCnx();

    
    public void ajouter(Stock s) {
         try{
            String sql="insert into stocks(idf,noms,date_ajout_s,date_fin_s,prix_s,qt_s)"+"Values(?,?,?,?,?,?)";
            PreparedStatement ste=cnx.prepareStatement(sql);
            ste.setInt(1, s.getIdf());
            ste.setString(2, s.getNoms());
            ste.setDate(3, s.getDate_ajout_s());
            ste.setDate(4, s.getDate_fin_s());
            ste.setFloat(5,s.getPrix_s());
            ste.setInt(6,s.getQt_s());
            ste.executeUpdate();
            System.out.println("Produit ajouté");
        }catch(SQLException ex){
             System.out.println(ex.getMessage());
        }

    }

    
    public List<Stock> afficher() {
        
        ObservableList<Stock> ols = FXCollections.observableArrayList();
        String sql ="select * from stocks";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
        while (rs.next()){
             Stock s= new Stock();
             s.setIds(rs.getInt("ids"));
             s.setIdf(rs.getInt("idf"));
             s.setNoms(rs.getString("noms"));
             s.setDate_ajout_s(rs.getDate("date_ajout_s"));
             s.setDate_fin_s(rs.getDate("date_fin_s"));
             s.setPrix_s(rs.getFloat("prix_s"));
             s.setQt_s(rs.getInt("qt_s"));
             ols.add(s);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return (ols);
    }
    
    
//   public void delete(Stock s){
//        String requete = "DELETE FROM stocks WHERE ids=?";
//        try {
//            
//            PreparedStatement pst = MaCon.getInstance().getCnx().prepareStatement(requete);
//            pst.setInt(1,s.getIds());
//            pst.executeUpdate();
//            System.out.println("Produit supprimé");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        
//    }
//    
//    }

   
//   public void update2(int ids,String noms,String date_ajout_s,String date_fin_s,float prix_s,int qt_s) {
//         try {
//            String requete = "UPDATE stocks SET noms=?,date_ajout_s=?,date_fin_s=?,prix_s=?,qt_s=? WHERE ids=?";
//            PreparedStatement pst = MaCon.getInstance().getCnx().prepareStatement(requete);
//            pst.setString(2,noms);
//            pst.setString(3, date_ajout_s);
//            pst.setString(4, date_fin_s);
//            pst.setFloat(5, prix_s);
//            pst.setInt(6, qt_s);
//            pst.executeUpdate();
//            System.out.println("Produit modifié");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
       
//   public void update(Stock s) {
//        
//    String sql="update stocks set  idf=?,noms=?, date_ajout_s= ?, date_fin_s=?, prix_s=?, qt_s=? where ids='"+s.getIds()+"'";
////    String sql="UPDATE fournisseurs SET `noms`='"+s.getNoms()+"',`date_ajout_s`='"+s.getDate_ajout_s() +"',`date_fin_s`='"+s.getDate_fin_s()+"',`prix_s`='"+s.getPrix_s()+"',`qt_s`='"+s.getQt_s()+"' WHERE `ids`='"+s.getIds()+"'";
//            try {
//            PreparedStatement ste =cnx.prepareStatement(sql);
//            ste.setInt(1, s.getIdf());
//            ste.setString(2, s.getNoms());
//            ste.setDate(3, s.getDate_ajout_s());
//            ste.setDate(4, s.getDate_fin_s());
//            ste.setFloat(5, s.getPrix_s());
//            ste.setInt(6, s.getQt_s());
//            
//           ste.executeUpdate();
//            System.out.println("Produit Modifié");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    
//}
    
    public void update(Stock s) throws SQLException{

        String req="UPDATE stocks SET `idf`='"+s.getIdf()+"',`noms`='"+s.getNoms()+"',`date_ajout_s`='"+s.getDate_ajout_s() +"',`date_fin_s`='"+s.getDate_fin_s()+"',`prix_s`='"+s.getPrix_s()+"',`qt_s`='"+s.getQt_s()+"' WHERE `ids`='"+s.getIds()+"'";
        Statement st=cnx.createStatement();
        st.executeUpdate(req);
    }
    
    
    public void delete(int ids) {
        
         
        try {
            String req="DELETE FROM stocks WHERE ids="+ids+";";
            
            Statement st=cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public ObservableList<Stock> RECHERCHE(int ids ) {
         ObservableList<Stock> Stock = FXCollections.observableArrayList();
        String req = "SELECT ids,idf,noms,date_ajout_s,date_fin_s,prix_s,qt_s FROM stocks where ids LIKE '" + ids + "%'  ";
        ObservableList<Stock> list=FXCollections.observableArrayList();
        try {
           Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){
               
              Stock s=new Stock(rst.getInt(1),rst.getInt(2),rst.getString(3),rst.getDate(4),rst.getDate(5),rst.getFloat(6),rst.getInt(7));
               list.add(s);
           }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
     }
    
    public ObservableList<Stock> TriS() {
     
  
        
          String req = "SELECT ids,idf,noms,date_ajout_s,date_fin_s,prix_s,qt_s FROM stocks order by prix_s DESC";

        ObservableList<Stock> list=FXCollections.observableArrayList();
        try {
           Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){
               
              Stock f=new Stock(rst.getInt(1),rst.getInt(2),rst.getString(3),rst.getDate(4),rst.getDate(5),rst.getFloat(6),rst.getInt(7));
               list.add(f);
           }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
       
     }
//    public String getMax() {
//        String req = "SELECT noms,prix_s FROM stocks WHERE (select max(prix_s)from stocks) ;";
//        
//        
//        try {
//           Statement ste = cnx.createStatement();
//            ResultSet rst = ste.executeQuery(req);
//   
//           while(rst.next()){
//             Stock s= new Stock();
//             s.setNoms(rst.getString("noms"));
//             s.setPrix_s(rst.getFloat("prix_s"));
//             a=rst.getString("noms");
//             
//              
//           }
//           return a;
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//            return "-1";
//        }
//    }
    
         public List<Stock> getMax() {
        
        ObservableList<Stock> ols = FXCollections.observableArrayList();
        String sql ="SELECT * FROM stocks ORDER BY prix_s DESC LIMIT 1";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
        while (rs.next()){
             Stock s= new Stock();
             s.setIds(rs.getInt("ids"));
             s.setIdf(rs.getInt("idf"));
             s.setNoms(rs.getString("noms"));
             s.setDate_ajout_s(rs.getDate("date_ajout_s"));
             s.setDate_fin_s(rs.getDate("date_fin_s"));
             s.setPrix_s(rs.getFloat("prix_s"));
             s.setQt_s(rs.getInt("qt_s"));
             ols.add(s);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return (ols);
    }
    
}
