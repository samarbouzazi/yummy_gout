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
public class personnel {
     private int idp,tel,salaire;
    private String nomp,prenom,mail,Specialite ; 

    public personnel( String nomp, String prenom, String mail,int tel, int salaire, String Specialite) {
       
        this.nomp = nomp;
        this.prenom = prenom;
        this.mail = mail;
         this.tel = tel;
        this.salaire = salaire;
        this.Specialite = Specialite;
    }



    public personnel() {
    }


    

    public int getIdp() {
        return idp;
    }

    public int getTel() {
        return tel;
    }

    public int getSalaire() {
        return salaire;
    }

    public String getNomp() {
        return nomp;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getSpecialite() {
        return Specialite;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setSpecialite(String Specialite) {
        this.Specialite = Specialite;
    }

    @Override
    public String toString() {
        return "personnel{" + "idp=" + idp + ", tel=" + tel + ", salaire=" + salaire + ", nomp=" + nomp + ", prenom=" + prenom + ", mail=" + mail + ", Specialite=" + Specialite + '}';
    }
    
    
}
