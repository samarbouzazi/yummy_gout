/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Email;
import Entities.Fournisseur;
import Entities.Stock;
import Services.SEmail;
import Services.mailUtil;
import Tools.MaCon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author DELL PRCISION 3551
 */
public class MailingController implements Initializable {
    Connection  connection =null;
    ObservableList<Email>  EmailList = FXCollections.observableArrayList();
     String query = null;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
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
    @FXML
    private TableColumn<?, ?> idmail;
    @FXML
    private TableColumn<?, ?> msg;
    @FXML
    private TableColumn<?, ?> destt;
    @FXML
    private TableColumn<?, ?> objj;
    
    Connection cnx= MaCon.getInstance().getCnx();
    @FXML
    private TableView<Email> msgtable;
    @FXML
    private Button envbtn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadStock();
        refresh();
    }    

    @FXML
    private void envoyer(ActionEvent event) {
        mailUtil mu=new mailUtil();
        SEmail se=new SEmail();
        Email e=new Email();
                 try {
            mu.sendMail(dest.getText(),"yummygout@gmail.com","yummy*1999",obj.getText(), contenu.getText(), file);
            e.setDestinataire(dest.getText());
            e.setObjet(obj.getText());
            e.setContenue(contenu.getText());
            se.ajouter(e);
            refresh();
            
            Notifications notificationBuild = Notifications.create()
                                      .title("Traitement Stock")
                                      .text("l'email a été envoyé avec succes")
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
     private void loadStock() {
         SEmail sm=new SEmail();
        connection= MaCon.getInstance().getCnx();
    refresh();
    idmail.setCellValueFactory(new PropertyValueFactory<>("idemail"));
    destt.setCellValueFactory(new PropertyValueFactory<>("destinataire"));
    objj.setCellValueFactory(new PropertyValueFactory<>("objet"));
    msg.setCellValueFactory(new PropertyValueFactory<>("contenue"));
    
    }

    private void refresh() {
      //To change body of generated methods, choose Tools | Templates.
     
        try {
            EmailList.clear();
            
            query = "select * from email";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                EmailList.add(new  Email(
                        resultSet.getString("destinataire"),
                        resultSet.getString("objet"),
                        resultSet.getString("contenue")
                        ));
                       
                         
               msgtable.setItems(EmailList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
      
      
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
         Email per = msgtable.getSelectionModel().getSelectedItem();
    dest.setText(String.valueOf(per.getDestinataire()));
//    prenom.setText(String.valueOf(per.getPrenomf()));
//    categorie.setText(String.valueOf(per.getCatf()));
//    telephone.setText(String.valueOf(per.getTelf()));
//    email.setText(String.valueOf(per.getAddf()));
    }

    @FXML
    private void Clear(ActionEvent event) {
         contenu.setText(null);
        
    }
}
