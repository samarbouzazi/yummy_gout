/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author chaim
 */
public class categorie {

     
    private int idcatt;
    private String Nomcat;
    private String Image;
    
     public categorie() {
    }

    public categorie(int idcatt,String Nomcat, String Image) {
        this.idcatt=idcatt;
        this.Nomcat = Nomcat;
        this.Image = Image;
        
    }
     public categorie(String Nomcat, String Image) {
       this.Nomcat = Nomcat;
        this.Image = Image;
        
    }

    public categorie(int idcatt) {
        this.idcatt =idcatt ;
    }
    
    public int getidcatt() {
        return idcatt;
    }

    public void setidcatt(int idcatt) {
        this.idcatt = idcatt;
    }
    
    public String getNomcat() {
        return Nomcat;
    }

    public void setNomcat(String Nomcat) {
        this.Nomcat = Nomcat;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    @Override
    public String toString() {
        return "categorie {" + "idcat=" + idcatt + "," + "Nomcat=" + Nomcat + ", Image=" + Image + '}';
    }

    

    
    
    
   
    



}
