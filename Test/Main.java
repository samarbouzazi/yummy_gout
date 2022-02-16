/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;


import Entities.Fournisseur;
import Entities.Stock;
import Services.SFournisseur;
import Services.SStock;
import Tools.MaCon;

/**
 *
 * @author DELL PRCISION 3551
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MaCon mc = MaCon.getInstance();
        
        /*FOUNISSEEEEEUR*/
        
        //Fournisseur f=new Fournisseur("SAMAR", "SAMAR", "SAMAR", 12345678, "SAMAR");
        Fournisseur f=new Fournisseur(3,"SAMAR", "SAMAR", "SAMAR", 12345678, "SAMAR");
        
        SFournisseur sf=new SFournisseur();
        //sf.ajouter(f);
        
        //sf.supprimer(f);
        //sf.update(f);
        //sf.supprimer(f);
        //System.out.println(sf.afficher());
        
        
        /*STOOOOCK*/
        
        Stock st=new Stock("yaourt","13-06-1010","15-06-1000", 13.5f, 3);
        //Stock st1=new Stock(3,"yaourt1111111111","13-06-1010","15-06-1000", 13.5555555555f, 3);
        //SStock ss=new SStock();
        
        //ss.ajouter(st1);
        //ss.deleteEvent(st1);
        //ss.update(st1);
        //System.out.println(ss.afficher());
        
    }
    
    
    
    
    
}
