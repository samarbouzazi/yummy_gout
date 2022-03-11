/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Services.SUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author DELL PRCISION 3551
 */
public class FirstRegistrationController implements Initializable {
@FXML
    private JFXTextField cin;
@FXML
    private JFXTextField userName;
@FXML
    private JFXTextField email;
@FXML
    private JFXPasswordField tf_password;
@FXML
    private JFXPasswordField tf_repeter;
@FXML
    
    private AnchorPane content1;
    
    
SUser userService = new SUser();
    private AnchorPane content2;
    private JFXRadioButton role_entreprise;
    private JFXRadioButton role_condidate;
    @FXML
    private Label ver_cin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public static User user = new User();
     String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
            + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
@FXML
    private void gotopage2(MouseEvent event) throws IOException, SQLException {
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(email.getText());
        if (controler.matches()) {
        if ((userService.getUserByUserName(userName.getText())!=0) || ((!tf_password.getText().equals(tf_repeter.getText())))) {
        if (userService.getUserByUserName(userName.getText())!=0) {

                Notifications notificationBuilder = Notifications.create()
                        .title("User already exists")
                        .text("Choose another user name")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.CENTER);

                notificationBuilder.showWarning();
                BoxBlur blur = new BoxBlur(3, 3, 3);
                content1.setEffect(blur);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Type again please");
                alert.setHeaderText("Invalid field");
                alert.setContentText("Check the field please");
                alert.showAndWait();
                content1.setEffect(null);

            } else if ((!tf_password.getText().equals(tf_repeter.getText()))) {

                Notifications notificationBuilder = Notifications.create()
                        .title("Password isn't the same")
                        .text("Type another password")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.CENTER);

                notificationBuilder.showWarning();
                BoxBlur blur = new BoxBlur(3, 3, 3);
                content1.setEffect(blur);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Type again please");
                alert.setHeaderText("Invalid field");
                alert.setContentText("Check the field please");
                alert.showAndWait();
                content1.setEffect(null);

            }
        } 
          
             else {
                     user.setEmail(email.getText());
             user.setPassword(tf_password.getText());
            user.setCin(Integer.parseInt(cin.getText()));
            user.setUsername(userName.getText());

            AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/Gui/SecondRegistration.fxml"));
            content1.getChildren().clear();

            content1.getChildren().add(newLoadedPane);
             }
            
            
        
            
        } else {

            Notifications notificationBuilder = Notifications.create()
                    .title("Bad syntax of Email")
                    .text("Type a correct Email")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER);
            notificationBuilder.showError();
            BoxBlur blur = new BoxBlur(3, 3, 3);
            content1.setEffect(blur);
           
            content1.setEffect(null);

        }
        
        

      
    }
    private void doRegistration(MouseEvent event) throws SQLException, IOException {
        userService.ajouterUser(user);
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/Gui/Authentification.fxml"));
        content2.getChildren().clear();
        content2.getChildren().add(newLoadedPane);
    }
    private void chooseRoleCandidate(ActionEvent event) {
    if (role_condidate.isSelected()){
            role_condidate.setSelected(false);
                      
            user.setRoles("[\"Candidat\"]");
        }
    }
    private void chooseRoleEntreprise(ActionEvent event) {
        if (role_entreprise.isSelected()){
            role_entreprise.setSelected(false);
                     
            user.setRoles("[\"Entreprise\"]");
        }
    }
    private void retour1(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Authentification.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    }

    
    

