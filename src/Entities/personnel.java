/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entités;

import java.util.Objects;

/**
 *
 * @author HP
 */
public class personnel {
     private int idp ,salaire ,nbrheure;
    private String Specialite ; 

    public personnel(int salaire, int nbrheure,String Specialite) {
        this.salaire = salaire;
        this.nbrheure = nbrheure;
        this.Specialite = Specialite;
    }

    public personnel(int idp, int salaire, int nbrheure, String Specialite) {
        this.idp = idp;
        this.salaire = salaire;
        this.nbrheure = nbrheure;
        this.Specialite = Specialite;
    }


    public personnel() {
    }

    public personnel(int idp) {
        this.idp = idp;
    }

    public int getIdp() {
        return idp;
    }

    public int getNbrheure() {
        return nbrheure;
    }
    public int getSalaire() {
        return salaire;
    }

   
    public String getSpecialite() {
        return Specialite;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public void setNbrheure(int nbrheure) {
        this.nbrheure = nbrheure;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    
    public void setSpecialite(String Specialite) {
        this.Specialite = Specialite;
    }

    @Override
    public String toString() {
        return "personnel{" + "idp=" + idp + ", salaire=" + salaire + ", nbrheure=" + nbrheure + ",Specialite=" + Specialite + '}';
    }

    
    
}
