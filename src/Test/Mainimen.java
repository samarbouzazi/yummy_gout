/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.avis;
import entities.Blog;
import iservice.AvisService;
import iservice.Blogservice;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import tools.MaConnexion;

/**
 *
 * @author rezki
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MaConnexion mc = MaConnexion.getInstance();
     // Blog b= new Blog("77778art","azer");
      //Blog b= new Blog(6);
   //   Blogservice bs = new Blogservice();
       //  bs.ajouter(b);
       //  bs.supprimer(b);
         // bs.modifier(b);
     //   System.out.println(bs.afficher());
      // System.out.println(bs.chercherTitreBlog("r"));
      //avis a = new avis (13,5,15,15);
      //AvisService av = new AvisService();
         //av.ajouter(a);
          //av.supprimer(a);
        //   av.modifier(a);
        //System.out.println(av.afficher());
       // System.out.println(bs.TriS());
        //System.out.println(av.getMax());
            
        
        
              String file_name="C:\\Users\\rezki\\OneDrive\\Bureau\\java projects\\yummy_gout\\yummy_gout.pdf";
        com.itextpdf.text.Document document =new com.itextpdf.text.Document();
        try {
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
           
             Connection cnx= MaConnexion.getInstance().getCnx();
            PreparedStatement ste = null;
            ResultSet rst=null;
            String query="select * from blog";
           // ste = cnx.createStatement(query);  
            ste = cnx.prepareStatement(query);
            rst =ste.executeQuery(query);
            while(rst.next()){
                Paragraph para=new Paragraph(rst.getString("Idblog")+" "+rst.getString("Titreblog")+" "+rst.getString("Descblog"));
                
            document.add(para);
            }
            
            ///addparaghrape
            //Paragraph para=new Paragraph("mohh ben slimene");
            
            //document.add(para);
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            
            
            //addimage
            document.add(Image.getInstance("C:\\Users\\rezki\\OneDrive\\Documents\\NetBeansProjects\\yummy_gout\\logo.png"));
            
            
            
  
            
            document.close();
            System.out.println("finished");
        } catch (Exception e) {
            System.err.println(e);
           
        }
            
    }
            
    }