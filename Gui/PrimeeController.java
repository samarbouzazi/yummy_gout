/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.personnel;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Services.PersonnelService;
import Tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PrimeeController implements Initializable {
    Connection connection=null;
    @FXML
    private TableView<personnel> idpp;
    @FXML
    private TableColumn<?, ?> idp1;
    @FXML
    private TableColumn<?, ?> nom1;
    @FXML
    private TableColumn<?, ?> prenom1;
    @FXML
    private TableColumn<?, ?> salaire1;
    @FXML
    private TableColumn<?, ?> nb1;
    @FXML
    private TableColumn<?, ?> taux1;
    @FXML
    private TableColumn<?, ?> prime1;
    @FXML
    private TextField iiddpp;
    @FXML
    private Button primer;
 private Statement ste;
    private personnel p;
    String query = null;
    
    Connection cnx=MaConnexion.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    personnel per = null ;
    ObservableList<personnel>  List = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Primer(ActionEvent event) {
              personnel p=new personnel();
             PersonnelService ps = new PersonnelService();
            int nbr= Integer.valueOf(iiddpp.getText());
            this.iiddpp.setText(String.valueOf(nbr));
            
            ps.prime(nbr);
            load();
            
    }
    
    
    private void load(){
              personnel p=new personnel();
    connection = MaConnexion.getInstance().getCnx();
    refresh();
    idp1.setCellValueFactory(new PropertyValueFactory<>("Idp"));
    nom1.setCellValueFactory(new PropertyValueFactory<>("nomp"));
    prenom1.setCellValueFactory(new PropertyValueFactory<>("prenomp"));
    salaire1.setCellValueFactory(new PropertyValueFactory<>("Salaire"));
    nb1.setCellValueFactory(new PropertyValueFactory<>("nbheure"));
    taux1.setCellValueFactory(new PropertyValueFactory<>("taux_horaire"));
    prime1.setCellValueFactory(new PropertyValueFactory<>("prime"));
    }
    
   
    private void refresh(){
         personnel p = new personnel();
         int nbr= Integer.valueOf(iiddpp.getText());
            this.iiddpp.setText(String.valueOf(nbr));
            PersonnelService ps = new PersonnelService();
        try {
            List.clear();
            query = "SELECT idp,nomp,prenomp,Salaire,nbheure,taux_horaire,(nbheure-taux_horaire)*(Salaire/taux_horaire)as prime from personnell where nbheure>taux_horaire and Idp like '"+nbr+"' ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                List.add(new  personnel(
                        resultSet.getInt("idp"),
                        resultSet.getString("nomp"),
                        resultSet.getString("prenomp"),
                        resultSet.getInt("Salaire"),
                        resultSet.getInt("nbheure"),
                        resultSet.getInt("taux_horaire"),
                        resultSet.getInt("prime")
                        )); 
               idpp.setItems(List);
               ps.prime(nbr);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
  
}
