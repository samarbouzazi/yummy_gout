/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author DELL PRCISION 3551
 */
public class Fournisseur {
     private int idf;
    private String nomf;
    private String prenomf;
    private String catf;
    private int telf;
    private String addf;

    public Fournisseur() {
    }

    public Fournisseur(int idf, String nomf, String prenomf, String catf, int telf, String addf) {
        this.idf = idf;
        this.nomf = nomf;
        this.prenomf = prenomf;
        this.catf = catf;
        this.telf = telf;
        this.addf = addf;
    }

    public Fournisseur(String nomf, String prenomf, String catf, int telf, String addf) {
        this.nomf = nomf;
        this.prenomf = prenomf;
        this.catf = catf;
        this.telf = telf;
        this.addf = addf;
    }

    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }

    public String getNomf() {
        return nomf;
    }

    public void setNomf(String nomf) {
        this.nomf = nomf;
    }

    public String getPrenomf() {
        return prenomf;
    }

    public void setPrenomf(String prenomf) {
        this.prenomf = prenomf;
    }

    public String getCatf() {
        return catf;
    }

    public void setCatf(String catf) {
        this.catf = catf;
    }

    public int getTelf() {
        return telf;
    }

    public void setTelf(int telf) {
        this.telf = telf;
    }

    public String getAddf() {
        return addf;
    }

    public void setAddf(String addf) {
        this.addf = addf;
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "idf=" + idf + ", nomf=" + nomf + ", prenomf=" + prenomf + ", catf=" + catf + ", telf=" + telf + ", addf=" + addf + '}';
    }
    
}
