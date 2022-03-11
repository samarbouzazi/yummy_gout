/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import Entities.reclamationn;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.controlsfx.control.Notifications;
import Services.BadWords;
import Services.PersonnelService;
import Services.controlesaisie;
import Services.reclamationService;
import Tools.MaConnexion;
import Tools.mailing;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Afficher_reclamationController implements Initializable {
    reclamationn ss=new reclamationn();
    private Statement ste;
    private reclamationn r;
    String query = null;
    Connection connection = null ;
    Connection cnx=MaConnexion.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    
   ObservableList<reclamationn>  List = FXCollections.observableArrayList();

    @FXML
    private TextArea txt_rec;
    @FXML
    private JFXComboBox<String> combox;
    @FXML
    private JFXButton ajout;
    @FXML
    private JFXButton clear1;

    @FXML
    private TableColumn<reclamationn, String> rec  ;
     
    @FXML
    private TableColumn<reclamationn, String> daterec;
    @FXML
    private TableColumn<reclamationn, String> idper;
    @FXML
    private TableView<reclamationn> recla;
    @FXML
    private Button modf;
    @FXML
    private TableColumn<reclamationn, String>  desc1;
    @FXML
    private Button mail;
    @FXML
    private Button rep1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
        refresh();
    }    
//BadWords bd = new BadWords();
    @FXML
    private void ajouter(MouseEvent event) throws MessagingException, AddressException, IOException, SQLException {
reclamationService rr = new reclamationService();
        connection= MaConnexion.getInstance().getCnx();
        String description =txt_rec.getText();
        String email = combox.getValue();
        int idp= rr.chercheridper(email);
        reclamationn re = new reclamationn(description,idp);
        
      
      if( !description.isEmpty()){
        rr.ajouter(re);
       
        refresh();
        BadWords bw=new BadWords();
        if (!bw.filterText(re.getDescription())){
        
        Notifications notificationBuilder = Notifications.create()
                .title("notif ")
                .text("reclamation validée")
                //.graphic(new ImageView(img))
         //       .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked on notification");
                  }
        });
       notificationBuilder.darkStyle();
       
         notificationBuilder.show();
         mailing mail = new mailing();
        mail.sendMail();
            }
            else
            {
                Notifications notificationBuilder = Notifications.create()
                .title("notif")
                .text("reclamation bloqué")
                //.graphic(new ImageView(img))
         //       .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked on notification");
                  }
        });
       notificationBuilder.darkStyle();
       
         notificationBuilder.show();
            }}
      else {Alert alert =new Alert(Alert.AlertType.WARNING);
            
            alert.setContentText("verifier les champs");
            alert.showAndWait(); }
    }
    
     private void load() {
    reclamationService r=new reclamationService();
    connection= MaConnexion.getInstance().getCnx();
    rec.setCellValueFactory(new PropertyValueFactory<>("idrec"));

    desc1.setCellValueFactory(new PropertyValueFactory<>("description"));
    daterec.setCellValueFactory(new PropertyValueFactory<>("daterec"));
    idper.setCellValueFactory(new PropertyValueFactory<>("email"));
    combox.setItems(FXCollections.observableArrayList(r.getAll()));
    }
    
    private void refresh() {
        try {
            List.clear();
            
            query = "select reclamationn.idrec ,reclamationn.descriptionrec,reclamationn.daterec,personnell.email from reclamationn inner join personnell where personnell.Idp=reclamationn.Idp";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                List.add(new  reclamationn(
                        resultSet.getInt("idrec"),
                        
                        resultSet.getString("descriptionrec"),
                        resultSet.getDate("daterec"),
                        resultSet.getString("email")
                        )); 
               recla.setItems(List);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    
    
    @FXML
    private void clear() {
        txt_rec.setText(null);
        combox.setValue(null);
    }

    @FXML
    private void supprimer(MouseEvent event) {
        if (!recla.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer le personnel " + recla.getSelectionModel().getSelectedItem().getDescription()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    reclamationService r=new reclamationService();
    r.supprimer(recla.getSelectionModel().getSelectedItem());
     Notifications notificationBuild = Notifications.create()
                                      .title("Traitement réclamation ")
                                      .text("la réclamation a été supprimé avec succes")
                                      .graphic(null)
                                      .position(Pos.CENTER)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      System.out.println("click here");
                                  }
                                  
                              });
                              notificationBuild.show(); 
    refresh();
}
 
        }
         else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("il faut séléctionner une ligne");
            alert.showAndWait();}
        
 
        
    }


    
    
@FXML
    void handleMouseAction(MouseEvent event)
       {
           try {
               reclamationn re = recla.getSelectionModel().getSelectedItem();
               txt_rec.setText(re.getDescription());
               
    
           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
       }
    
    @FXML
    private void modifier(ActionEvent event) {
        reclamationn rr =new reclamationn();
   reclamationService rs = new reclamationService();
   rr=recla.getSelectionModel().getSelectedItem();
   rr.setDescription(txt_rec.getText());
   rs.modifier(rr);
    Notifications notificationBuild = Notifications.create()
                                      .title("Traitement Personnel ")
                                      .text("la réclamation a été modifiée avec succes")
                                      .graphic(null)
                                      //.hideAfter(Duration.Hours(5))
                                      .position(Pos.CENTER)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      System.out.println("click here");
                                  }
                                  
                              });
                              notificationBuild.show(); 
   load(); 
   refresh();
    }

    @FXML
    private void home3(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }



    @FXML
    private void mailnotif(ActionEvent event) throws MessagingException, AddressException, IOException {
        mailing mail = new mailing();
        mail.sendMail();
    }

    @FXML
    private void repondre(MouseEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/Gui/Affiche_reponse.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();  
        
    }
    
}
