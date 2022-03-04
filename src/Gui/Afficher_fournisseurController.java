/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Fournisseur;
import Services.SControl;
import Services.SFournisseur;
//import Services.SStock;
import Tools.MaCon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author DELL PRCISION 3551
 */
public class Afficher_fournisseurController implements Initializable {
Connection connection = null ;
    @FXML
    private TableView<Fournisseur> tablestock;
    @FXML
    private TableColumn<Fournisseur, String> nomCol;
    @FXML
    private TableColumn<Fournisseur, String> prenomCol;
    @FXML
    private TableColumn<Fournisseur, String> catCol;
    @FXML
    private TableColumn<Fournisseur, String> telCol;
    @FXML
    private TableColumn<Fournisseur, String> mailCol;
    @FXML
    private TextField find;
    @FXML
    private TextField nomfounisseur;
    @FXML
    private TextField email;
    @FXML
    private TextField prenom;
    @FXML
    private Button ajout;
    @FXML
    private Button ajout1;
    
    @FXML
    private TextField categorie;
    @FXML
    private TextField telephone;
    ObservableList<Fournisseur>  fourList = FXCollections.observableArrayList();
    String query = null;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    @FXML
    private TableColumn<Fournisseur, Integer> idCol;
    @FXML
    private ImageView logo;
    @FXML
    private Button ajout3;
    @FXML
    private Button ajout4;
    @FXML
    private TextField txtnb;
    
    SFournisseur df=new SFournisseur();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadFour();
        refresh();
        txtnb();
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


    private void refresh() {
        
        try {
            fourList.clear();
            
            query = "select * from fournisseurs";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                fourList.add(new  Fournisseur(
                        resultSet.getInt("idf"),
                        resultSet.getString("nomf"),
                        resultSet.getString("prenomf"),
                        resultSet.getString("catf"),
                        resultSet.getInt("telf"),
                        resultSet.getString("addf")
                        )); 
               tablestock.setItems(fourList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }


    @FXML
    private void Ajouter(MouseEvent event) {
        
        String nomf =nomfounisseur.getText();
        String prenomf =prenom.getText();
        String addf =email.getText();
        String catf =categorie.getText();
        int telf=Integer.valueOf(telephone.getText());
        SControl sc=new SControl();
        Fournisseur f=new Fournisseur(nomf, prenomf, catf, telf, addf);
        if(nomf.isEmpty()||prenomf.isEmpty()||addf.isEmpty()||catf.isEmpty()){
            Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("verifier les champs");
            alert.showAndWait();
        }
        else{
        SFournisseur sf=new SFournisseur();
        sf.ajouter(f);
            refresh();
        Clear();
        //tablestock.setItems(StockData);
        }
    }

    @FXML
    private void Clear() {
        
        nomfounisseur.setText(null);
        prenom.setText(null);
        telephone.setText(null);
        categorie.setText(null);
        email.setText(null);
    }

    @FXML
    private void GoHome(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    private void loadFour() {
        //To change body of generated methods, choose Tools | Templates.
        SFournisseur sf=new SFournisseur();
    connection= MaCon.getInstance().getCnx();
    //refresh();
    idCol.setCellValueFactory(new PropertyValueFactory<>("idf"));
    nomCol.setCellValueFactory(new PropertyValueFactory<>("nomf"));
    prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenomf"));
    catCol.setCellValueFactory(new PropertyValueFactory<>("catf"));
    telCol.setCellValueFactory(new PropertyValueFactory<>("telf"));
    mailCol.setCellValueFactory(new PropertyValueFactory<>("addf"));
    
    }

    @FXML
    private void supprimer(MouseEvent event) {
        if (!tablestock.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer le produit " + tablestock.getSelectionModel().getSelectedItem().getNomf()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
     SFournisseur cp = new SFournisseur();
    cp.delete(tablestock.getSelectionModel().getSelectedItem());
     Notifications notificationBuild = Notifications.create()
                                      .title("Traitement Stock")
                                      .text("le fournisseur a été supprimé avec succes")
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

   

    SFournisseur sf=new SFournisseur();
    int cc=sf.getnbrefr();
    @FXML
    private void txtnb(){
    
        SFournisseur sf=new SFournisseur();
        this.txtnb.setText(String.valueOf(cc));
        
        
    }
    @FXML
    private void rechercher(KeyEvent event) {
        SFournisseur bs=new SFournisseur(); 
            Fournisseur b= new Fournisseur();
        ObservableList<Fournisseur>filter= bs.chercherav(find.getText());
        populateTable(filter);
    }
        private void populateTable(ObservableList<Fournisseur> branlist){
       tablestock.setItems(branlist);
   }

  
    @FXML
    private void handleMouseAction(MouseEvent event) {
         
    Fournisseur per = tablestock.getSelectionModel().getSelectedItem();
    nomfounisseur.setText(String.valueOf(per.getNomf()));
    prenom.setText(String.valueOf(per.getPrenomf()));
    categorie.setText(String.valueOf(per.getCatf()));
    telephone.setText(String.valueOf(per.getTelf()));
    email.setText(String.valueOf(per.getAddf()));
        
    }


    @FXML
    private void trier(MouseEvent event) {
        
         SFournisseur ps=new SFournisseur();
        Fournisseur b= new Fournisseur();
        ObservableList<Fournisseur>filter=  ps.Trif();
        populateTable(filter);
        
    }

    @FXML
    private void fav(ActionEvent event) {
        try{
            FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/Gui/fourFav.fxml"));
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
    private void modifierfournisseur(ActionEvent event) {
        Fournisseur f1=new Fournisseur();
   SFournisseur sf=new SFournisseur();
   f1=tablestock.getSelectionModel().getSelectedItem();
   f1.setNomf(nomfounisseur.getText());
   f1.setPrenomf(prenom.getText());
   int tel=Integer.valueOf(telephone.getText());
   f1.setTelf(tel);
   f1.setCatf(categorie.getText());
   f1.setAddf(email.getText());
  sf.update(f1);
   loadFour(); 
   refresh();
    }

    @FXML
    private void update(MouseEvent event) {
    }

    @FXML
    private void refreshh(MouseEvent event) {
           try {
            fourList.clear();
            
            query = "select * from fournisseurs";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                fourList.add(new  Fournisseur(
                        resultSet.getInt("idf"),
                        resultSet.getString("nomf"),
                        resultSet.getString("prenomf"),
                        resultSet.getString("catf"),
                        resultSet.getInt("telf"),
                        resultSet.getString("addf")
                        )); 
               tablestock.setItems(fourList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
    

