/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import Tools.MaConnexion;
import com.jfoenix.controls.JFXTextArea;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import Entities.avis;
import Services.AvisService;
import Services.BadWords;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
//import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author rezki
 */
public class CrudavisController implements Initializable {
Connection connection=null;

    @FXML
    private Button supprimer;
    @FXML
    private FontAwesomeIconView close;
    @FXML
    private TableView<avis> liistesdesavis;
    @FXML
    private TableColumn<?, ?> descavis;
    
    @FXML
    private TableColumn<?, ?> ida;
    @FXML
    private TableColumn<?, ?> idc;
    @FXML
    private TableColumn<?, ?> davis;
    @FXML
    private TableColumn<?, ?> likee;
    @FXML
    private TableColumn<?, ?> deslikee;
    String query = null;
   
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    ObservableList<avis> liisstt  =FXCollections.observableArrayList();
    private avis a;
    Statement ste;
    avis pp =new avis();
    @FXML
    private JFXTextArea descraviss;
    @FXML
    private TextField likeee;
    @FXML
    private TextField Deslikeee;
    @FXML
    private ComboBox<String> idclientt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        load();
        AvisService aa=new AvisService();
        refreshTable();
        idclientt.setItems(FXCollections.observableArrayList(aa.getAll()));
    }    

    @FXML
    private void refreshTable() {
    try {
        liisstt .clear();
        
        query = "select * from avis";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
          liisstt.add(new  avis(
                    resultSet.getInt("idavis"),
                    //resultSet.getInt("id_client "),
                    resultSet.getDate("dateavis"),
                    resultSet.getInt("like"),
                    resultSet.getInt("Deslike"),
                    resultSet.getString("descriptionavis")
            ));
            liistesdesavis.setItems(liisstt);
            
            
            
        }
   
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }

    private void Clear() {
       ida.setText(null);
       idc.setText(null);
       davis.setText(null);
        likee.setText(null);
         deslikee.setText(null);
          
         deslikee.setText(null);
          descavis .setText(null);
         
       
          
        
        
    }

    @FXML
    private void close(MouseEvent event) {
        Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez vous fermer la fenêtre?") ;
                Optional <ButtonType> result=alert.showAndWait();
                if (result.get()==ButtonType.OK){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();}
    }
      private void load() {
        connection= MaConnexion.getInstance().getCnx();
         //connection = MaConnexion.getInstance().getCnx();
         
   AvisService aa =new AvisService() ;
    refreshTable();
    
    ida.setCellValueFactory(new PropertyValueFactory<>("idavis"));
    idc.setCellValueFactory(new PropertyValueFactory<>("Id_client"));
    davis.setCellValueFactory(new PropertyValueFactory<>("dateavis"));
    likee.setCellValueFactory(new PropertyValueFactory<>("like"));
    deslikee.setCellValueFactory(new PropertyValueFactory<>("Deslike"));
    descavis.setCellValueFactory(new PropertyValueFactory<>("descriptionavis"));
//    idclient.setItems(FXCollections.observableArrayList(aa.getAll()));
    }

    @FXML
    private void Supprimer(MouseEvent event) { 
        if (!liistesdesavis.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer le blog " + liistesdesavis.getSelectionModel().getSelectedItem().getDescriptionavis()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
     AvisService aa =new AvisService() ;
    aa.supprimer(liistesdesavis.getSelectionModel().getSelectedItem());
     Notifications notificationBuild = Notifications.create()
                                      .title("Traitement blog")
                                      .text("le produit a été supprimé avec succes")
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
    refreshTable();
}
 
        }
         else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("il faut séléctionner une ligne");
            alert.showAndWait();}
        
 
        
    
    }


    private void Update(MouseEvent event) {
         
  
    }

    private void home(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void add(ActionEvent event) throws SQLException {
        
         connection= MaConnexion.getInstance().getCnx();
          String Id_clientt =idclientt.getValue();
               AvisService aa =new AvisService() ;
               int idcli = aa.chercherclient(Id_clientt);
        int likeees =  Integer.valueOf(likeee.getText());
        int Deslikee =  Integer.valueOf(Deslikeee.getText());
        String descriptionavis =descraviss.getText();
         
        avis ppp =new avis(idcli,likeees ,Deslikee ,descriptionavis);
   
         if(! descriptionavis.isEmpty()){
        aa.ajouter(ppp);
        sms(idclientt.getValue());
       
        refreshTable();
        BadWords bw=new BadWords();
      
        if (!bw.filterText(ppp.getDescriptionavis())){
        Image img = new Image("images/tick.png");
        Notifications notificationBuilder = Notifications.create()
                .title("notif avis")
                .text("avis validée")
                .graphic(new ImageView(img))
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
            }
            else
            {
                Notifications notificationBuilder = Notifications.create()
                .title("notif avis")
                .text("avis bloqué")
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
         else{
    Alert alert =new Alert(Alert.AlertType.WARNING);
//            
        alert.setContentText("verifier les champs");
            alert.showAndWait();}
      

        
    }

    @FXML
    private void home1(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
         Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void select(MouseEvent event) {
        avis a = liistesdesavis.getSelectionModel().getSelectedItem();
      descraviss.setText(a.getDescriptionavis());
    }

    @FXML
    private void Update(ActionEvent event) {
         avis r =new avis();
   AvisService rs = new AvisService();
   r=liistesdesavis.getSelectionModel().getSelectedItem();
   r.setDescriptionavis(descraviss.getText());
   
   rs.modifier(r);
   load(); 
    }
    void sms(String idclientt)
    {
       
        int num=99245400;
        
        
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername("samar.bouzezi@esprit.tn");
        defaultClient.setPassword("80F5AB45-8424-636D-A60E-F0017BF442DE");
        SmsApi apiInstance = new SmsApi(defaultClient);

        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body("avis de  '"+idclientt+"' a été ajouté avec succes" );
        smsMessage.to("+216"+num);
        smsMessage.source("reservation");
        

        List<SmsMessage> smsMessageList = Arrays.asList(smsMessage);
        // SmsMessageCollection | SmsMessageCollection model
        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);
        try {
            String result = apiInstance.smsSendPost(smsMessages);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SmsApi#smsSendPost");
            e.printStackTrace();
        }
    }
    
    
                } 
//@FXML
//    private void ajouter(MouseEvent event) throws MessagingException, AddressException, IOException, SQLException {
//reclamationService rr = new reclamationService();
//        connection= MaConnexion.getInstance().getCnx();
//        String description =txt_rec.getText();
//        String email = combox.getValue();
//        int idp= rr.chercheridper(email);
//        reclamationn re = new reclamationn(description,idp);
//        
//      
//      if( !description.isEmpty()){
//        rr.ajouter(re);
//       mailing mail = new mailing();
//        mail.sendMail();
//        refresh();
//        BadWords bw=new BadWords();
//        if (!bw.filterText(re.getDescription())){
//        
//        Notifications notificationBuilder = Notifications.create()
//                .title("notif ")
//                .text("reclamation validée")
//                //.graphic(new ImageView(img))
//         //       .hideAfter(Duration.seconds(5))
//                .position(Pos.TOP_LEFT)
//                .onAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("clicked on notification");
//                  }
//        });
//       notificationBuilder.darkStyle();
//       
//         notificationBuilder.show();
//            }
//            else
//            {
//                Notifications notificationBuilder = Notifications.create()
//                .title("notif")
//                .text("reclamation bloqué")
//                //.graphic(new ImageView(img))
//         //       .hideAfter(Duration.seconds(5))
//                .position(Pos.TOP_LEFT)
//                .onAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("clicked on notification");
//                  }
//        });
//       notificationBuilder.darkStyle();
//       
//         notificationBuilder.show();
//            }}
//      else {Alert alert =new Alert(Alert.AlertType.WARNING);
//            
//            alert.setContentText("verifier les champs");
//            alert.showAndWait(); }
//    }

                                  

    

