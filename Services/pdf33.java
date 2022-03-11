/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
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
import Tools.MaConnexion;
/**
 *
 * @author HP
 */
public class pdf33 {
    private Connection con;
        private Statement ste;
    public pdf33()  {
        con = MaConnexion.getInstance().getCnx();
          
    
}
    public void add(String file,String N1,String N2 ,String N3,String N4,String N5,String N6,String N7,String N8,String N9) throws FileNotFoundException, SQLException, DocumentException{
        
        /* Create Connection objects */
//                con = DataBase.getInstance().getConnection();
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(2);
                //create a cell object
                PdfPCell table_cell;
                                
                              
                            
                               
                                    table_cell=new PdfPCell(new Phrase("nom personnel"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N1));
                                my_report_table.addCell(table_cell);
                               
                                table_cell=new PdfPCell(new Phrase("prenom personnel"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N2));
                                my_report_table.addCell(table_cell);
                           
                             
                                table_cell=new PdfPCell(new Phrase("CIN"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N3));
                                my_report_table.addCell(table_cell);
                                
                            
                                
                                
                                table_cell=new PdfPCell(new Phrase("Num télephone"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N4));
                                my_report_table.addCell(table_cell);
                                
                                
                                
                                table_cell=new PdfPCell(new Phrase("Email"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N5));
                                my_report_table.addCell(table_cell);
                                
                                
                                
                                 table_cell=new PdfPCell(new Phrase("Salaire"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N6));
                                my_report_table.addCell(table_cell);
                                
                                
                                
                                 table_cell=new PdfPCell(new Phrase("Specialité"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N7));
                                my_report_table.addCell(table_cell);
                                
                              table_cell=new PdfPCell(new Phrase("Nombre d'heure"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N8));
                                my_report_table.addCell(table_cell);
                                
                              
                                
                                
                                table_cell=new PdfPCell(new Phrase("Taux horaire"));
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(N9));
                                my_report_table.addCell(table_cell);
                               
              
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
              

        
    }

}
