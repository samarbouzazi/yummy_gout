/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Stock;
import javafx.scene.control.ListCell;

/**
 *
 * @author DELL PRCISION 3551
 */
public class ListViewStock extends ListCell<Stock> {
    
    @Override
     public void updateItem(Stock e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            StockItemController data = new StockItemController();
            data.setInfo(e);
            setGraphic(data.getBox());
        }
    }
    
}
