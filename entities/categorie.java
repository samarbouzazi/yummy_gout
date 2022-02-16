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
public class categorie {

     
    private int idcat;
    private String Nomcat;
    private String Image;
    
     public categorie() {
    }

    public categorie(int idcat,String Nomcat, String Image) {
        this.idcat=idcat;
        this.Nomcat = Nomcat;
        this.Image = Image;
        
    }
     public categorie(String Nomcat, String Image) {
       this.Nomcat = Nomcat;
        this.Image = Image;
        
    }

    public categorie(int idcat) {
        this.idcat =idcat ;
    }
    
    public int getidcat() {
        return idcat;
    }

    public void setidcat(int idcat) {
        this.idcat = idcat;
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
        return "categorie {" + "idcat=" + idcat + "," + "Nomcat=" + Nomcat + ", Image=" + Image + '}';
    }
    
    
   
    



}
