/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import java.util.Objects;
import java.sql.Date;

/**
 *
 * @author HP
 */
public class personnel {
     int Idp ; 
     String nomp;
     String prenomp;
     String cinp ;
     String telp;
     String email ; 
     int Salaire ; 
     String Specialite ; 
     int nbheure ; 
     Date Date_embauche ; 
     int taux_horaire; 
     float prime ;
     String image ;

    public String getImage() {
        return image;
    }

    public personnel(int Idp, String nomp, String prenomp, String cinp, String telp, String email, int Salaire, String Specialite, int nbheure, Date Date_embauche, int taux_horaire, float prime, String image) {
        this.Idp = Idp;
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.cinp = cinp;
        this.telp = telp;
        this.email = email;
        this.Salaire = Salaire;
        this.Specialite = Specialite;
        this.nbheure = nbheure;
        this.Date_embauche = Date_embauche;
        this.taux_horaire = taux_horaire;
        this.prime = prime;
        this.image = image;
    }

    public personnel(String nomp, String prenomp, String cinp, String telp, String email, int Salaire, String Specialite, int nbheure, Date Date_embauche, int taux_horaire, String image) {
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.cinp = cinp;
        this.telp = telp;
        this.email = email;
        this.Salaire = Salaire;
        this.Specialite = Specialite;
        this.nbheure = nbheure;
        this.Date_embauche = Date_embauche;
        this.taux_horaire = taux_horaire;
        this.image = image;
    }

    public personnel(int Idp, String nomp, String prenomp, String cinp, String telp, String email, int Salaire, String Specialite, int nbheure, Date Date_embauche, int taux_horaire, String image) {
        this.Idp = Idp;
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.cinp = cinp;
        this.telp = telp;
        this.email = email;
        this.Salaire = Salaire;
        this.Specialite = Specialite;
        this.nbheure = nbheure;
        this.Date_embauche = Date_embauche;
        this.taux_horaire = taux_horaire;
        this.image = image;
    }

    public personnel(int Idp, String nomp, String prenomp, String cinp, String telp, String email, int Salaire, String Specialite, int nbheure, Date Date_embauche, String image) {
        this.Idp = Idp;
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.cinp = cinp;
        this.telp = telp;
        this.email = email;
        this.Salaire = Salaire;
        this.Specialite = Specialite;
        this.nbheure = nbheure;
        this.Date_embauche = Date_embauche;
        this.image = image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public personnel(String nomp, String prenomp, int Salaire, int nbheure, int taux_horaire, float prime) {
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.Salaire = Salaire;
        this.nbheure = nbheure;
        this.taux_horaire = taux_horaire;
        this.prime = prime;
    }

    public personnel(int Idp, String nomp, String prenomp, String cinp, String telp, String email, int Salaire, String Specialite, int nbheure, Date Date_embauche) {
        this.Idp = Idp;
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.cinp = cinp;
        this.telp = telp;
        this.email = email;
        this.Salaire = Salaire;
        this.Specialite = Specialite;
        this.nbheure = nbheure;
        this.Date_embauche = Date_embauche;
    }

    public personnel(String nomp, String prenomp, String cinp, String telp, String email, int Salaire, int nbheure, Date Date_embauche) {
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.cinp = cinp;
        this.telp = telp;
        this.email = email;
        this.Salaire = Salaire;
        this.nbheure = nbheure;
        this.Date_embauche = Date_embauche;
    }

    public personnel(String nomp, String prenomp, String cinp, String telp, String email, int Salaire, String Specialite, int nbheure, Date Date_embauche) {
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.cinp = cinp;
        this.telp = telp;
        this.email = email;
        this.Salaire = Salaire;
        this.Specialite = Specialite;
        this.nbheure = nbheure;
        this.Date_embauche = Date_embauche;
    }

    public personnel() {
    }

    public personnel(int Idp, String nomp, String prenomp, String cinp, String telp, String email, int Salaire, String Specialite, int nbheure, Date Date_embauche, int taux_horaire) {
        this.Idp = Idp;
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.cinp = cinp;
        this.telp = telp;
        this.email = email;
        this.Salaire = Salaire;
        this.Specialite = Specialite;
        this.nbheure = nbheure;
        this.Date_embauche = Date_embauche;
        this.taux_horaire = taux_horaire;
    }

    public personnel(String nomp, String prenomp, String cinp, String telp, String email, int Salaire, String Specialite, int nbheure, Date Date_embauche, String image) {
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.cinp = cinp;
        this.telp = telp;
        this.email = email;
        this.Salaire = Salaire;
        this.Specialite = Specialite;
        this.nbheure = nbheure;
        this.Date_embauche = Date_embauche;
        this.image = image;
    }

    public personnel(int Idp, String nomp, String prenomp, String cinp, String telp, String email, int Salaire, String Specialite, int nbheure, Date Date_embauche, int taux_horaire, float prime) {
        this.Idp = Idp;
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.cinp = cinp;
        this.telp = telp;
        this.email = email;
        this.Salaire = Salaire;
        this.Specialite = Specialite;
        this.nbheure = nbheure;
        this.Date_embauche = Date_embauche;
        this.taux_horaire = taux_horaire;
        this.prime = prime;
    }

    public personnel(int Idp, String nomp, String prenomp, int Salaire, int nbheure, int taux_horaire, float prime) {
        this.Idp = Idp;
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.Salaire = Salaire;
        this.nbheure = nbheure;
        this.taux_horaire = taux_horaire;
        this.prime = prime;
    }

    public personnel(int Idp, String nomp, String prenomp, int Salaire, int nbheure, int taux_horaire) {
        this.Idp = Idp;
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.Salaire = Salaire;
        this.nbheure = nbheure;
        this.taux_horaire = taux_horaire;
        
    }
   
    public int getIdp() {
        return Idp;
    }

    public int getTaux_horaire() {
        return taux_horaire;
    }

    public void setTaux_horaire(int taux_horaire) {
        this.taux_horaire = taux_horaire;
    }

    public float getPrime() {
        return prime;
    }

    public void setPrime(float prime) {
        this.prime = prime;
    }

    public String getNomp() {
        return nomp;
    }

    public String getPrenomp() {
        return prenomp;
    }

    public String getCinp() {
        return cinp;
    }

    public String getTelp() {
        return telp;
    }

    public String getEmail() {
        return email;
    }

    public int getSalaire() {
        return Salaire;
    }

    public String getSpecialite() {
        return Specialite;
    }

    public int getNbheure() {
        return nbheure;
    }

    public Date getDate_embauche() {
        return Date_embauche;
    }

    public void setIdp(int Idp) {
        this.Idp = Idp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public void setPrenomp(String prenomp) {
        this.prenomp = prenomp;
    }

    public void setCinp(String cinp) {
        this.cinp = cinp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalaire(int Salaire) {
        this.Salaire = Salaire;
    }

    public void setSpecialite(String Specialite) {
        this.Specialite = Specialite;
    }

    public void setNbheure(int nbheure) {
        this.nbheure = nbheure;
    }

    public void setDate_embauche(Date Date_embauche) {
        this.Date_embauche = Date_embauche;
    }

    @Override
    public String toString() {
        return "personnel{" + "Idp=" + Idp + ", nomp=" + nomp + ", prenomp=" + prenomp + ", cinp=" + cinp + ", telp=" + telp + ", email=" + email + ", Salaire=" + Salaire + ", Specialite=" + Specialite + ", nbheure=" + nbheure + ", Date_embauche=" + Date_embauche + ", taux_horaire=" + taux_horaire + ", prime=" + prime + ", image=" + image + '}';
    }

   
    
  
    
}
