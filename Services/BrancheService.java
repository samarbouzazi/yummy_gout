/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Services.Iservice_1;
import Entities.Branche;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Tools.MaConnexion;

/**
 *
 * @author LENOVO
 */
public class BrancheService implements Iservice_1 <Branche>{
Connection cnx= MaConnexion.getInstance().getCnx();
         public int exist(Branche y) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from branche WHERE Emplacement = '" + y.getEmplacement() +"'");
        int size = 0;
        rs.next();
        size=rs.getInt(1);
        return size;
    }
    @Override
    public void ajouter(Branche y) {
        String sql ="insert into branche(Nombranche,Contact, Emplacement, Heureo, Heuref, image ) values(?,?,?,?,?,?) ";
        try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, y.getNomBranche());
            ste.setString(2, y.getContact());
            ste.setString(3, y.getEmplacement());
            ste.setString(4, y.getHeureo());
            ste.setString(5, y.getHeuref());
            ste.setString(6, y.getImage());
            ste.executeUpdate();
            System.out.println("Branche Ajoutée");}            
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Branche> afficher() {
        List<Branche> branches = new ArrayList<>();
        String sql ="select * from branche";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Branche b = new Branche();
                b.setIdbranche(rs.getInt("Idbranche"));
                b.setNomBranche(rs.getString("NomBranche"));
                b.setContact(rs.getString("Contact"));
                b.setEmplacement(rs.getString("Emplacement"));
                b.setHeureo(rs.getString("Heureo"));
                b.setHeuref(rs.getString("Heuref"));
                b.setImage(rs.getString("image"));
                branches.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return branches;
    }

    @Override
    public void modifier(Branche y) {
            String sql="update Branche set Nombranche=?, Contact=?, Emplacement=?,  Heureo= ?, Heuref= ?, image=?  where Idbranche='"+y.getIdbranche()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, y.getNomBranche());
            ste.setString(2, y.getContact());
            ste.setString(3, y.getEmplacement());
            ste.setString(4, y.getHeureo());
            ste.setString(5, y.getHeuref());
            ste.setString(6, y.getImage());
            ste.executeUpdate();
            System.out.println("Branche Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Branche y) {
        String sql="delete from branche where Idbranche = '"+y.getIdbranche()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println("Branche supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public ObservableList<Branche> chercherav(String chaine){
          String req="SELECT * FROM branche WHERE (Idbranche LIKE ? or Nombranche LIKE ? or Emplacement LIKE ? or Heureo LIKE ? or Heuref LIKE ? )";            
            String ch="%"+chaine+"%";
            ObservableList<Branche> myList= FXCollections.observableArrayList();
        try {
            Statement ste = cnx.createStatement();
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, ch);
            ps.setString(2, ch);
            ps.setString(3, ch);
            ps.setString(4, ch);
            ps.setString(5, ch);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Branche b = new Branche ();
                b.setIdbranche(rs.getInt(1));
                b.setNomBranche(rs.getString(2));
                b.setContact(rs.getString(3));
                b.setEmplacement(rs.getString(4));
                b.setHeureo(rs.getString(5));
                b.setHeuref(rs.getString(6)); 
                myList.add(b);
                System.out.println("branche trouvée! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
    public ObservableList<Branche> TriLivraison() {       
          String req = "SELECT * from branche ORDER BY `Heureo` DESC;";

        ObservableList<Branche> list=FXCollections.observableArrayList();
        try {
           Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){               
              Branche l=new Branche(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4), rst.getString(5),rst.getString(6));
               list.add(l);
           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;       
     }
}
