/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entités;

/**
 *
 * @author HP
 */
public class livreur extends personnel{
    private int idliv,idp,dispo;
    private String zonegeo ;

    public livreur(int idliv, int dispo, String zonegeo, String nomp, String prenom, String mail, int tel, int salaire, String Specialite) {
        super(nomp, prenom, mail, tel, salaire, Specialite);
        this.idliv = idliv;
        this.dispo = dispo;
        this.zonegeo = zonegeo;
    }

   
    

  

    

    public int getIdliv() {
        return idliv;
    }

    public int getIdp() {
        return idp;
    }

    public int getDispo() {
        return dispo;
    }

    public String getZonegeo() {
        return zonegeo;
    }

    public void setIdliv(int idliv) {
        this.idliv = idliv;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public void setDispo(int dispo) {
        this.dispo = dispo;
    }

    public void setZonegeo(String zonegeo) {
        this.zonegeo = zonegeo;
    }

    @Override
    public String toString() {
        return "livreur{" + "idliv=" + idliv + ", idp=" + idp + ", dispo=" + dispo + ", zonegeo=" + zonegeo + '}';
    }
    
    
}
