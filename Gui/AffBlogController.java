/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Tools.MaConnexion;
import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import Entities.Blog;
import Entities.avis;
import Services.AvisService;
import Services.BadWords;
import Services.Blogservice;
import Services.Pdf23;
import java.awt.Desktop;
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
import javafx.scene.control.Label;
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

/**
 * FXML Controller class
 *
 * @author rezki
 */
public class AffBlogController implements Initializable {
 String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Blog Blog =null;
    BadWords badd =new BadWords();
       @FXML
    private TextField titrebl;

    

    private TextField immg;

    @FXML
    private TableView<Blog> blogliiist;

    @FXML
    private TableColumn<Blog, Integer> idblog;

    @FXML
    private TableColumn<Blog, String> titre;

    @FXML
    private JFXTextArea desc;

    private TableColumn<Blog, String> imag;

    @FXML
    private FontAwesomeIconView close;

    

   
Blogservice sc=new Blogservice();
    @FXML
    private TableColumn<Blog, String> decccss;
    @FXML
    private TableColumn<Blog, String> imaggeeee;
   ObservableList<Blog> blogList =FXCollections.observableArrayList();
    @FXML
    private TextField Recherche;
    @FXML
    private Button modifierrrrrrr;
    @FXML
    private TextField imgpathh;
    @FXML
    private Button iconn_importtt;
    @FXML
    private ImageView imgvieww;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       //refresh();
        loadStock();
    }    
    private void Clear() {
       idblog.setText(null);
       titrebl.setText(null);
       desc.setText(null);
        
    }
    

 private void loadStock() {
         //Blogservice bb= new Blogservice();
        connection= MaConnexion.getInstance().getCnx();
   refresh();
    idblog.setCellValueFactory(new PropertyValueFactory<>("idblog"));
    titre.setCellValueFactory(new PropertyValueFactory<>("titreblog"));
    decccss.setCellValueFactory(new PropertyValueFactory<>("descblog"));
    imaggeeee.setCellValueFactory(new PropertyValueFactory<>("image"));
     }
 
 
 @FXML
    private void supp(MouseEvent event) { 
        if (!blogliiist.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer le blog " + blogliiist.getSelectionModel().getSelectedItem().getTitreblog()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
      Blogservice bb  = new  Blogservice ();
    bb.supprimer(blogliiist.getSelectionModel().getSelectedItem());
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
    refresh();
}
 
        }
         else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("il faut séléctionner une ligne");
            alert.showAndWait();}
        
 
        
    
    }

//    @FXML
//    private void close(MouseEvent event) {
//            Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Confirmation") ;
//                alert.setHeaderText("Voulez vous fermer la fenêtre?") ;
//                Optional <ButtonType> result=alert.showAndWait();
//                if (result.get()==ButtonType.OK){
//        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//        stage.close();}
//    }

    
    
    @FXML
    void ajouter(MouseEvent event) throws SQLException {
        String titreblog =titrebl.getText();
        String descblog=desc.getText();
       // String image =  immg.getText();
        Blog b=new Blog(titreblog,descblog,imgpathh.getText());
         Blogservice bb= new Blogservice();
        if(!bb.Controlechar(b)||!(bb.existe(b)== 0))
        {  Alert alert =new Alert(Alert.AlertType.WARNING);
            
            alert.setContentText("verifier les champs");
            alert.showAndWait();
        }
        else{
      
       
        bb.ajouter(b);
        
    
        
    }
    refresh();
      //  refresh();
        Clear();
    }
     @FXML
    void close(MouseEvent event) {

    }

   

    
   
    private void refresh() {
        try {
            blogList.clear();
            
            query = "select * from blog";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                blogList.add(new  Blog(
                        resultSet.getInt("idblog"),
                        resultSet.getString("titreblog"),
                        resultSet.getString("descblog"),
                        resultSet.getString("image")
                        )); 
               blogliiist.setItems(blogList);
               
               
    
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
  
   
           try {
    Blog b = blogliiist.getSelectionModel().getSelectedItem();
    titrebl.setText(String.valueOf(b.getTitreblog()));
    desc.setText(String.valueOf(b.getDescblog()));
    String path = b.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
              imgvieww.setImage(img);
   // immg.setText(String.valueOf(b.getImage()));
    
   
           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
       
        
    }

    

    
    private void populateTable(ObservableList<Blog> branlist){
       blogliiist.setItems(branlist);
   }

    @FXML
    private void modifier(MouseEvent event) {
    }
    
    

    @FXML
    private void trier(ActionEvent event) {
        Blogservice ps=new Blogservice();
        Blog b= new Blog();
        ObservableList<Blog>filter=  ps.TriS();
        populateTable(filter);}

    @FXML
    private void rechercher(KeyEvent event) {
          Blogservice bs = new Blogservice();
         Blog b=new Blog();
        ObservableList<Blog>filter= bs.chercherTitreBlog(Recherche.getText());
        populateTable(filter);
    }

    @FXML
    private void home(MouseEvent event) throws IOException {  Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void update(ActionEvent event) {
        Blog b=new Blog();
   Blogservice bs = new Blogservice();
   b=blogliiist.getSelectionModel().getSelectedItem();
   b.setTitreblog(titrebl.getText());
   b.setDescblog(desc.getText());
//   b.setImage(immg.getText());
   bs.modifier(b);
   loadStock(); 
  refresh();
    }

    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException {
        
        Blog tab_Recselected = blogliiist.getSelectionModel().getSelectedItem();

       String Var_prix = String.valueOf(tab_Recselected.getTitreblog());
       String Var_dateajout=String.valueOf(tab_Recselected.getDescblog());
       String Var_datefin=String.valueOf(tab_Recselected.getImage());
     
        Pdf23 p = new Pdf23();
    p.add(tab_Recselected.getTitreblog()+".pdf",Var_dateajout,Var_datefin,Var_prix);
    
   try {
            String a;
            a = "C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\tester_crud_1-20220308T220817Z-001\\tester_crud_1";
            System.out.println(a);
        File file = new File(a);
        if (file.exists()){ 
        if(Desktop.isDesktopSupported()){
            Desktop.getDesktop().open(file);
        }}
    }
catch(Exception e){System.out.println("");}
    }

    @FXML
    private void uploaddd(ActionEvent event) throws FileNotFoundException, IOException {
        
        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
      //  String DBPath = "C:\\\\xampp\\\\htdocs\\\\YummyGout\\\\Cat\\\\"+ "Cat"  + x + ".jpg";
       String DBPath = "C:\\\\wamp64\\\\www\\\\Yummygoutt\\\\blog"+ "blog"  + x + ".jpg";
        if (file != null) {
            
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            imgvieww.setImage(img);
           /* File File1 = new File(DBPath);
            Image img = new Image(File1.getAbsolutePath());
            image_event.setImage(img);*/
           imgpathh.setText(DBPath);
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
}
    

