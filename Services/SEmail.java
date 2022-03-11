/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Email;
import Entities.Fournisseur;
import Tools.MaCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL PRCISION 3551
 */
public class SEmail {
    Connection cnx= MaCon.getInstance().getCnx();
        
        public void ajouter(Email e) {SControl sc=new SControl();
        
        String sql ="insert into email(destinataire,objet,contenue) values(?,?,?) ";
        try {
            
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, e.getDestinataire());
            ste.setString(2, e.getObjet());
            ste.setString(3, e.getContenue());
            //ste.setInt(6, f.getIdf());
            ste.executeUpdate();
            System.out.println("Email a éte envoyé"); 
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
        public List<Email> afficher() {
        List<Email> Four = new ArrayList<>();
        String sql ="select * from fournisseurs";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Email e = new Email();
                e.setIdemail(rs.getInt("idemail"));
                e.setObjet(rs.getString("objet"));
                e.setDestinataire(rs.getString("destinatire"));
                e.setContenue(rs.getString("contenue"));
                Four.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Four;
    }

    
}
