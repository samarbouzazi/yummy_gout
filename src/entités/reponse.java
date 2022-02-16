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
    private int idrep,idchef,idp ; 
    private String reponse ;
    private Date daterep; 

    public reponse() {
    }

    public reponse(int idchef, int idp, String reponse, Date daterep) {
        this.idchef = idchef;
        this.idp = idp;
        this.reponse = reponse;
        this.daterep = daterep;
    }

    public reponse(int idrep) {
        this.idrep = idrep;
    }

    public reponse(int idchef, int idp, String reponse) {
        this.idchef = idchef;
        this.idp = idp;
        this.reponse = reponse;
    }

    public reponse(int idrep, String reponse) {
        this.idrep = idrep;
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

    public int getIdchef() {
        return idchef;
    }

    public int getIdp() {
        return idp;
    }

    public String getReponse() {
        return reponse;
    }

    public void setIdchef(int idchef) {
        this.idchef = idchef;
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
        return "reponse{" + "idrep=" + idrep + ", idchef=" + idchef + ", idp=" + idp + ", reponse=" + reponse + ", daterep=" + daterep + '}';
    }

    
    
}
