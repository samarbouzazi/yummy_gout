/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.Scontrol_1;
import Services.categorieservice;
import com.mysql.jdbc.MySQLConnection;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import Entities.categorie;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Tools.MaConnexion;
import static Tools.MaConnexion.getInstance;
import javafx.geometry.Rectangle2D;

//import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author chaim
 */
public class Crud_categorieController implements Initializable {

    @FXML
    private TextField nomcat;
    
    private TextField imagecat;

    
    @FXML
    private Button ajoutre;
    @FXML
    private Button supp;
    
    Connection connection=null;
   
    ObservableList<categorie>  catList = FXCollections.observableArrayList();
    
    String query = null;
  
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    categorie categorie=null;
     Connection cnx= MaConnexion.getInstance().getCnx();
    int index = -1;
   private categorie c;
   int getidcatt;
    public static int idcatt ;
    Scontrol_1 sc=new Scontrol_1();
    private Button imagee;
    @FXML
    private Button clear;
    @FXML
    private TableColumn<categorie, Integer> idddca;
    @FXML
    private TableColumn<categorie, String> nomcatt;
    @FXML
    private TableColumn<categorie, String> immaagg;
    @FXML
    private TableView<categorie> tabbbcattt;
   
    @FXML
    private ImageView image_event;
    @FXML
    private Button icon_import;
    @FXML
    private Label label_image;
    @FXML
    private FontAwesomeIconView homeecategorieeeee;
    @FXML
    private Button mmmmodiff;
  
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    //idddcatt.setEditable(false);
loadStock(); 
actualiser();
     
        categorieservice p=new categorieservice();
         Connection cnx =MaConnexion.getInstance().getCnx();
         List <categorie> l=p.afficher();
         
        // data=FXCollections.observableArrayList(l);
       
      
    }    

    @FXML
    private void ajouter(MouseEvent event) {
        
         String Nomcat =nomcat.getText();
         String Image =immaagg.getText();
        categorie c=new categorie(Nomcat, Image);
        if( !sc.Controlechar(c)||Nomcat.isEmpty()){
            Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("verifier les champs");
            alert.showAndWait();
            }
        else{
        
        categorieservice cc= new categorieservice();
        cc.ajouter(c);
        actualiser();
        
        //refresh();
       // Clear();
        //tablestock.setItems(StockData);
        }
    }
    
       categorie ss=new categorie();
       categorieservice cc= new categorieservice();
       
//      @FXML
//       void getSelected (MouseEvent event){
//             index = tabbbcattt.getSelectionModel().getSelectedIndex();
//             if(index <= -1){
//                 return;
//                 
//             }
//          
//           nomcat.setText(nomcatt.getCellData(index).toString());
//           imagecat.setText(immaagg.getCellData(index).toString());
//       }
      
       //  Connection = Maconnexion.getInstance().getCnx();
    
       @FXML
       void handleMouseAction(MouseEvent event)
       {
           try {
               categorie cat = tabbbcattt.getSelectionModel().getSelectedItem();
               nomcat.setText(cat.getNomcat());
              
               imagecat.setText(cat.getImage());
           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
       }
       
   private void loadStock() {
       
       connection= MaConnexion.getInstance().getCnx();
       actualiser();
       idddca.setCellValueFactory(new PropertyValueFactory<>("idcatt"));
       nomcatt.setCellValueFactory(new PropertyValueFactory<>("Nomcat"));
       immaagg.setCellValueFactory(new PropertyValueFactory<>("Image"));
   
     
   
   
    }
   

//    void modifier(ActionEvent event)  {
////   categorie cat=new categorie();
////   categorieservice cs = new categorieservice();
////   cat=tabbbcattt.getSelectionModel().getSelectedItem();
////   cat.setNomcat(nomcat.getText());
////   cat.setImage(imagecat.getText());
////   cs.modifier(cat);
////   loadStock(); 
////actualiser();    
//       
//  }
    

    
    @FXML
    private void supprimer(MouseEvent event) {
         if (!tabbbcattt.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer une categorie " + tabbbcattt.getSelectionModel().getSelectedItem().getNomcat()+ " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    categorieservice cc = new categorieservice();
    cc.supprimer(tabbbcattt.getSelectionModel().getSelectedItem());
    actualiser();
}
  }
         else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("il faut séléctionner une ligne");
            alert.showAndWait();}
        
 
        
    }
    
 

    
    private void upload(ActionEvent event) {
        
 Stage primary = new Stage();
 FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Selectionner une image");
fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
File file = fileChooser.showOpenDialog(primary);
String path = "src\\image";
imagee.setText(file.getPath());
String m = file.getPath();

if (file != null) {
try{
Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());

    System.out.println(m);
}catch (Exception e) {
e.printStackTrace();
}
}
    }
    
     private void actualiser() {
        try {
            catList.clear();
            query = "select * from categorie";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
              catList.add(new  categorie(
                        resultSet.getInt("idcatt"),
                        resultSet.getString("Nomcat"),
                        resultSet.getString("Image")
              )); 
               tabbbcattt.setItems( catList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        
        
      
    }

    @FXML
    private void clearr(MouseEvent event) {
   
        nomcat.setText(null);
        imagecat.setText(null);
        //image_event.setViewport(Rectangle2D.EMPTY);
      
    }

    @FXML
    private void clear(ActionEvent event) {
    }
//
//    private void modifier(MouseEvent event) {
//         categorie cat=new categorie();
//   categorieservice cs = new categorieservice();
//   cat=tabbbcattt.getSelectionModel().getSelectedItem();
//   cat.setNomcat(nomcat.getText());
//   cat.setImage(imagecat.getText());
//   cs.modifier(cat);
//   loadStock(); 
//actualiser();    
//    }
    
    
    private void populateTable(ObservableList<categorie> list){
       tabbbcattt.setItems(list);
   }
    @FXML
    private void triee(MouseEvent event) {
       
       categorieservice ps=new categorieservice();
        categorie b= new categorie();
        ObservableList<categorie>filter=  ps.Tric();
        populateTable(filter);
    }

    @FXML
    private void importimage(MouseEvent event) {
        
                
        FileChooser fo =new FileChooser();
        fo.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
    File f=fo.showOpenDialog(null);
    if( f != null)
    {
    label_image.setText(f.getAbsolutePath());
       Image image=new Image(f.toURI().toString(),image_event.getFitHeight(),image_event.getFitWidth(),true,true);
       image_event.setImage(image);
    }
    }

    @FXML
    private void homeegestioonncategorie(MouseEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void moodifierrrrcattt(MouseEvent event) {
             categorie cat=new categorie();
   categorieservice cs = new categorieservice();
   cat=tabbbcattt.getSelectionModel().getSelectedItem();
   cat.setNomcat(nomcat.getText());
   //cat.setImage(imagecat.getText());
   cs.modifier(cat);
   loadStock(); 
actualiser();  
    }

   
    
    
}

   

 