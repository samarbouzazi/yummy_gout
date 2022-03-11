/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.sql.Date;


/**
 *
 * @author HP
 */
public class reclamationn {
    private int idrec,idp ; 
    private String description ;
    private Date daterec;
    private String email;

    public String getEmail() {
        return email;
    }

    public reclamationn(int idrec,  String description, Date daterec,String email) {
        this.idrec = idrec;
        
        this.description = description;
        this.daterec = daterec;
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

   
    public reclamationn(int idrec, int idp, String description, Date daterec) {
        this.idrec = idrec;
        this.idp = idp;
        this.description = description;
        this.daterec = daterec;
    }

    public reclamationn(int idrec, String description) {
        this.idrec = idrec;
        this.description = description;
    }
    public reclamationn(String description,int idp) {
        this.idp = idp;
        this.description = description;
    }

  
    public reclamationn(int idrec) {
        this.idrec = idrec;
    }
    
   public reclamationn() {
    }

    public reclamationn(String description, String idp) {

    }

    
    public Date getDaterec() {
        return daterec;
    }

    public void setDaterec(Date daterec) {
        this.daterec = daterec;
    }

    
    public int getIdrec() {
        return idrec;
    }
    public int getIdp() {
        return idp;
    }

    public String getDescription() {
        return description;
    }

    public void setIdrec(int idrec) {
        this.idrec = idrec;
    }

   

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "reclamationn{" + "idrec=" + idrec + ", idp=" + idp + ", description=" + description + ", daterec=" + daterec + '}';
    }

   

   

   
}
