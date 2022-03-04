/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import Entities.Stock;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author DELL PRCISION 3551
 */
public class StockItemController implements Initializable {

    @FXML
    private Text nomproduit;
    @FXML
    private Text nomfournisseur;
    @FXML
    private Text ajoutproduit;
    @FXML
    private Text finproduit;
    @FXML
    private Text prixproduit;
    @FXML
    private Text quantiteproduit;
    @FXML
    private VBox VBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public StockItemController(){
    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/StockItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        
    }
     public void setInfo(Stock string)  {   
          System.out.println("items"+string);

            
        nomproduit.setText(string.getNoms());
        nomfournisseur.setText(String.valueOf(string.getIdf()));
        ajoutproduit.setText(String.valueOf(string.getDate_ajout_s()));
        finproduit.setText(String.valueOf(string.getDate_fin_s()));
        prixproduit.setText(String.valueOf(string.getPrix_s()));
        quantiteproduit.setText(String.valueOf(string.getQt_s()));
     
}
    public VBox getBox() {
        return VBox;
    }
}
