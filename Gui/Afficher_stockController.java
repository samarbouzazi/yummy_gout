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
import Entities.Stock;
import Services.BareCode;
import Services.Pdf2;
import Services.SControl;
import Services.SStock;
import Tools.MaCon;
import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import java.util.Arrays;
import java.util.List;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author DELL PRCISION 3551
 */
public class Afficher_stockController implements Initializable {
    
    @FXML
    private TableView<Stock> tablestock;
    @FXML
    private TableColumn<Stock, String> idfCol;
    @FXML
    private TableColumn<Stock, String> nomsCol;
    @FXML
    private TableColumn<Stock, String> dajoutCol;
    @FXML
    private TableColumn<Stock, String> dfinCol;
    @FXML
    private TableColumn<Stock, String> prixsCol;
    @FXML
    private TableColumn<Stock, String> qtsCol;
    @FXML
    private TextField find;
    @FXML
    private TextField nom;
    @FXML
    private TextField prix;
    @FXML
    private TextField qt;
    private TextField idfour;
    @FXML
    private Button ajout;
    
    
    int id ;
    
    private ObservableList<Stock>StockData = FXCollections.observableArrayList();
    
    SStock ss=new SStock();
    private Statement ste;
    private Stock s;
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Stock stock = null ;
    Connection cnx= MaCon.getInstance().getCnx();
     private ObservableList<Integer> ObservableFour;
    
    ObservableList<Stock>  StockList = FXCollections.observableArrayList();
    @FXML
    private Button ajout1;
    @FXML
    private JFXDatePicker daaaatea;
    @FXML
    private JFXDatePicker daaaatef;
   
    ObservableList<Stock> data;
    
    public static int ids ;
    SControl sc=new SControl();
    @FXML
    private ComboBox<String> cbidf;
    @FXML
    private TableColumn<?, ?> idsCol;
    @FXML
    private ImageView image;
    @FXML
    private Button ajout3;
    @FXML
    private Button ajout31;
    @FXML
    private Button ajout311;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadStock();
        refresh();
        //notif();
    }    


     @FXML
    private void Clear() {
        cbidf.setValue(null);
        nom.setText(null);
        daaaatea.setValue(null);
        daaaatef.setValue(null);
        prix.setText(null);
        qt.setText(null);
    }
    @FXML
    private void Ajouter(MouseEvent event) throws SQLException {
        String noms =nom.getText();
        String email = cbidf.getValue();
        int idff=ss.chercherIdF(email);
        Date date_ajout_s = Date.valueOf(daaaatea.getValue());
        Date date_fin_s=Date.valueOf(daaaatef.getValue());
        float prix_s= Float.valueOf(prix.getText());
        int qt_s=Integer.valueOf(qt.getText());
        Stock s=new Stock(idff, noms, date_ajout_s, date_fin_s, prix_s, qt_s);
        if (sc.existe(s)==0){
         
        if(noms.isEmpty()){
            Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("Les champs ne doivent pas etre vides");
            alert.showAndWait();
       
        } 
        else if(!sc.ControleEnd(s))
            
        {
             Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("La date de fin de validité doit etre superieure a la date d'ajout");
            alert.showAndWait();
        }else if(prix_s<=0)
        {
             Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("Le prix ne doit etre pas null");
            alert.showAndWait();
        }
        else if( !sc.Controlechar(s))
        {
             Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("Le nom du produit doit etre compris entre aA et zZ");
            alert.showAndWait();
        }else {
        SStock sb= new SStock();
        sb.ajouter(s);
        sms(nom.getText());
        refresh();
        Notifications notificationBuild = Notifications.create()
                                      .title("Traitement Stock")
                                      .text("le produit a été ajouté avec succes")
                                      .graphic(null)
                                      //.hideAfter(Duration.Hours(5))
                                      .position(Pos.CENTER)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      System.out.println("click here");
                                  }});
        notificationBuild.show(); 
        Clear();
        //tablestock.setItems(StockData);
        }}
        else{
            Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("Le produit déja existe");
            alert.showAndWait();
        
    
}
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


    

    private void loadStock() {
        SStock ss=new SStock();
        connection= MaCon.getInstance().getCnx();
    refresh();
    idsCol.setCellValueFactory(new PropertyValueFactory<>("ids"));
    idfCol.setCellValueFactory(new PropertyValueFactory<>("nomf"));
    cbidf.setItems(FXCollections.observableArrayList(ss.getAllFOUR()));
    nomsCol.setCellValueFactory(new PropertyValueFactory<>("noms"));
    dajoutCol.setCellValueFactory(new PropertyValueFactory<>("date_ajout_s"));
    dfinCol.setCellValueFactory(new PropertyValueFactory<>("date_fin_s"));
    prixsCol.setCellValueFactory(new PropertyValueFactory<>("prix_s"));
    qtsCol.setCellValueFactory(new PropertyValueFactory<>("qt_s"));
    }

    

    
        
    


    private void Rechercher(ActionEvent event) {
        
        
        idfCol.setCellValueFactory(
            new PropertyValueFactory<>("idf")
        );
        nomsCol.setCellValueFactory(
            new PropertyValueFactory<>("noms")
        );
        dajoutCol.setCellValueFactory(
            new PropertyValueFactory<>("date_ajou_s")
        );
        dfinCol.setCellValueFactory(
            new PropertyValueFactory<>("date_fin_s")
        );
        prixsCol.setCellValueFactory(
            new PropertyValueFactory<>("prix_s")
        );
        qtsCol.setCellValueFactory(
            new PropertyValueFactory<>("qt_s")
        );
        
            
        
    
    
    }

   
//public void notif(){
//    Stock s=new Stock();
//    if(s.getQt_s()<2){
//        Image img = new Image("Image/check-mark2.png");
//        Notifications notificationBuild = Notifications.create()
//                                      .title("Traitement Stock")
//                                      .text("Stock est epuisé")
//                                      .graphic(null)
//                                      //.hideAfter(Duration.Hours(5))
//                                      .position(Pos.CENTER)
//                                      .onAction(new EventHandler<ActionEvent>() {
//                                  @Override
//                                  public void handle(ActionEvent event) {
//                                      System.out.println("click here");
//                                  }
//                                  
//                              });
//                              notificationBuild.show(); }
//}

    private void refresh() {
        try {
            StockList.clear();
            
            query = "select stocks.ids,fournisseurs.nomf,stocks.noms,stocks.date_ajout_s,stocks.date_fin_s,stocks.prix_s,stocks.qt_s from stocks inner join fournisseurs where fournisseurs.idf=stocks.idf";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                StockList.add(new  Stock(
                        resultSet.getInt("ids"),
                        resultSet.getString("nomf"),
                        resultSet.getString("noms"),
                        resultSet.getDate("date_ajout_s"),
                        resultSet.getDate("date_fin_s"),
                        resultSet.getFloat("prix_s"),
                        resultSet.getInt("qt_s")
                        )); 
               tablestock.setItems(StockList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimer(MouseEvent event) {
        if (!tablestock.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer le produit " + tablestock.getSelectionModel().getSelectedItem().getNoms()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
     SStock cp = new SStock();
    cp.delete(tablestock.getSelectionModel().getSelectedItem());
     Notifications notificationBuild = Notifications.create()
                                      .title("Traitement Stock")
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

    @FXML
    private void modifier(MouseEvent event) throws SQLException {
        
//        int ids = Integer.valueOf(idsCol.getText());
//        String noms =nom.getText();
//        Date date_ajout_s=Date.valueOf(daaaatea.getValue());
//        Date date_fin_s=Date.valueOf(daaaatef.getValue());
//        float prix_s= Float.valueOf(prix.getText());
//        int qt_s=Integer.valueOf(qt.getText());
//        SStock ss=new SStock();
//        
//        ss.updateFormation(ids, noms, date_ajout_s, date_fin_s, prix_s, qt_s);
//         Notifications notificationBuild = Notifications.create()
//                                      .title("Traitement Stock")
//                                      .text("le produit a été modifié avec succes")
//                                      .graphic(null)
//                                      //.hideAfter(Duration.Hours(5))
//                                      .position(Pos.CENTER)
//                                      .onAction(new EventHandler<ActionEvent>() {
//                                  @Override
//                                  public void handle(ActionEvent event) {
//                                      System.out.println("click here");
//                                  }
//                                  
//                              });
//                              notificationBuild.show(); 
//        
    }

    

//    String cinp =txt_cin.getText();
//        String telp = txt_tel.getText();
//        String email = txt_email.getText();
//        int Salaire =Integer.valueOf(txt_salaire.getText());
//        String Specialite = (String)combox.getValue();
//        Date Date_embauche = Date.valueOf(txt_date_embauche.getValue());
//        int nbheure = Integer.valueOf(txt_nbheure.getText());
    
    @FXML
    private void GoHome(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void PDF(MouseEvent event) throws FileNotFoundException, SQLException, DocumentException, IOException  {
        
         Stock tab_Recselected = tablestock.getSelectionModel().getSelectedItem();

       String Var_prix = String.valueOf(tab_Recselected.getPrix_s());
       String Var_dateajout=String.valueOf(tab_Recselected.getDate_ajout_s());
       String Var_datefin=String.valueOf(tab_Recselected.getDate_fin_s());
       String Var_id=String.valueOf(tab_Recselected.getIds());
       String Var_qt=String.valueOf(tab_Recselected.getQt_s());
        Pdf2 p = new Pdf2();
    p.add("Mon stock",tab_Recselected.getNoms(),tab_Recselected.getNomf(),Var_dateajout,Var_datefin,Var_prix,Var_qt,Var_id);
    
   try {
            String a;
            a = "C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\tester_crud_1";
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
    private void handleMouseAction(MouseEvent event) {
         Stock per = tablestock.getSelectionModel().getSelectedItem();
    nom.setText(String.valueOf(per.getNoms()));
    
    prix.setText(String.valueOf(per.getPrix_s()));
    qt.setText(String.valueOf(per.getQt_s()));
    
    }

    @FXML
    private void update(ActionEvent event) {
             Stock f1=new Stock();
   SStock sf=new SStock();
   f1=tablestock.getSelectionModel().getSelectedItem();
//   f1.setNoms(nom.getText());
//   f1.setPrenomf(prenom.getText());
   int qt_s=Integer.valueOf(qt.getText());
   f1.setQt_s(qt_s);
   float prixx= Float.valueOf(prix.getText());
   
   f1.setQt_s(qt_s);
   f1.setPrix_s(prixx);
//   f1.setCatf(categorie.getText());
//   f1.setAddf(email.getText());
  sf.update(f1);
   loadStock(); 
   refresh();
    }

    @FXML
    private void rechercher(KeyEvent event) {
        
        SStock bs=new SStock(); 
            Stock b= new Stock();
        ObservableList<Stock>filter= bs.chercherav(find.getText());
        populateTable(filter);
    }
    private void populateTable(ObservableList<Stock> branlist){
       tablestock.setItems(branlist);
   }

    @FXML
    private void trier(MouseEvent event) {
    }

    @FXML
    private void refreshh(MouseEvent event) {
         try {
            StockList.clear();
            
            query = "select stocks.ids,fournisseurs.nomf,stocks.noms,stocks.date_ajout_s,stocks.date_fin_s,stocks.prix_s,stocks.qt_s from stocks inner join fournisseurs where fournisseurs.idf=stocks.idf";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                StockList.add(new  Stock(
                        resultSet.getInt("ids"),
                        resultSet.getString("nomf"),
                        resultSet.getString("noms"),
                        resultSet.getDate("date_ajout_s"),
                        resultSet.getDate("date_fin_s"),
                        resultSet.getFloat("prix_s"),
                        resultSet.getInt("qt_s")
                        )); 
               tablestock.setItems(StockList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void excel(MouseEvent event) {
        connection= MaCon.getInstance().getCnx();
        
       
try{
String filename="C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\tester_crud_1\\data.xls" ;
    HSSFWorkbook hwb=new HSSFWorkbook();
    HSSFSheet sheet =  hwb.createSheet("new sheet");

    HSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("ID");
rowhead.createCell((short) 1).setCellValue("nom produit");
rowhead.createCell((short) 2).setCellValue("nom fournisseur");
rowhead.createCell((short) 3).setCellValue("prix unitaire");
rowhead.createCell((short) 4).setCellValue("quantité");

Statement st=cnx.createStatement();
ResultSet rs=st.executeQuery("select stocks.ids,stocks.noms,fournisseurs.nomf,stocks.prix_s,stocks.qt_s from stocks inner join fournisseurs on stocks.idf=fournisseurs.idf");
int i=1;
while(rs.next()){
HSSFRow row=   sheet.createRow((short)i);

row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("ids")));
row.createCell((short) 1).setCellValue(rs.getString("noms"));
row.createCell((short) 2).setCellValue(rs.getString("nomf"));
row.createCell((short) 3).setCellValue(Float.toString(rs.getFloat("prix_s")));
row.createCell((short) 4).setCellValue(Integer.toString(rs.getInt("qt_s")));

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

    @FXML
    private void barecode(ActionEvent event) {
    if (!tablestock.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir generer le code a barre de ce produit " + tablestock.getSelectionModel().getSelectedItem().getNoms()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();
if (alert.getResult() == ButtonType.YES) {
String val = ""+((int)(Math.random()*9000)+1000);
String nom=tablestock.getSelectionModel().getSelectedItem().getNoms();
BareCode bc=new BareCode();
bc.generateImage(nom,val);

 Notifications notificationBuild = Notifications.create()
                                      .title("Traitement Stock")
                                      .text("le code a été generé avec succes"
                                              + "Il se trouve sous C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\Avant final")
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
 
        }
         else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("il faut séléctionner une ligne");
            alert.showAndWait();}
        
 
        
    }
      
    void sms(String nom)
    {
       
        int num=24052930;
        
        
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername("samar.bouzezi@esprit.tn");
        defaultClient.setPassword("80F5AB45-8424-636D-A60E-F0017BF442DE");
        SmsApi apiInstance = new SmsApi(defaultClient);

        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body("le produit '"+nom+"' a été ajouté avec succes" );
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

  

    

    

