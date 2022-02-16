/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yummygout;

import Services.BrancheService;
import Services.LivraisonService;
import entities.Branche;
import entities.Livraison;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import tools.MaConnexion;
/**
 *
 * @author LENOVO
 */
public class Yummygout {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        MaConnexion mc = MaConnexion.getInstance();
       //Branche p1 = new Branche(4, "hhhhh", "5333333", "bizerte", "22h10", "55555", "url1");
       Branche p3 = new Branche( 6,"yummy gout", "533333", "rariana", "22h10", "23h10", "url");
       //Branche p2 =new Branche(3);
        BrancheService bs = new BrancheService();
        bs.modifier(p3);
        //bs.ajouter(p3);
        //bs.supprimer(p2);
        System.out.println(bs.afficher());        
        //LivraisonService Ls= new LivraisonService();
        //Livraison L= new Livraison(10 , 9);
        //Livraison L2= new Livraison(12,9,9);
        //Ls.ajouter(L);
        //Livraison L1= new Livraison (13);
        //Ls.supprimer(L1);
        //Ls.modifier(L2);
        //Livraison L4= new Livraison(11,0,1);
        //Ls.ajouter(L4);
        //System.out.println(Ls.afficher());
        
    }
    
}
