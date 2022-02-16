/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entités.personnel;
import entités.reclamationn;
import entités.reponse;
import services.PersonnelService;
import services.reclamationService;
import services.reponseService;
import tools.MaConnexion;

/**
 *
 * @author HP
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MaConnexion mc = MaConnexion.getInstance();
       //personnel p = new personnel(5,"chaima", "benslimen","nnnnnnnnnhs",99896566, 2656,"eihdiehd");
         //personnel p1 = new personnel("Ben ali", "ahmed","hahhahaha",25866566,2656,"eihdiehd");
          //PersonnelService ps = new PersonnelService();
          //ps.ajouter(p1);
          //ps.modifier(p);
          //ps.supprimer(p);
         // System.out.println(ps.afficher());
          //reclamationn r = new reclamationn(1,2,"amaaaaaaaaaaaaaaaa");
          //reclamationService rs = new reclamationService();
          //rs.ajouter(r);
          //rs.supprimer(r);
          //reclamationn rm = new reclamationn(12,"amaaaaaaaaaaaaaaaa");

          //rs.modifier(rm);
          //System.out.println(rs.afficher());
          reponseService rp = new reponseService();
          //reponse r = new reponse(1,2,"heeeloooooo");
           //reponse r = new reponse(1);
           reponse r = new reponse(5,"xxxxxxxxxxxxxxxx");
          //rp.ajouter(r);
          //rp.supprimer(r);
          rp.modifier(r);
          System.out.println(rp.afficher());

    }
    
}
