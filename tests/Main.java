/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.avis;
import entities.Blog;
import iservice.AvisService;
import iservice.Blogservice;
import tools.MaConnexion;

/**
 *
 * @author rezki
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MaConnexion mc = MaConnexion.getInstance();
        //Blog b= new Blog(2);
         Blog b= new Blog(3,"ben","Samar");
         Blogservice bs = new Blogservice();
         // bs.ajouter(b);
          //bs.supprimer(b);
          bs.modifier(b);
          System.out.println(bs.afficher());
       // avis a = new avis (13,5,1,20);
       // AvisService av = new AvisService();
          //av.ajouter(a);
          //av.supprimer(a);
           //av.modifier(a);
        //System.out.println(av.afficher());
            
    }
    
}
