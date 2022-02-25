/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Fournisseur;
import entités.Stock;
import entités.User;
import entités.personnel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MaConnexion;

/**
 *
 * @author HP
 */
public class PersonnelService implements IService<personnel> {

    Connection cnx = MaConnexion.getInstance().getCnx();

    public int existe(personnel p) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from personnell WHERE email = '" + p.getEmail() + "'");
        int size = 0;
        rs.next();
        size = rs.getInt(1);
        return size;
    }

    @Override
    public void ajouter(personnel p) {
        
          String sql ="insert into personnell(nomp,prenomp,cinp,telp,email,Salaire,Specialite,nbheure,Date_embauche) values(?,?,?,?,?,?,?,?,?); ";
        try {

           if (existe(p) == 0) {
                PreparedStatement ste = cnx.prepareStatement(sql);
                
                ste.setString(1, p.getNomp());
                ste.setString(2, p.getPrenomp());
                ste.setInt(3, p.getCinp());
                ste.setInt(4, p.getTelp());
                ste.setString(5, p.getEmail());
                ste.setInt(6, p.getSalaire());
                ste.setString(7, p.getSpecialite());
                ste.setInt(8, p.getNbheure());
                ste.setDate(9, p.getDate_embauche());
                ste.executeUpdate();
                System.out.println("Personnel Ajoutée");
          } else {
              System.out.println("Personnel  existe déja ");
           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    


  @Override
    public List<personnel> afficher() {
        List<personnel> personnel = new ArrayList<>();
        String sql ="SELECT * FROM personnell ";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                personnel p = new personnel();
                p.setIdp(rs.getInt("Idp"));
                p.setNomp(rs.getString("nomp"));
                p.setPrenomp(rs.getString("prenomp"));
                p.setCinp(rs.getInt("cinp"));
                p.setTelp(rs.getInt("telp"));
                p.setEmail(rs.getString("email"));
                p.setSalaire(rs.getInt("Salaire"));
                p.setSpecialite(rs.getString("Specialite"));
                p.setNbheure(rs.getInt("nbheure"));
                p.setDate_embauche(rs.getDate("Date_embauche"));
                personnel.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnel;
    }
    @Override
    public void modifier(personnel p) {
        String sql = "update personnell set `nomp`='"+p.getNomp()+"' "
                + ",`prenomp`='"+p.getPrenomp()+"'"
                +",`prenomp`='"+p.getPrenomp()+"'"
                +",`cinp`='"+p.getCinp()+"'"
                +",`telp`='"+p.getTelp()+"'"
                +",`email`='"+p.getEmail()+"'"
                +",`Salaire`='"+p.getSalaire()+"'"
                +",`Specialite`='"+p.getSpecialite()+"'"
                +",`nbheure`='"+p.getNbheure()+"'"
                +",`Date_embauche`='"+p.getDate_embauche()+"'"
                + " WHERE `Idp`='"+p.getIdp()+"'";
                
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            Statement st=cnx.createStatement();
            ste.executeUpdate();
            System.out.println("personnel Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void supprimer(personnel p) {
        String sql = " DELETE FROM personnell WHERE Idp = '" + p.getIdp() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Personnel supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
public List<personnel> chercherav(String facteur){
          String req="SELECT * FROM personnell WHERE (nomp LIKE ? or prenomp LIKE ? or email LIKE ? or Specialite LIKE ? )";
            MaConnexion myCNX = MaConnexion.getInstance();
            String ch="%"+facteur+"%";
            ArrayList<personnel> myList= new ArrayList();
        try {
            Statement st = myCNX.getCnx().createStatement();
            PreparedStatement pst = myCNX.getCnx().prepareStatement(req);
            pst.setString(1, ch);
            pst.setString(2, ch);
            pst.setString(3, ch);
            pst.setString(4, ch);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                personnel p = new personnel ();
                p.setIdp(rs.getInt(1));
                p.setNomp(rs.getString(2));
                p.setPrenomp(rs.getString(3));
                p.setEmail(rs.getString(4));
                p.setSpecialite(rs.getString(5));
                
                
                
                myList.add(p);
                //System.out.println("founisseur trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
    
  public int getnbrepersonnel() {
        String req = "select count(*) from personnell";
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
  public ObservableList<personnel> Triper() {
    
          String req = "SELECT Idp,nomp,prenomp,cinp,telp,email,Salaire,Specialite,nbheure,Date_embauche FROM personnell order by nomp";

        ObservableList<personnel> list=FXCollections.observableArrayList();
        try {
           Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){
               
              personnel p=new personnel(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4),rst.getInt(5),rst.getString(6),rst.getInt(7),rst.getString(8),rst.getInt(9),rst.getDate(10));
               list.add(p);
           }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
       
     }
}
