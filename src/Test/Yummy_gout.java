/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yummy_gout;
import Services.Factureservice;
import Services.Panierservice;
import Services.Platservice;
import Services.categorieservice;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import static com.itextpdf.text.pdf.PdfName.C;
import com.itextpdf.text.pdf.PdfWriter;
import entities.categorie;
import entities.facture;
import entities.panier;
import entities.platt;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import tools.Maconnexion;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author chaim
 */
public class Yummy_gout {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
          Maconnexion mc = Maconnexion.getInstance();
     //     Date d1= Date.valueOf("12-10-2000");
          //**************categorie**********
         // categorie c = new categorie("ali", "pppp");
    //  categorie c= new categorie(6,"trtrt", "ppp");                            
       //categorie c2 = new categorie("lluuii", "mohamedd",);
        //categorie c1=new categorie(3,"chaima","slimene");
    
       //  categorieservice cs = new categorieservice();
       //   cs.modifier(c);
         //cs.ajouter(c2);      
          // categorie c= new categorie(4);
         // categorie c3 = new categorie(5);
        // cs.supprimer(c3);
         //  cs.modifier(c);
       //  System.out.println(cs.Tric());
        
       // System.out.println(cs.afficher());;
        
       //**************plat**********************
       
        Platservice ps = new Platservice();
//        System.out.println(ps.getNomCateg());
            //platt p1=new platt("chakchouka","felfel","url",1,);
            //platt p2=new platt("ma9arouna","batata","url",3,2);
            //platt p=new platt(1);
     //       platt p4=new platt("leblabii","batata","url",7,3);
       //
       //ps.ajouter(p1);
            // ps.supprimer(p);
           // platt p2=new platt(3,"makrouna","felfel","url",9,2);
            //ps.modifier(p2);
            // System.out.println(ps.afficher());
           // System.out.println(ps.chercherTitreplat("m"));;
             
             //*******************pannier***************
            // Panierservice pas =new Panierservice();
            // panier pa=new panier(3,5);
            // pas.ajouter(pa);
            // panier pa1=new panier(1);
            // pas.supprimer(pa1);
            //panier pa2=new panier(3,4,7);
            //pas.modifier(pa2);
            //System.out.println(pas.afficher());

            //*********************facture******************
           //Factureservice fs =new Factureservice();
          //facture f=new facture(3,4,d1);
          //fs.ajouter(f);
            // facture f1=new facture(1);
             //fs.supprimer(f1);
            //facture f=new facture(2,4,4);
            //fs.modifier(f);
             //System.out.println(fs.afficher());
    
    
             
             
             
        //pdf     
        String file_name="C:\\Users\\chaim\\Documents\\NetBeansProjects\\yummy_gout\\yummy_gout.pdf";
        Document document =new Document();
        try {
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
           
             Connection cnx= Maconnexion.getInstance().getCnx();
            PreparedStatement ste = null;
            ResultSet rst=null;
            String query="select * from facture";
           // ste = cnx.createStatement(query);  
            ste = cnx.prepareStatement(query);
            rst =ste.executeQuery(query);
            while(rst.next()){
                Paragraph para=new Paragraph(rst.getString("Idfac")+" "+rst.getString("Idplat")+" "+rst.getString("Idclient")+" "+rst.getString("Date_de_facture"));
                
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
            document.add(Image.getInstance("C:\\Users\\chaim\\Documents\\NetBeansProjects\\yummy_gout\\logo.png"));
            
            
            
  
            
            document.close();
            System.out.println("finished");
        } catch (Exception e) {
            System.err.println(e);
           
        }
    
    }
    


   
    
    
    
    
    
    

    
}
