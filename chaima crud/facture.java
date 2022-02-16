/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author chaim
 */
public class facture {
    private int Idfac; 
    private int Idplat ;
    private int Idclient ;

    public facture(int Idfac, int Idplat, int Idclient) {
        this.Idfac = Idfac;
        this.Idplat = Idplat;
        this.Idclient = Idclient;
    }
     public facture()
          {
    
             }
    public facture(int Idplat, int Idclient) {
        this.Idplat = Idplat;
        this.Idclient = Idclient;
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
