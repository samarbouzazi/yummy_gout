/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;


/**
 *
 * @author LENOVO
 */
public class Livraison {
    private int id_Livraison; 
    private Date date;
     private int id_facture;
    private int id_Livreur;

    public Livraison(int id_Livraison) {
        this.id_Livraison = id_Livraison;
    }
   

    public Livraison( int id_facture, int id_Livreur) {
        this.id_facture = id_facture;
        this.id_Livreur = id_Livreur;
    }

    public Livraison(Date date, int id_Livreur, int id_facture) {
        this.date = date;
        this.id_Livreur = id_Livreur;
        this.id_facture = id_facture;
    }

    public Livraison(int id_Livraison, int id_facture, int id_Livreur) {
        this.id_Livraison = id_Livraison;
        this.id_facture = id_facture;
        this.id_Livreur = id_Livreur;
    }

    public Livraison(int id_Livraison, Date date, int id_Livreur, int id_facture) {
        this.id_Livraison = id_Livraison;
        this.date = date;
        this.id_Livreur = id_Livreur;
        this.id_facture = id_facture;
    }

    public int getId_Livraison() {
        return id_Livraison;
    }

    public void setId_Livraison(int id_Livraison) {
        this.id_Livraison = id_Livraison;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_Livreur() {
        return id_Livreur;
    }

    public void setId_Livreur(int id_Livreur) {
        this.id_Livreur = id_Livreur;
    }

    public int getId_facture() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id_Livraison=" + id_Livraison + ", date=" + date + ", id_facture=" + id_facture + ", id_Livreur=" + id_Livreur + '}';
    }
    public Livraison() {
    }
    //utilisé dans Livraison service
}
