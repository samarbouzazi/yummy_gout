/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author chaim
 */
public class CalculatriceController_1 implements Initializable {

    
    Float data =0f;
    int operation =-1;
    @FXML
    private Button one;
    @FXML
    private Button two;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button seven;
    @FXML
    private Button zero;
    @FXML
    private Button height;
    @FXML
    private Button equals;
    @FXML
    private Button sex;
    @FXML
    private Button three;
    @FXML
    private Button addition;
    @FXML
    private Button soustraction;
    @FXML
    private Button nine;
    @FXML
    private Button cl;
    @FXML
    private Button multiplication;
    @FXML
    private Button division;
    @FXML
    private TextField display;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handaction(ActionEvent event) {
        if(event.getSource() == one)
        {
            display.setText(display.getText() + "1");
        }else if (event.getSource() == two){
            display.setText(display.getText()+ "2");
        }else if (event.getSource() == three){
            display.setText(display.getText()+ "3");
       }else if (event.getSource() == four){
          display.setText(display.getText()+ "4");
             }else if (event.getSource() == five){
            display.setText(display.getText()+ "5");
             }else if (event.getSource() == sex){
            display.setText(display.getText()+ "6");
             }else if (event.getSource() == seven){
            display.setText(display.getText()+ "7");
             }else if (event.getSource() == height){
            display.setText(display.getText()+ "8");
             }else if (event.getSource() == nine){
            display.setText(display.getText()+ "9");
             }else if (event.getSource() == zero){
            display.setText(display.getText()+ "0");
             }else if (event.getSource() == cl){
            display.setText("");
             }else if (event.getSource() == addition){
              data = Float.parseFloat(display.getText());
              operation =1;//addition
              display.setText("");
             }else if (event.getSource() == soustraction){
              data = Float.parseFloat(display.getText());
              operation =2;//addition
              display.setText("");
      }else if (event.getSource() == multiplication){
              data = Float.parseFloat(display.getText());
              operation =3;//addition
              display.setText("");
}else if (event.getSource() == division){
              data = Float.parseFloat(display.getText());
              operation =4;//addition
              display.setText("");
}else if (event.getSource() == equals)
{
             Float secondOperand =Float.parseFloat(display.getText());
             switch(operation)
             {
                 case 1://add
                     Float ans =data + secondOperand;
                     display.setText(String.valueOf(ans));break;
                 case 2://sustraction
                    ans =data - secondOperand;
                     display.setText(String.valueOf(ans));break;
                     case 3://multip
                      ans =data * secondOperand;
                     display.setText(String.valueOf(ans));break;
                     case 4://div
                         ans =0f;
                         try {
                             ans =data / secondOperand;
                         } catch (Exception e) {
                             display.setText("erreur");
                         }
                      display.setText(String.valueOf(ans));break;
             }
}
    }
}
