/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Stock;
import Tools.MaCon;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.management.Notification;
import org.controlsfx.control.Notifications;

/**
 *
 * @author DELL PRCISION 3551
 */
public class SStock implements IService<Stock>{
    private Statement ste;
    Connection cnx = MaCon.getInstance().getCnx();

    @Override
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

    @Override
    public List<Stock> afficher() {
        
        ObservableList<Stock> ols = FXCollections.observableArrayList();
        String sql ="select stocks.ids, fournisseurs.nomf, stocks.noms, stocks.date_ajout_s, stocks.date_fin_s, stocks.prix_s, stocks.qt_s FROM fournisseurs inner join stocks where fournisseurs.idf=stocks.idf";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Stock s= new Stock();
        while (rs.next()){
             s.setIds(rs.getInt("ids"));
             s.setNomf(rs.getString("nomf"));
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
    
    
   public void delete(Stock s){
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
       
   public void update(Stock s) {
//        
    String sql="update stocks set  noms=?, date_ajout_s= ?, date_fin_s=?, prix_s=?, qt_s=? where ids='"+s.getIds()+"'";
//    String sql="UPDATE fournisseurs SET `noms`='"+s.getNoms()+"',`date_ajout_s`='"+s.getDate_ajout_s() +"',`date_fin_s`='"+s.getDate_fin_s()+"',`prix_s`='"+s.getPrix_s()+"',`qt_s`='"+s.getQt_s()+"' WHERE `ids`='"+s.getIds()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            
            ste.setString(1, s.getNoms());
            ste.setDate(2, s.getDate_ajout_s());
            ste.setDate(3, s.getDate_fin_s());
            ste.setFloat(4, s.getPrix_s());
            ste.setInt(5, s.getQt_s());
            
           ste.executeUpdate();
            System.out.println("Produit Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}
    
// public void update(Stock s) throws SQLException{
//
//        String req="UPDATE stocks SET `idf`='"+s.getIdf()+"',`noms`='"+s.getNoms()+"',`date_ajout_s`='"+s.getDate_ajout_s() +"',`date_fin_s`='"+s.getDate_fin_s()+"',`prix_s`='"+s.getPrix_s()+"',`qt_s`='"+s.getQt_s()+"' WHERE `ids`='"+s.getIds()+"'";
//        Statement st=cnx.createStatement();
//        st.executeUpdate(req);
//    }   
    
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
//    public List<Stock> readAllReclamation() throws SQLException {
//        List<Stock> list = new ArrayList<>();
//        ResultSet res = ste.executeQuery("select * from reclamation ");
//        Stock sto = null;
//        while (res.next()) {
//            sto = new   Stock(res.getInt(1), res.getInt(2), res.getString(3), res.getDate(4), res.getDate(5), res.getFloat(6), res.getInt(7));
//            list.add(sto);
//            System.out.println("all" + list);
//        }
//        return list;
//    }
//    public void notif(){
//        Stock ss=new Stock();
//        if(ss.getQt_s()<2){
//        Image img = new Image("Image/check-mark2.png");
//        Notifications notificationBuilder = Notifications.create()
//                .title("Stock")
//                .text("Verifier votre stock il est ")
//                .graphic(new ImageView(img))
//                .hideAfter(Duration.seconds(5))
//                .position(Pos.TOP_RIGHT);
//       notificationBuilder.darkStyle();
//       
//         notificationBuilder.show();}
//        }
    
//   public ObservableList<Stock> getAllArticleObs() throws SQLDataException, SQLException {
//        
//            List<Stock> list =new ArrayList<Stock>();
//            int count =0;
//            
//            String requete="select * from stocks";
//            
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(requete);
//            
//            while (rs.next()){
//                
//                Stock s = new Stock();
//                
//                s.setIds(rs.getInt(1));
//                s.setIdf(rs.getInt(2));
//                s.setDate_ajout_s(rs.getDate(3));
//                s.setDate_fin_s(rs.getDate(4));
//                s.setPrix_s(rs.getFloat(5));
//                s.setIdf(rs.getInt(6));
//                
//                list.add(s);
//                
//                count++;
//            }
//            
//            if(count == 0){
//                return null;
//            }else{
//                ObservableList lc_final = FXCollections.observableArrayList(list);
//                return lc_final;
//            }
//}
         public List<String> getAllFOUR() {
        List<String> list = new ArrayList<String>();
        try {
            String requetee = "SELECT addf FROM fournisseurs";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());

            while (rs.next()) {
                list.add(rs.getString("addf"));
            }

            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
         
         public void updateFormation(int ids,int idf,String noms,Date date_ajout_s,Date date_fin_s,float prix,int qt_s) {
         try {
            String requete = "UPDATE stocks SET idf=?,noms=?,date_ajout_s=?,date_fin_s=?,prix=?,qt_s=? WHERE ids = ?";
            PreparedStatement pst = MaCon.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, idf);
            pst.setString(2,noms);
            pst.setDate(3, date_ajout_s);
            pst.setDate(4, date_fin_s);
            pst.setFloat(5, prix);
            pst.setInt(6, qt_s);

         
            pst.setInt(7,ids);
            
            pst.executeUpdate();
            System.out.println("Produit modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         public ObservableList<Stock> chercherav(String facteur){
          String req="SELECT * FROM stocks WHERE (noms LIKE ?)";
            MaCon myCNX = MaCon.getInstance();
            String ch="%"+facteur+"%";
            ObservableList<Stock> myList= FXCollections.observableArrayList();
        try {
            Statement st = myCNX.getCnx()
                    .createStatement();
            PreparedStatement pst = myCNX.getCnx().prepareStatement(req);
            pst.setString(1, ch);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                Stock f = new Stock ();
                
                f.setIds(rs.getInt(1));
                f.setIdf(rs.getInt(2));
                f.setNoms(rs.getString(3));
                f.setDate_ajout_s(rs.getDate(4));
                f.setDate_fin_s(rs.getDate(5));
                f.setPrix_s(rs.getFloat(6));
                f.setQt_s(rs.getInt(7));
                //f.setNomf(rs.getString(8));
              
                
                
                myList.add(f);
                //System.out.println("founisseur trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
         public int chercherIdF(String email) throws SQLException{
         int id=0;
         String requetee = "SELECT idf FROM fournisseurs where addf='"+email+"';";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {id= rs.getInt("idf");}
            return id;
         }
         
   }   

