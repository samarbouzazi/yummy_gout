/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Fournisseur;
import Services.SFournisseur;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author DELL PRCISION 3551
 */
public class ListController implements Initializable {

    @FXML
    private AnchorPane listpane;
    @FXML
    private ListView<?> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<Fournisseur> oblist = FXCollections.observableArrayList();
        SFournisseur prod = new SFournisseur();
        List<Fournisseur> listedem = prod.list();
         oblist.addAll(listedem);
//        list.getItems().addAll(oblist);
//         list.setCellFactory(new Callback<ListView<Categoris>, ListCell<Categoris>>() {
//
//            @Override
//            public ListCell<Categoris> call(ListView<Categoris> arg0) {
//                return new ListCell<Categoris>() {
//
//                    @Override
//                    protected void updateItem(Categoris item, boolean bln) {
//                        super.updateItem(item, bln);
//                        if (item != null) {
//
//                            GridPane gridPane = new GridPane();
//                            Label NomProduit = new Label();
//                            ImageView imagecategorie = new ImageView();
//                            AnchorPane content = new AnchorPane();
//                            imagecategorie.setFitWidth(155);
//                            imagecategorie.setPreserveRatio(true);
//                          GridPane.setConstraints(imagecategorie, 0, 0, 1, 3);
//                            GridPane.setValignment(imagecategorie, VPos.TOP);
//                            // 
//                            NomProduit.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em; ");
//                            GridPane.setConstraints(NomProduit, 1, 0);
//                            gridPane.getChildren().setAll(imagecategorie, NomProduit);
//                            AnchorPane.setTopAnchor(gridPane, 0d);
//                            AnchorPane.setLeftAnchor(gridPane, 0d);
//                            AnchorPane.setBottomAnchor(gridPane, 0d);
//                            AnchorPane.setRightAnchor(gridPane, 0d);
//                            content.getChildren().add(gridPane);
//                            NomProduit.setText(item.getLibelle());
//                            Image i = new Image(item.getImage());
//                            imagecategorie.setImage(i);
//                            setText(null);
//                            setGraphic(content);
//                            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//
//                        }
//                    }
//
//                };
//            }
//        });
//         list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Categoris>() {
//            @Override
//            public void changed(ObservableValue<? extends Categoris> observable, Categoris oldValue, Categoris newValue) {
//                id=newValue.getId();
//                nomProd=newValue.getLibelle();
//                image=newValue.getImage();
//	} 
//         });
//        
//        
//    }    
    
}
}