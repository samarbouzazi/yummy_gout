/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entités;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class reponse {
    private int idrep ,idp ; 
    private String reponse ;
    private Date daterep; 

    public reponse() {
    }

    public reponse( int idp, String reponse, Date daterep) {
        
        this.idp = idp;
        this.reponse = reponse;
        this.daterep = daterep;
    }

    public reponse(int idrep) {
        this.idrep = idrep;
    }

    public reponse(int idrep, String reponse) {
        this.idrep = idrep;
        this.reponse = reponse;
    }

    
    public reponse(String reponse) {
        this.reponse = reponse;
    }

    

    public Date getDaterep() {
        return daterep;
    }

    public void setDaterep(Date daterep) {
        this.daterep = daterep;
    }

    

    public int getIdrep() {
        return idrep;
    }

   

    public int getIdp() {
        return idp;
    }

    public String getReponse() {
        return reponse;
    }

    
    public void setIdp(int idp) {
        this.idp = idp;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setIdrep(int idrep) {
        this.idrep = idrep;
    }

    @Override
    public String toString() {
        return "reponse{" + "idrep=" + idrep + ",  idp=" + idp + ", reponse=" + reponse + ", daterep=" + daterep + '}';
    }

    
    
}
