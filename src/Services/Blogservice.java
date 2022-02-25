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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MaConnexion;

/**
 *
 * @author rezki
 */
public class Blogservice  implements IService<Blog> {
 Connection cnx= MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(Blog b) {
          try {if(Controlechar(b)){
              if(existe(b)== 0){
            String sql;
              sql = "insert into Blog(titreblog,descblog) values('"+b.getTitreblog()+"','"+b.getDescblog()+"')";
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println(" Blog Ajoutée");}
              else{System.out.println("le titre de blog est déja existe");}
          }
              else{System.out.println("le titre de blog ne peut contenir que des caractéres");}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
public int existe(Blog b) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from Blog WHERE titreblog = '" + b.getTitreblog().toLowerCase() +"'");
        int size = 0;
        rs.next();
        size=rs.getInt(1);
        return size;
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
                b.setIdblog(bs.getInt("Idblog"));
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

   public ObservableList<Blog> TriS() {
     
  
        
          String req = "SELECT idblog,titreblog,descblog FROM blog order by titreblog DESC";

        ObservableList<Blog> list=FXCollections.observableArrayList();
        try {
           Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(req);
           while(rst.next()){
               
             Blog f=new Blog(rst.getInt(1),rst.getString(2),rst.getString(3));
               list.add(f);
           }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
       
     }
   public List<Blog> chercherTitreBlog(String titre){
          String sql="SELECT * FROM Blog WHERE (titreblog LIKE ? or descblog LIKE ? )";
         //   Connection CNX = MaConnexion.getCNX();
         Connection cnx= MaConnexion.getInstance().getCnx();
            String ch="%"+titre+"%";
            ArrayList<Blog> myList= new ArrayList();
        try {
            
                  Statement ste = cnx.createStatement();
                    
           // PreparedStatement pst = CNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =cnx.prepareStatement(sql);
            stee.setString(1, ch);
            stee.setString(2, ch);
            
            
            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                Blog b = new Blog ();
                b.setIdblog(rs.getInt(1));
                b.setTitreblog (rs.getString(2));
                b.setDescblog(rs.getString(3));
                
               
                myList.add(b);
                System.out.println("titre trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
   public static boolean Controlechar(Blog b) {
		String str = (b.getTitreblog()).toLowerCase();
                if (str.length() == 0)
                    return false;
		char[] charArray = str.toCharArray();
                
		for (int i = 0; i < charArray.length; i++) {
			char ch = charArray[i];
			if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
				return false;
			}
		}
		return true;
	}

}
   

    
    
    
   
    

