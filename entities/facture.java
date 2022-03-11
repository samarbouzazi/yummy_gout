/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author chaim
 */
public class facture {
    private int Idfac; 
    private int Idplat ;
    private int Idclient ;
    private Date Date_de_facture;

    
     public facture()
          {
    
             }

    public facture(int Idfac, int Idplat, int Idclient, Date Date_de_facture) {
        this.Idfac = Idfac;
        this.Idplat = Idplat;
        this.Idclient = Idclient;
        this.Date_de_facture = Date_de_facture;
    }

    public facture(int Idplat, int Idclient, Date Date_de_facture) {
        this.Idplat = Idplat;
        this.Idclient = Idclient;
        this.Date_de_facture = Date_de_facture;
    }

    public Date getDate_de_facture() {
        return Date_de_facture;
    }

    public void setDate_de_facture(Date Date_de_facture) {
        this.Date_de_facture = Date_de_facture;
    }
    

    public facture(int Idfac) {
        this.Idfac = Idfac;
    }

    public int getIdfac() {
        return Idfac;
    }

    public int getIdplat() {
        return Idplat;
    }

    public int getIdclient() {
        return Idclient;
    }

    public void setIdfac(int Idfac) {
        this.Idfac = Idfac;
    }

    public void setIdplat(int Idplat) {
        this.Idplat = Idplat;
    }

    public void setIdclient(int Idclient) {
        this.Idclient = Idclient;
    }

    @Override
    public String toString() {
        return "facture{" + "Idfac=" + Idfac + ", Idplat=" + Idplat + ", Idclient=" + Idclient + '}';
    }
    
    
}
