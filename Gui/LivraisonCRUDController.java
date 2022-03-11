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
import Services.LivraisonService;
import Services.LivraisonServicee;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import Entities.Livraison;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.controlsfx.control.Notifications;
import Tools.MaConnexion;
import Tools.mailing_amani;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class LivraisonCRUDController implements Initializable {
    @FXML
    private JFXButton vider;
    @FXML
    private JFXButton ajouter;
    @FXML
    private TextField Recherche;
    @FXML
    private FontAwesomeIconView close;
    @FXML
    private TableView<Livraison> livraisonlist;
    @FXML
    private TableColumn<?, ?> idlivrai;
    @FXML
    private TableColumn<?, ?> coldate;
    @FXML
    private TableColumn<?, ?> colliv;
    @FXML
    private TableColumn<?, ?> colclient;
    String query = null;
    ObservableList<Livraison>listeliv = FXCollections.observableArrayList();
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Connection cnx= MaConnexion.getInstance().getCnx();
    @FXML
    private Button tri;
    @FXML
    private Button actualiser;
    @FXML
    private Button supp;
    @FXML
    private Button btnmail;
    @FXML
    private FontAwesomeIconView home;
    @FXML
    private TableColumn<?, ?> colEtat;
    @FXML
    private TableColumn<?, ?> colnumfacture;
    @FXML
    private TableColumn<?, ?> colprenom;
    @FXML
    private TableColumn<?, ?> colprenomclient;
    @FXML
    private TableColumn<?, ?> coltelclt;
    @FXML
    private TableColumn<?, ?> coldelegation;
    @FXML
    private TableColumn<?, ?> colidplat;
    @FXML
    private JFXComboBox<String> numfacture;
    @FXML
    private Button smsss;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadlivraison();
        refresh();
        // TODO
    }    
    @FXML
    private void Vider(MouseEvent event) {
        numfacture.setValue(null);
    }
    @FXML
    private void AjouterLivraison(MouseEvent event) throws SQLException {         
        LivraisonService ls= new LivraisonService();
        //LivraisonServicee lse= new LivraisonServicee();
                String facturee= numfacture.getValue();
                int idpanier=ls.chercherIdpanier(facturee);
                String adresse=ls.getadr(idpanier); 
                int ide= ls.ReturnIdLivreur(idpanier);
            Livraison l1= new Livraison(idpanier, ide);
                ls.ajouter(l1);  
                ls.modifierDispoLivreur(ide);
                refresh();
               Alert alert =new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Livraison ajoutée");
                alert.showAndWait();
                envoyersms();
                }
    
    @FXML
    private void close(MouseEvent event) {
         Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez vous fermer la fenêtre?") ;
                Optional <ButtonType> result=alert.showAndWait();
                if (result.get()==ButtonType.OK){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}
    private void loadlivraison(){
        LivraisonService ss=new LivraisonService();
        connection= MaConnexion.getInstance().getCnx();
        refresh();
        idlivrai.setCellValueFactory(new PropertyValueFactory<>("id_Livraison"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        colnumfacture.setCellValueFactory(new PropertyValueFactory<>("Num_facture"));
        colliv.setCellValueFactory(new PropertyValueFactory<>("Nom_livreur"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("Prenom_livreur"));
        coldelegation.setCellValueFactory(new PropertyValueFactory<>("Adresse_livraison"));
        colidplat.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
        colclient.setCellValueFactory(new PropertyValueFactory<>("Nom_client")); 
        colprenomclient.setCellValueFactory(new PropertyValueFactory<>("Prenom_client")); 
        coltelclt.setCellValueFactory(new PropertyValueFactory<>("telclient"));
        numfacture.setItems(FXCollections.observableArrayList(ss.getAllfacture()));
    }
  private void refresh() {
        try {
            listeliv.clear();   
            query = "SELECT livraison.id_livraison, livraison.date, livraison.Etat, panier.Numfacture, personnell.Nomp, personnell.Prenomp, panier.Delegation, panier.Idplat, clientinfo.nom, clientinfo.prenom, clientinfo.Tel FROM livraison, personnell, clientinfo INNER JOIN panier where livraison.Idpanier=panier.Idpanier AND panier.Id_client=clientinfo.Id_client AND livraison.Idp=personnell.Idp;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                listeliv.add(new Livraison(
               resultSet.getInt("id_livraison"),
               resultSet.getDate("date"),
               resultSet.getString("Etat"),
               resultSet.getString("Numfacture"),
               resultSet.getString("Nomp"),
               resultSet.getString("Prenomp"),
               resultSet.getString("Delegation"),
               resultSet.getInt("Idplat"),
               resultSet.getString("Nom"),
               resultSet.getString("Prenom"),
               resultSet.getInt("Tel")));   
            livraisonlist.setItems(listeliv);}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//              listeliv.clear();
//            LivraisonService ls= new LivraisonService();
//            listeliv=ls.afficher();
    }
    @FXML
    private void trier(ActionEvent event) {
       LivraisonService ps=new LivraisonService();
        Livraison b= new Livraison();
        ObservableList<Livraison>filter= ps.TriLivraison();
        populateTable(filter);
    }    
    private void populateTable(ObservableList<Livraison> branlist){
       livraisonlist.setItems(branlist);
   }
    @FXML
    private void rechercher(KeyEvent event) {
        LivraisonService bs=new LivraisonService(); 
        Livraison b= new Livraison();
        ObservableList<Livraison>filter= bs.chercherav(Recherche.getText());
        populateTable(filter);       
    }
    @FXML
    private void actualiser(ActionEvent event) {
        refresh();
    }

    @FXML
    private void supprimerliv(ActionEvent event) {
        if (!livraisonlist.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer la branche " + livraisonlist.getSelectionModel().getSelectedItem().getId_Livraison()+ " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

    if (alert.getResult() == ButtonType.YES) {
    LivraisonService bs = new LivraisonService();
    bs.supprimer(livraisonlist.getSelectionModel().getSelectedItem());
     Notifications notificationBuild = Notifications.create()
                                      .title("Traitement branche")
                                      .text("la branche a été supprimée avec succes")
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
    private void handleButtonAction(ActionEvent event) throws IOException {
        try{
            FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/Gui/modifierpop.fxml"));
            Parent root1=(Parent) fxmlloader.load();
            Stage stage= new Stage();
            stage.setTitle("second window");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println("erreur");
        }
    }

    @FXML
    private void sendmaillivreur(ActionEvent event) throws MessagingException, AddressException, IOException, SQLException {
        mailing_amani mail=new mailing_amani();
        mail.sendMail();
    }

    @FXML
    private void gotohome(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    private void envoyersms(){
         int num=93863919;
        
        
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername("samar.bouzezi@esprit.tn");
        defaultClient.setPassword("80F5AB45-8424-636D-A60E-F0017BF442DE");
        SmsApi apiInstance = new SmsApi(defaultClient);

        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body("la livraison est en attente voulez consulter le service livraison" );
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


