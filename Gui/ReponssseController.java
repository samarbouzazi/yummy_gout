/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import Entities.personnel;
import Entities.reclamationn;
import Entities.reponse;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import Services.reclamationService;
import Services.reponseService;
import Tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ReponssseController implements Initializable {
reclamationn ss=new reclamationn();
    private Statement ste;
    private reclamationn r;
    String query = null;
    Connection connection = null ;
    Connection cnx=MaConnexion.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    
   ObservableList<reponse>  List = FXCollections.observableArrayList();
   ObservableList<reclamationn>  Listr = FXCollections.observableArrayList();
    @FXML
    private TextField txt_rep;
    //private JFXComboBox<Integer> combox;
    @FXML
    private JFXButton ajout;
    @FXML
    private JFXButton clear1;
    @FXML
    private TableView<reponse> rep;
    @FXML
    private TableColumn<?, ?> idrep;
    @FXML
    private TableColumn<?, ?> repo;
    @FXML
    private TableColumn<?, ?> idrec;
    @FXML
    private TableColumn<?, ?> daterep;
    @FXML
    private Button modf;
    @FXML
    private TableView<reclamationn> recla;
    @FXML
    private TableColumn<?, ?> rec;
    @FXML
    private TableColumn<?, ?> desc1;
    @FXML
    private TableColumn<?, ?> idper;
    @FXML
    private TableColumn<?, ?> daterec;
   
   
int index=-1;
    @FXML
    private JFXComboBox<Integer> combox1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadr();
        refreshr();    
        load();
        refresh();      
    }    
@FXML
    private void clear() {
         txt_rep.setText(null);
        combox1.setValue(null);
    }
    @FXML
    private void ajouter(MouseEvent event) {
     connection=MaConnexion.getInstance().getCnx();
        String reponse =txt_rep.getText();
        int idrec = combox1.getValue();
        reponse re = new reponse(reponse,idrec);
        if( reponse.isEmpty()){
            
            Alert alert =new Alert(Alert.AlertType.WARNING);
            
            alert.setContentText("check fields may contain bad words");
            alert.showAndWait();
            
            
        }
        
        else{
         reponseService reps = new reponseService();
            reps.ajouter(re);
            Notifications notificationBuild = Notifications.create()
                                      .title("Traitement Personnel ")
                                      .text("la réponse a été ajoutée avec succes")
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

        }
refreshr();
clear();
    }
    private void loadr() {
    reponseService rsp =new reponseService();
    connection= MaConnexion.getInstance().getCnx();
    idrep.setCellValueFactory(new PropertyValueFactory<>("idrep"));
    repo.setCellValueFactory(new PropertyValueFactory<>("reponse"));
    daterep.setCellValueFactory(new PropertyValueFactory<>("daterep"));
    idrec.setCellValueFactory(new PropertyValueFactory<>("idrec"));
    combox1.setItems(FXCollections.observableArrayList(rsp.getAllr()));
    }
    
    private void refreshr() {
        try {
            List.clear();
            
            query = "select * from reponse";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                List.add(new  reponse(
                        resultSet.getInt("idrep"),
                        resultSet.getInt("idrec"),
                        resultSet.getString("reponse"),
                        resultSet.getDate("daterep")
                        )); 
               rep.setItems(List);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    

    @FXML
    private void supprimer(MouseEvent event) {
        if (!rep.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer le personnel " + rep.getSelectionModel().getSelectedItem().getReponse()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    reponseService r=new reponseService();
    r.supprimer(rep.getSelectionModel().getSelectedItem());
     Notifications notificationBuild = Notifications.create()
                                      .title("Traitement réclamation ")
                                      .text("la réponse a été supprimée avec succes")
                                      .graphic(null)
                                      .position(Pos.CENTER)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      System.out.println("click here");
                                  }
                                  
                              });
                              notificationBuild.show(); 
    refreshr();
}
 
        }
         else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("il faut séléctionner une ligne");
            alert.showAndWait();}
        
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        {
           try {
               reponse re = rep.getSelectionModel().getSelectedItem();
               txt_rep.setText(re.getReponse());
               
    
           } catch (Exception e) {
               System.out.println(e.getMessage());
           } 
    }
    }

    @FXML
    private void modifier(ActionEvent event) {
        reponse rrr =new reponse();
   reponseService rs = new reponseService();
   rrr=rep.getSelectionModel().getSelectedItem();
   rrr.setReponse(txt_rep.getText());
   rs.modifier(rrr);
   Notifications notificationBuild = Notifications.create()
                                      .title("Traitement Personnel ")
                                      .text("la réponse a été modifiée avec succes")
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
   loadr(); 
   refreshr(); 
    }

@FXML
    private void home6(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/homemarwaa.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    
    
    @FXML
    private void getSlected(MouseEvent event) {
        reclamationn recl = (reclamationn) recla.getSelectionModel().getSelectedItem();
        index =recla.getSelectionModel().getSelectedIndex();
   if(index<= -1){
    return ;
    }
   int c =  recl.getIdrec();
    combox1.setValue(c);
    }
   
    
     private void load() {
    reclamationService r=new reclamationService();
    connection= MaConnexion.getInstance().getCnx();
    rec.setCellValueFactory(new PropertyValueFactory<>("idrec"));
    desc1.setCellValueFactory(new PropertyValueFactory<>("description"));
    daterec.setCellValueFactory(new PropertyValueFactory<>("daterec"));
    idper.setCellValueFactory(new PropertyValueFactory<>("Idp"));
  
    }
    
    private void refresh() {
        try {
            Listr.clear();
            
            query = "select * from reclamationn";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Listr.add(new  reclamationn(
                        resultSet.getInt("idrec"),
                        resultSet.getInt("Idp"),
                        resultSet.getString("descriptionrec"),
                        resultSet.getDate("daterec")
                        )); 
               recla.setItems(Listr);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

   

    
}