package GUI;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import Tools.DataBaseConnection;
import Entities.Reservation;
import Services.ReservationService;
import com.jfoenix.controls.JFXDatePicker;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.controlsfx.control.Notifications;
import Entities.Reservation;
import Services.Control;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;

import java.time.format.DateTimeFormatter;
import javafx.scene.layout.HBox;
import Tools.DataBaseConnection;
import Tools.JavaMail;
import Tools.QrCode;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.encoder.QRCode;
import com.itextpdf.text.Paragraph;
import java.util.Optional;
import java.util.logging.Level;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

 




public class ReservationController implements Initializable {
    
    @FXML
    private TableView<Reservation> resTable;

    @FXML
    private TableColumn<Reservation, String> branch_col;

    @FXML
    private TableColumn<Reservation, String> view_col;

    @FXML
    private TableColumn<Reservation, String> event_col;

    @FXML
    private TableColumn<Reservation, String> note_col;

    @FXML
    private TableColumn<Reservation, LocalDate> date_col;

    @FXML
    private TableColumn<Reservation, Integer> guest_col;
      
  
    
    
   
    @FXML
    private TextField guestNum;

    @FXML
    private TextField search;

    @FXML
    private Button add;

    @FXML
    private ImageView logo;

    @FXML
    private Button clear;

    @FXML
    private TextField notes;

    @FXML
    private TextField fieldnumber;
    @FXML
    private Button edit;

    @FXML
    private Button delete_btn;

    @FXML
    private Button filter_btn;

    @FXML
    private ChoiceBox<String> branch_choiceBox;

    @FXML
    private ChoiceBox<String> view_choiceBox;

    @FXML
    private ChoiceBox<String> event_choicebox;

    @FXML
    private JFXDatePicker date_picker;

    @FXML
    private Button print_btn;

    @FXML
    private ImageView refresh;
    
    @FXML
    private TextField tablesNum;
          
    
        ObservableList<Reservation>  resList = FXCollections.observableArrayList();

    ReservationService ms = new ReservationService();
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
 List<String> lt = new ArrayList<String>() ;
 
            branch_choiceBox.getItems().addAll("Marssa","Lac","Zaghouan","Tunis","Ariana","Ben Arous","Boumhal");
       
        view_choiceBox.getItems().addAll("Vue sur mer ","Vue sur Terasse","Vue au Jardin");
        event_choicebox.getItems().addAll("Anniversaire","fete","meeting","rien de spécial");
       load();
       resTable.setEditable(true);
       resTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       
    }
    @FXML
    void sendQr(ActionEvent event) throws WriterException, MessagingException, NotFoundException, SQLException {
        ReservationService rs= new ReservationService();
        try {
            QrCode.getQRCodeImage(ms.getById().toString());
            JavaMail.sendMail();
            Notifications notificationBuild = Notifications.create()
                                      .title("Check your Email")
                                      .text("Your reservation in Qrcode format")
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
        } catch (IOException ex) {
           
        }
    }
    @FXML
    void Clear(MouseEvent event) {
          branch_choiceBox.setValue(null);
        view_choiceBox.setValue(null);
        event_choicebox.setValue(null);
        notes.setText(null);
        guestNum.setText(null);
        date_picker.setValue(null);

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
    void addRes(ActionEvent event) throws MessagingException, WriterException, NotFoundException, SQLException {
        Control ctrl = new Control();
        String branchLocation = branch_choiceBox.getValue();
          String viewName = view_choiceBox.getValue();         
          Integer guest_number = Integer.parseInt(guestNum.getText());
          String note = notes.getText();
          LocalDate date = date_picker.getValue();
          String evenType = event_choicebox.getValue();
          Reservation r = new  Reservation( note,  viewName,  guest_number,  date,  evenType, branchLocation);
        if(guest_number >200){
            Alert alert =new Alert(Alert.AlertType.WARNING);
            //alert.setHeaderText("null");
            alert.setContentText("can't handle more than 200 persons");
            alert.showAndWait();

        } else if (date==null|| note.equals("")||guest_number==0 ){
        Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Verify your fields");
            alert.showAndWait();
        } else {
       ReservationService ms = new ReservationService();
        ms.ajouter(r);
              Image img = new Image("resources/success.png");
         Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Infos added Successfully") ;
                
                Optional <ButtonType> result=alert.showAndWait();
                if (result.get()==ButtonType.OK){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        }
       
        
        load();

    }}

    @FXML
    void calcul(ActionEvent event) {
        
            ReservationService rs = new ReservationService();
            int num = rs.calculateRes();
            tablesNum.setText(String.valueOf(num));
        }
    

    @FXML
    void clear(ActionEvent event) {
          branch_choiceBox.setValue(null);
        view_choiceBox.setValue(null);
        event_choicebox.setValue(null);
        notes.setText(null);
        guestNum.setText(null);
        date_picker.setValue(null);
    }
      @FXML
    void fieldnumber(ActionEvent event) {

    }
    
    void refresh() throws SQLException {
          resList.clear();
                Connection cn = DataBaseConnection.getInstance().getCn();

            String query = "select * from reservation";
            PreparedStatement pst = cn.prepareStatement(query);
           ResultSet rs = pst.executeQuery();
           Reservation rev = new Reservation();
            while (rs.next()){
                rev.setResId(rs.getInt("resId"));
                rev.setBranchLocation((rs.getString("branchLocation")));
                rev.setEventType((rs.getString("eventType")));
                rev.setGuestNum((rs.getInt("guestNum")));
                rev.setNotes((rs.getString("notes")));
                rev.setResDate((rs.getDate("resDate").toLocalDate()));
                rev.setView((rs.getString("view")));
                        
                /* resList.add(new  Reservation(
                         rs.getInt("resId"),
                       rs.getString("notes"),
                        rs.getString("view"),
                           rs.getInt("guestNum"),
                         rs.getDate("resDate").toLocalDate(),
                         rs.getString("eventType"),
                        rs.getString("branchLocation")
                       
                      
                       
                        )); */
resList.add(rev)  ; 
        resTable.setItems(resList);
            }
    }

   private void load() {
       
        Reservation r=new Reservation();
            ReservationService sv = new ReservationService();
            ObservableList<Reservation> list = sv.getAll();
             System.out.println(list);
           
   branch_col.setCellValueFactory(new PropertyValueFactory<Reservation,String>("branchLocation"));   
   event_col.setCellValueFactory(new PropertyValueFactory<Reservation,String>("eventType"));
    view_col.setCellValueFactory(new PropertyValueFactory<Reservation,String>("view"));
    note_col.setCellValueFactory(new PropertyValueFactory<Reservation,String>("notes"));
    date_col.setCellValueFactory(new PropertyValueFactory<Reservation,LocalDate>("resDate"));
    guest_col.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("guestNum"));
      branch_col.setCellValueFactory(new PropertyValueFactory<Reservation,String>("branchLocation"));

    resTable.setItems(list);
    
    }
    @FXML
    void filterBy(ActionEvent event) {
           ReservationService rs=new ReservationService();
        
        ObservableList<Reservation>filter=  rs.filterBy();
         resTable.setItems(filter);
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
    Reservation r = resTable.getSelectionModel().getSelectedItem();
    branch_choiceBox.setValue(String.valueOf(r.getBranchLocation()));
    view_choiceBox.setValue(String.valueOf(r.getView()));
    event_choicebox.setValue(String.valueOf(r.getEventType()));
    notes.setText(String.valueOf(r.getNotes()));
    date_picker.setValue((r.getResDate()));
    guestNum.setText(String.valueOf(r.getGuestNum()));
        System.out.println(r.getResId());
     
    }

    @FXML
    void modify(ActionEvent event) throws SQLException {
         Reservation r=new Reservation();
   ReservationService rs=new ReservationService();
   r =resTable.getSelectionModel().getSelectedItem();
         if (!resTable.getSelectionModel().isEmpty()) {
      
         

         
   r.setBranchLocation(branch_choiceBox.getValue());
   r.setView(view_choiceBox.getValue());
   r.setEventType(event_choicebox.getValue());
   r.setNotes(notes.getText());
   int guests=Integer.valueOf(guestNum.getText());
   r.setGuestNum(guests);
   r.setResDate(date_picker.getValue());
   rs.edit(r);
    Notifications notificationBuild = Notifications.create()
                                      .title("Edditing")
                                      .text("table was added successfully")
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

   resTable.getItems().clear();
   load(); 
    }else {
              Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setContentText("select a row ");
            alert.showAndWait();
    }
    }
    
    @FXML
    void printRes(ActionEvent event) {
        
  
              Image img = new Image("resources/success.png");
Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sur about the procedure", ButtonType.YES, ButtonType.NO);
alert.showAndWait();
if (alert.getResult() == ButtonType.YES){
    String file_name="C:\\Users\\tchet\\Desktop\\Infores.pdf";
        Document document =new Document() {};
        try {
            
            PdfWriter.getInstance((com.itextpdf.text.Document) document, new FileOutputStream(file_name));
            document.open();
           
    Connection connection = DataBaseConnection.getInstance().getCn();
            PreparedStatement ste = null;
            ResultSet rst=null;
            String query="select * from reservation";
           // ste = cnx.createStatement(query);  
            ste = connection.prepareStatement(query);
            rst =ste.executeQuery(query);
            while(rst.next()){
                Paragraph para=new Paragraph("L'emplacement:  "+rst.getString("branchLocation")+"   Description:  "
                        + " "+rst.getString("notes")+"   Vue:  "+rst.getString("view")+"    Date:  "+rst.getString("resDate")+"     Nobmre de personne:  "+rst.getInt("guestNum")+"   Type d'evenement:  " + ""+rst.getString("eventType"));
                
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
    void delete(ActionEvent event) throws SQLException {
        if (!resTable.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sur you want to delete " + resTable.getSelectionModel().getSelectedItem().getBranchLocation()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
//    ObservableList<Reservation> selecReservations,allres;
//    allres= resTable.getItems();
//    selecReservations = resTable.getSelectionModel().getSelectedItems();
        ReservationService cp = new ReservationService();

    
     Reservation   r= resTable.getSelectionModel().getSelectedItem();
    System.out.println(r);
    cp.deleteRes(r);
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
    resTable.getItems().clear();
    load();
}
 
        }
         
    }
    @FXML
    void search(KeyEvent event) throws SQLException {
 ReservationService rs=new ReservationService(); 
            Reservation r= new Reservation();
        ObservableList<Reservation>filter= rs.search(search.getText());
        populateTable(filter);
        System.out.println(r.getResId());
    }
     private void populateTable(ObservableList<Reservation> reslist){
       resTable.setItems(reslist);
   }
   

    @FXML
    private void backAction(ActionEvent event) throws IOException {
         
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Adm.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

 

}