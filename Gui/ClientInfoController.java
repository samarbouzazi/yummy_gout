/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;





import Entities.ClientInfo;
import Entities.Reservation;
import Services.ClientInfoService;
import Services.Control;
import Services.ReservationService;
import Tools.DataBaseConnection;
import Tools.JavaMail;
import Tools.QrCode;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXDatePicker;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author tchet
 */
public class ClientInfoController implements Initializable {

      @FXML
    private TableView<ClientInfo> clTable;

    @FXML
    private TableColumn<ClientInfo, String> nom_col;

    @FXML
    private TableColumn<ClientInfo, String> prenom_col;

    @FXML
    private TableColumn<ClientInfo, String> adress_col;

    @FXML
    private TableColumn<ClientInfo, String> email_col;

    @FXML
    private TableColumn<ClientInfo, Integer> tel_col;

    @FXML
    private TableColumn<ClientInfo, LocalDate> date_col;

    @FXML
    private TableColumn<ClientInfo, Integer> points_col;

    @FXML
    private TextField search;

    @FXML
    private Button add;

    @FXML
    private ImageView logo;

    @FXML
    private Button clear;

    @FXML
    private TextField email_txt;

    @FXML
    private Button edit;

    @FXML
    private Button delete_btn;

    @FXML
    private TextField tablesNum;

    @FXML
    private Button filter_btn;

    @FXML
    private JFXDatePicker date_picker;

    @FXML
    private Button print_btn;

    @FXML
    private ImageView refresh;

    @FXML
    private TextField adress_txt;

    @FXML
    private ImageView home_btn;

    @FXML
    private Button confirmQr;

    @FXML
    private TextField nom_txt;

    @FXML
    private TextField prenom_txt;

    @FXML
    private TextField tel_txt;

    @FXML
    private TextField points_txt;
           
               


    @FXML
    void Clear(MouseEvent event) {
          nom_txt.setText(null);
        prenom_txt.setText(null);
        adress_txt.setText(null);
        email_txt.setText(null);
        tel_txt.setText(null);
        date_picker.setValue(null);
        points_txt.setText(null);
    }

    @FXML
    void GoHome(MouseEvent event) throws IOException {
            Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
      Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home.fxml")));
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    void addCl(ActionEvent event) throws SQLException {
        Control ctrl = new Control();
         String nom = nom_txt.getText();
          String prenom = prenom_txt.getText();
         Integer tel = Integer.parseInt(tel_txt.getText());
         Integer point =Integer.parseInt(points_txt.getText());
          LocalDate date = date_picker.getValue();
          String email = email_txt.getText();
          String adress = adress_txt.getText();
          ClientInfo cl = new ClientInfo (nom,  prenom,  adress,  email,  point, tel,date);
       
        if(nom.equals("") || prenom.equals("") || adress.equals("") ||email.equals(""))  {
            
            Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Verify your fields");
            alert.showAndWait();
            
        } else if (date==null) {
            
         Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Verify your fields");
            alert.showAndWait();
            System.out.println("1");
        } 
    else {
       ClientInfoService cli = new ClientInfoService();
            System.out.println("1");
        cli.ajouter(cl);
              Image img = new Image("resources/success.png");
         Alert a =new Alert (Alert.AlertType.CONFIRMATION);
                a.setTitle("Confirmation") ;
                a.setHeaderText("Clientinfo added Successfully") ;
                
                Optional <ButtonType> result=a.showAndWait();
                if (result.get()==ButtonType.OK){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        }

        load();
        }
       
    }
     private void load() {
       
        ClientInfo cl=new ClientInfo();
            ClientInfoService cls = new ClientInfoService();
            ObservableList<ClientInfo> list = cls.getAll();
             System.out.println(list);
           
   nom_col.setCellValueFactory(new PropertyValueFactory<ClientInfo,String>("nom"));   
   prenom_col.setCellValueFactory(new PropertyValueFactory<ClientInfo,String>("prenom"));
   email_col.setCellValueFactory(new PropertyValueFactory<ClientInfo,String>("email"));
    adress_col.setCellValueFactory(new PropertyValueFactory<ClientInfo,String>("adress"));
    tel_col.setCellValueFactory(new PropertyValueFactory<ClientInfo,Integer>("tel"));
    points_col.setCellValueFactory(new PropertyValueFactory<ClientInfo,Integer>("points"));
    date_col.setCellValueFactory(new PropertyValueFactory<ClientInfo,LocalDate>("date"));
    clTable.setItems(list);
    
    }
    @FXML
    void calcul(ActionEvent event) {
 ClientInfoService rs = new ClientInfoService();
            int num = rs.calculateRes();
            tablesNum.setText(String.valueOf(num));
    }

    @FXML
    void clear(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {
if (!clTable.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sur you want to delete " + clTable.getSelectionModel().getSelectedItem().getEmail()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {

    ClientInfoService cp = new ClientInfoService();
     ClientInfo   cl= clTable.getSelectionModel().getSelectedItem();
    System.out.println(cl);
    cp.delete(cl);
     Notifications notificationBuild = Notifications.create()
                                      .title("Reservation Process")
                                      .text("Res deleted with success")
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
    clTable.getItems().clear();
    load();
    }
}
    }

    @FXML
    void filterBy(ActionEvent event) {
        ClientInfoService cl=new ClientInfoService();
        ObservableList<ClientInfo>filter=  cl.filterBy();
         clTable.setItems(filter);
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
 ClientInfo c = clTable.getSelectionModel().getSelectedItem();
    nom_txt.setText(String.valueOf(c.getNom()));
    prenom_txt.setText(String.valueOf(c.getPrenom()));
    adress_txt.setText(String.valueOf(c.getAdress()));
    email_txt.setText(String.valueOf(c.getEmail()));
    date_picker.setValue((c.getDate()));
    points_txt.setText(String.valueOf(c.getPoints()));
    tel_txt.setText(String.valueOf(c.getTel()));
        System.out.println(c.getId_client());
      
    }

    @FXML
    void modify(ActionEvent event) {
           ClientInfo c=new ClientInfo();

  ClientInfoService cli=new ClientInfoService();
  c = clTable.getSelectionModel().getSelectedItem();
  if (!clTable.getSelectionModel().isEmpty()) {
    c.setNom(nom_txt.getText());
   c.setPrenom(prenom_txt.getText());
   c.setAdress(adress_txt.getText());
   c.setEmail(email_txt.getText());
    Integer pts=Integer.valueOf(points_txt.getText());
   c.setPoints(pts);
   Integer phone=Integer.valueOf(tel_txt.getText());
   c.setTel(phone);
    c.setDate(date_picker.getValue());
   cli.edit(c);
   
    Notifications notificationBuild = Notifications.create()
                                      .title("Edditing")
                                      .text("ClientInfo was edited successfully")
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

   clTable.getItems().clear();
   load(); 
  }else {
       Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Select a row ");
            alert.showAndWait();
  }
  
  }
   
    
    @FXML
    void printRes(ActionEvent event) {
    Image img = new Image("resources/success.png");
Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sur about the procedure", ButtonType.YES, ButtonType.NO);
alert.showAndWait();
if (alert.getResult() == ButtonType.YES){
    String file_name="C:\\Users\\tchet\\Desktop\\ClientInfo.pdf";
        Document document =new Document() {};
        try {
            
            PdfWriter.getInstance((com.itextpdf.text.Document) document, new FileOutputStream(file_name));
            document.open();
           
    Connection connection = DataBaseConnection.getInstance().getCn();
            PreparedStatement ste = null;
            ResultSet rst=null;
            String query="select * from clientinfo";
            ste = connection.prepareStatement(query);
            rst =ste.executeQuery(query);
            while(rst.next()){
                Paragraph para=new Paragraph("Firstname:   "+rst.getString("nom")+"   Prenom:  "
                        + " "+rst.getString("prenom")+"   Adress:  "+rst.getString("adress")+"    Email:  "+rst.getString("email")+"    PhoneNumber:  "+rst.getInt("tel")+"   Points:  " + ""+rst.getInt("points")+"  "
                                + "RegistrationDate:" + ""+rst.getDate("date"));
                
            document.add(para);
            }
            Notifications notificationBuild = Notifications.create()
                                      .title("Printing")
                                      
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
              Notifications notificationbuilder = Notifications.create()
                   .title("Printing reservation'")
                   .text("Successful insertion!")
                   .graphic(new ImageView (img) )
                   .hideAfter(Duration.seconds(2))
                   .position(Pos.BOTTOM_LEFT)
                   .onAction(new EventHandler<ActionEvent> (){
                      @Override 
                       public void handle(ActionEvent event){
                         System.out.println("Notification");
                       }
                       
                   });
             notificationbuilder.darkStyle();
                notificationbuilder.showConfirm();
              
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            
            
            //addimage
            document.close();
            System.out.println("finished");
        } catch (Exception e) {
            System.err.println(e);
           
        }
    }
    }

    @FXML
    void search(KeyEvent event) {
        ClientInfoService rs=new ClientInfoService(); 
            ClientInfo c= new ClientInfo();
        ObservableList<ClientInfo>filter= rs.search(search.getText());
        populateTable(filter);
    }
     private void populateTable(ObservableList<ClientInfo> cllist){
       clTable.setItems(cllist);
    }

//    @FXML
//    void sendQr(ActionEvent event) throws SQLException, WriterException, NotFoundException, MessagingException {
// ClientInfoService cs= new ClientInfoService();
//        try {
//            QrCode.getQRCodeImage(cs.getById().toString());
//            JavaMail.sendMail();
//        } catch (IOException ex) {
//           
//        }
//    }
//    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
        
    }    
    
}
