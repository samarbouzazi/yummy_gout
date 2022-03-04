/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
<<<<<<< HEAD

/**
 *
 * @author chaim
 */


=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



>>>>>>> 8845519aac4386f17f05bd0a9b2fb7ace06bb4ce
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
<<<<<<< HEAD
import tools.Maconnexion;
=======
import Tools.MaCon;
>>>>>>> 8845519aac4386f17f05bd0a9b2fb7ace06bb4ce
public class Pdf2 {
     private Connection con;
        private Statement ste;
    public Pdf2()  {
<<<<<<< HEAD
        con = Maconnexion.getInstance().getCnx();
          
    
}
    public void add(String file,String N1,String N2 ,String N3,String N4) throws FileNotFoundException, SQLException, DocumentException{
=======
        con = MaCon.getInstance().getCnx();
          
    
}
    public void add(String file,String N1,String N2 ,String N3,String N4,String N5,String N6,String N7) throws FileNotFoundException, SQLException, DocumentException{
>>>>>>> 8845519aac4386f17f05bd0a9b2fb7ace06bb4ce
        
        /* Create Connection objects */
//                con = DataBase.getInstance().getConnection();
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(2);
                //create a cell object
                PdfPCell table_cell;
                                
                              
                            
                               
<<<<<<< HEAD
                                    table_cell=new PdfPCell(new Phrase("nom plat"));
=======
                                    table_cell=new PdfPCell(new Phrase("nom fournisseur"));
>>>>>>> 8845519aac4386f17f05bd0a9b2fb7ace06bb4ce
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N1));
                                my_report_table.addCell(table_cell);
                               
<<<<<<< HEAD
                                table_cell=new PdfPCell(new Phrase("Quantité démandé"));
=======
                                table_cell=new PdfPCell(new Phrase("nom produit"));
>>>>>>> 8845519aac4386f17f05bd0a9b2fb7ace06bb4ce
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N2));
                                my_report_table.addCell(table_cell);
                             // hayka mchet haw tw chnzid maaha lprix beh
                             
<<<<<<< HEAD
                                table_cell=new PdfPCell(new Phrase("prix "));
=======
                                table_cell=new PdfPCell(new Phrase("date d'ajout"));
>>>>>>> 8845519aac4386f17f05bd0a9b2fb7ace06bb4ce
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N3));
                                my_report_table.addCell(table_cell);
                                
                            
                                
                                
<<<<<<< HEAD
                                table_cell=new PdfPCell(new Phrase("prix total"));
=======
                                table_cell=new PdfPCell(new Phrase("valide jusqu'a"));
>>>>>>> 8845519aac4386f17f05bd0a9b2fb7ace06bb4ce
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N4));
                                my_report_table.addCell(table_cell);
                                
                                
                                
<<<<<<< HEAD
=======
                                table_cell=new PdfPCell(new Phrase("prix unitaire"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N5));
                                my_report_table.addCell(table_cell);
                                
                                
                                
                                 table_cell=new PdfPCell(new Phrase("quantité"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N6));
                                my_report_table.addCell(table_cell);
                                
                                
                                
                                 table_cell=new PdfPCell(new Phrase("id produit"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N7));
                                my_report_table.addCell(table_cell);
                                
>>>>>>> 8845519aac4386f17f05bd0a9b2fb7ace06bb4ce
                              
                               
                                
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
               /* Close all DB related objects */

        
    }

    
}
