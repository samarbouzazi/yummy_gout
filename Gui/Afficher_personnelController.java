/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import Entities.personnel;
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
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.observableList;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
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
import org.controlsfx.control.Notifications;
import Services.PersonnelService;
import Services.controlesaisie;
import Services.pdf33;
import Tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Afficher_personnelController implements Initializable {
personnel ss=new personnel();
    private Statement ste;
    private personnel p;
    String query = null;
    Connection connection = null ;
    Connection cnx=MaConnexion.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    personnel per = null ;
      String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
            + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
   ObservableList<personnel>  List = FXCollections.observableArrayList();
    @FXML
    private TextField idnomp;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_cin;
    @FXML
    private TextField txt_tel;
    @FXML
    private TextField txt_email;
    @FXML
    private JFXDatePicker txt_date_embauche;
    @FXML
    private TextField txt_salaire;
    @FXML
    private TextField txt_nbheure;
    

    ObservableList<personnel>  personnel = FXCollections.observableArrayList();
    @FXML
    private JFXTextField id_recherche;
    @FXML
    private JFXComboBox<String> combox;
    controlesaisie sc=new controlesaisie();
    @FXML
    private TableView<personnel> perso;
    @FXML
    private TableColumn<personnel, String> idp;
    @FXML
    private TableColumn<personnel, String> nom;
    @FXML
    private TableColumn<personnel, String> prenom;
    @FXML
    private TableColumn<personnel, String> cin;
    @FXML
    private TableColumn<personnel, String> tel;
    @FXML
    private TableColumn<personnel, String> email;
    @FXML
    private TableColumn<personnel, String> salaire;
    @FXML
    private TableColumn<personnel, String> specialite;
    @FXML
    private TableColumn<personnel, String> nb;
    @FXML
    private TableColumn<personnel, String> date;
    @FXML
    private TableColumn<personnel, String> taux;
    @FXML
    private Label txtnb;
    private TextField id;
    private TableView<personnel> idpp;
    @FXML
    private JFXButton ajout;
    @FXML
    private JFXButton prime;
    @FXML
    private JFXButton clear1;
    @FXML
    private Button modf;
    @FXML
    private Button ded;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ObservableList<String> list = FXCollections.observableArrayList("Livreur","chefemployée","cuisinier","serveur");
        combox.setItems(list);
        loadperso();
        refresh();
        //load1();
        //refresh1();   
    }  
    @FXML
    private void select(ActionEvent event) {
        String ss = combox.getSelectionModel().getSelectedItem().toString();
    }
    @FXML
    private void ajouter(MouseEvent event) throws SQLException {
        connection=MaConnexion.getInstance().getCnx();
        String nomp =idnomp.getText();
        String prenomp = txt_prenom.getText();
        String cinp =txt_cin.getText();
        String telp = txt_tel.getText();
        String email = txt_email.getText();
        int Salaire =Integer.valueOf(txt_salaire.getText());
        String Specialite = (String)combox.getValue();
        Date Date_embauche = Date.valueOf(txt_date_embauche.getValue());
        int nbheure = Integer.valueOf(txt_nbheure.getText());
        
        personnel p = new personnel(nomp,prenomp,cinp,telp,email,Salaire,Specialite,nbheure,Date_embauche);
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(txt_email.getText());
       if(controler.matches()){
        if( !sc.Controlechar1(p)||nomp.isEmpty()||!sc.Controlechar2(p)||prenomp.isEmpty()||!sc.isNumeric(cinp)||cinp.length()!=8||!sc.isNumeric(telp)||telp.length()!=8||email.isEmpty()){
            Alert alert =new Alert(Alert.AlertType.WARNING);
            
            alert.setContentText("verifier les champs");
            alert.showAndWait();
        }
        else{

         PersonnelService ps = new PersonnelService();
            ps.ajouter(p);
             Notifications notificationBuild = Notifications.create()
                                      .title("Traitement Personnel ")
                                      .text("le personnel a été ajouté avec succes")
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
refresh();
clear();
       }
       else{
           Alert alert =new Alert(Alert.AlertType.WARNING);
            
            alert.setContentText("verifier votre adresse email");
            alert.showAndWait();
       }
    }
    

@FXML
    private void primer(MouseEvent event) throws IOException {
  try{
            FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("/Gui/Primee.fxml"));
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
    private void close(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void loadperso() {
    personnel p=new personnel();
    connection= MaConnexion.getInstance().getCnx();
    idp.setCellValueFactory(new PropertyValueFactory<>("Idp"));
    nom.setCellValueFactory(new PropertyValueFactory<>("nomp"));
    prenom.setCellValueFactory(new PropertyValueFactory<>("prenomp"));
    cin.setCellValueFactory(new PropertyValueFactory<>("cinp"));
    tel.setCellValueFactory(new PropertyValueFactory<>("telp"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    salaire.setCellValueFactory(new PropertyValueFactory<>("Salaire"));
    specialite.setCellValueFactory(new PropertyValueFactory<>("Specialite"));
    nb.setCellValueFactory(new PropertyValueFactory<>("nbheure"));
    date.setCellValueFactory(new PropertyValueFactory<>("Date_embauche"));
    taux.setCellValueFactory(new PropertyValueFactory<>("taux_horaire"));
    
    
    
    }
    
    private void refresh() {
        try {
            List.clear();
            
            query = "select * from personnell";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                List.add(new  personnel(
                        resultSet.getInt("Idp"),
                        resultSet.getString("nomp"),
                        resultSet.getString("prenomp"),
                        resultSet.getString("cinp"),
                        resultSet.getString("telp"),
                        resultSet.getString("email"),
                        resultSet.getInt("Salaire"),
                        resultSet.getString("Specialite"),
                        resultSet.getInt("nbheure"),
                        resultSet.getDate("Date_embauche"),
                        resultSet.getInt("taux_horaire")
                        )); 
               perso.setItems(List);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }



  
@FXML
    private void clear() {

        idnomp.setText(null);
        txt_prenom.setText(null);
        txt_cin.setText(null);
        txt_tel.setText(null);
        txt_email.setText(null);
        txt_salaire.setText(null);
        txt_nbheure.setText(null);
        txt_date_embauche.setValue(null);
        combox.setValue(null);
        
        
        
    }

@FXML
    private void supprimer(MouseEvent event) {
        if (!perso.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer le personnel " + perso.getSelectionModel().getSelectedItem().getNomp()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    PersonnelService p = new PersonnelService();
    p.supprimer(perso.getSelectionModel().getSelectedItem());
     Notifications notificationBuild = Notifications.create()
                                      .title("Traitement Personnel ")
                                      .text("le personnel a été supprimé avec succes")
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
    private void getnbr(MouseEvent event) {
         PersonnelService ps = new PersonnelService();
      int nbr=ps.getnbrepersonnel();
      this.txtnb.setText(String.valueOf(nbr));
    }
    
    @FXML
void handleMouseAction(MouseEvent event)
       {
           try {
               personnel per = perso.getSelectionModel().getSelectedItem();
    idnomp.setText(String.valueOf(per.getNomp()));
    txt_prenom.setText(String.valueOf(per.getPrenomp()));
    txt_cin.setText(String.valueOf(per.getCinp()));
    txt_tel.setText(String.valueOf(per.getTelp()));
    txt_email.setText(String.valueOf(per.getEmail()));
    txt_salaire.setText(String.valueOf(per.getSalaire()));
    String c =  per.getSpecialite();
    combox.setValue(c);
    txt_nbheure.setText(String.valueOf(per.getNbheure()));
//    Date d =  per.getDate_embauche();
//    txt_date_embauche.setValue(d);
           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
       }
@FXML
    private void modifier(ActionEvent event) {
   personnel p=new personnel();
   PersonnelService ps = new PersonnelService();
   p=perso.getSelectionModel().getSelectedItem();
   p.setNomp(idnomp.getText());
   p.setPrenomp(txt_prenom.getText());
   p.setCinp(txt_cin.getText());
   p.setTelp(txt_tel.getText());
   p.setEmail(txt_email.getText());
   int Salaire=Integer.valueOf(txt_salaire.getText());
   p.setSalaire(Salaire);
   String Specialite = (String)combox.getValue();
   p.setSpecialite(Specialite);
   int nbheure=Integer.valueOf(txt_nbheure.getText());
   p.setNbheure(nbheure);
   Pattern pattern = Pattern.compile(masque);
   Matcher controler = pattern.matcher(txt_email.getText());
//  Date Date_embauche=Date.valueOf(txt_date_embauche.getValue());
//  p.setDate_embauche(Date_embauche);
if( !controler.matches()||!sc.Controlechar1(p)||idnomp.getText().isEmpty()||!sc.Controlechar2(p)||txt_prenom.getText().isEmpty()||!sc.isNumeric(txt_cin.getText())||txt_cin.getText().length()!=8||!sc.isNumeric(txt_tel.getText())||txt_tel.getText().length()!=8||txt_email.getText().isEmpty()){
            Alert alert =new Alert(Alert.AlertType.WARNING);
            
            alert.setContentText("verifier les champs");
            alert.showAndWait();
        }
        else{
   ps.modifier(p);
    Notifications notificationBuild = Notifications.create()
                                      .title("Traitement Personnel ")
                                      .text("le personnel a été modifié avec succes")
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
   loadperso(); 
   refresh();
    }
    }
private void populateTable(ObservableList<personnel> list){
       perso.setItems(list);
   }
    

@FXML
    private void trier(ActionEvent event) { 
       PersonnelService ps=new PersonnelService(); 
        personnel b= new personnel();
        ObservableList<personnel>filter=  ps.Triper();
        populateTable(filter); 
    }
@FXML
    private void calulator(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Calculatrice.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
             stage.show();
        
    }



        
   private void refresh1(){
        personnel p = new personnel();
        int nbr= Integer.valueOf(id.getText());
            this.txtnb.setText(String.valueOf(nbr));
        try {
            List.clear();
            query = "SELECT idp,nomp,prenomp,Salaire,nbheure,taux_horaire,(nbheure-taux_horaire)*(Salaire/taux_horaire)as prime from personnell where nbheure>taux_horaire and Idp like  '" +nbr+ "'";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                List.add(new  personnel(
                        resultSet.getInt("idp"),
                        resultSet.getString("nomp"),
                        resultSet.getString("prenomp"),
                        resultSet.getInt("Salaire"),
                        resultSet.getInt("nbheure"),
                        resultSet.getInt("taux_horaire"),
                        resultSet.getInt("prime")
                        )); 
               idpp.setItems(List);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }

    @FXML
    private void home1(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void rechercher(KeyEvent event) {
         PersonnelService ps=new PersonnelService(); 
        personnel b= new personnel();
        ObservableList<personnel>filter=  ps.chercherav(id_recherche.getText());
        populateTable(filter);
    }

    @FXML
    private void actualiser(MouseEvent event) {
       try {
            List.clear();
            
            query = "select * from personnell";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                List.add(new  personnel(
                        resultSet.getInt("Idp"),
                        resultSet.getString("nomp"),
                        resultSet.getString("prenomp"),
                        resultSet.getString("cinp"),
                        resultSet.getString("telp"),
                        resultSet.getString("email"),
                        resultSet.getInt("Salaire"),
                        resultSet.getString("Specialite"),
                        resultSet.getInt("nbheure"),
                        resultSet.getDate("Date_embauche"),
                        resultSet.getInt("taux_horaire")
                        )); 
               perso.setItems(List);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
        
    }

    @FXML
    private void excel(MouseEvent event) {
        connection= MaConnexion.getInstance().getCnx();
        
       
try{
//String filename="C:\\Users\\HP\\Documents\\NetBeansProjects\\yummygout\\data.xls" ;
String filename="C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\tester_crud_1\\data.xls" ;
    HSSFWorkbook hwb=new HSSFWorkbook();
    HSSFSheet sheet =  hwb.createSheet("new sheet");

    HSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("Nom");
rowhead.createCell((short) 1).setCellValue("Prenom");
rowhead.createCell((short) 2).setCellValue("Cin");
rowhead.createCell((short) 3).setCellValue("Tel");
rowhead.createCell((short) 4).setCellValue("Email");
rowhead.createCell((short) 5).setCellValue("Salaire");
rowhead.createCell((short) 6).setCellValue("Specialite");
rowhead.createCell((short) 7).setCellValue("Nombre d'heure");
//rowhead.createCell((short) 8).setCellValue("Date d'embauche");
rowhead.createCell((short) 8).setCellValue("taux horaire");
rowhead.createCell((short) 9).setCellValue("Id");

Statement st=cnx.createStatement();
ResultSet rs=st.executeQuery("select * from personnell");
int i=1;
while(rs.next()){
HSSFRow row=   sheet.createRow((short)i);

row.createCell((short) 9).setCellValue(Integer.toString(rs.getInt("Idp")));
row.createCell((short) 0).setCellValue(rs.getString("nomp"));
row.createCell((short) 1).setCellValue(rs.getString("prenomp"));
row.createCell((short) 2).setCellValue(Integer.toString(rs.getInt("cinp")));
row.createCell((short) 3).setCellValue(Integer.toString(rs.getInt("telp")));
row.createCell((short) 4).setCellValue(rs.getString("email"));
row.createCell((short) 5).setCellValue(Integer.toString(rs.getInt("Salaire")));
row.createCell((short) 6).setCellValue(rs.getString("Specialite"));
row.createCell((short) 7).setCellValue(Integer.toString(rs.getInt("nbheure")));
row.createCell((short) 8).setCellValue(Integer.toString(rs.getInt("Taux_horaire")));


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
    private void pdf(MouseEvent event) throws FileNotFoundException, SQLException, DocumentException {
        
        
        
         personnel tab_Recselected = perso.getSelectionModel().getSelectedItem();

       
       //String an = String.valueOf(tab_Recselected.getNomp());
       //String ap=String.valueOf(tab_Recselected.getPrenomp());
       String ac=String.valueOf(tab_Recselected.getCinp());
       String at=String.valueOf(tab_Recselected.getTelp());
       //String ae=String.valueOf(tab_Recselected.getEmail());
       String as=String.valueOf(tab_Recselected.getSalaire());
       //String ass =String.valueOf(tab_Recselected.getSpecialite());
       String anb=String.valueOf(tab_Recselected.getNbheure());
       String ath=String.valueOf(tab_Recselected.getTaux_horaire());
        pdf33 p = new pdf33();
    p.add("`"+tab_Recselected.getNomp()+"`.pdf",tab_Recselected.getNomp(),tab_Recselected.getPrenomp(),ac,at,tab_Recselected.getEmail(),as,tab_Recselected.getSpecialite(),anb,ath);
    
   try {
            String ax;
            ax = "C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\tester_crud_1-20220308T220817Z-001\\tester_crud_1";
            System.out.println(ax);
        File file = new File(ax);
        if (file.exists()){ 
        if(Desktop.isDesktopSupported()){
            Desktop.getDesktop().open(file);
        }}
    }
catch(Exception e){System.out.println("");}
    
   }
        
    }

    

   

