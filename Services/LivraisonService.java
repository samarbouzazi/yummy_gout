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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Tools.MaConnexion;

/**
 *
 * @author LENOVO
 */
public class LivraisonService implements IService_amani<Livraison>{
    Connection cnx= MaConnexion.getInstance().getCnx();   
    public int exist(Livraison y) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from livraison WHERE Idpanier = '" + y.getId_panier() +"' and Idp='"+y.getId_Livreur()+"'");
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
        public String getadr(int id) throws SQLException{
        String ch="amani";
        String sql="SELECT `Delegation` FROM `panier` where panier.Idpanier= '"+id+"';";
        Statement st4 = cnx.createStatement();
        ResultSet rs_4 = st4.executeQuery(sql);
        while (rs_4.next())
        ch=rs_4.getString("Delegation");
        return ch;
    }
    public int ReturnIdLivreur(int id) throws SQLException{
        List <Integer> disponible=new ArrayList<>();
        int idlivreur=0;
        int idp=0;
        String ad=null;
        String sql_2="SELECT `Idp` FROM `personnell` WHERE Disponibilité='oui' AND Specialite='livreur' AND Idp NOT IN (SELECT Idp FROM livraison);";
        Statement st = cnx.createStatement();
        ResultSet rst = st.executeQuery(sql_2);
        while (rst.next()){
           disponible.add(rst.getInt("Idp")); }
           idlivreur=disponible.get(0);       
        String sql_1 ="SELECT livraison.Idp,panier.Delegation FROM panier INNER JOIN livraison on panier.Idpanier=livraison.Idpanier ORDER BY livraison.id_livraison DESC Limit 1;";
        Statement ste = cnx.createStatement();
        ResultSet rs_1 = ste.executeQuery(sql_1);
        while (rs_1.next()){
         idp=rs_1.getInt("livraison.Idp"); 
         ad= rs_1.getString("panier.Delegation");}  
        Double distance= Distance(ad, getadr(id));
        if (distance < 9000.0)   
        {   return idp;}
        else {
            return idlivreur;
        }
    }
       
    public void modifierDispoLivreur(int id) throws SQLException{
    String sql ="Select count(*) from livraison where idp='"+id+"'";
    Statement ste = cnx.createStatement();
    ResultSet rs = ste.executeQuery(sql);
    int size = 0;
        rs.next();
        size=rs.getInt(1);
    if (size ==3){
    String sql_1="Update personnell set Disponibilité='non' where idp='"+id+"'";
    PreparedStatement st =cnx.prepareStatement(sql_1);         
    st.executeUpdate();
    }else {
        String sql_1="Update personnell set Disponibilité='oui ' where idp='"+id+"'";
    PreparedStatement st =cnx.prepareStatement(sql_1);         
    st.executeUpdate();
    }
    }

    @Override
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
    @Override
    public ObservableList<Livraison> afficher() {
        ObservableList<Livraison> Livraison =FXCollections.observableArrayList();
        String sql ="SELECT livraison.id_livraison, livraison.date, livraison.Etat, panier.Numfacture, personnell.nomp, personnell.prenomp,panier.Delegation, panier.Idplat, clientinfo.nom, clientinfo.prenom, clientinfo.Tel FROM livraison, personnell, clientinfo INNER JOIN panier where livraison.Idpanier=panier.Idpanier AND panier.Id_client=clientinfo.Id_client AND livraison.Idp=personnell.Idp;";
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(sql);
           while(rst.next()){
               Livraison L= new Livraison();
               L.setId_Livraison(rst.getInt("id_livraison"));
               L.setDate(rst.getDate("date"));
               L.setEtat(rst.getString("Etat"));
               L.setNum_facture(rst.getString("Numfacture"));
               L.setNom_livreur(rst.getString("nomp"));
               L.setPrenom_livreur(rst.getString("prenomp"));
               L.setAdresse_livraison(rst.getString("Delegation"));
               L.setIdplat(rst.getInt("Idplat"));
               L.setNom_client(rst.getString("nom"));
               L.setPrenom_client(rst.getString("prenom"));
               L.setTelclient(rst.getInt("tel"));             
            Livraison.add(L);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Livraison;
    }

    @Override
    public void modifier(Livraison y) {
        String sql="update Livraison set Idpanier=?, Idp=? where Id_livraison='"+y.getId_Livraison()+"'";
            try {            
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setInt(1, y.getId_panier());
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
          String req="SELECT * FROM livraison WHERE (id_livraison LIKE ? or date LIKE ? or Idpanier LIKE ? or Idp LIKE ? )";            
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
                y.setId_panier(rs.getInt(3));
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
          String req = "SELECT livraison.id_livraison, livraison.date, livraison.Etat, panier.Numfacture, personnell.Nomp, personnell.Prenomp,panier.Delegation, panier.Idplat, clientinfo.nom, clientinfo.prenom, clientinfo.Tel FROM livraison, personnell, clientinfo INNER JOIN panier where livraison.Idpanier=panier.Idpanier AND panier.Id_client=clientinfo.Id_client AND livraison.Idp=personnell.Idp ORDER BY`date` DESC;";

        ObservableList<Livraison> list=FXCollections.observableArrayList();
        try {
           Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){               
              Livraison l=new Livraison(rst.getInt(1),rst.getDate(2),rst.getString(3),rst.getString(4), rst.getString(5),rst.getString(6),rst.getString(7),rst.getInt(8),rst.getString(9),rst.getString(10), rst.getInt(11));
               list.add(l);
           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;       
     }
     public List<String> getAllfacture() {
        List<String> list = new ArrayList<String>();
        try {
            String requetee = "SELECT panier.Numfacture FROM panier WHERE panier.Idpanier NOT IN (SELECT livraison.Idpanier FROM livraison);";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());
            while (rs.next()) {
                list.add(rs.getString("panier.Numfacture"));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
     public List<String> getModiffacture() {
        List<String> list = new ArrayList<String>();
        try {
            String requetee = "SELECT Numfacture FROM panier, livraison where livraison.Idpanier=panier.Idpanier;";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());
            while (rs.next()) {
                list.add(rs.getString("Numfacture"));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
     public List<String> getModiflivreur() {
        List<String> list = new ArrayList<String>();
        try {
            String requetee = "SELECT Nomp FROM personnell where Disponibilité='oui' ;";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());
            while (rs.next()) {
                list.add(rs.getString("Nomp"));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
     public int chercherIdpanier(String numfacture) throws SQLException{
         int id=0;
         String requetee = "SELECT idpanier FROM panier where Numfacture='"+numfacture+"';";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {id= rs.getInt("idpanier");
            
     }return id;}
     public int chercherIdlivreur(String nomp) throws SQLException{
         int id=0;
         String requetee = "SELECT idp FROM personnell where Nomp='"+nomp+"';";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {id= rs.getInt("idp");
            
     }return id;}
      public int chercherIdlivraison(int idl) throws SQLException{
         int id=0;
         String requetee = "SELECT id_livraison FROM livraison where Idpanier='"+idl+"';";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {id= rs.getInt("id_livraison");
            
     }return id;}
     public List<String>getlistlivreur() throws SQLException{
         List<String> listmail=new ArrayList<String>();
        String sql = "SELECT personnell.email FROM livraison INNER JOIN personnell where livraison.Idp=personnell.Idp AND livraison.Etat='en cours';";
            PreparedStatement pst = cnx.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());
            while (rs.next()) {
                listmail.add(rs.getString("email"));
            }
            return listmail; 
     }
     public String bodymail(String mail) throws SQLException{
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now(); 
            String sql="SELECT livraison.id_livraison, panier.Numfacture, panier.Delegation, clientinfo.nom, clientinfo.prenom, clientinfo.Tel FROM panier INNER JOIN livraison on livraison.Idpanier=panier.Idpanier INNER JOIN clientinfo on panier.Id_client=clientinfo.Id_client and clientinfo.email='"+mail+"'";
         PreparedStatement pst = cnx.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            String ch1=null;
            String ch=null;
            while (rs.next()) {          
                int id2=rs.getInt("id_livraison");
                String fact=rs.getString("Numfacture");
                String deleg= rs.getString("Delegation");
                String clt=rs.getString("nom");
                String cltt=rs.getString("prenom");
                int tel=rs.getInt("Tel");            
                 ch1="Détails de la commande: numéro de livraison est: "+id2+", le numéro de facture est: "+" "+fact+", la delegation est: "+" "+deleg+", le nom du client est: "+clt+" "+" "+cltt+" "+", le numéro de télephone du client est: "+tel+" ";
                 ch="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
"<head>\n" +
"<!--[if gte mso 9]>\n" +
"<xml>\n" +
"  <o:OfficeDocumentSettings>\n" +
"    <o:AllowPNG/>\n" +
"    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
"  </o:OfficeDocumentSettings>\n" +
"</xml>\n" +
"<![endif]-->\n" +
"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"  <meta name=\"x-apple-disable-message-reformatting\">\n" +
"  <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n" +
"  <title></title>\n" +
"  \n" +
"    <style type=\"text/css\">\n" +
"      table, td { color: #000000; } @media (max-width: 480px) { #u_content_text_1 .v-container-padding-padding { padding: 50px 20px 10px !important; } }\n" +
"@media only screen and (min-width: 620px) {\n" +
"  .u-row {\n" +
"    width: 600px !important;\n" +
"  }\n" +
"  .u-row .u-col {\n" +
"    vertical-align: top;\n" +
"  }\n" +
"\n" +
"  .u-row .u-col-100 {\n" +
"    width: 600px !important;\n" +
"  }\n" +
"\n" +
"}\n" +
"\n" +
"@media (max-width: 620px) {\n" +
"  .u-row-container {\n" +
"    max-width: 100% !important;\n" +
"    padding-left: 0px !important;\n" +
"    padding-right: 0px !important;\n" +
"  }\n" +
"  .u-row .u-col {\n" +
"    min-width: 320px !important;\n" +
"    max-width: 100% !important;\n" +
"    display: block !important;\n" +
"  }\n" +
"  .u-row {\n" +
"    width: calc(100% - 40px) !important;\n" +
"  }\n" +
"  .u-col {\n" +
"    width: 100% !important;\n" +
"  }\n" +
"  .u-col > div {\n" +
"    margin: 0 auto;\n" +
"  }\n" +
"}\n" +
"body {\n" +
"  margin: 0;\n" +
"  padding: 0;\n" +
"}\n" +
"\n" +
"table,\n" +
"tr,\n" +
"td {\n" +
"  vertical-align: top;\n" +
"  border-collapse: collapse;\n" +
"}\n" +
"\n" +
"p {\n" +
"  margin: 0;\n" +
"}\n" +
"\n" +
".ie-container table,\n" +
".mso-container table {\n" +
"  table-layout: fixed;\n" +
"}\n" +
"\n" +
"* {\n" +
"  line-height: inherit;\n" +
"}\n" +
"\n" +
"a[x-apple-data-detectors='true'] {\n" +
"  color: inherit !important;\n" +
"  text-decoration: none !important;\n" +
"}\n" +
"\n" +
"</style>\n" +
"  \n" +
"  \n" +
"\n" +
"</head>\n" +
"\n" +
"<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #e00909;color: #000000\">\n" +
"  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
"  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
"  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #8c0700;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"  <tbody>\n" +
"  <tr style=\"vertical-align: top\">\n" +
"    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
"    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #8c0700;\"><![endif]-->\n" +
"    \n" +
"\n" +
"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #688983;\">\n" +
"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #688983;\"><![endif]-->\n" +
"      \n" +
"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
"  \n" +
"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 0px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
"    <tbody>\n" +
"      <tr style=\"vertical-align: top\">\n" +
"        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
"          <span>&#160;</span>\n" +
"        </td>\n" +
"      </tr>\n" +
"    </tbody>\n" +
"  </table>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"    </div>\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"\n" +
"\n" +
"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n" +
"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-image:url('C:\\Users\\LENOVO\\Desktop\\logoo.png');background-repeat: no-repeat;background-position: center top;background-color: transparent;\">\n" +
"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-image:url('C:\\Users\\LENOVO\\Desktop\\logoo.png');background-repeat: no-repeat;background-position: center top;background-color: transparent;\"><![endif]-->\n" +
"      \n" +
"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
"  \n" +
"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:136px 10px 120px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <h1 style=\"margin: 0px; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: impact,chicago; font-size: 50px;\">\n" +
"    Alerte livraison !<br />" +
"  </h1>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"    </div>\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"\n" +
"\n" +
"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #fff1cc;\">\n" +
"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #fff1cc;\"><![endif]-->\n" +
"      \n" +
"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"  <div style=\"width: 100% !important;\">\n" +
"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n" +
"  \n" +
"<table id=\"u_content_text_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:50px 40px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <div style=\"color: #191e32; line-height: 190%; text-align: left; word-wrap: break-word;\">\n" +
"    <p style=\"font-size: 14px; line-height: 190%;\"><span style=\"font-size: 16px; line-height: 30.4px; font-family: 'trebuchet ms', geneva;\">"+dtf.format(now)+"</span></p>\n "+
"<p style=\"font-size: 14px; line-height: 190%;\"><span style=\"font-size: 16px; line-height: 30.4px; font-family: 'trebuchet ms', geneva;\"></span></p>\n" +
"<p style=\"font-size: 14px; line-height: 190%;\"><span style=\"font-size: 16px; line-height: 30.4px; font-family: 'trebuchet ms', geneva;\"></span></p>\n" +
"<p style=\"font-size: 14px; line-height: 190%;\">&nbsp;</p>\n" +
"<p style=\"font-size: 14px; line-height: 190%;\"><span style=\"font-size: 18px; line-height: 34.2px;\"><strong><span style=\"line-height: 34.2px; font-family: 'trebuchet ms', geneva; font-size: 18px;\">Bonjour monsieur,</span></strong></span></p>\n" +
"<p style=\"font-size: 14px; line-height: 190%;\">&nbsp;</p>\n" +
"<p style=\"font-size: 14px; line-height: 190%;\"><span style=\"font-size: 16px; line-height: 30.4px; font-family: 'trebuchet ms', geneva;\">"+ch1+"<span></p>\n" +
"<p style=\"font-size: 14px; line-height: 190%;\">&nbsp;</p>\n" +
"<p style=\"font-size: 14px; line-height: 190%;\"><span style=\"font-size: 18px; line-height: 34.2px;\"><strong><span style=\"line-height: 34.2px; font-family: 'trebuchet ms', geneva; font-size: 18px;\">Codialement.</span></strong></span></p>\n" +
"<p style=\"font-size: 14px; line-height: 190%;\"><span style=\"font-size: 16px; line-height: 30.4px; font-family: 'trebuchet ms', geneva;\">Chef employé.&nbsp;</span></p>\n" +
"<p style=\"font-size: 14px; line-height: 190%;\">&nbsp;</p>\n" +
"<p style=\"font-size: 14px; line-height: 190%;\">&nbsp;</p>\n" +
"<p style=\"font-size: 14px; line-height: 190%;\">&nbsp;</p>\n" +
"  </div>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"    </div>\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"\n" +
"\n" +
"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #688983;\">\n" +
"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #688983;\"><![endif]-->\n" +
"      \n" +
"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
"  \n" +
"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 0px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
"    <tbody>\n" +
"      <tr style=\"vertical-align: top\">\n" +
"        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
"          <span>&#160;</span>\n" +
"        </td>\n" +
"      </tr>\n" +
"    </tbody>\n" +
"  </table>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"    </div>\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"\n" +
"    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
"    </td>\n" +
"  </tr>\n" +
"  </tbody>\n" +
"  </table>\n" +
"  <!--[if mso]></div><![endif]-->\n" +
"  <!--[if IE]></div><![endif]-->\n" +
"</body>\n" +
"\n" +
"</html>";
            }
            return ch;
     }
}

