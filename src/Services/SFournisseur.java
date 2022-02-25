/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Fournisseur;
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
            if( sc.Controle(f)){
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
        }
        else
        {
            System.out.println("FOURNISSEUR invalide"); 
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

        try {
            String req="UPDATE fournisseurs SET `nomf`='"+f.getNomf()+"',`prenomf`='"+f.getPrenomf() +"',`catf`='"+f.getCatf()+"',`telf`='"+f.getTelf()+"',`addf`='"+f.getAddf()+"' WHERE `idf`='"+f.getIdf()+"'";
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
         public List<Fournisseur> chercherav(String facteur){
          String req="SELECT * FROM fournisseurs WHERE (nomf LIKE ? or prenomf LIKE ? or catf LIKE ? or addf LIKE ? )";
            MaCon myCNX = MaCon.getInstance();
            String ch="%"+facteur+"%";
            ArrayList<Fournisseur> myList= new ArrayList();
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
    }

    

