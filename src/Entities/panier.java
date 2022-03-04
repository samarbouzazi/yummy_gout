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
public class panier {
    
       private int Idpanier;
       private int Idplat;
       private int quantite;
       private String Nomplat;
        private int prix_plat;
        private int somme;
       
      

       public panier(){
           
       }

    public panier(int Idpanier, int Idplat, int quantite, String Nomplat, int prix_plat,int somme) {
        this.Idpanier = Idpanier;
        this.Idplat = Idplat;
        this.quantite = quantite;
        this.Nomplat = Nomplat;
        this.prix_plat = prix_plat;
        this.somme=somme;
    }

    public void setNomplat(String Nomplat) {
        this.Nomplat = Nomplat;
    }

    public void setPrix_plat(int prix_plat) {
        this.prix_plat = prix_plat;
    }

    public panier(int Idpanier, int quantite, String Nomplat, int prix_plat) {
        this.Idpanier = Idpanier;
        this.quantite = quantite;
        this.Nomplat = Nomplat;
        this.prix_plat = prix_plat;
    }

    public panier(int Idpanier, int quantite, String Nomplat, int prix_plat, int somme) {
        this.Idpanier = Idpanier;
        this.quantite = quantite;
        this.Nomplat = Nomplat;
        this.prix_plat = prix_plat;
        this.somme = somme;
    }

    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }
    

    public String getNomplat() {
        return Nomplat;
    }

    public int getPrix_plat() {
        return prix_plat;
    }
       
    public panier(int Idpanier, int Idplat, int quantite) {
        this.Idpanier = Idpanier;
        this.Idplat = Idplat;
        this.quantite = quantite;
      
    }
    
    
    public panier(int Idplat, int quantite) {
        this.Idplat = Idplat;
        this.quantite = quantite;
       
    }

    public panier(int Idpanier) {
        this.Idpanier = Idpanier;
    }

    


    public int getIdpanier() {
        return Idpanier;
    }

    public int getIdplat() {
        return Idplat;
    }

    public int getQuantite() {
        return quantite;
    }


    public void setIdpanier(int Idpanier) {
        this.Idpanier = Idpanier;
    }

    public void setIdplat(int Idplat) {
        this.Idplat = Idplat;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "panier{" + "Idpanier=" + Idpanier + ", Idplat=" + Idplat + ", quantite=" + quantite + ", Nomplat=" + Nomplat + ", prix_plat=" + prix_plat + '}';
    }
    
     

   
       
    
    
   
    
    
    
    
    
    
    
    
    
    
    
}
