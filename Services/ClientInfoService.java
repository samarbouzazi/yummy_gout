/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.ClientInfo;
import Entities.Reservation;
import IService.ClientInfoImpl;
import Tools.DataBaseConnection;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


/**
 *
 * @author tchet
 */
public class ClientInfoService implements ClientInfoImpl<ClientInfo>{
    
Connection connection = DataBaseConnection.getInstance().getCn();
 
    @Override
    public void ajouter(ClientInfo c) {
        Control ctrl= new Control();
    
        String sql= "INSERT INTO clientinfo (nom,prenom,adress, email, points, tel  ,date) values (?,?,?,?,?,?,?)" ;
        try {    
          
        PreparedStatement  pste = connection.prepareStatement(sql);
            pste.setString(1, c.getNom());
            pste.setString(2, c.getPrenom());
            pste.setString(3, c.getAdress());
            pste.setString(4, c.getEmail());
            pste.setInt(5, c.getTel());
            pste.setInt(6, c.getPoints());
           String x = String.valueOf(c.getDate());
        pste.setDate(7,  java.sql.Date.valueOf(x));
            pste.executeUpdate();
            System.out.println("Adding Clieninfo with success");
           
       
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }  
    

    

    public void delete(ClientInfo c) {
         try {
       String sql= "delete from  clientinfo where id_client= ?" ;
       int x =c.getId_client();
            PreparedStatement  pste = connection.prepareStatement(sql);
            pste.setInt(1, x);
          pste.executeUpdate();
            System.out.println("Deleting Clieninfo with success");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }

   
    public void edit(ClientInfo c ) {
        System.out.println("start");
        try {    
        String sql= "UPDATE clientinfo SET nom=?, prenom=?, adress=?, email=?, tel=?, points=?, date=? where id_client =?" ;
            System.out.println("1");
        PreparedStatement  pste = connection.prepareStatement(sql);
          
        pste.setString(1, c.getNom());
            pste.setString(2, c.getPrenom());
            pste.setString(3, c.getAdress());
              pste.setString(4, c.getEmail());
             pste.setInt(5, c.getTel());
              pste.setInt(6, c.getPoints());
             String x = String.valueOf(c.getDate());
            pste.setDate(7,  java.sql.Date.valueOf(x));
              pste.setInt(8, c.getId_client());
                pste.executeUpdate();
            System.out.println("Editing Clieninfo with success");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
            
    }
    }

    @Override
    public ObservableList<ClientInfo> getAll() {
        
        ObservableList<ClientInfo> clients =  FXCollections.observableArrayList();
        String sql ="select * from clientinfo";
        try {
            Statement ste= connection.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                ClientInfo cl = new ClientInfo();
               
                cl.setNom(rs.getString("nom"));
                cl.setPrenom(rs.getString("prenom"));
                  cl.setEmail(rs.getString("email"));
                    cl.setAdress(rs.getString("adress"));
                    cl.setPoints(rs.getInt("points"));                     
                     cl.setTel(rs.getInt("tel"));
                      cl.setDate(rs.getDate("date").toLocalDate());
                        cl.setId_client(rs.getInt("id_client"));

                clients.add(cl);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clients;
    
        
    }

    
    public ObservableList<ClientInfo> filterBy() {
     
      String req = "SELECT nom,prenom,adress,email,tel,points,date FROM clientinfo ORDER BY points DESC";

        ObservableList<ClientInfo> list =FXCollections.observableArrayList();
        try {
           Statement st = connection.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){
               ClientInfo cl = new ClientInfo();
              
                  ClientInfo r=new ClientInfo(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getInt(5),rst.getInt(6),rst.getDate(7).toLocalDate()); 
               list.add(r);  
           }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
       
     }

    public int calculateRes() {
        String req = "select count(*) from clientinfo";
        int a=-1;
        try {
           Statement ste = connection.createStatement();
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

    public ObservableList<ClientInfo> search(String chaine){
         String req="SELECT * FROM clientinfo WHERE (nom LIKE ? or prenom LIKE ? or adress LIKE ? or email LIKE ? )";
            DataBaseConnection cn = DataBaseConnection.getInstance();
            String ch="%"+chaine+"%";
            ObservableList<ClientInfo> myList= FXCollections.observableArrayList();
        try {
            Statement st = cn.getCn()
                    .createStatement();
            PreparedStatement pst = cn.getCn().prepareStatement(req);
            pst.setString(1, ch);
            pst.setString(2, ch);
            pst.setString(3, ch);
            pst.setString(4, ch);

            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                ClientInfo c = new ClientInfo();
                
                 c.setNom(rs.getString(2));
                 c.setPrenom(rs.getString(3));
                 
                 c.setAdress(rs.getString(4));
                c.setEmail(rs.getString(5));
                c.setTel(rs.getInt(6));
                 c.setTel(rs.getInt(7));
                c.setDate(rs.getDate(8).toLocalDate());
                
                
                
                myList.add(c);
                //System.out.println("founisseur trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
        
      }
    
     public List getById() throws SQLException{
         List<ClientInfo> res = new ArrayList<ClientInfo>();
        String sql = "SELECT * FROM clientinfo ORDER BY id_client  DESC LIMIT 1";
           Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
           while(rs.next()){
               
       ClientInfo r = new ClientInfo();
//      r.setResId(rst.getInt("resId"));
//
//          Reservation r1 = new Reservation (rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4),rst.getDate(5).toLocalDate(),rst.getString(6),rst.getString(7));
//               res.add(r1);
 r.setId_client(rs.getInt("id_client"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setAdress(rs.getString("adress"));
                r.setDate(rs.getDate("date").toLocalDate());
                r.setEmail(rs.getString("email"));
                r.setTel(rs.getInt("tel"));
                r.setPoints(rs.getInt("points"));
                
                res.add(r);
           }
            return res;
    }
     
    
}
