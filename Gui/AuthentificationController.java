/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import Entities.User;
import Services.SUser;
import Tools.MaCon;
//import com.google.common.io.Resources;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.crypto.Cipher;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;
//import org.bouncycastle.crypto.Digest;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author DELL PRCISION 3551
 */
public class AuthentificationController implements Initializable {
public static int  codem;
    Connection con = MaCon.getInstance().getCnx();

      @FXML
    private AnchorPane content;

    @FXML
    private JFXTextField username_login;

    @FXML
    private JFXPasswordField password_login;

    @FXML
    private Label sign_up;

    @FXML
    private JFXButton btn_login;

    @FXML
    private FontAwesomeIconView home_btn;
     
    SUser su= new SUser();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

    @FXML
    void back(MouseEvent event) throws IOException {
   Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML
    private void btn_forget_password(MouseEvent event) throws IOException {
        AnchorPane newLoadedPane =FXMLLoader.load(getClass().getResource("/Gui/ForgetPassword.fxml"));
      content.getChildren().clear();
      content.getChildren().add(newLoadedPane);
    }
    

    @FXML
    void gotosignup(MouseEvent event) throws IOException {
  Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Gui/FirstRegistration.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

//    @FXML
//    void login(MouseEvent event) throws SQLException {
//   User user;
//        user = SUser.authentifier(username_login.getText(), password_login.getText());
//        System.out.println("useeeeeeeeeer :"+ user);
//        if (user != null) {
//            user_connecte= user;
//            System.out.println(user);
//            //static
//           if(user.getRoles().equals("[\"Admin\"]")){
//               AnchorPane newLoadedPane =FXMLLoader.load(getClass().getResource("/event/back.fxml"));
//            content.getChildren().clear();
//            content.getChildren().add(newLoadedPane);
//           }
//           else  if(user.getRoles().equals("Candidat")){
//               AnchorPane newLoadedPane =FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
//            content.getChildren().clear();
//            content.getChildren().add(newLoadedPane);
//           }
//           else  if(user.getRoles().equals("[\"Entreprise\"]")){
//               AnchorPane newLoadedPane =FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
//            content.getChildren().clear();
//            content.getChildren().add(newLoadedPane);
//           }
//           
//            
//        } else {
//             Notifications notificationBuilder = Notifications.create()
//                    .title("wrong email or password")
//                    .text("type the correct fields")
//                    .hideAfter(Duration.seconds(5))
//                    .position(Pos.CENTER);
//            notificationBuilder.showError();
//            BoxBlur blur = new BoxBlur(3, 3, 3);
//            content.setEffect(blur);
//           
//            content.setEffect(null);
//        }
//    }

    @FXML
    public void login1(ActionEvent event) throws IOException  {
    String email = username_login.getText();
    String password = password_login.getText();
    
    
    if (email.isEmpty() || password.isEmpty()){
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Verify please");
        alert.setHeaderText("Verify");
        alert.setContentText("Verify your Credentials");
        alert.show();
    } else if (su.Login(email,password)){
         Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success");
        alert.setContentText("Success");
        alert.show();
               Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
      
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Home.fxml")));
        stage.setScene(scene);
        stage.show(); 
    } else {
         Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Verify please");
        alert.setHeaderText("Verify");
        alert.setContentText("Verify your Credentials");
        alert.show();
            }   
            
            }
        
         
        
        
   
          
        
        
    
       
//  @FXML
//      private String Login(){
//          String us= password_login.getText();
//       String pass= password_login.getText();
//       try  {
//         String   query = "select * from users where email = ? and password = ?";
//            PreparedStatement pst = con.prepareStatement(query);       
//           
//            pst.setString(1, us);
//            pst.setString(2, pass);
//            //pst.setString(2,DigestUtils.sha1Hex(pass));
//            ResultSet rs = pst.executeQuery();
//            if (!rs.next())
//            { 
//             JOptionPane.showMessageDialog(null, "Login Failed Check Your Credentials");
//             return "Error";
//             } 
//            else {
//             JOptionPane.showMessageDialog(null, "Login ");
//             return "Success";
//                
//                    }
//             
//            
//            
//       } catch(SQLException ex) {
//           System.out.println(ex);
//           return "Exception";
//       }
// 
//}


}


  
