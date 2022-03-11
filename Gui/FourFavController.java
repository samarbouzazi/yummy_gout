/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Fournisseur;
import Services.SFournisseur;
import Tools.MaCon;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author DELL PRCISION 3551
 */
public class FourFavController implements Initializable {
    Connection connection=null;
    Connection cnx= MaCon.getInstance().getCnx();
    SFournisseur sf=new SFournisseur();
    Fournisseur f=new Fournisseur();
    @FXML
    private TableColumn<Fournisseur, String> nomfav;
    @FXML
    private TableColumn<Fournisseur, Integer> nbfav;
    @FXML
    private TableView<Fournisseur> fav;
ObservableList<Fournisseur>  List = FXCollections.observableArrayList();
String query = null;
PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load();
        refresh();
        SFournisseur sf=new SFournisseur();
        sf.fournisseur_favorie();
    }    

   private void refresh(){
        try {
            List.clear();
            query = "select fournisseurs.nomf,sum(stocks.prix_s*stocks.qt_s) as somme from fournisseurs inner join stocks where fournisseurs.idf=stocks.idf group by fournisseurs.idf order by somme desc LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                List.add(new  Fournisseur(
                        
                        resultSet.getString("nomf"),
                        resultSet.getFloat("somme")
                        )); 
               fav.setItems(List);
               //ps.prime(nbr);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
        
    
    private void load(){
    Fournisseur p=new Fournisseur();
    connection = MaCon.getInstance().getCnx();
    refresh();
    nomfav.setCellValueFactory(new PropertyValueFactory<>("nomf"));
    nbfav.setCellValueFactory(new PropertyValueFactory<>("somme"));
    
    }

}
    

