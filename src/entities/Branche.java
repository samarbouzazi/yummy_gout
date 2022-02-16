/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author LENOVO
 */
public class Branche {
    private int Idbranche;
    private String NomBranche;
    private String Contact;
    private String Emplacement;
    private String Heureo;
    private String Heuref;
    private String Imageb;

    public Branche() {
    }

    public Branche(int Idbranche, String NomBranche, String Contact, String Emplacement, String Heureo, String Heuref, String Imageb) {
        this.Idbranche = Idbranche;
        this.NomBranche = NomBranche;
        this.Contact = Contact;
        this.Emplacement = Emplacement;
        this.Heureo = Heureo;
        this.Heuref = Heuref;
        this.Imageb = Imageb;
    }

    public Branche(String NomBranche, String Contact, String Emplacement, String Heureo, String Heuref, String Imageb) {
        this.NomBranche = NomBranche;
        this.Contact = Contact;
        this.Emplacement = Emplacement;
        this.Heureo = Heureo;
        this.Heuref = Heuref;
        this.Imageb = Imageb;
    }

    public Branche(int Idbranche) {
        this.Idbranche = Idbranche;
    }

    public int getIdbranche() {
        return Idbranche;
    }

    public void setIdbranche(int Idbranche) {
        this.Idbranche = Idbranche;
    }

    public String getNomBranche() {
        return NomBranche;
    }

    public void setNomBranche(String NomBranche) {
        this.NomBranche = NomBranche;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public String getEmplacement() {
        return Emplacement;
    }

    public void setEmplacement(String Emplacement) {
        this.Emplacement = Emplacement;
    }

    public String getHeureo() {
        return Heureo;
    }

    public void setHeureo(String Heureo) {
        this.Heureo = Heureo;
    }

    public String getHeuref() {
        return Heuref;
    }

    public void setHeuref(String Heuref) {
        this.Heuref = Heuref;
    }

    public String getImageb() {
        return Imageb;
    }

    public void setImageb(String Imageb) {
        this.Imageb = Imageb;
    }

    @Override
    public String toString() {
        return "Branche{" + "Idbranche=" + Idbranche + ", NomBranche=" + NomBranche + ", Contact=" + Contact + ", Emplacement=" + Emplacement + ", Heureo=" + Heureo + ", Heuref=" + Heuref + ", Imageb=" + Imageb + '}';
    }
    
    
}
