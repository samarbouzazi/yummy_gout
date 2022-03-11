/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reservation;
import IService.ReservationImpl;
import Tools.DataBaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;    
import java.time.LocalDate;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author tchet
 */
public class ReservationService implements ReservationImpl<Reservation>{
    Connection connection = DataBaseConnection.getInstance().getCn();
 
    @Override
    public void ajouter(Reservation r) {
        
    try {
        String sql= "INSERT INTO reservation (notes,view,guestNum,resDate,eventType,branchLocation) values (?,?,?,?,?,?)" ;
        PreparedStatement  pste = connection.prepareStatement(sql);
        pste.setString(1, r.getNotes());    
        pste.setString(2, r.getView());
        pste.setInt(3, r.getGuestNum());
        String x = String.valueOf(r.getResDate());
        pste.setDate(4,  java.sql.Date.valueOf(x));
        pste.setString(5, r.getEventType());
        pste.setString(6, r.getBranchLocation());
                
            
            pste.executeUpdate();
            System.out.println("Adding reservation with success");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        
    }

    
    public void deleteRes(Reservation r ) {
         try {
       String sql= "DELETE  FROM  reservation WHERE resId = ?" ;
       int x =r.getResId();
       
            PreparedStatement  pste = connection.prepareStatement(sql);
            pste.setInt(1, x);
                        pste.executeUpdate();
            System.out.println("Deleting reservation with success");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }
public ObservableList<Reservation> search(String chaine){
         String req="SELECT * FROM reservation WHERE (view LIKE ? or eventType LIKE ? or branchLocation LIKE ? or notes LIKE ? )";
            DataBaseConnection cn = DataBaseConnection.getInstance();
            String ch="%"+chaine+"%";
            ObservableList<Reservation> myList= FXCollections.observableArrayList();
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
                Reservation r = new Reservation ();
                 r.setView(rs.getString(3));
                 r.setResDate(rs.getDate(5).toLocalDate());

                 r.setGuestNum(rs.getInt(4));
                r.setNotes(rs.getString(2));
                r.setEventType(rs.getString(6));
                 r.setBranchLocation(rs.getString(7));

                
                
                
                myList.add(r);
                //System.out.println("founisseur trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
        
      }
       @Override

    public void edit(Reservation r ) {
        try { 
        String sql= "UPDATE reservation SET notes=?, view=?, guestNum=?, resDate=?, eventType=?, branchLocation=? where resId=? " ;
        PreparedStatement  pste = connection.prepareStatement(sql);
            
            pste.setString(1, r.getNotes());
            pste.setString(2, r.getView());
            pste.setInt(3, r.getGuestNum());
            String x = String.valueOf(r.getResDate());
            pste.setDate(4,  java.sql.Date.valueOf(x));
            pste.setString(5, r.getEventType());
            pste.setString(6, r.getBranchLocation());
            pste.setInt(7, r.getResId());
            System.out.println(r.getResId());
            pste.executeUpdate();
            System.out.println("Editing reservation with success");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }
 


    
    @Override
    public ObservableList<Reservation> getAll() {
        
        ObservableList<Reservation> reservations =FXCollections.observableArrayList();
        String sql ="select * from reservation";
        try {
            Statement ste= connection.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Reservation r = new Reservation();
                
                r.setBranchLocation(rs.getString("branchLocation"));
                r.setNotes(rs.getString("notes"));
                r.setView(rs.getString("view"));
                r.setGuestNum(rs.getInt("guestNum"));
                r.setResDate(rs.getDate("resDate").toLocalDate());
                r.setEventType(rs.getString("eventType"));
                r.setResId(rs.getInt("resId"));
                
                reservations.add(r);
                   
            }
             System.out.println("This is our list ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reservations;
    
}
    public List getById() throws SQLException{
         List<Reservation> res = new ArrayList<Reservation>();
        String sql = "SELECT * FROM reservation ORDER BY resId  DESC LIMIT 1";
           Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
           while(rs.next()){
               
       Reservation r = new Reservation();
//      r.setResId(rst.getInt("resId"));
//
//          Reservation r1 = new Reservation (rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4),rst.getDate(5).toLocalDate(),rst.getString(6),rst.getString(7));
//               res.add(r1);
 r.setBranchLocation(rs.getString("branchLocation"));
                r.setNotes(rs.getString("notes"));
                r.setView(rs.getString("view"));
                r.setGuestNum(rs.getInt("guestNum"));
                r.setResDate(rs.getDate("resDate").toLocalDate());
                r.setEventType(rs.getString("eventType"));
                r.setResId(rs.getInt("resId"));
                
                res.add(r);

           }

       
            return res;
    }
    

   
    @Override
    public void deleteResWithID(Integer t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
  public ObservableList<Reservation> filterBy() {
     
      String req = "SELECT notes,view,guestNum,resDate,eventType,branchLocation FROM reservation ORDER BY resDate ASC";

        ObservableList<Reservation> list=FXCollections.observableArrayList();
        try {
           Statement st = connection.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){
               
              Reservation r=new Reservation(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDate(4).toLocalDate(),rst.getString(5),rst.getString(6)); 
               list.add(r);
           }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
       
     }
    
 
   public int calculateRes() {
        String req = "select count(*) from reservation";
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
}