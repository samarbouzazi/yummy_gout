/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entities.ClientInfo;
import Entities.Reservation;
import Services.ClientInfoService;
import Services.ReservationService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



/**
 *
 * @author tchet
 */
public  class QrCode  {
    
   
     public  static void  getQRCodeImage(String text) throws WriterException, IOException {
        
              Connection connection = DataBaseConnection.getInstance().getCn();
             ReservationService rs = new ReservationService();
             
             String  content = rs.getById(7).toString();
             File path= new File("C:\\Users\\tchet\\Desktop\\qrcode.png");
             QRCodeWriter qrCodeWriter = new QRCodeWriter();
             BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300);
                MatrixToImageWriter.writeToPath(bitMatrix,"PNG",path.toPath());
    
}
      public  static void  readQRCodeImage(File path) throws WriterException, IOException, NotFoundException, ChecksumException, FormatException {
          
            BufferedImage bf = ImageIO.read(path);
          BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
           new BufferedImageLuminanceSource(bf)));
    Result result = new MultiFormatReader().decode(bitmap);
          System.out.println(result.getText());
}
public static void main(String[] args) throws WriterException, IOException, NotFoundException, FormatException, ChecksumException {

  
             QrCode code = new QrCode();
             
             Connection connection = DataBaseConnection.getInstance().getCn();
             ReservationService rs = new ReservationService();
             
             String  content = rs.getById(7).toString();
           
         try {
             getQRCodeImage(content);
         } catch (WriterException | IOException ex) {
         }
         
         readQRCodeImage(new File("C:\\Users\\tchet\\Desktop\\qrcode.png"));
             
}

}


