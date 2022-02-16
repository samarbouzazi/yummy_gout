/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yummy_gout;
import Services.Factureservice;
import Services.Panierservice;
import Services.Platservice;
import Services.categorieservice;
import entities.categorie;
import entities.facture;
import entities.panier;
import entities.plat;
import tools.Maconnexion;

/**
 *
 * @author chaim
 */
public class Yummy_gout {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
          Maconnexion mc = Maconnexion.getInstance();
          //**************categorie**********
        //  categorie c = new categorie("imen", "salah");
        //categorie c= new categorie(8,"mimii", "momo");                            
        //categorie c2 = new categorie("chaima", "salah");
         //categorie c1=new categorie(7,"chhhh","slimene");
    
          //categorieservice cs = new categorieservice();
           //cs.modifier(c1);
          //cs.ajouter(c2);      
          // categorie c= new categorie(6);
          
        // cs.supprimer(c);
          // cs.modifier(c);
       // System.out.println(cs.afficher());;
       //**************plat**********************
       
         Platservice ps = new Platservice();
          //  plat p1=new plat("kosksi","felfel","url",1,7);
            //plat p=new plat(1);
            // ps.ajouter(p1);
             //ps.supprimer(p);
            //plat p2=new plat(2,"makrouna","felfel","url",2,7);
           // ps.modifier(p2);
            // System.out.println(ps.afficher());
             
             //*******************pannier***************
             //Panierservice pas =new Panierservice();
            // panier pa=new panier(2,5);
             //pas.ajouter(pa);
             // System.out.println(pas.afficher());
             //panier pa1=new panier(1);
             //pas.supprimer(pa1);
            // panier pa2=new panier(3,2,7);
             //pas.modifier(pa2);
            //*********************facture******************
           // Factureservice fs =new Factureservice();
          // facture f=new facture(2,4);
            // fs.ajouter(f);
            // facture f1=new facture(1);
             //fs.supprimer(f1);
           // facture f=new facture(2,2,8);
            //fs.modifier(f);
            // System.out.println(fs.afficher());
    
    
    
    }
    
    
    
    
    
    

    
}
