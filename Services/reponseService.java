/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.reclamationn;
import Entities.reponse;
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
 * @author HP
 */
public class reponseService  implements IServiceMarwa<reponse>{
Connection cnx= MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(reponse r) {
String sql ="insert into reponse( reponse,daterep,idrec) values(?,CURRENT_TIMESTAMP,?) ";
        try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, r.getReponse());
            ste.setInt(2, r.getIdrec());
            ste.executeUpdate();
            System.out.println(" Reponse Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<reponse> afficher() {
   List<reponse> reponses = new ArrayList<>();
        String sql ="select * from reponse";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                reponse r = new reponse();
                
                r.setIdrep(rs.getInt("idrep"));
                
                r.setReponse(rs.getString("reponse"));
                r.setDaterep(rs.getDate("daterep"));
                r.setIdrec(rs.getInt("idrec"));
                reponses.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reponses;
    }

    @Override
    public void supprimer(reponse r) {
String sql =" DELETE FROM reponse WHERE idrep = '"+r.getIdrep()+"'" ;
        try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("réponse supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(reponse r) {
String sql="update reponse set reponse=?  where idrep='"+r.getIdrep()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);           
                ste.setString(1,r.getReponse());
                ste.executeUpdate();
            System.out.println("Réponse Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
public List<Integer> getAllr() {
        List<Integer> list = new ArrayList<Integer>();
        try {
            String requetee = "SELECT idrec FROM reclamationn";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());

            while (rs.next()) {
                list.add(rs.getInt("idrec"));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
   
}
