/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author DELL PRCISION 3551
 */
public class Email {
    private int idemail;
    private String destinataire;
    private String objet;
    private String contenue;

    public void setIdemail(int idemail) {
        this.idemail = idemail;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public Email(String destinataire, String objet, String contenue) {
        this.destinataire = destinataire;
        this.objet = objet;
        this.contenue = contenue;
    }

    public Email(int idemail, String destinataire, String objet, String contenue) {
        this.idemail = idemail;
        this.destinataire = destinataire;
        this.objet = objet;
        this.contenue = contenue;
    }

    public Email() {
    }
    

    public int getIdemail() {
        return idemail;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public String getObjet() {
        return objet;
    }

    public String getContenue() {
        return contenue;
    }

    @Override
    public String toString() {
        return "Email{" + "idemail=" + idemail + ", destinataire=" + destinataire + ", objet=" + objet + ", contenue=" + contenue + '}';
    }
    
    
}
