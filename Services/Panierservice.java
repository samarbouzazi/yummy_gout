/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.categorie;
import Entities.panier;
import Entities.platt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Tools.MaConnexion;

/**
 *
 * @author chaim 
 */
public class Panierservice implements Iservice_1<panier>{
Connection cnx= MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(panier pas) {
        int stock=0;
        Platservice ps=new Platservice();
         try {
            String sql1="select stock from platt where platt.Idplat='"+pas.getIdplat()+"'";
            Statement ste_1 = cnx.createStatement();
            ResultSet rs=ste_1.executeQuery(sql1);
            while(rs.next()){
            stock=rs.getInt("stock");}
            if(stock ==0)
            { System.out.println("stock insufisant");}
            else {
                String sql="insert into panier(Idpanier,Idplat,quantite,numfacture) values('"+pas.getIdpanier()+"','"+pas.getIdplat()+"','"+pas.getQuantite()+"','"+pas.getNumfacture()+"')";
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println(" Panier Ajoutée");
            stock=stock-1;
         //   ps.modifiersstock(pas.getIdplat());
             
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
         
    
    }
    public int calculer_pannier() throws SQLException{
  
        String sql="SELECT sum(quantite*prix_plat) as somme FROM panier INNER JOIN platt where platt.Idplat=panier.Idplat";
        int a=0;
          
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
           //  panier pas =new panier(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
              
               
               
                
                
            
            a=rs.getInt("somme");     
        
    }return a;  
    }

    @Override
    public List<panier> afficher() {
  List<panier> panier = new ArrayList<>();
       // String sql ="select * from panier";
       String sql="SELECT panier.Idpanier, panier.quantite, platt.Nomplat , platt.prix_plat panier.numfacture  FROM panier INNER JOIN platt where platt.Idplat=panier.Idplat";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
               panier pas = new panier();
                pas.setIdpanier(rs.getInt("Idpanier"));
                
                pas.setNumfacture(rs.getString("numfacture"));
                pas.setIdplat(rs.getInt("quantite"));
                pas.setNomplat(rs.getString("Nomplat"));
                pas.setPrix_plat(rs.getInt("prix_plat"));
              
                panier.add(pas);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return panier;        
    }

    @Override
    public void supprimer(panier pas) {
       String sql="delete from panier where Idpanier = '"+pas.getIdpanier()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println(" panier supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    public void vider(panier pas) {
        
       String sql="TRUNCATE TABLE `panier`";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println(" panier vider");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(panier pas) {
         String sql="update panier set   quantite= ?  where Idpanier='"+pas.getIdpanier()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
          //  ste.setInt(1, pas.getIdplat());
            ste.setInt(1, pas.getQuantite());
            
           ste.executeUpdate();
            System.out.println("panier Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public List<Integer> getAll() {
        List<Integer> list = new ArrayList<Integer>();
        try {
            String requetee = "SELECT Idplat FROM platt";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());

            while (rs.next()) {
                list.add(rs.getInt("Idplat"));
            }

            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
        
   

}

