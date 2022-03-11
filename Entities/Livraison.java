/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;


/**
 *
 * @author LENOVO
 */
public class Livraison {


    private int id_Livraison; 
    private Date date;
    private String Etat;
    private String Num_facture;
    private String Nom_livreur;

    private String Prenom_livreur;
     private int id_panier;
    private int id_Livreur;
    private String Adresse_livraison;
    private int Idplat;
    private int Idclient;
    private String Nom_client;
    private String Prenom_client;
    private int telclient;

    public int getTelclient() {
        return telclient;
    }

    public Livraison(int id_Livraison, int id_panier, int id_Livreur) {
        this.id_Livraison = id_Livraison;
        this.id_panier = id_panier;
        this.id_Livreur = id_Livreur;
    }
    public void setTelclient(int telclient) {
        this.telclient = telclient;
    }

    public Livraison(int id_Livraison, Date date, String Etat, String Num_facture, String Nom_livreur, String Prenom_livreur, String Adresse_livraison, int Idplat, String Nom_client, String Prenom_client, int telclient) {
        this.id_Livraison = id_Livraison;
        this.date = date;
        this.Etat = Etat;
        this.Num_facture = Num_facture;
        this.Nom_livreur = Nom_livreur;
        this.Prenom_livreur = Prenom_livreur;
        this.Adresse_livraison = Adresse_livraison;
        this.Idplat = Idplat;
        this.Nom_client = Nom_client;
        this.Prenom_client = Prenom_client;
        this.telclient = telclient;
    }
    public Livraison(int id_Livraison, Date date, String Etat, String Num_facture, String Nom_livreur, String Prenom_livreur, int id_panier, int id_Livreur, String Adresse_livraison, int Idplat, int Idclient, String Nom_client, String Prenom_client) {
        this.id_Livraison = id_Livraison;
        this.date = date;
        this.Etat = Etat;
        this.Num_facture = Num_facture;
        this.Nom_livreur = Nom_livreur;
        this.Prenom_livreur = Prenom_livreur;
        this.id_panier = id_panier;
        this.id_Livreur = id_Livreur;
        this.Adresse_livraison = Adresse_livraison;
        this.Idplat = Idplat;
        this.Idclient = Idclient;
        this.Nom_client = Nom_client;
        this.Prenom_client = Prenom_client;
    }

    public Livraison(int id_panier) {
        this.id_panier = id_panier;
    }

    public Livraison(int id_panier, int id_Livreur) {
        this.id_panier = id_panier;
        this.id_Livreur = id_Livreur;
    }

    public Livraison() {
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

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public String getNum_facture() {
        return Num_facture;
    }

    public void setNum_facture(String Num_facture) {
        this.Num_facture = Num_facture;
    }


    public String getNom_livreur() {
        return Nom_livreur;
    }

    public void setNom_livreur(String Nom_livreur) {
        this.Nom_livreur = Nom_livreur;
    }

    public String getPrenom_livreur() {
        return Prenom_livreur;
    }

    public void setPrenom_livreur(String Prenom_livreur) {
        this.Prenom_livreur = Prenom_livreur;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_Livreur() {
        return id_Livreur;
    }

    public void setId_Livreur(int id_Livreur) {
        this.id_Livreur = id_Livreur;
    }

    public String getAdresse_livraison() {
        return Adresse_livraison;
    }

    public void setAdresse_livraison(String Adresse_livraison) {
        this.Adresse_livraison = Adresse_livraison;
    }

    public int getIdplat() {
        return Idplat;
    }

    public void setIdplat(int Idplat) {
        this.Idplat = Idplat;
    }

    public int getIdclient() {
        return Idclient;
    }

    public void setIdclient(int Idclient) {
        this.Idclient = Idclient;
    }

    public String getNom_client() {
        return Nom_client;
    }

    public void setNom_client(String Nom_client) {
        this.Nom_client = Nom_client;
    }

    public String getPrenom_client() {
        return Prenom_client;
    }

    public void setPrenom_client(String Prenom_client) {
        this.Prenom_client = Prenom_client;
    }

    public Livraison(String Num_facture) {
        this.Num_facture = Num_facture;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id_Livraison=" + id_Livraison + ", date=" + date + ", Etat=" + Etat + ", Num_facture=" + Num_facture + ", Nom_livreur=" + Nom_livreur + ", Prenom_livreur=" + Prenom_livreur + ", id_panier=" + id_panier + ", id_Livreur=" + id_Livreur + ", Adresse_livraison=" + Adresse_livraison + ", Idplat=" + Idplat + ", Idclient=" + Idclient + ", Nom_client=" + Nom_client + ", Prenom_client=" + Prenom_client + '}';
    }

   
}
