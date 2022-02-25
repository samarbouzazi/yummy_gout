

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.categorie;
import entities.platt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.Maconnexion;
import entities.categorie;

/**
 *
 * @author chaim
 */
public class Platservice implements Iservice<platt>{
 Connection cnx= Maconnexion.getInstance().getCnx();
    @Override
    public void ajouter(platt p ) {
         try {
            String sql="insert into platt(Idplat,Descplat,Nomplat,image,favorie,idcatt) values('"+p.getIdplat()+"','"+p.getDescplat()+"','"+p.getNomplat()+"','"+p.getimage()+"','"+p.getFavorie()+"','"+p.getidcatt()+"')";
            Statement ste = cnx.createStatement();
           
            ste.executeUpdate(sql);
            System.out.println(" plat Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<platt> afficher() {
         List<platt> plat = new ArrayList<>();
       // String sql ="select * from platt";
        String sql ="select p.Idplat,p.Descplat,p.Nomplat,p.image,p.favorie,c.Nomcat from platt p  INNER JOIN categorie c on p.idcatt=c.idcatt ";
         
       
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                platt p = new platt();
                categorie c=new categorie();
                p.setIdplat(rs.getInt("Idplat"));
                p.setDescplat(rs.getString("Descplat"));
                p.setNomplat(rs.getString("Nomplat"));
                p.setimage(rs.getString("image"));
                p.setFavorie(rs.getInt("favorie"));
                p.setidcatt( rs.getInt("idcatt"));
                plat.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return plat;

    }

    @Override
    public void supprimer(platt p) {
          String sql="delete from platt where Idplat = '"+p.getIdplat()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println("plat supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(platt p) {
         String sql="update platt set  Descplat=?, Nomplat= ?, image=? ,favorie=? ,Nomcat=?  where Idplat='"+p.getIdplat()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, p.getDescplat());
            ste.setString(2, p.getNomplat());
            ste.setString(3, p.getimage());
            ste.setInt(4, p.getFavorie()); 
            ste.setInt(5, p.getidcatt());
                
           ste.executeUpdate();
            System.out.println("plat Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<platt> chercherTitreplat(String Nomplat){
          String sql="SELECT * FROM platt WHERE (Nomplat LIKE ? or Descplat )";
            
             Connection cnx= Maconnexion.getInstance().getCnx();
            String ch="%"+Nomplat+"%";
            ArrayList<platt> myList= new ArrayList();
        try {
           
            Statement ste= cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =cnx.prepareStatement(sql);  
            stee.setString(1, ch);
            stee.setString(2, ch);
            
            
            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                platt p = new platt ();
                p.setIdplat(rs.getInt(1));
                p.setDescplat(rs.getString(2));
                p.setNomplat(rs.getString(3));
                p.setimage(rs.getString(4));
                p.setFavorie(rs.getInt(5));
                p.setidcatt(rs.getInt(6));
               
                
                myList.add(p);
                System.out.println("titre trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
    
   
            
}

  


    

