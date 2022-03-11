/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Fournisseur;
import Entities.Stock;
import Tools.MaCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL PRCISION 3551
 */
public class SFournisseur implements IService<Fournisseur>{
    Connection cnx= MaCon.getInstance().getCnx();
    @Override
    public void ajouter(Fournisseur f) {
        SControl sc=new SControl();
        
        String sql ="insert into fournisseurs(nomf,prenomf,catf,telf,addf) values(?,?,?,?,?) ";
        try {
            
        if (sc.existe(f)==0 ){
            
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, f.getNomf());
            ste.setString(2, f.getPrenomf());
            ste.setString(3, f.getCatf());
            ste.setInt(4, f.getTelf());
            ste.setString(5, f.getAddf());
            //ste.setInt(6, f.getIdf());
            ste.executeUpdate();
            System.out.println("FOURNISSEUR Ajouté"); 
            
        }
        else
        {
            System.out.println("FOURNISSEUR déjà existe"); 
        }
        
       
            
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Fournisseur> afficher() {
        List<Fournisseur> Four = new ArrayList<>();
        String sql ="select * from fournisseurs";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Fournisseur f = new Fournisseur();
                f.setIdf(rs.getInt("idf"));
                f.setNomf(rs.getString("nomf"));
                f.setPrenomf(rs.getString("prenomf"));
                f.setCatf(rs.getString("catf"));
                f.setTelf(rs.getInt("telf"));
                f.setAddf(rs.getString("addf"));
                Four.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Four;
    }
    
    

    
//    public void supprimer(Fournisseur f) {
//        String requete = "DELETE FROM fournisseurs WHERE idf=?";
//        try {
//            
//            PreparedStatement pst = MaCon.getInstance().getCnx().prepareStatement(requete);
//            pst.setInt(1,f.getIdf());
//            pst.executeUpdate();
//            System.out.println("Fournisseur supprimé");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        
//    }
//    
//    }
     public void delete(Fournisseur f){
        String requete = "DELETE FROM fournisseurs WHERE idf=?";
        try {
            
            PreparedStatement pst = MaCon.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,f.getIdf());
            pst.executeUpdate();
            System.out.println("Produit supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
    }
    
    }

   
     public ObservableList<Fournisseur> RECHERCHE(int idf ) {
         ObservableList<Fournisseur> Fournisseur = FXCollections.observableArrayList();
        String req = "SELECT idf,nomf,prenomf,catf,telf,addf FROM fournisseurs where idf LIKE '" + idf + "%'  ";
       

        ObservableList<Fournisseur> list=FXCollections.observableArrayList();
        try {
           Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){
               
              Fournisseur f=new Fournisseur(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getInt(5),rst.getString(6));
               list.add(f);
           }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
     }
    
//    public void update2(int idf,String nomf,String prenomf,String catf,int telf,String addf) {
//        
//         try {
//            
//            String requete = "UPDATE fournisseurs SET nomf=?,prenomf=?,catf=?,telf=?,addf=? WHERE idf = ?";
//            PreparedStatement pst = MaCon.getInstance().getCnx().prepareStatement(requete);
//            pst.setString(2,nomf);
//            pst.setString(3,prenomf);
//            pst.setString(4,catf);
//            pst.setInt(5, telf);
//            pst.setString(6, addf);
//            pst.executeUpdate();
//            System.out.println("fourniseur modifié");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//    public void update1(Fournisseur f) {
//        
//    String sql="update fournisseurs set  nomf=?, prenomf= ?, catf=?, telf=?, addf=? where idf='"+f.getIdf()+"'";
//            try {
//            PreparedStatement ste =cnx.prepareStatement(sql);
//            ste.setString(1, f.getNomf());
//            ste.setString(2, f.getPrenomf());
//            ste.setString(3, f.getCatf());
//            ste.setInt(4, f.getTelf());
//            ste.setString(5, f.getAddf());
//            
//           ste.executeUpdate();
//            System.out.println("Fournisseur Modifié");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    
//}
     
         public void update(Fournisseur f) {

        
            String req="UPDATE fournisseurs SET"
                    + " `nomf`='"+f.getNomf()+"'"
                    + ",`prenomf`='"+f.getPrenomf() +"'"
                    + ",`catf`='"+f.getCatf()+"',"
                    + "`telf`='"+f.getTelf()+"'"
                    + ",`addf`='"+f.getAddf()+"'"
                    + " WHERE `idf`='"+f.getIdf()+"'";
            try {
                PreparedStatement ste=cnx.prepareStatement(req);
            Statement st=cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("fournisseur modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
         public void deletef(int idf) {
        
         
        try {
            String req="DELETE FROM fournisseurs WHERE idf="+idf+";";
            
            Statement st=cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Fournisseur supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
         public ObservableList<Fournisseur> chercherav(String facteur){
          String req="SELECT * FROM fournisseurs WHERE (nomf LIKE ? or prenomf LIKE ? or catf LIKE ? or addf LIKE ? )";
            MaCon myCNX = MaCon.getInstance();
            String ch="%"+facteur+"%";
            ObservableList<Fournisseur> myList= FXCollections.observableArrayList();
        try {
            Statement st = myCNX.getCnx()
                    .createStatement();
            PreparedStatement pst = myCNX.getCnx().prepareStatement(req);
            pst.setString(1, ch);
            pst.setString(2, ch);
            pst.setString(3, ch);
            pst.setString(4, ch);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                Fournisseur f = new Fournisseur ();
                f.setIdf(rs.getInt(1));
                f.setNomf(rs.getString(2));
                f.setPrenomf(rs.getString(3));
                f.setCatf(rs.getString(4));
                f.setTelf(rs.getInt(5));
                f.setAddf(rs.getString(6));
                
                
                myList.add(f);
                //System.out.println("founisseur trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
         
        public int getnbrefr() {
        String req = "select count(*) from fournisseurs";
        int a=-1;
        try {
           Statement ste = cnx.createStatement();
            ResultSet rst = ste.executeQuery(req);
   
           while(rst.next()){
              a= rst.getInt(1);
           }
           return a;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }
        public String calculer_pannier(){
   List<Fournisseur> panier = new ArrayList<>();
        String sql="select fournisseurs.nomf, count(stocks.ids) as maxx from fournisseurs INNER join stocks where fournisseurs.idf=stocks.idf group by stocks.idf order by maxx desc limit 1";
        String ch=null;
          try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
           //  panier pas =new panier(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
               
//               
             ch="Fournisseur favorie: "+rs.getString("nomf")+" avec nombre de produits= "+rs.getInt("maxx");
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                
        return ch;
    }
        public List<Fournisseur> fournisseur_favorie(){
        List<Fournisseur> panier = new ArrayList<>();
        String sql="select fournisseurs.nomf,sum(stocks.prix_s*stocks.qt_s)as somme from fournisseurs inner join stocks where fournisseurs.idf=stocks.idf group by fournisseurs.idf";
        
          try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
           //  panier pas =new panier(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
               Fournisseur pas = new Fournisseur();
               
                
                pas.setNomf(rs.getString("nomf"));
                pas.setSomme(rs.getFloat("somme"));
                
                panier.add(pas);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           return panier;     
        
    }
         public ObservableList<Fournisseur> Trif() {
     
  
        
          String req = "SELECT idf,nomf,prenomf,catf,telf,addf FROM fournisseurs order by nomf DESC";

        ObservableList<Fournisseur> list=FXCollections.observableArrayList();
        try {
           Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){
               
              Fournisseur f=new Fournisseur(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getInt(5),rst.getString(6));
               list.add(f);
           }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
       
     }
         
         public static List<Fournisseur> list() {
        List<Fournisseur> myList = new ArrayList<Fournisseur>();
//
//        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
//            String requete = "SELECT * from fournisseurs"; //MAJUSCULE NON OBLIGATOIRE 
//            Statement pst = MaCon.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
//            ResultSet rs = pst.executeQuery(requete);
//            while (rs.next()) {
//                Fournisseur p2 = new Fournisseur();
//
//                p2.setIdf(rs.getInt(1));// soit numero , soit nom de la colonne , comme id => numero (index ) =1
//
//                Fournisseur c = new Fournisseur(rs.getInt(2));
//
//                String requete2 = "SELECT * from fournisseurs WHERE idf=? ";
//                PreparedStatement pst2 = MaCon.getInstance().getCnx().prepareStatement(requete2); // import java.sql.Statement
//                pst2.setInt(1, rs.getInt(2));
//                ResultSet rs2 = pst2.executeQuery();
//                while (rs2.next()) {
//                    c.setLibelle(rs2.getString("libelle"));
//                }
//
//                //c.setLibelle("jj");
//                String categ = c.getLibelle();
//                p2.setIdcategoriemere(categ);
//                p2.setIdcategoriemere(c);
//
//                p2.setLibelle(rs.getString(3));
//                p2.setDiscription(rs.getString(4));
////                ImageView imagev = new ImageView(new Image("file:C:\\wamp\\www\\TunisiaBonPlan2\\web\\uploads\\images\\" + rs.getString(5)));
////                imagev.setFitHeight(100);
////                imagev.setFitWidth(100);
//                p2.setImage("file:C:\\wamp64\\www\\TunisiaBonPlan2\\web\\uploads\\images\\" + rs.getString(5));
//
//                //p2.setImage(imagev);
//                myList.add(p2);
//
//            }
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
        return myList;

    }
    }

    

