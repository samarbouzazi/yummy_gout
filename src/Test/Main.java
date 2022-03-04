/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entités.personnel;
import entités.reclamationn;
import entités.reponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import services.PersonnelService;
import services.reclamationService;
import services.reponseService;
import tools.MaConnexion;
import tools.mailing;

/**
 *
 * @author HP
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MessagingException, AddressException, IOException {
        // TODO code application logic here
        MaConnexion mc = MaConnexion.getInstance();
        Date d1= Date.valueOf("2014-01-3");
         //personnel p = new personnel(32);
         //personnel p1 = new personnel("nomp","prenomp",656959,2656656,"guygygu",878,"hgyug",2656,d1);
         personnel p2 = new personnel(1,"chaima","laila","13464","1655664","ojobjb",1000, "kkkkkk",80, d1);
         
          PersonnelService ps = new PersonnelService();
          //ps.ajouter(p2);
         //System.out.println(ps.controlmail("eiohfeiof@gmail.com"));
          
         //System.out.println( ps.afficher());
         System.out.println(ps.prime(10));
          //ps.chercherav("jjj");
          //ps.modifier(p2);
         // ps.supprimer(p2);a
        // System.out.println(ps.chercherav("samar"));
        //System.out.println(ps.getnbrepersonnel());
        // System.out.println(ps.Triper());
          //System.out.println(ps.afficher());
          reclamationService rs = new reclamationService();
         reclamationn r = new reclamationn("fuck",2);
          //reclamationn r1 = new reclamationn(3);
          // reclamationn r2 = new reclamationn(17,"obbguigi");
        
          rs.ajouter(r);
          //rs.supprimer(r1);
          //reclamationn rm = new reclamationn(12,"amaaaaaaaaaaaaaaaa");

          //rs.modifier(r2);
          System.out.println(rs.afficher());
         //reponseService rp = new reponseService();
          //reponse r = new reponse(4,"hihihi");
          //reponse r1 = new reponse(3,"heeeloooooo");
           //reponse r1 = new reponse("heeeloooooo");
           //reponse r2 = new reponse(2);
           //reponse r = new reponse(5,"xxxxxxxxxxxxxxxx");
          //rp.ajouter(r1);
          //rp.supprimer(r2);
          //rp.modifier(r);
          //System.out.println(rp.afficher());
          

          
          String file_name="C:\\Users\\HP\\Documents\\NetBeansProjects\\yummygout\\yummygout.pdf";
        Document document =new Document();
        try {
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
           
             Connection cnx= MaConnexion.getInstance().getCnx();
            PreparedStatement ste = null;
            ResultSet rst=null;
            String query="select * from personnell";
           // ste = cnx.createStatement(query);  
            ste = cnx.prepareStatement(query);
            rst =ste.executeQuery(query);
            while(rst.next()){
                Paragraph para=new Paragraph(rst.getString("Idp")+" "+rst.getString("nomp")+" "+rst.getString("prenomp")+" "+rst.getString("cinp")+" "+rst.getString("telp")+" "+rst.getString("email")+" "+rst.getString("Salaire")+" "+rst.getString("Specialite")+" "+rst.getString("nbheure")+" "+rst.getString("Date_embauche"));
                
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
            
            document.add(Image.getInstance("C:\\Users\\HP\\Documents\\NetBeansProjects\\yummygout\\marwa.png"));
            
  
            
            document.close();
            System.out.println("finished");
        } catch (Exception e) {
            System.err.println(e);
           
        }
          
          
          
          
    }
    
}
