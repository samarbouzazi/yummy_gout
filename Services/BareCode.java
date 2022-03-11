/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
/**
 *
 * @author DELL PRCISION 3551
 */
public class BareCode {
    
    
    public void generateImage(String a,String b){
    BareCode.createImage(a, b);
		System.out.println("finished");
    }
    public static void createImage(String image_name,String myCode)  {
		try {
		Code128Bean code128 = new Code128Bean();
		code128.setHeight(15f);
		code128.setModuleWidth(0.3);
		code128.setQuietZone(10);
		code128.doQuietZone(true);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
		code128.generateBarcode(canvas, myCode);
		canvas.finish();
		//write to png file
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Smayra\\Desktop\\JAVAFX Projects\\Avant final\\"+image_name+".png");
		fos.write(baos.toByteArray());
		fos.flush();
		fos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
