/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Livraison1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

/**
 *
 * @author LENOVO
 */
public class LivraisonService1 implements IService1<Livraison1>{
    Connection cnx= MaConnexion.getInstance().getCnx();   
    public int exist(Livraison1 y) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from Livraison WHERE Idfac = '" + y.getId_facture() +"' and Idp='"+y.getId_Livreur()+"'");
        int size = 0;
        rs.next();
        size=rs.getInt(1);
        return size;
    }
    @Override
    public void ajouter(Livraison1 y) {
        try {
            if (exist(y)== 0){
            int id = 0;
            String requete = "SELECT `Idfac` FROM `facture` WHERE Idfac=?";
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, y.getId_facture());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }else {
                System.out.println("Facture n'existe pas");
            }
            int id_2 = 0;
            String req = "SELECT Idp FROM personnel  WHERE Specialite='livreur' and Idp=?";
            PreparedStatement p = cnx.prepareStatement(req);
            p.setInt(1, y.getId_Livreur());
            ResultSet rst = p.executeQuery();
            if (rst.next()) {
                id_2 = rst.getInt(1);
            }else {
               System.out.println("Personnel n'existe pas"); 
            }
            String sql ="INSERT INTO `livraison`(`date`, `Idfac`, `Idp`) VALUES(CURRENT_TIMESTAMP,?,?) ";
            PreparedStatement pse =cnx.prepareStatement(sql);
            pse.setInt(1, id);
            pse.setInt(2, id_2);           
            pse.executeUpdate();
            System.out.println("Livraison Ajoutée");}
            else{
            System.out.println("Livraison n'existe pas ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
    @Override
    public List<Livraison1> afficher() {
        List<Livraison1> Livraison = new ArrayList<>();
        String sql ="SELECT livraison.id_livraison, livraison.date, livraison.Idfac, livraison.Idp , facture.Idplat, facture.Idclient FROM `livraison` INNER JOIN `facture` where livraison.Idfac=facture.Idfac;";
        try {
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(sql);
           while(rst.next()){
              Livraison1 L =new Livraison1(rst.getInt(1),rst.getDate(2),rst.getInt(3),rst.getInt(4),rst.getInt(5),rst.getInt(6));
               Livraison.add(L);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Livraison;
    }

   /* @Override
    public void modifier(Livraison y) {
        String sql="update Livraison set Idfac=?, Idliv=? where Id_livraison='"+y.getId_Livraison()+"'";
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
    }*/

    @Override
    public void modifier(Livraison1 y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Livraison1 y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

