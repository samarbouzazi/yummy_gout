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
public class reponse {
    private int idrep ,idrec ; 
    private String reponse ;
    private Date daterep; 

    public reponse() {
    }

    public reponse( int idrec, String reponse, Date daterep) {
        
        this.idrec = idrec;
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

    public reponse(int idrep, int idrec, String reponse, Date daterep) {
        this.idrep = idrep;
        this.idrec = idrec;
        this.reponse = reponse;
        this.daterep = daterep;
    }

    

    public reponse(String reponse, int idrec) {
        this.idrec = idrec;
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

    public int getIdrec() {
        return idrec;
    }

   

   
    public String getReponse() {
        return reponse;
    }

    public void setIdrec(int idrec) {
        this.idrec = idrec;
    }

    
    

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setIdrep(int idrep) {
        this.idrep = idrep;
    }

    @Override
    public String toString() {
        return "reponse{" + "idrep=" + idrep + ",  idrec=" + idrec + ", reponse=" + reponse + ", daterep=" + daterep + '}';
    }

    
    
}
