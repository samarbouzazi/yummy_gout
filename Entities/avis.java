/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author rezki
 */

public class avis {
    private int idavis;
    private int Id_client ;
    private Date dateavis;
    private int like ;
    private int Deslike ;
   private String descriptionavis;

    public avis()  { 
    }

    public avis(int idavis, Date dateavis, int like, int Deslike, String descriptionavis) {
        this.idavis = idavis;
        this.dateavis = dateavis;
        this.like = like;
        this.Deslike = Deslike;
        this.descriptionavis = descriptionavis;
    }

    public avis(int idavis, int Id_client, Date dateavis, int like, int Deslike, String descriptionavis) {
        this.idavis = idavis;
        this.Id_client  = Id_client;
        this.dateavis = dateavis;
        this.like = like;
        this.Deslike = Deslike;
        this.descriptionavis = descriptionavis;
    }

    public avis(int Id_client, int like, int Deslike, String descriptionavis) {
        this.Id_client = Id_client;
        this.like = like;
        this.Deslike = Deslike;
        this.descriptionavis = descriptionavis;
    }

  

    public int getIdavis() {
        return idavis;
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

    public String getDescriptionavis() {
        return descriptionavis;
    }

    public void setIdavis(int idavis) {
        this.idavis = idavis;
    }

    public int getId_client() {
        return Id_client;
    }

    public void setId_client(int Id_client) {
        this.Id_client = Id_client;
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

    public void setDescriptionavis(String descriptionavis) {
        this.descriptionavis = descriptionavis;
    }

    @Override
    public String toString() {
        return "avis{" + "idavis=" + idavis + ", idclient=" + Id_client + ", dateavis=" + dateavis + ", like=" + like + ", Deslike=" + Deslike + ", descriptionavis=" + descriptionavis + '}';
    }

    
    
}

    
