/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author rezki
 */

public class avis {
    private int idavis;
    private int idclient;
    private Date dateavis;
    private int like ;
    private int Deslike ;

    public avis()  { 
    }

    public avis(int idclient, int like, int Deslike) {
        this.idclient = idclient;
       
        this.like = like;
        this.Deslike = Deslike;
    }
    public avis(int idavis ) { 
        this.idavis = idavis;
        
    }

    public avis(int idavis, int idclient, int like, int Deslike) {
        this.idavis = idavis;
        this.idclient = idclient;
        this.like = like;
        this.Deslike = Deslike;
    }
   
    public avis(int idavis, int idclient, Date dateavis, int like, int Deslike) {
        this.idavis = idavis;
        this.idclient = idclient;
        this.dateavis = dateavis;
        this.like = like;
        this.Deslike = Deslike;
    }

   
    public int getIdavis() {
        return idavis;
    }

    public int getIdclient() {
        return idclient;
    }

    public Date getDateavis() {
        return dateavis;
    }

    public int getLike() {
        return like;
    }

    public int getDeslike() {
        return Deslike;
    }

    public void setIdavis(int idavis) {
        this.idavis = idavis;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public void setDateavis(Date dateavis) {
        this.dateavis = dateavis;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setDeslike(int Deslike) {
        this.Deslike = Deslike;
    }

    @Override
    public String toString() {
        return "Blog{" + "idavis=" + idavis + ", idclient=" + idclient + ", dateavis=" + dateavis + ", like=" + like + ", Deslike=" + Deslike + '}';
    }
    
    
}

    
