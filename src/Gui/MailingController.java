/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.mailUtil;
import java.io.File;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL PRCISION 3551
 */
public class MailingController implements Initializable {

    @FXML
    private TextField dest;
    @FXML
    private TextField obj;
    @FXML
    private TextArea contenu;
    @FXML
    private ImageView joindre;
    @FXML
    private Button envbtn;
     File file=null;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyer(ActionEvent event) {
        mailUtil mu=new mailUtil();
         try {
            mu.sendMail(dest.getText(),"samar.bouzezi@esprit.tn","191JFT3562@.",obj.getText(), contenu.getText(), file);
        } catch (Exception ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void GoHome(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
        
    }

    @FXML
    private void joinFile(MouseEvent event) {
        
        FileChooser fc=new FileChooser();
        fc.setTitle("choisir un fichier");
        Stage stage=(Stage) AnchorPane.getScene().getWindow();
        file=fc.showOpenDialog(stage);
        
    }
    
}
