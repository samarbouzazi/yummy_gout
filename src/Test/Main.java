/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< HEAD
<<<<<<< HEAD
package tests;

=======

package Test;
>>>>>>> 0747a8f63e661a3bc8cf25b409880a74c6393ff2
=======

package Test;
>>>>>>> 8ce309909b8891b9609a6aecedc676ec7ee5f913
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entités.personnel;
import entités.reclamationn;
import entités.reponse;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import services.PersonnelService;
import services.reclamationService;
import services.reponseService;
import tools.MaConnexion;

/**
 *
 * @author HP
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MaConnexion mc = MaConnexion.getInstance();
        Date d1= Date.valueOf("2012-01-3");
         //personnel p = new personnel(32);
         personnel p1 = new personnel("nomp","prenomp",656959,2656656,"guygygu",878,"hgyug",2656,d1);
         personnel p2 = new personnel(1,"chaima","laila",13,14, "ojo(",44, "kkkkkk",4, d1);
         
          PersonnelService ps = new PersonnelService();
          ps.ajouter(p2);
          System.out.println( ps.afficher());
          //ps.chercherav("jjj");
          //ps.modifier(p2);
         // ps.supprimer(p2);
         System.out.println(ps.chercherav("samar"));
         System.out.println(ps.getnbrepersonnel());
         System.out.println(ps.Triper());
          //System.out.println(ps.afficher());
         // reclamationn r = new reclamationn(2,"amaaaaaaaaaaaaaaaa");
          //reclamationn r1 = new reclamationn(3);
           //reclamationn r2 = new reclamationn(4,"oooooooo");
          //reclamationService rs = new reclamationService();
          //rs.ajouter(r);
          //rs.supprimer(r1);
          //reclamationn rm = new reclamationn(12,"amaaaaaaaaaaaaaaaa");

          //rs.modifier(r2);
          //System.out.println(rs.afficher());
         // reponseService rp = new reponseService();
          //reponse r = new reponse(4,"oooo");
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
