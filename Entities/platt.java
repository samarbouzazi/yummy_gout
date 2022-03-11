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
public class platt {
    
    private int Idplat;
    private String Descplat;
    private String Nomplat;
    private String image;
    private int idcatt;
    private int prix_plat;
    private int q_plat;
    private int stock;
   private String Nomcat;

    public platt(int Idplat, String Descplat, String Nomplat, String image, String Nomcat, int prix_plat, int q_plat, int stock) {
        this.Idplat = Idplat;
        this.Descplat = Descplat;
        this.Nomplat = Nomplat;
        this.image = image;
        this.Nomcat = Nomcat;
        this.prix_plat = prix_plat;
        this.q_plat = q_plat;
        this.stock = stock;
        
    }

    public String getNomcat() {
        return Nomcat;
    }

    public void setNomcat(String Nomcat) {
        this.Nomcat = Nomcat;
    }
    
    public platt(){
        
    }
 public platt(int Idplat) {
        this.Idplat = Idplat;
    }
    public platt(int Idplat, String Descplat, String Nomplat, String image, int idcatt, int prix_plat, int q_plat ,int stock ) {
        this.Idplat = Idplat;
        this.Descplat = Descplat;
        this.Nomplat = Nomplat;
        this.image = image;
        this.idcatt = idcatt;
        this.prix_plat = prix_plat;
        this.q_plat = q_plat;
        this.stock =stock;
    }

    public platt(String Descplat, String Nomplat, String image, int idcatt, int prix_plat, int q_plat, int stock) {
        this.Descplat = Descplat;
        this.Nomplat = Nomplat;
        this.image = image;
        this.idcatt = idcatt;
        this.prix_plat = prix_plat;
        this.q_plat = q_plat;
        this.stock=stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdplat() {
        return Idplat;
    }

    public String getDescplat() {
        return Descplat;
    }

    public String getNomplat() {
        return Nomplat;
    }

    public String getImage() {
        return image;
    }

    public int getIdcatt() {
        return idcatt;
    }

    public int getPrix_plat() {
        return prix_plat;
    }

    public int getQ_plat() {
        return q_plat;
    }

    public void setIdplat(int Idplat) {
        this.Idplat = Idplat;
    }

    public void setDescplat(String Descplat) {
        this.Descplat = Descplat;
    }

    public platt(int Idplat, String Descplat, String Nomplat, String image, int prix_plat, int q_plat, int stock) {
        this.Idplat = Idplat;
        this.Descplat = Descplat;
        this.Nomplat = Nomplat;
        this.image = image;
        this.prix_plat = prix_plat;
        this.q_plat = q_plat;
        this.stock = stock;
    }
   

    public void setNomplat(String Nomplat) {
        this.Nomplat = Nomplat;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIdcatt(int idcatt) {
        this.idcatt = idcatt;
    }

    public void setPrix_plat(int prix_plat) {
        this.prix_plat = prix_plat;
    }

    public void setQ_plat(int q_plat) {
        this.q_plat = q_plat;
    }

    @Override
    public String toString() {
        return "platt{" + "Idplat=" + Idplat + ", Descplat=" + Descplat + ", Nomplat=" + Nomplat + ", image=" + image + ", idcatt=" + idcatt + ", prix_plat=" + prix_plat + ", q_plat=" + q_plat + ", stock=" + stock + '}';
    }

    
   
   
  
    
}
