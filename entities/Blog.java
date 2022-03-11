/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author rezki
 */
public class Blog {
    private int idblog ; 
    private String titreblog ;
    private String descblog ; 
    private String image;
    
    
    
    
 

    public Blog() {
    }

    public Blog(String titreblog, String descblog,String image) {
        this.titreblog = titreblog;
        this.descblog = descblog;
        this.image=image;
    }  
    public Blog(int idblog,String titreblog, String descblog,String image) {
        this.idblog=idblog;
        this.titreblog = titreblog;
        this.descblog = descblog;
        this.image=image;
    } 
     public Blog(int idblog) {
        this.idblog = idblog;
        this.image=image;
        
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        return "Blog{" + "idblog=" + idblog + ", titreblog=" + titreblog + ", descblog=" + descblog +",image=" + image+ '}';
    }
    
    
    
    
}

