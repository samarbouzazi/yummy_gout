/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.ClientInfo;
import IService.ClientInfoImpl;
import Tools.DataBaseConnection;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author tchet
 */
public class ClientInfoService implements ClientInfoImpl<ClientInfo>{
    
Connection connection = DataBaseConnection.getInstance().getCn();
 
    @Override
    public void ajouter(ClientInfo c) {
        
    try {
        String sql= "INSERT INTO clientinfo (nom,prenom,mdp,email,tel,adresse,points,id_commande) values (?,?,?,?,?,?,?,?)" ;
        PreparedStatement  pste = connection.prepareStatement(sql);
            pste.setString(1, c.getNom());
            pste.setString(2, c.getPrenom());
            pste.setString(3, c.getMdp());
            pste.setString(4, c.getEmail());
            pste.setInt(5, c.getTel());
            pste.setString(6, c.getAdress());
            pste.setInt(7, c.getPoints());
            pste.setInt(8, c.getId_commande());
            pste.executeUpdate();
            System.out.println("Adding Clieninfo with success");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        
    }

    

    public void delete(ClientInfo c) {
         try {
       String sql= "delete from  clientinfo where id_client= '"+c.getId_client()+"'" ;
            PreparedStatement  pste = connection.prepareStatement(sql);
          pste.executeUpdate();
            System.out.println("Deleting Clieninfo with success");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }

   
    public void edit(ClientInfo c ) {
        try {
             
        String sql= "update clientinfo set Nom=? ,Prenom=? ,Adresse=? ,Mdp=? ,Email=? ,Tel=?, Points=?, Id_commande=? where id_client='"+c.getId_client()+"'" ;
        PreparedStatement  pste = connection.prepareStatement(sql);
             pste.setString(1, c.getNom());
            pste.setString(2, c.getPrenom());
            pste.setString(3, c.getAdress());
             pste.setString(4, c .getMdp());
            pste.setString(5, c.getEmail());
            pste.setInt(6, c.getTel());
             pste.setInt(7, c.getPoints());
            pste.setInt(8, c.getId_commande());
            
            pste.executeUpdate();
            System.out.println("Editing Clieninfo with success");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @Override
    public List<ClientInfo> getAll() {
        
        List<ClientInfo> clients = new ArrayList<>();
        String sql ="select * from clientinfo";
        try {
            Statement ste= connection.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                ClientInfo cl = new ClientInfo();
                cl.setId_client(rs.getInt("id_client"));
                cl.setNom(rs.getString("nom"));
                cl.setPrenom(rs.getString("prenom"));
                cl.setAdress(rs.getString("adresse"));
                 cl.setMdp(rs.getString("mdp"));
                  cl.setEmail(rs.getString("email"));
                   cl.setTel(rs.getInt("tel"));
                    cl.setPoints(rs.getInt("points"));
                     cl.setId_commande(rs.getInt("id_commande"));
                clients.add(cl);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clients;
    
        
    }

    
    

   

    
}
