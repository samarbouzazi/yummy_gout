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

       public panier(){
           
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
        return "panier{" + "Idpanier=" + Idpanier + ", Idplat=" + Idplat + ", quantite=" + quantite + '}';
    }

       
    
    
   
    
    
    
    
    
    
    
    
    
    
    
}
