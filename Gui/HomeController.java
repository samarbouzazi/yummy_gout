/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL PRCISION 3551
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnfr;
    @FXML
    private Button btnstock;
    @FXML
    private Button idbranche;
    @FXML
    private Button idbranche1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnfou(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Afficher_fournisseur.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
                }
    @FXML
    private void btnsto(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Afficher_stock.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void btnmail(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Mailing.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void rec(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Afficher_reclamation.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
        
    }

    @FXML
    private void rep(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/Gui/reponssse.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void per(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Afficher_personnel.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void pan(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Commande.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void plat(ActionEvent event) throws IOException {
        
                 Parent root = FXMLLoader.load(getClass().getResource("/Gui/Crud_menu.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void catg(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/Gui/Crud_categorie.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    

    @FXML
    private void brancheee(ActionEvent event) throws IOException {
              Parent root = FXMLLoader.load(getClass().getResource("/Gui/AjouterBranche.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void Livraison(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/LivraisonCRUD.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void avis(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/crudavis.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void blog(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/AffBlog.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void res(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Reservation.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void fich(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/ClientInfo.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    
}
