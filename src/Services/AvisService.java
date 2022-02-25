/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice;


import entities.avis;
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
 * @author rezki
 */
public class AvisService implements IService<avis>{
Connection cnx= MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(avis t) {
        String  sql; 
    sql = "insert into `avis`(`Idclient`, `dateavis`, `Like`, `Deslike`) values(?,CURRENT_TIMESTAMP,?,?)";
        try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setInt(1, t.getIdclient());
            ste.setInt(2, t.getLike());
            ste.setInt(3, t.getDeslike());           
            ste.executeUpdate();
            System.out.println("avis Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }          
    }

    

    @Override
    public List<avis> afficher() {
        
        List<avis> Avis = new ArrayList<>();
        String sql ="select * from avis";
        try {
            Statement ste= cnx.createStatement();
            ResultSet bs =ste.executeQuery(sql);
            while(bs.next()){
                avis b = new avis();
                b.setIdclient(bs.getInt("Idclient"));
                b.setIdavis(bs.getInt("setIdavis"));
                b.setDateavis(bs.getDate("Dateavis"));
                b.setLike(bs.getInt("Like"));
                b.setDeslike(bs.getInt("Deslike"));
                Avis.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Avis;
    }

    @Override
    public void modifier(avis t) {
         String sql="UPDATE `avis` SET `Idclient`=?,`Like`=?,`Deslike`=? WHERE Idavis='"+t.getIdavis()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);   
            ste.setInt(1, t.getIdclient());
            ste.setInt(2, t.getLike());
            ste.setInt(3, t.getDeslike());
            ste.executeUpdate();
            System.out.println("Avis Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(avis t) {
        {
        String sql="delete from avis where Idavis  = '"+t.getIdavis ()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println("Avis supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
    }

public List<avis> getMax() {
        
        ObservableList<avis> ols = FXCollections.observableArrayList();
        String sql ="SELECT * FROM avis ORDER BY `like` DESC LIMIT 1";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
        while (rs.next()){
             avis s= new avis();
             s.setIdavis(rs.getInt("idavis"));
             s.setIdclient(rs.getInt("idclient"));
             
             s.setDateavis(rs.getDate("dateavis"));
             s.setLike(rs.getInt("like"));
             s.setDeslike(rs.getInt("Deslike"));
             
             ols.add(s);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return (ols);
    }
  //  public static void showNotif(String text, String text2) {
//        Notifications notificationBuilder = Notifications.create()
//                .title(text)
//                .text(text2)
//                .graphic(null)
//                .hideAfter(Duration.ofSeconds(10))
//                
//                .onAction(new EventHandler<ActionEvent>() {
//                    public void handle(ActionEvent event) {
//
//                    }
//                });
//
//        notificationBuilder.darkStyle();
//        notificationBuilder.showConfirm();
//    }
   
   
    }

    
    
    

   
    
  
