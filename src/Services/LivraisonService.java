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
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from Livraison WHERE Idfac = '" + y.getId_facture() +"' and Idp='"+y.getId_Livreur()+"'");
        int size = 0;
        rs.next();
        size=rs.getInt(1);
        return size;
    }

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

    public int ReturnIdLivreur(String adresse) throws SQLException{
        List <Integer> disponible=new ArrayList<>();
        int idlivreur=0;
        int idpersonnel=0;
        String sql_2="SELECT `Idp` FROM `personnel` WHERE Disponibilité='oui' AND Specialite='livreur';";
        Statement st = cnx.createStatement();
        ResultSet rs_2 = st.executeQuery(sql_2);
        while (rs_2.next()){
           disponible.add(rs_2.getInt("Idp")); }
        for(int i=0; i<disponible.size();i++){
            idpersonnel=disponible.get(0);    }            
        String sql_1 ="SELECT Delegation FROM panier, livraison INNER JOIN facture WHERE facture.Idplat=panier.Idplat AND facture.Idfac=livraison.Idfac AND livraison.Etat='en cours' LIMIT 1;";
        Statement ste = cnx.createStatement();
        ResultSet rs_1 = ste.executeQuery(sql_1);
        rs_1.absolute(1);
        String adr= rs_1.getString("Delegation");      
        Double distance= Distance(adr, adresse);
        if ((distance>8426.5)){
        String sql_4="SELECT `Idp` FROM `personnel` WHERE Disponibilité='oui'and Specialite='livreur' ORDER BY RAND ( ) LIMIT 1 ;";
        Statement st4 = cnx.createStatement();
        ResultSet rs_4 = st4.executeQuery(sql_2);
        return rs_4.getInt("Idp"); 
        }   else        
        idlivreur=idpersonnel;
        return idlivreur;
    }
//    public String returnadresse(int idf) throws SQLException{
//        String sql="SELECT panier.Delegation FROM panier ,livraison INNER JOIN facture WHERE panier.Idplat=facture.Idplat AND facture.Idfac='"+idf+"'";
//        Statement st4 = cnx.createStatement();
//        ResultSet rs_4 = st4.executeQuery(sql);
//        return rs_4.getString("Delegation");
//    }
    public void modifierDispoLivreur(int id) throws SQLException{
    String sql ="Select count(*) from livraison where idp='"+id+"'";
    Statement ste = cnx.createStatement();
    ResultSet rst = ste.executeQuery(sql);
    int val =  ((Number) rst.getObject(1)).intValue();
    if (val ==3){
    String sql_1="Update personnel set Disponibilité='non' where idp='"+id+"'";
    PreparedStatement st =cnx.prepareStatement(sql_1);         
    st.executeUpdate();
    }}
    public String getadr(int id) throws SQLException{
        String sql="SELECT `Delegation` FROM `panier` INNER JOIN facture where facture.Idfac='"+id+"' AND facture.Idplat=panier.Idplat;";
        Statement st4 = cnx.createStatement();
        ResultSet rs_4 = st4.executeQuery(sql);
        while (rs_4.next()){
        return rs_4.getString("Delegation");}
        return null;
    }
    @Override
    public void ajouter(Livraison y) {
        String sql ="INSERT INTO `livraison`(`date`, `Idfac`, `Idp`,`Etat`) VALUES(CURRENT_TIMESTAMP,?,?,'en cours') ";

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
    public ObservableList<Livraison> afficher() {
        ObservableList<Livraison> Livraison =FXCollections.observableArrayList();
        String sql ="SELECT livraison.id_livraison, livraison.date, livraison.Idfac, livraison.Idp, facture.Idplat, facture.Idclient FROM livraison INNER JOIN facture where livraison.Idfac=facture.Idfac;";
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(sql);
           while(rst.next()){
               Livraison L= new Livraison();
               L.setId_Livraison(rst.getInt("id_livraison"));
               L.setDate(rst.getDate("date"));
               L.setId_facture(rst.getInt("Idfac"));
               L.setId_Livreur(rst.getInt("Idp"));
               L.setIdplat(rst.getInt("Idplat"));
               L.setIdclient(rst.getInt("Idclient"));             
            Livraison.add(L);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Livraison;
    }

    @Override
    public void modifier(Livraison y) {
        String sql="update Livraison set Idfac=?, Idp=? where Id_livraison='"+y.getId_Livraison()+"'";
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
            ps.setString(5, ch);
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
     public List<Integer> getAllliv() {
        List<Integer> list = new ArrayList<Integer>();
        try {
            String requetee = "SELECT facture.Idfac FROM facture WHERE facture.Idfac NOT IN (SELECT livraison.Idfac FROM livraison where Etat='en cours');";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());
            while (rs.next()) {
                list.add(rs.getInt("Idfac"));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
     public List<Integer> getModiffacture() {
        List<Integer> list = new ArrayList<Integer>();
        try {
            String requetee = "SELECT Idfac FROM livraison ;";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());
            while (rs.next()) {
                list.add(rs.getInt("Idfac"));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
     public List<Integer> getModiflivreur() {
        List<Integer> list = new ArrayList<Integer>();
        try {
            String requetee = "SELECT Idp FROM personnel where Disponibilité='oui' ;";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());
            while (rs.next()) {
                list.add(rs.getInt("Idp"));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
     public List<String>getlistlivreur() throws SQLException{
         List<String> listmail=new ArrayList<String>();
        String sql = "SELECT personnel.mail FROM livraison INNER JOIN personnel where livraison.Idp=personnel.Idp AND livraison.Etat='en cours';";
            PreparedStatement pst = cnx.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());
            while (rs.next()) {
                listmail.add(rs.getString("mail"));
            }
            return listmail; 
     }
     public String bodymail(String mail) throws SQLException{
         String sql="SELECT panier.Idpanier,livraison.id_livraison,ficheclient.Idclient, panier.Idplat, panier.quantite, panier.Adresse_livraison, panier.Etat, panier.Delegation FROM `panier` INNER JOIN livraison, facture, ficheclient WHERE ficheclient.E_mail='"+mail+"'AND ficheclient.Idclient=facture.Idclient AND facture.Idfac=livraison.Idfac;";
         PreparedStatement pst = cnx.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            String ch=null;
            while (rs.next()) {
                int id=rs.getInt("Idpanier");
                int id2=rs.getInt("id_livraison");
                int id3=rs.getInt("Idclient");
                int id4=rs.getInt("Idplat");
                int qte=rs.getInt("quantite");
                String adr=rs.getString("Adresse_livraison");
                String etat=rs.getString("Etat");
                String deleg=rs.getString("Delegation"); 
                 ch="détails de la commande:"+id+""
                         + ""+id2+" "
                         + ""+id3+" "
                         + ""+id4+""
                         + " "+qte+" "
                         + ""+adr+""
                         + " "+etat+""
                         + " "+deleg ;
            }
            return ch;
     }
}

