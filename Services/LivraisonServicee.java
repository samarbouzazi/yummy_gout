/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Livraison;
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
 * @author LENOVO
 */
public class LivraisonServicee {
    Connection cnx= MaConnexion.getInstance().getCnx();
      public Double deg2rad(Double deg) {
        Double deg2rad= (deg * Math.PI / 180.0);
        return deg2rad;
    }

    public Double rad2deg(Double rad) {
        return (rad * 180.0 / Math.PI);
    }
    public Double returnlat(String adresse) throws SQLException{
        List <Double> latitudes = new ArrayList<>();
        Double latitude=0.0;
        String sql="SELECT `Latitude` FROM `geocode` WHERE Address='"+adresse+"'";       
        Statement st= cnx.createStatement();
        ResultSet rs =st.executeQuery(sql);
        while (rs.next()){
          latitudes.add(rs.getDouble("Latitude")); }
        for (int i=0; i< latitudes.size();i++)
         latitude= latitudes.get(i);       
       return latitude; 
    }    
    public Double returnlong(String adresse) throws SQLException{
        List <Double> longitudes = new ArrayList<>();
        String sql="SELECT `Longitude` FROM `geocode` WHERE Address='"+adresse+"'"; 
        Double longitude=0.0;
        Statement st= cnx.createStatement();
        ResultSet rs =st.executeQuery(sql);
        while (rs.next()){
           longitudes.add(rs.getDouble("Longitude")); }
        for (int i=0; i<  longitudes.size();i++)
         longitude=  longitudes.get(i);       
       return longitude;
    }
    public Double Distance(String adresse1, String adresse2) throws SQLException{
        Double lon1= returnlong(adresse1);
        Double lat1= returnlat(adresse1);
        Double lon2= returnlong(adresse2);
        Double lat2= returnlat(adresse2);
        Double theta = lon1 - lon2;
        Double dist =(Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta)));
        dist =Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60; // 60 nautical miles per degree of seperation
        dist = dist * 1852; // 1852 meters per nautical mile
        return dist;        
    }
        public String getadr(int id) throws SQLException{
        String sql="SELECT `Delegation` FROM `panier`INNER JOIN livraison on livraison.Idpanier=Panier.Idpanier where livraison.Idpanier= '"+id+"';";
        Statement st4 = cnx.createStatement();
        ResultSet rs_4 = st4.executeQuery(sql);
        while (rs_4.next()){
        return rs_4.getString("Delegation");}
        return null;
    } 
    public int ReturnIdLivreur() throws SQLException{
        List <Integer> disponible=new ArrayList<>();
        int idp=0;
        int idpp=0;
        String ad=null;                      
        String sql_1 ="SELECT livraison.Idp,panier.Delegation FROM panier INNER JOIN livraison on panier.Idpanier=livraison.Idpanier ORDER BY livraison.id_livraison DESC Limit 1;";
        Statement ste = cnx.createStatement();
        ResultSet rs_1 = ste.executeQuery(sql_1);
         while (rs_1.next()){
         idp=rs_1.getInt("livraison.Idp"); 
          ad= rs_1.getString("panier.Delegation");
         idpp=idp;}   
        return idpp;         
    }
    public void ajouter(Livraison y) {
        String sql ="INSERT INTO `livraison`(`date`, `Idpanier`, `Idp`,`Etat`) VALUES(CURRENT_TIMESTAMP,?,?,'en cours') ";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setInt(1, y.getId_panier());
            ste.setInt(2, y.getId_Livreur());
            ste.executeUpdate();
            System.out.println("Livraison Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
    
}
