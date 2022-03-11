

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.categorie;
import Entities.platt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Tools.MaConnexion;
import Entities.categorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author chaim
 */
public class Platservice implements Iservice_1<platt>{
 Connection cnx= MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(platt p ) {
         try {
            String sql="insert into platt(Idplat,Descplat,Nomplat,image,idcatt,prix_plat,q_plat,stock) values('"+p.getIdplat()+"','"+p.getDescplat()+"','"+p.getNomplat()+"','"+p.getImage()+"','"+p.getIdcatt()+"','"+p.getPrix_plat()+"','"+p.getQ_plat()+"','"+p.getStock()+"')";
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
        String sql ="select platt.Idplat ,platt.Descplat ,platt.Nomplat ,platt.image, categorie.Nomcat, platt.prix_plat ,platt.Q_plat, platt.stock from platt INNER JOIN categorie where platt.idcatt=categorie.idcatt ";
         
       
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                platt p = new platt();
               // categorie c=new categorie();
                p.setIdplat(rs.getInt("Idplat"));
                p.setDescplat(rs.getString("Descplat"));
                p.setNomplat(rs.getString("Nomplat"));
                p.setImage(rs.getString("image"));
              
                p.setNomcat(rs.getString("Nomcat"));
                p.setPrix_plat( rs.getInt("prix_plat"));
                 p.setQ_plat(rs.getInt("Q_plat"));
                  p.setStock(rs.getInt("stock"));
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
         String sql="update platt set  Descplat=?, Nomplat= ?, image=? ,idcatt=? ,prix_plat=?,q_plat=?,stock=? where Idplat='"+p.getIdplat()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, p.getDescplat());
            ste.setString(2, p.getNomplat());
            ste.setString(3, p.getImage());
            ste.setInt(4, p.getIdcatt()); 
            ste.setInt(5, p.getPrix_plat());
             ste.setInt(6, p.getQ_plat());   
              ste.setInt(7, p.getStock()); 
           ste.executeUpdate();
            System.out.println("plat Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public void modifiersstock(int a){
        platt p=new platt();
          String sql2= "UPDATE platt set stock=stock-1 WHERE platt.Idplat='"+a+"'";
                   try{
            PreparedStatement ste =cnx.prepareStatement(sql2);
            ste.setInt(1,p.getStock());
          //  ResultSet rs=ste_2.executeQuery(sql2);
            ste.executeUpdate(); 
                       System.out.println("stock modifier");
              } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
  
 // public ObservableList<Branche> chercherav(String chaine)  
    public ObservableList<platt> chercherTitreplat(String chaine){
          String sql="SELECT * FROM platt WHERE (Descplat LIKE ? or Nomplat LIKE ?  )";
            
             Connection cnx= MaConnexion.getInstance().getCnx();
            String ch="%"+chaine+"%";
            ObservableList<platt> myList= FXCollections.observableArrayList();
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
                p.setImage(rs.getString(4));
             
                p.setPrix_plat(rs.getInt(6));
                p.setQ_plat(rs.getInt(7));
               
                
                myList.add(p);
                System.out.println("titre trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
    public int chercherIdcat(String Nomcat) throws SQLException{
         int id=0;
         String requetee = "SELECT idcatt FROM categorie where Nomcat='"+Nomcat+"';";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {id= rs.getInt("idcatt");
            }return id;}
   
    public List<String> getAll() {
        List<String> list = new ArrayList<String>();
        try {
            String requetee = "SELECT Nomcat FROM categorie";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());

            while (rs.next()) {
                list.add(rs.getString("Nomcat"));
            }

            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
            
}

  


    

