/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yummygout;

import Services.BrancheService;
import Services.LivraisonService;
import Services.LivraisonService1;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Branche;
import entities.Livraison;
import entities.Livraison1;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import tools.MaConnexion;
/**
 *
 * @author LENOVO
 */
public class Yummygout {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        MaConnexion mc = MaConnexion.getInstance();
       //Branche p1 = new Branche(4, "hhhhh", "5333333", "bizerte", "22h10", "55555", "url1");
       //Branche p3 = new Branche( "gggggg", "28695369", "tozeur", "22:50","23:55", "url");
       //Branche p2 =new Branche(3);
        //BrancheService bs = new BrancheService();
        //bs.modifier(p3);
        //bs.ajouter(p3);       
        //bs.supprimer(p2);
        //bs.chercherav("gg");
        //System.out.println(bs.afficher());        
        LivraisonService Ls= new LivraisonService();
        //Livraison L= new Livraison(9, 1);
        //Livraison L2= new Livraison(15,9,1);
        //Ls.ajouter(L);
        //Livraison L1= new Livraison (15);
        //Ls.supprimer(L1);
        //Ls.modifier(L2);
        //Livraison L4= new Livraison(11,0,1);
        //Ls.ajouter(L4);
        //System.out.println(Ls.afficher());  
        //System.out.println(LS.afficher());
        Date d=new Date(12-12-1999);
        String ch=d.toString();        
        System.out.println(Ls.chercherav(ch));
        //System.out.println(Ls.TriLivraison());
   
    
    
    
   String file_name="C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\yummygout\\yummygout.pdf";
        Document document =new Document();
     try {
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
           
             Connection cnx= MaConnexion.getInstance().getCnx();
            PreparedStatement ste = null;
            ResultSet rst=null;
            String query="select * from livraison";
           // ste = cnx.createStatement(query);  
            ste = cnx.prepareStatement(query);
            rst =ste.executeQuery(query);
            while(rst.next()){
                Paragraph para=new Paragraph(rst.getString("Id_livraison")+" "+rst.getString("date")+" "+rst.getString("Idfac")+" "+rst.getString("Idp"));
                
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
            document.add(Image.getInstance("C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\yummygout\\logoo.png"));
            
            
            
  
            
            document.close();
            System.out.println("finished");
        } catch (Exception e) {
            System.err.println(e);
           
        }
    
    
           
    } 
    
    
}
