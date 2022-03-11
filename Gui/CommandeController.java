/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.print.PrinterException;
import java.awt.print.PrinterException;
import Services.Panierservice;
import Services.Pdf2;
import Services.Platservice;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
//import dejensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import Entities.panier;
import Entities.platt;
import Services.Pdf22;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import Tools.MaConnexion;
import com.itextpdf.text.pdf.PdfWriter;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import Tools.mailingchaima;
/**
 * FXML Controller class
 *
 * @author chaim
 */
public class CommandeController implements Initializable {

    @FXML
    private TableView<panier> tablepannier;
    @FXML
    private TableColumn<?, ?> callidpan;
    @FXML
    private TableColumn<?, ?> callnomplt;
    @FXML
    private TableColumn<?, ?> callq;
    @FXML
    private TableColumn<?, ?> callprixxpp;
    @FXML
    private JFXComboBox<Integer> quantiteeee;
    @FXML
    private JFXComboBox<Integer> idplattttttt;
    @FXML
    private Label txttotal;
  ObservableList<panier>  pannierList = FXCollections.observableArrayList();
  panier ss=new panier();
     Statement ste;
     panier r;
    String query = null;
    Connection connection = null ;
    Connection cnx=MaConnexion.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    @FXML
    private TableColumn<?, ?> callfacture;
    @FXML
    private TextField txtnumfact;
    @FXML
    private TextField idpan;
    @FXML
    private Button ajouterrrr;
    @FXML
    private Button clear;
    @FXML
    private Button modifierrr;
    @FXML
    private Button supp;
    @FXML
    private FontAwesomeIconView homeeggcc;
    @FXML
    private Button calc;
    @FXML
    private TextArea textttt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          ObservableList<Integer> list = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
        quantiteeee.setItems(list);
        load();
         refpannier();
        try {
            getsomme();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouter(MouseEvent event) throws MessagingException, AddressException, IOException {
            connection= MaConnexion.getInstance().getCnx();
       // int Idpanier =Integer.valueOf(idpan.getText());
          int Idplat = idplattttttt.getValue();
        int quantite = quantiteeee.getValue();
         String numfacture = txtnumfact.getText();
        
        panier p = new panier(Idplat,quantite,numfacture);
       
        
//               Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
//            
//            alert.setContentText("commande ajouter");
//            alert.showAndWait();
            
            Panierservice ppp=new Panierservice();
            ppp.ajouter(p);
             refpannier();
             mailingchaima mail=new mailingchaima();
        mail.sendMail();
        
        
       
             Image img = new Image("Images/tick.png");
        Notifications notificationBuilder = Notifications.create()
                .title("notif commande")
                .text("commande validée")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked on notification");
            }
        });
       notificationBuilder.darkStyle();
       
         notificationBuilder.show();

        
    }
private void load() {
    Panierservice p=new Panierservice();
    connection= MaConnexion.getInstance().getCnx();
   refpannier();
    callidpan.setCellValueFactory(new PropertyValueFactory<>("Idpanier"));
    callnomplt.setCellValueFactory(new PropertyValueFactory<>("Nomplat"));
    callq.setCellValueFactory(new PropertyValueFactory<>("quantite"));
    callprixxpp.setCellValueFactory(new PropertyValueFactory<>("prix_plat"));
     callfacture.setCellValueFactory(new PropertyValueFactory<>("numfacture"));
     
    idplattttttt.setItems(FXCollections.observableArrayList(p.getAll()));
     
    }

private void refpannier() {     
        try {
            pannierList.clear();
            
            query = "SELECT panier.Idpanier, panier.quantite, platt.Nomplat , platt.prix_plat, panier.numfacture FROM panier INNER JOIN platt where platt.Idplat=panier.Idplat";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                pannierList.add(new  panier(
                        resultSet.getInt("Idpanier"),
                         resultSet.getInt("quantite"),
                        resultSet.getString("Nomplat"),
                         resultSet.getInt("prix_plat"),
                        resultSet.getString("numfacture")
                        
                        )); 
               tablepannier.setItems(pannierList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void cearr(MouseEvent event) {
        
         txtnumfact.setText(null);
         quantiteeee.setValue(null);
         textttt.setText("");
      
    }

    @FXML
    private void modifierr(MouseEvent event) {
          panier pann=new panier();
   Panierservice pl = new Panierservice();
   pann=tablepannier.getSelectionModel().getSelectedItem();

   int quantite = (Integer)quantiteeee.getValue();
   pann.setQuantite(quantite);
//   int Idplat = (Integer)idplattttttt.getValue();
//   pann.setIdplat(Idplat);
   
 
    
      
   pl.modifier(pann);
   load(); 
    refpannier();      
    }

    @FXML
    private void supprimierr(MouseEvent event) {
         
             if (!tablepannier.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer plat " + tablepannier.getSelectionModel().getSelectedItem().getNomplat()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    Panierservice r=new Panierservice();
    r.supprimer(tablepannier.getSelectionModel().getSelectedItem());
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
   refpannier();
}
 
        }
         else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("il faut séléctionner une ligne");
            alert.showAndWait();}
    }

    @FXML
    private void getsomme() throws SQLException {
          Panierservice ps = new Panierservice();
      int nbr=ps.calculer_pannier();
      this.txttotal.setText(String.valueOf(nbr));
      load(); 
    }

    @FXML
    private void onnclikeddd(MouseEvent event) {
          try {
     panier ppa = tablepannier.getSelectionModel().getSelectedItem();
    int c =  ppa.getQuantite();
    quantiteeee.setValue(c);
    idplattttttt.setValue(c);
    //txtnumfact.setValue(c);
        txtnumfact.setText(String.valueOf(ppa.getPrix_plat()));

//        int b =  ppa.getIdplat();
//    idplattttttt.setValue(b);
//    
//    descp.setText(String.valueOf(cattt.getDescplat()));
//    nompp.setText(String.valueOf(cattt.getNomplat()));
//    imageeep.setText(String.valueOf(cattt.getImage()));
//    prixplattt.setText(String.valueOf(cattt.getPrix_plat()));
//    quantiteee.setText(String.valueOf(cattt.getQ_plat()));
//    quantiteee.setText(String.valueOf(cattt.getStock()));
    
        } catch (Exception e) {
               System.out.println(e.getMessage());
           }
    }

    @FXML
    private void homeeegestioncomm(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void calculatrice(MouseEvent event) {
     
        try{
            FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("Calculatrice_1.fxml"));
            Parent root1=(Parent) fxmlloader.load();
            Stage stage= new Stage();
            stage.setTitle("calculatrice");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println("erreur");
        }
    
    }

    @FXML
    private void pdf(MouseEvent event) throws FileNotFoundException, SQLException, DocumentException {
//        
//        
//         panier tab_Recselected = tablepannier.getSelectionModel().getSelectedItem();
//
//       
//       String Var_nomplat=String.valueOf(tab_Recselected.getNomplat());
//       String qt=String.valueOf(tab_Recselected.getNomplat());
//       
//       String Var_prixplat=String.valueOf(tab_Recselected.getPrix_plat());
//       //String Var_id=String.valueOf(tab_Recselected.calculer_pannier());
//       String Var_somme=String.valueOf(tab_Recselected.getSomme());
//          String Var_numero_facture=String.valueOf(tab_Recselected.getNumfacture());
//        Pdf22 p = new Pdf22();
//    p.add(Var_nomplat+".pdf",Var_nomplat,qt,Var_prixplat,Var_somme,Var_numero_facture);
//    
//   try {
//            String a;
//            //a = "C:\\Users\\chaim\\Documents\\NetBeansProjects\\yummyyygoutttttttt";
//            a="C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\tester_crud_1-20220308T220817Z-001\\tester_crud_1";
//            System.out.println(a);
//        File file = new File(a);
//        if (file.exists()){ 
//        if(Desktop.isDesktopSupported()){
//            Desktop.getDesktop().open(file);
//        }}
//    }
//catch(Exception e){System.out.println("");}
//  
//        
    }

    @FXML
    private void addrecord(ActionEvent event) {
        
//          try{
//            FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("capttt.fxml"));
//            Parent root1=(Parent) fxmlloader.load();
//            Stage stage= new Stage();
//            stage.setTitle("facture");
//            stage.setScene(new Scene(root1));
//            stage.show();
//        }catch(Exception e){
//            System.out.println("erreur");
        
          textttt.appendText("\t\t detaile commande \n\n"+
            
            "\n=====================================\n" +
            
            " Idplat :\t\t\t" + idplattttttt.getValue()+
            "quantite :\t\t\t " + quantiteeee.getValue()+ "\n\n" +
            "numfacture :\t\t\t " + txtnumfact.getText()+"\n\n" +
            "somme:\t\t\t" +txttotal.getText()+"\n" +
                  "================================"
                  
                  );
    
    }
    @FXML
    private void cleeearr(ActionEvent event) {
       textttt.setText("");
    //   quantiteeee.set;
       
        
    }

    @FXML
    private void printtttpdff(ActionEvent event) throws FileNotFoundException, BadElementException, IOException{
                Document document =new Document();
        //String path ="C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\tester_crud_1-20220308T220817Z-001\\tester_crud_1";
        //PdfWriter  pdfWriter =new PdfWriter(path);
        Document doc=new Document();
        try{
            PdfWriter pp=PdfWriter.getInstance(doc, new FileOutputStream("Facture.pdf"));
            
            doc.open();
            doc.add(new Paragraph(textttt.getText()));
            
            //document.add(com.itextpdf.text.Image.getInstance("C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\tester_crud_1-20220308T220817Z-001\\tester_crud_1\\src\\Images\\logo.png"));
            document.close();
            doc.close();
            pp.close();
            System.out.println("finished");
        }
        catch(DocumentException e){
            e.printStackTrace();
        }       
    }

    


}
