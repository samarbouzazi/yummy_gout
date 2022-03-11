/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import Entities.User;
import Services.SUser;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyEvent;
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
public class AuthentificationController implements Initializable {
public static int  codem;
    @FXML
    private JFXTextField username_login;
    @FXML
    private JFXPasswordField password_login;
    @FXML
    private Label sign_up;
    
    SUser userService = new SUser();
    public static User user_connecte = new User();
    @FXML
    private AnchorPane content;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
          User user;
        user = userService.authentifier(username_login.getText(), password_login.getText());
        System.out.println("useeeeeeeeeer :"+ user);
        if (user != null) {
            user_connecte= user;
            System.out.println(user);
            //static
           if(user.getRoles().equals("[\"Admin\"]")){
               AnchorPane newLoadedPane =FXMLLoader.load(getClass().getResource("/event/back.fxml"));
            content.getChildren().clear();
            content.getChildren().add(newLoadedPane);
           }
           else  if(user.getRoles().equals("Candidat")){
               AnchorPane newLoadedPane =FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
            content.getChildren().clear();
            content.getChildren().add(newLoadedPane);
           }
           else  if(user.getRoles().equals("[\"Entreprise\"]")){
               AnchorPane newLoadedPane =FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
            content.getChildren().clear();
            content.getChildren().add(newLoadedPane);
           }
           
            
        } else {
             Notifications notificationBuilder = Notifications.create()
                    .title("wrong email or password")
                    .text("type the correct fields")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER);
            notificationBuilder.showError();
            BoxBlur blur = new BoxBlur(3, 3, 3);
            content.setEffect(blur);
           
            content.setEffect(null);
        }
    }

    @FXML
    private void btn_forget_password(MouseEvent event) throws IOException {
        AnchorPane newLoadedPane =FXMLLoader.load(getClass().getResource("/Gui/ForgetPassword.fxml"));
      content.getChildren().clear();
      content.getChildren().add(newLoadedPane);
    }

    @FXML
    private void gotosignup(MouseEvent event) throws IOException {
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/Gui/FirstRegistration.fxml"));
        content.getChildren().clear();
        content.getChildren().add(newLoadedPane);
        
    }



    @FXML
    private void login(MouseEvent event) {
    }
    
}
