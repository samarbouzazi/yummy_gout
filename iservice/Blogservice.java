/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice;

import entities.Blog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

/**
 *
 * @author rezki
 */
public class Blogservice  implements IService<Blog> {
 Connection cnx= MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(Blog b) {
          try {
            String sql="insert into Blog(titreblog,descblog) values('"+b.getTitreblog()+"','"+b.getDescblog()+"')";
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println(" Blog Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public List<Blog> afficher() {
        
 
        List<Blog> Blogs = new ArrayList<>();
        String sql ="select * from blog";
        try {
            Statement ste= cnx.createStatement();
            ResultSet bs =ste.executeQuery(sql);
            while(bs.next()){
                Blog b = new Blog();
              //  b.setIdblog(bs.getInt("setIdblog"));
                b.setTitreblog(bs.getString("Titreblog"));
                b.setDescblog(bs.getString("Descblog"));
                Blogs.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Blogs;
    }

    @Override
    public void supprimer(Blog b) {
        String sql="delete from blog where Idblog  = '"+b.getIdblog ()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println("Blog supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Blog b) {
        String sql="update blog set  titreblog= ?, descblog= ?  where Idblog='"+b.getIdblog()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);   
            ste.setString(1, b.getTitreblog());
            ste.setString(2, b.getDescblog());          
            ste.executeUpdate();
            System.out.println("Blog Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    }

    
    
   
    

