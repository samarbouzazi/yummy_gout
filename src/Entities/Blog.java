/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author rezki
 */
public class Blog {
    private int idblog ; 
    private String titreblog ;
    private String descblog ; 

    public Blog() {
    }

    public Blog(String titreblog, String descblog) {
        this.titreblog = titreblog;
        this.descblog = descblog;
    }  
    public Blog(int idblog,String titreblog, String descblog) {
        this.idblog=idblog;
        this.titreblog = titreblog;
        this.descblog = descblog;
    } 
     public Blog(int idblog) {
        this.idblog = idblog;
        
    }
    

    
    

    public int getIdblog() {
        return idblog;
    }

    public void setIdblog(int idblog) {
        this.idblog = idblog;
    }

    public String getTitreblog() {
        return titreblog;
    }

    public void setTitreblog(String titreblog) {
        this.titreblog = titreblog;
    }

    public String getDescblog() {
        return descblog;
    }

    public void setDescblog(String descblog) {
        this.descblog = descblog;
    }

    @Override
    public String toString() {
        return "Blog{" + "idblog=" + idblog + ", titreblog=" + titreblog + ", descblog=" + descblog + '}';
    }
    
    
    
    
}

