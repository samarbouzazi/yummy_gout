/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.LivraisonService;
import com.jfoenix.controls.JFXComboBox;
import Entities.Livraison;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import Tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifierpopController implements Initializable {

    @FXML
    private Button modifierbtn;
    @FXML
    private JFXComboBox<String> combofac;
    @FXML
    private JFXComboBox<String> combolivreur;
    Connection connection = null ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadlivraison();
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        Livraison br=new Livraison();
        LivraisonService bs = new LivraisonService();
        int id=bs.chercherIdpanier(combofac.getValue());
        int idlivraison= bs.chercherIdlivraison(id);
        br.setId_Livraison(idlivraison);
        br.setId_panier(id);
        br.setId_Livreur(bs.chercherIdlivreur(combolivreur.getValue()));       
        bs.modifier(br);  
        Alert alert =new Alert(Alert.AlertType.WARNING);
                 alert.setContentText("Livraison modifiée");
                 alert.showAndWait();
    }
    private void loadlivraison(){
        LivraisonService ss=new LivraisonService();
        connection= MaConnexion.getInstance().getCnx();
        combofac.setItems(FXCollections.observableArrayList(ss.getModiffacture()));
        combolivreur.setItems(FXCollections.observableArrayList(ss.getModiflivreur()));
    }
}
