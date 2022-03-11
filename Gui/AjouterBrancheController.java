/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.BrancheService;
import Services.Scontrol_1;
import Services.Scontrol_amani;
import com.itextpdf.text.Image;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import Entities.Branche;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Random;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import javafx.scene.image.ImageView;
import Tools.MaConnexion;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterBrancheController implements Initializable {

    @FXML
    private TextField nomb;
    @FXML
    private TextField contact;
    @FXML
    private TextField emplacement;
    @FXML
    private ComboBox<String> heureouv;
    @FXML
    private ComboBox<String> minouv;
    @FXML
    private ComboBox<String> heureferm;
    @FXML
    private ComboBox<String> minferm;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton modifier;
    @FXML
    private TextField Recherche;
    @FXML
    private FontAwesomeIconView close;
    @FXML
    private JFXButton vider;
    @FXML
    private TableView<Branche> listebranche;
    @FXML
    private TableColumn<?, ?> idbranche;
    @FXML
    private TableColumn<?, ?> nombranche;
    @FXML
    private TableColumn<?, ?> Houvert;
    @FXML
    private TableColumn<?, ?> Hferm;
    @FXML
    private TableColumn<?, ?> cont;
    @FXML
    private TableColumn<?, ?> emp;
    ObservableList<Branche>listeb = FXCollections.observableArrayList();
    Branche b=new Branche();
    private Statement ste;
    private Branche s;
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Branche branche = null ;
    Connection cnx= MaConnexion.getInstance().getCnx();
    @FXML
    private Button supp;
    @FXML
    private FontAwesomeIconView home;
    @FXML
    private Button actualiser;
    @FXML
    private ImageView tri;
    @FXML
    private JFXButton trierr;
    @FXML
    private TextField imageb;
    @FXML
    private ImageView imagev;
    @FXML
    private Button uploadaaa;
    @FXML
    private TableColumn<?, ?> callimage;
    /**
     * Initializes the controller class.
     */
  
    @FXML
    private void SelectHeureOuverture(ActionEvent event) {
        String heure_ouverture= heureouv.getSelectionModel().getSelectedItem();
    }
 @FXML
    private void SelectMinOuverture(ActionEvent event) {
        String Minute_ouverture= minouv.getSelectionModel().getSelectedItem();
    }


    @FXML
    private void SelectHeureFermeture(ActionEvent event) {
        String heure_fermeture= heureferm.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void SelectMinFermeture(ActionEvent event) {
        String Min_fermeture= minferm.getSelectionModel().getSelectedItem();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listH= FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        heureouv.setItems(listH);
        ObservableList<String> listMin= FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45",
                "46","47","48","49","50","51","52","53","54","55","56","57","58","59");
        minouv.setItems(listMin);
        ObservableList<String> listHF= FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        heureferm.setItems(listHF);
        ObservableList<String> listMinF= FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45",
                "46","47","48","49","50","51","52","53","54","55","56","57","58","59");
        minferm.setItems(listMinF);
        loadbranch();
        // TODO
    }    

    @FXML
    private void AjouterBranche(MouseEvent event) throws SQLException {
        String nombranche= nomb.getText();
        String Contact= contact.getText();
        String Emplacement= emplacement.getText();
        String heureouverture= heureouv.getValue();
        String Minouverture= minouv.getValue();
        String time= heureouverture+":"+Minouverture;
        String heurefermeture= heureferm.getValue();
        String Minfermeture= minferm.getValue();
        String time2= heurefermeture+":"+Minfermeture;
        Branche b= new Branche(nombranche,Contact, Emplacement,time, time2,imageb.getText());
        LocalTime t1 = LocalTime.parse(time);
        LocalTime t2 = LocalTime.parse(time2);
        BrancheService bs=new BrancheService(); 
        Scontrol_amani sc= new Scontrol_amani();
        if (bs.exist(b)==0){
            Alert alert1 =new Alert(Alert.AlertType.WARNING);           
            if(nomb.getText().isEmpty()||heureouv.getSelectionModel().getSelectedItem().equals("")|| minouv.getSelectionModel().getSelectedItem().equals("")|| heureferm.getSelectionModel().getSelectedItem().equals("")||minferm.getSelectionModel().getSelectedItem().equals("")){
             alert1.setContentText("Il y a des champs vides !");
            alert1.showAndWait();
            }
            else if(!sc.isNumeric(Contact)|| Contact.length()!=8){
                alert1.setContentText("Le contact est invalide !");
            alert1.showAndWait();
            }
            else if (!sc.Controlechar(b)){             
            //alert.setHeaderText("null");
            alert1.setContentText("Le nom est invalide");
            alert1.showAndWait();
        }
            else if (t2.isBefore(t1)){
            Alert alert2 =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert2.setContentText("Vérifier les heures d'ouverture et de fermeture");
            alert2.showAndWait();
                    }
            else{ bs.ajouter(b);
        refresh();
        Vider();
        alert1.setContentText("Branche est ajoutée avec succes");
        alert1.showAndWait();            
        }
        }
        else{
            Alert alert1 =new Alert(Alert.AlertType.WARNING);
            alert1.setContentText("Branche existe");
            alert1.showAndWait();}}

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

    @FXML
    private void Vider() {
        nomb.setText(null);
        contact.setText(null);
        emplacement.setText(null);
        heureouv.setValue(null);
        minouv.setValue(null);
        heureferm.setValue(null);
        minferm.setValue(null);
    }
   private void refresh() {
        try {
            listeb.clear();       
            query = "select * from branche;";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listeb.add(new Branche(
                        resultSet.getInt("Idbranche"),
                        resultSet.getString("Nombranche"),
                        resultSet.getString("Contact"),
                        resultSet.getString("Emplacement"),
                        resultSet.getString("Heureo"),
                        resultSet.getString("Heuref"),
                          resultSet.getString("image")
                        )); 
               listebranche.setItems(listeb);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadbranch(){
    BrancheService ss=new BrancheService();
    connection= MaConnexion.getInstance().getCnx();
    refresh();
    idbranche.setCellValueFactory(new PropertyValueFactory<>("Idbranche"));
    nombranche.setCellValueFactory(new PropertyValueFactory<>("NomBranche"));
    cont.setCellValueFactory(new PropertyValueFactory<>("Contact"));
    emp.setCellValueFactory(new PropertyValueFactory<>("Emplacement"));
    Houvert.setCellValueFactory(new PropertyValueFactory<>("Heureo"));
    Hferm.setCellValueFactory(new PropertyValueFactory<>("Heuref")); 
    callimage.setCellValueFactory(new PropertyValueFactory<>("image"));

    }   

//    @FXML
//    private void selectrow() {
//    Branche b=listebranche.getSelectionModel().getSelectedItem();
//    nomb.setText(String.valueOf(b.getNomBranche()));
//    contact.setText(String.valueOf(b.getContact()));
//    emplacement.setText(String.valueOf(b.getEmplacement()));
//    String HH= b.getHeureo().substring(0, 2);
//    String MM=b.getHeureo().substring(3);
//    heureouv.setValue(HH);
//    minouv.setValue(MM);
//    String HHf= b.getHeuref().substring(0, 2);
//    String MMf=b.getHeuref().substring(3);
//    heureferm.setValue(HHf);
//    minferm.setValue(MMf);   
//    }

    @FXML
    private void supprimer(ActionEvent event) {
        if (!listebranche.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer la branche " + listebranche.getSelectionModel().getSelectedItem().getNomBranche()+ " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

    if (alert.getResult() == ButtonType.YES) {
     BrancheService bs = new BrancheService();
    bs.supprimer(listebranche.getSelectionModel().getSelectedItem());
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
//       private void handleMouseAction(MouseEvent event)
//       {
////    Branche b=listebranche.getSelectionModel().getSelectedItem();
////    nomb.setText(String.valueOf(b.getNomBranche()));
////    contact.setText(String.valueOf(b.getContact()));
////    emplacement.setText(String.valueOf(b.getEmplacement()));
////    String HH= b.getHeureo().substring(0, 2);
////    String MM=b.getHeureo().substring(3);
////    heureouv.setValue(HH);
////    minouv.setValue(MM);
////    String HHf= b.getHeuref().substring(0, 2);
////    String MMf=b.getHeuref().substring(3);
////    heureferm.setValue(HHf);
////    minferm.setValue(MMf);  
//////    String path = b.getImage();
//////    File file=new File(path);
//////    javafx.scene.image.Image img = new javafx.scene.image.Image(file.toURI().toString());
//////    imagev.setImage(img);
//       }
    @FXML
   void modifier(ActionEvent event) throws SQLException  {
        Branche br=new Branche();
        BrancheService bs = new BrancheService();
        br=listebranche.getSelectionModel().getSelectedItem();
        br.setNomBranche(nomb.getText());
        br.setContact(contact.getText());
        br.setEmplacement(emplacement.getText());
        br.setHeureo(heureouv.getValue()+":"+minouv.getValue());
        br.setHeuref(heureferm.getValue()+":"+minferm.getValue());
        String heureouverture= heureouv.getValue();
        String Minouverture= minouv.getValue();
        String time= heureouverture+":"+Minouverture;
        String heurefermeture= heureferm.getValue();
        String Minfermeture= minferm.getValue();
        String time2= heurefermeture+":"+Minfermeture;
        LocalTime t1 = LocalTime.parse(time);
        LocalTime t2 = LocalTime.parse(time2);
        Scontrol_amani sc= new Scontrol_amani();       
         if (bs.exist(br)==0){
            Alert alert1 =new Alert(Alert.AlertType.WARNING);           
            if(nomb.getText().isEmpty()||heureouv.getSelectionModel().getSelectedItem().equals("")|| minouv.getSelectionModel().getSelectedItem().equals("")|| heureferm.getSelectionModel().getSelectedItem().equals("")||minferm.getSelectionModel().getSelectedItem().equals("")){
             alert1.setContentText("Il y a des champs vides !");
            alert1.showAndWait();
            }
            else if(!sc.isNumeric(contact.getText())|| contact.getText().length()!=8){
                alert1.setContentText("Le contact est invalide !");
            alert1.showAndWait();
            }
            else if (!sc.Controlechar(br)){             
            //alert.setHeaderText("null");
            alert1.setContentText("Le nom est invalide");
            alert1.showAndWait();
        }
            else if (t2.isBefore(t1)){
            Alert alert2 =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert2.setContentText("Vérifier les heures d'ouverture et de fermeture");
            alert2.showAndWait();
                    }
            else{ 
                bs.modifier(br);   
        loadbranch();
         refresh();
         Vider();               
        alert1.setContentText("Branche est modifiée avec succes");
        alert1.showAndWait();            
        }
        }
        else{
            Alert alert1 =new Alert(Alert.AlertType.WARNING);
            alert1.setContentText("Branche existe");
            alert1.showAndWait();}
   }
   private void populateTable(ObservableList<Branche> branlist){
       listebranche.setItems(branlist);
   }
    @FXML
    private void rechercher(ActionEvent event) {       
        BrancheService bs=new BrancheService(); 
        Branche b= new Branche();
        ObservableList<Branche>filter= bs.chercherav(Recherche.getText());
        populateTable(filter);
    }

    @FXML
    private void gotohome(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void actualiserrr(ActionEvent event) {
        refresh();
    }

    @FXML
    private void trierparheure(ActionEvent event) {
        BrancheService ps=new BrancheService();
        Branche b= new Branche();
        ObservableList<Branche>filter= ps.TriLivraison();
        populateTable(filter);
    }

    @FXML
    private void upload(ActionEvent event) throws FileNotFoundException, IOException {
            Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\\\wamp64\\\\www\\\\Yummygoutt\\\\branche\\\\"+ "branche"  + x + ".jpg";
    
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            javafx.scene.image.Image img = new javafx.scene.image.Image(file.toURI().toString());
            imagev.setImage(img);
           /* File File1 = new File(DBPath);
            Image img = new Image(File1.getAbsolutePath());
            image_event.setImage(img);*/
            imageb.setText(DBPath);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
            
        } else {
            System.out.println("error");

        }
    }

    @FXML
    private void handleMouseActionnnnn(MouseEvent event) {
        
         Branche b=listebranche.getSelectionModel().getSelectedItem();
    nomb.setText(String.valueOf(b.getNomBranche()));
    contact.setText(String.valueOf(b.getContact()));
    emplacement.setText(String.valueOf(b.getEmplacement()));
    String HH= b.getHeureo().substring(0, 2);
    String MM=b.getHeureo().substring(3);
    heureouv.setValue(HH);
    minouv.setValue(MM);
    String HHf= b.getHeuref().substring(0, 2);
    String MMf=b.getHeuref().substring(3);
    heureferm.setValue(HHf);
    minferm.setValue(MMf);  
    String path = b.getImage();
    File file=new File(path);
    javafx.scene.image.Image img = new javafx.scene.image.Image(file.toURI().toString());
    imagev.setImage(img);
    }

    }

   



   
    

