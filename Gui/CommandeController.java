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
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
    @FXML
    private Button exell;
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
            sms(txtnumfact.getText());
       
             refpannier();
             
        sendMail();
        
        
       
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
        
          textttt.appendText("\t\t  \n\n"+
            
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

      public void sendMail() throws AddressException, MessagingException, IOException{
    Properties property = new Properties();
    
   property.put("mail.smtp.auth",true);
   property.put("mail.smtp.starttls.enable",true);
   property.put("mail.smtpsmtp.EnableSsl",true);
   property.put("mail.smtp.host","smtp.gmail.com");
   property.put("mail.smtp.port","25");
   property.put("mail.transport.protocl","smtp");
   
     Session session = Session.getInstance(property, new Authenticator() {
            
        @Override
        protected PasswordAuthentication getPasswordAuthentication (){
        
        return new PasswordAuthentication("yummygout@gmail.com","yummy*1999");
        }

        });
     // craeation of an empty object hen you fill the content with whatever you want 
        Message message = new MimeMessage(session);
          message.setSubject("Verifying commande");
          
          Address addressTo = new InternetAddress("benslimene.chaima@esprit.tn");
          message.setRecipient(Message.RecipientType.TO,addressTo);
          
          MimeMultipart emailContent = new MimeMultipart();
          
          //attachment.attachFile(new File("C:\\Users\\tchet\\Documents\\NetBeansProjects\\FirstCrud\\src\\Files\\pdf.pdf"));

            
          //First The HTML Part 
          MimeBodyPart messageBodyPart = new MimeBodyPart();
          String htmlText = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
"<head>\n" +
"<!--[if gte mso 9]>\n" +
"<xml>\n" +
"  <o:OfficeDocumentSettings>\n" +
"    <o:AllowPNG/>\n" +
"    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
"  </o:OfficeDocumentSettings>\n" +
"</xml>\n" +
"<![endif]-->\n" +
"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"  <meta name=\"x-apple-disable-message-reformatting\">\n" +
"  <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n" +
"  <title></title>\n" +
"  \n" +
"    <style type=\"text/css\">\n" +
"      table, td { color: #000000; } a { color: #0000ee; text-decoration: underline; } @media (max-width: 480px) { #u_content_image_1 .v-container-padding-padding { padding: 25px 10px 10px !important; } #u_content_image_1 .v-src-width { width: auto !important; } #u_content_image_1 .v-src-max-width { max-width: 32% !important; } #u_content_image_1 .v-text-align { text-align: center !important; } #u_content_heading_2 .v-container-padding-padding { padding: 50px 40px 30px !important; } #u_content_heading_2 .v-font-size { font-size: 58px !important; } #u_content_image_2 .v-container-padding-padding { padding: 10px 10px 30px !important; } #u_content_image_2 .v-src-width { width: 100% !important; } #u_content_image_2 .v-src-max-width { max-width: 100% !important; } #u_content_heading_1 .v-font-size { font-size: 29px !important; } #u_content_heading_1 .v-line-height { line-height: 150% !important; } #u_content_text_1 .v-container-padding-padding { padding: 40px 15px 70px 20px !important; } #u_content_heading_3 .v-container-padding-padding { padding: 50px 15px 20px !important; } #u_content_heading_3 .v-font-size { font-size: 16px !important; } }\n" +
"@media only screen and (min-width: 620px) {\n" +
"  .u-row {\n" +
"    width: 600px !important;\n" +
"  }\n" +
"  .u-row .u-col {\n" +
"    vertical-align: top;\n" +
"  }\n" +
"\n" +
"  .u-row .u-col-100 {\n" +
"    width: 600px !important;\n" +
"  }\n" +
"\n" +
"}\n" +
"\n" +
"@media (max-width: 620px) {\n" +
"  .u-row-container {\n" +
"    max-width: 100% !important;\n" +
"    padding-left: 0px !important;\n" +
"    padding-right: 0px !important;\n" +
"  }\n" +
"  .u-row .u-col {\n" +
"    min-width: 320px !important;\n" +
"    max-width: 100% !important;\n" +
"    display: block !important;\n" +
"  }\n" +
"  .u-row {\n" +
"    width: calc(100% - 40px) !important;\n" +
"  }\n" +
"  .u-col {\n" +
"    width: 100% !important;\n" +
"  }\n" +
"  .u-col > div {\n" +
"    margin: 0 auto;\n" +
"  }\n" +
"}\n" +
"body {\n" +
"  margin: 0;\n" +
"  padding: 0;\n" +
"}\n" +
"\n" +
"table,\n" +
"tr,\n" +
"td {\n" +
"  vertical-align: top;\n" +
"  border-collapse: collapse;\n" +
"}\n" +
"\n" +
"p {\n" +
"  margin: 0;\n" +
"}\n" +
"\n" +
".ie-container table,\n" +
".mso-container table {\n" +
"  table-layout: fixed;\n" +
"}\n" +
"\n" +
"* {\n" +
"  line-height: inherit;\n" +
"}\n" +
"\n" +
"a[x-apple-data-detectors='true'] {\n" +
"  color: inherit !important;\n" +
"  text-decoration: none !important;\n" +
"}\n" +
"\n" +
"</style>\n" +
"  \n" +
"  \n" +
"\n" +
"<!--[if !mso]><!--><link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\n" +
"\n" +
"</head>\n" +
"\n" +
"<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #ffffff;color: #000000\">\n" +
"  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
"  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
"  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #ffffff;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"  <tbody>\n" +
"  <tr style=\"vertical-align: top\">\n" +
"    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
"    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #ffffff;\"><![endif]-->\n" +
"    \n" +
"\n" +
"<div class=\"u-row-container\" style=\"padding: 0px;background-color: #a2c1d6\">\n" +
"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #bfedd2;\">\n" +
"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: #a2c1d6;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #bfedd2;\"><![endif]-->\n" +
"      \n" +
"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"  <div style=\"width: 100% !important;\">\n" +
"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n" +
"  \n" +
"<table id=\"u_content_image_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:22px 10px 20px 50px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
"  <tr>\n" +
"    <td class=\"v-text-align\" style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
"      <a href=\"https://unlayer.com\" target=\"_blank\">\n" +
"      <img align=\"center\" border=\"0\" src=\"images/image-3.png\" alt=\"Logo\" title=\"Logo\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 20%;max-width: 108px;\" width=\"108\" class=\"v-src-width v-src-max-width\"/>\n" +
"      </a>\n" +
"    </td>\n" +
"  </tr>\n" +
"</table>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"    </div>\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"\n" +
"\n" +
"<div class=\"u-row-container\" style=\"padding: 0px;background-color: #a2c1d6\">\n" +
"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: #a2c1d6;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
"      \n" +
"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
"  \n" +
"<table id=\"u_content_heading_2\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:50px 40px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <h1 class=\"v-text-align v-line-height v-font-size\" style=\"margin: 0px; color: #2f3448; line-height: 120%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: 'Montserrat',sans-serif; font-size: 72px;\">\n" +
"    <strong>Hello!</strong>\n" +
"  </h1>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"<table id=\"u_content_image_2\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
"  <tr>\n" +
"    <td class=\"v-text-align\" style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
"      \n" +
"      <img align=\"center\" border=\"0\" src=\"images/image-4.png\" alt=\"Hero Image\" title=\"Hero Image\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 70%;max-width: 406px;\" width=\"406\" class=\"v-src-width v-src-max-width\"/>\n" +
"      \n" +
"    </td>\n" +
"  </tr>\n" +
"</table>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"<table id=\"u_content_heading_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:5px 40px 30px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <h1 class=\"v-text-align v-line-height v-font-size\" style=\"margin: 0px; color: #2f3448; line-height: 190%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: 'Montserrat',sans-serif; font-size: 35px;\">\n" +
"    Soyez&nbsp; les&nbsp; bienvenue\n" +
"  </h1>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"    </div>\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"\n" +
"\n" +
"<div class=\"u-row-container\" style=\"padding: 0px;background-color: #a2c1d6\">\n" +
"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ddf2fe;\">\n" +
"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: #a2c1d6;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ddf2fe;\"><![endif]-->\n" +
"      \n" +
"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
"  \n" +
"<table id=\"u_content_text_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:44px 55px 70px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <div class=\"v-text-align v-line-height\" style=\"color: #536475; line-height: 180%; text-align: left; word-wrap: break-word;\">\n" +

"<p style=\"font-size: 14px; line-height: 180%;\">&nbsp;</p>\n" +
                  
                  textttt.getText()+

"<p style=\"font-size: 14px; line-height: 180%;\"><br /><span style=\"font-size: 16px; line-height: 28.8px; font-family: 'trebuchet ms', geneva;\">Merci de nous revisiter ,.</span></p>\n" +
                  
"<p style=\"font-size: 14px; line-height: 180%;\"><br /><strong><span style=\"font-size: 16px; line-height: 28.8px; font-family: 'trebuchet ms', geneva;\">Best regards,</span></strong><br /><span style=\"font-size: 16px; line-height: 28.8px; font-family: 'trebuchet ms', geneva;\">[yummy gout]</span></p>\n" +
"  </div>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"    </div>\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"\n" +
"\n" +
"<div class=\"u-row-container\" style=\"padding: 0px;background-color: #a2c1d6\">\n" +
"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #489066;\">\n" +
"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: #a2c1d6;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #489066;\"><![endif]-->\n" +
"      \n" +
"<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
"  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
"  \n" +
"<table id=\"u_content_heading_3\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:50px 10px 20px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"  <h1 class=\"v-text-align v-line-height v-font-size\" style=\"margin: 0px; color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: trebuchet ms,geneva; font-size: 22px;\">\n" +
"    w w w . y u m m y g o u t . c o m\n" +
"  </h1>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"  <tbody>\n" +
"    <tr>\n" +
"      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 45px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
"        \n" +
"<div align=\"center\">\n" +
"  <div style=\"display: table; max-width:140px;\">\n" +
"  <!--[if (mso)|(IE)]><table width=\"140\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"border-collapse:collapse;\" align=\"center\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse; mso-table-lspace: 0pt;mso-table-rspace: 0pt; width:140px;\"><tr><![endif]-->\n" +
"  \n" +
"    \n" +
"    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 15px;\" valign=\"top\"><![endif]-->\n" +
"    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 15px\">\n" +
"      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
"        <a href=\"https://facebook.com/\" title=\"Facebook\" target=\"_blank\">\n" +
"          <img src=\"images/image-1.png\" alt=\"Facebook\" title=\"Facebook\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n" +
"        </a>\n" +
"      </td></tr>\n" +
"    </tbody></table>\n" +
"    <!--[if (mso)|(IE)]></td><![endif]-->\n" +
"    \n" +
"    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 15px;\" valign=\"top\"><![endif]-->\n" +
"    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 15px\">\n" +
"      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
"        <a href=\"https://linkedin.com/\" title=\"LinkedIn\" target=\"_blank\">\n" +
"          <img src=\"images/image-5.png\" alt=\"LinkedIn\" title=\"LinkedIn\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n" +
"        </a>\n" +
"      </td></tr>\n" +
"    </tbody></table>\n" +
"    <!--[if (mso)|(IE)]></td><![endif]-->\n" +
"    \n" +
"    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 0px;\" valign=\"top\"><![endif]-->\n" +
"    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 0px\">\n" +
"      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
"        <a href=\"https://twitter.com/\" title=\"Twitter\" target=\"_blank\">\n" +
"          <img src=\"images/image-2.png\" alt=\"Twitter\" title=\"Twitter\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n" +
"        </a>\n" +
"      </td></tr>\n" +
"    </tbody></table>\n" +
"    <!--[if (mso)|(IE)]></td><![endif]-->\n" +
"    \n" +
"    \n" +
"    <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"      </td>\n" +
"    </tr>\n" +
"  </tbody>\n" +
"</table>\n" +
"\n" +
"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
"  </div>\n" +
"</div>\n" +
"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
"    </div>\n" +
"  </div>\n" +
"</div>\n" +
"\n" +
"\n" +
"    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
"    </td>\n" +
"  </tr>\n" +
"  </tbody>\n" +
"  </table>\n" +
"  <!--[if mso]></div><![endif]-->\n" +
"  <!--[if IE]></div><![endif]-->\n" +
"</body>\n" +
"\n" +
"</html>";
          messageBodyPart.setContent(htmlText,"text/html");
                    
           // second part the image 
           //MimeBodyPart fileAttach = new MimeBodyPart();
            //fileAttach.attachFile(new File("C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\yummyyygoutttttttt\\yummyyygoutttttttt\\Ma facture.pdf"));
          
          //adding the image and the html Part 
          emailContent.addBodyPart(messageBodyPart);
          //emailContent.addBodyPart(fileAttach);

          //multipart.addBodyPart(attachment);
          
          //putting everything together in our Email Content 
          message.setContent(emailContent);
          // Sending the email
          Transport.send(message);
		  }

    @FXML
    private void exelllll(ActionEvent event) {
        connection= MaConnexion.getInstance().getCnx();
        
       
try{
String filename="C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\tester_crud_1-20220308T220817Z-001\\tester_crud_1\\datacommandeclient.xls" ;
    HSSFWorkbook hwb=new HSSFWorkbook();
    HSSFSheet sheet =  hwb.createSheet("new sheet");

    HSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("numero facture");
rowhead.createCell((short) 1).setCellValue("nom plat");
rowhead.createCell((short) 2).setCellValue("quantité");

rowhead.createCell((short) 3).setCellValue("idpanier");
Statement st=cnx.createStatement();
ResultSet rs=st.executeQuery("select * from panier");
int i=1;
while(rs.next()){
HSSFRow row=   sheet.createRow((short)i);

row.createCell((short) 0).setCellValue(rs.getString("numfacture"));
row.createCell((short) 1).setCellValue(rs.getString("Idplat"));
row.createCell((short) 2).setCellValue(rs.getString("quantite"));


row.createCell((short) 3).setCellValue(Integer.toString(rs.getInt("Idpanier")));

i++;
}
    FileOutputStream fileOut =  new FileOutputStream(filename);
hwb.write(fileOut);
fileOut.close();
System.out.println("Your excel file has been generated!");
 File file = new File(filename);
        if (file.exists()){ 
        if(Desktop.isDesktopSupported()){
            Desktop.getDesktop().open(file);
        }}
        
} catch ( Exception ex ) {
    System.out.println(ex);

}
    }

//    @FXML
//    private void smssss(String txtnumfact) {
//           int num=99245400;
//        
//        
//        ApiClient defaultClient = new ApiClient();
//        defaultClient.setUsername("samar.bouzezi@esprit.tn");
//        defaultClient.setPassword("80F5AB45-8424-636D-A60E-F0017BF442DE");
//        SmsApi apiInstance = new SmsApi(defaultClient);
//
//        SmsMessage smsMessage = new SmsMessage();
//        smsMessage.body("votre commande de numero '"+txtnumfact+"' a été effectuer avec succes" );
//        smsMessage.to("+216"+num);
//        smsMessage.source("reservation");
//        
//
//        List<SmsMessage> smsMessageList = Arrays.asList(smsMessage);
//        // SmsMessageCollection | SmsMessageCollection model
//        SmsMessageCollection smsMessages = new SmsMessageCollection();
//        smsMessages.messages(smsMessageList);
//        try {
//            String result = apiInstance.smsSendPost(smsMessages);
//            System.out.println(result);
//        } catch (ApiException e) {
//            System.err.println("Exception when calling SmsApi#smsSendPost");
//            e.printStackTrace();
//        }
//    }
    
        void sms(String txtnumfact)
    {
       
        int num=99245400;
        
        
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername("samar.bouzezi@esprit.tn");
        defaultClient.setPassword("80F5AB45-8424-636D-A60E-F0017BF442DE");
        SmsApi apiInstance = new SmsApi(defaultClient);

        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body("lvotre commande  a été effectuer avec succes" );
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
    



