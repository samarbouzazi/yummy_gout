/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;


import Entities.Fournisseur;
import Entities.Stock;
import Entities.User;
import Services.SControl;
import Services.SFournisseur;
import Services.SStock;
import Services.SUser;
import Tools.MaCon;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException{
        
            // TODO code application logic here
            MaCon mc = MaCon.getInstance();
            
            //SControl sc=new SControl();
            
            /*FOUNISSEEEEEUR*/
            
            Fournisseur f=new Fournisseur(14,"aaa22", "S", "SAaa", 1234555, "aywaa");
            Fournisseur f1=new Fournisseur(5);
            SFournisseur sf=new SFournisseur();
            
            //sf.ajouter(f);
            
            //sf.supprimer(f);
            //sf.update(f);
            //sf.deletef(10);
            //System.out.println(sf.afficher());
            //System.out.println(sf.RECHERCHE(10));
            //System.out.println(sf.getnbrefr());
            
            /*STOOOOCK*/
            Date d1= Date.valueOf("2022-01-3");
            Date d2= Date.valueOf("2022-01-3");
            Stock st2=new Stock(14,"samaraaa",d2,d2, 22, 4);
            Stock st1=new Stock(13);
            //Stock st1=new Stock(3,"yaourt1111111111","13-06-1010","15-06-1000", 13.5555555555f, 3);
            SStock ss=new SStock();
            
            
            //ss.ajouter(st2);
            //st1.setIdf(15);
            
            //ss.update(st1);
            //ss.delete(21);
            //ss.update(st2);
            //ss.delete(15);
            //System.out.println(ss.afficher());
            //System.out.println(ss.RECHERCHE(7));
            //System.out.println(ss.TriS());
            //System.out.println(ss.getMax());
        //**********USER*********
        User u=new User(2,"BOUZEZI", "SAMAR", 12345678, 12345678, "samar.esp@gmail.com", "azzaaa", "admin");
        SUser su=new SUser();
        //su.ajouter(u);
        //u.setNomu("abidi");
        //su.update(u);
        //su.deletef(2);
        //System.out.println(su.afficher());
        //System.out.println(su.chercherav("ama"));
      //  System.out.println(su.getnbreUSR());
    
    
    String file_name="C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\yummy_gout - Crud final\\yummy_gout.pdf";
        Document document =new Document();
        try {
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
           
             Connection cnx= MaCon.getInstance().getCnx();
            PreparedStatement ste = null;
            ResultSet rst=null;
            String query="select * from fournisseurs";
           // ste = cnx.createStatement(query);  
            ste = cnx.prepareStatement(query);
            rst =ste.executeQuery(query);
            while(rst.next()){
                Paragraph para=new Paragraph(rst.getString("idf")+" "+rst.getString("nomf")+" "+rst.getString("prenomf")+" "+rst.getString("catf")+" "+rst.getString("telf")+" "+rst.getString("addf"));
                
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
            document.add(Image.getInstance("C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\yummy_gout - Crud final\\logo.png"));
            
            
            
  
            
            document.close();
            System.out.println("finished");
        } catch (Exception e) {
            System.err.println(e);
           
        }
    
    }
     }
    
    
    

