/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.Platservice;
import Services.Scontrol_1;
import Services.categorieservice;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import Entities.categorie;
import Entities.platt;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import Tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author chaim
 */
public class Crud_menuController implements Initializable {

    @FXML
    private TextField descp;
    @FXML
    private TextField nompp;
    @FXML
    private TextField imageeep;
    @FXML
    private TextField prixplattt;
    @FXML
    private TextField quantiteee;
    @FXML
    private TextField stockkk;
    @FXML
    private TableColumn<platt, Integer> collidp;
    @FXML
    private TableColumn<platt, String> colldescrp;
    @FXML
    private TableColumn<platt, String> collnomplatt;
    @FXML
    private TableColumn<platt, String> callimage;
    @FXML
    private TableColumn<platt, Integer> callidcat;
    @FXML
    private TableColumn<platt, Integer> callprixplat;
    @FXML
    private TableColumn<platt, Integer> callq_plat;
    @FXML
    private TableColumn<platt, Integer> callstock;
    @FXML
    private TableView<platt> calltabmenu;
 
 // ObservableList<platt>  List = FXCollections.observableArrayList();
   ObservableList<platt>  platList = FXCollections.observableArrayList();
  platt ss=new platt();
     Statement ste;
    private platt r;
    String query = null;
    Connection connection = null ;
    Connection cnx=MaConnexion.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    @FXML
    private JFXComboBox<String> idddccaatttppp;
    @FXML
    private TextField rechercher;
    @FXML
    private Button cleear;
    @FXML
    private FontAwesomeIconView homegestmenuuuu;
    @FXML
    private Button icon_import;
    @FXML
    private ImageView imageview;
    @FXML
    private Button reff;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      load();
      refplat();
    }    

    @FXML
    private void ajouterplat(MouseEvent event) throws SQLException {
          connection= MaConnexion.getInstance().getCnx();
          String Descplat =descp.getText();
          String Nomplat =nompp.getText();
         // String image =imageeep.getText();
          String idcatt = idddccaatttppp.getValue();
            Platservice rec = new Platservice();
          int idcategore= rec.chercherIdcat(idcatt);
          int prix_plat =Integer.valueOf(prixplattt.getText());
          int q_plat =Integer.valueOf(quantiteee.getText());
          int stock = Integer.valueOf(stockkk.getText());      
          Scontrol_1 sc= new Scontrol_1();
//int Salaire =Integer.valueOf(prixplattt.getText());
  
        
       platt re = new platt(Descplat,Nomplat,imageeep.getText(),idcategore,prix_plat,q_plat,stock);
       
        if( Nomplat.isEmpty()){           
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);            
            alert.setContentText("champs vides");
            alert.showAndWait();
            
         if (! sc.isNumeric(quantiteee.getText())){
          Alert alert1 =new Alert(Alert.AlertType.CONFIRMATION);            
            alert1.setContentText("quantité doit étre un nombre");
            alert1.showAndWait();   
        } else if (!(sc.Controlechar1(re))){
             Alert alert2 =new Alert(Alert.AlertType.CONFIRMATION);            
            alert2.setContentText("le nom doit contenir des caractéres");
            alert2.showAndWait();
        }}
        else {
            rec.ajouter(re);
             refplat();
             Alert alert =new Alert(Alert.AlertType.CONFIRMATION);            
            alert.setContentText("plat ajouter");
            alert.showAndWait();}
        }    
    private void load() {
    Platservice pp=new Platservice();
    connection= MaConnexion.getInstance().getCnx();
    refplat();
    collidp.setCellValueFactory(new PropertyValueFactory<>("Idplat"));
    colldescrp.setCellValueFactory(new PropertyValueFactory<>("Descplat"));
    collnomplatt.setCellValueFactory(new PropertyValueFactory<>("Nomplat"));
    callimage.setCellValueFactory(new PropertyValueFactory<>("image"));
    callidcat.setCellValueFactory(new PropertyValueFactory<>("Nomcat"));
    callprixplat.setCellValueFactory(new PropertyValueFactory<>("prix_plat"));
    callq_plat.setCellValueFactory(new PropertyValueFactory<>("q_plat"));
    callstock.setCellValueFactory(new PropertyValueFactory<>("stock"));
    idddccaatttppp.setItems(FXCollections.observableArrayList(pp.getAll()));
    }   
    
   private void refplat() {     
        try {
            platList.clear();
            
            query = "select platt.Idplat ,platt.Descplat ,platt.Nomplat ,platt.image, categorie.Nomcat, platt.prix_plat ,platt.Q_plat, platt.stock from platt INNER JOIN categorie where platt.idcatt=categorie.idcatt";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                platList.add(new  platt(
                        resultSet.getInt("Idplat"),
                        resultSet.getString("Descplat"),
                        resultSet.getString("Nomplat"),
                        resultSet.getString("image"),
                        resultSet.getString("Nomcat"),
                        resultSet.getInt("prix_plat"),
                        resultSet.getInt("q_plat"),
                        resultSet.getInt("stock")
                        
                        )); 
               calltabmenu.setItems(platList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
            
   
    @FXML
    private void modifierplat(MouseEvent event) {
    platt cat=new platt();
   Platservice pl = new Platservice();
   cat=calltabmenu.getSelectionModel().getSelectedItem();
   cat.setDescplat(descp.getText());
   cat.setNomplat(nompp.getText());
   cat.setImage(imageeep.getText());
  
   int prix_plat=Integer.valueOf(prixplattt.getText());
   cat.setPrix_plat(prix_plat);
 
    int q_plat=Integer.valueOf(quantiteee.getText());
    cat.setQ_plat(q_plat);  
    int stock=Integer.valueOf(stockkk.getText());
   cat.setStock(stock);  
   pl.modifier(cat);
   load(); 
refplat();      
        
    }

    @FXML
    private void supprimerpalt(MouseEvent event) {
        
         if (!calltabmenu.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer plat " + calltabmenu.getSelectionModel().getSelectedItem().getNomplat()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    Platservice r=new Platservice();
    r.supprimer(calltabmenu.getSelectionModel().getSelectedItem());
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
    refplat();
}
 
        }
         else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("il faut séléctionner une ligne");
            alert.showAndWait();}
    }

    @FXML
    private void onclikedd(MouseEvent event) {
    
    try {
     platt cattt = calltabmenu.getSelectionModel().getSelectedItem();
   
    
    descp.setText(String.valueOf(cattt.getDescplat()));
    nompp.setText(String.valueOf(cattt.getNomplat()));
   // imageeep.setText(String.valueOf(cattt.getImage()));
    prixplattt.setText(String.valueOf(cattt.getPrix_plat()));
    quantiteee.setText(String.valueOf(cattt.getQ_plat()));
    stockkk.setText(String.valueOf(cattt.getStock()));
        String path = cattt.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imageview.setImage(img);
    
        } catch (Exception e) {
               System.out.println(e.getMessage());
           }
    }

    @FXML
    private void rechercher(KeyEvent event) {
      //  public ObservableList<platt> chercherTitreplat(String chaine)
      
        Platservice bs=new Platservice(); 
        platt b= new platt();
        ObservableList<platt>filter= bs.chercherTitreplat(rechercher.getText());
        populateTable(filter);
    } 
   
 private void populateTable(ObservableList<platt> branlist){
       calltabmenu.setItems(branlist);
   
       }

    @FXML
    private void cleear(MouseEvent event) {
        descp.setText(null);
        nompp.setText(null);
        imageeep.setText(null);
        prixplattt.setText(null);
        quantiteee.setText(null);
        stockkk.setText(null);
    }

    @FXML
    private void homegestioonmenuuu(MouseEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
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
        String DBPath = "C:\\\\wamp64\\\\www\\\\Yummygoutt\\\\plat\\\\"+ "plat"  + x + ".jpg";
        
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            imageview.setImage(img);
           /* File File1 = new File(DBPath);
            Image img = new Image(File1.getAbsolutePath());
            image_event.setImage(img);*/
            imageeep.setText(DBPath);
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
    private void accttuualiserrr(ActionEvent event) {
        refplat();
    }

}

