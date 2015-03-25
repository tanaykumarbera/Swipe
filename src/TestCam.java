import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.sun.xml.internal.stream.writers.WriterUtility;

public class TestCam {
	static byte MIN_RED = (byte)(200 - 128);
	static byte MAX_RED = (byte)(255 - 128);
	
	static byte MIN_GREEN = (byte)(0 - 128);
	static byte MAX_GREEN = (byte)(200 - 128);
	
	static byte MIN_BLUE = (byte)(0 - 128);
	static byte MAX_BLUE = (byte)(200 - 128);
	public static void main(String[] args) {
		try{
			Webcam cam = Camera.init();
			Dimension resolution = new Dimension(640, 480);
			cam.setViewSize(resolution);
			cam.open();
			int[][] binaryImg = new int[resolution.width][resolution.height];
			
			BufferedImage img;
			int pixData;
			Date date;
			try{
				date = new Date(); System.out.println("START :: " + date.toString());
				img = cam.getImage();
				for(int j = 0; j < resolution.height; j++){
					for(int i = 0; i < resolution.width; i++){
						pixData = img.getRGB(i, j);
						/*
						int alpha = (pixData >> 24) & 0xff;
					    int red = (pixData >> 16) & 0xff;
					    int green = (pixData >> 8) & 0xff;
					    int blue = (pixData) & 0xff;
					    System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
					    */
						
						int r = (pixData >> 16) & 0xff;
					    int g = (pixData >> 8) & 0xff;
					    int b = (pixData) & 0xff;
						
					    binaryImg[i][j] = (MIN_RED <= r) && (r <= MAX_RED) &&
					    		(MIN_GREEN <= g) && (g <= MAX_GREEN) &&
					    		(MIN_BLUE < b) && (b <= MAX_BLUE) ? 255 : 0;				    		
					}
				}
				date = new Date(); System.out.println("IMAGE PROCESSED :: " + date.toString());
			    
			    Writer.pgm(binaryImg, resolution, "bin.pgm");
				date = new Date(); System.out.println("END :: " + date.toString());				
			}catch(Exception e){
				System.out.println("ERROR :: FILE WRITE FAILD.");
			}
			System.out.println("---------------------");
			
			cam.close();
		}catch(FalseException f){
			System.out.println(f.toString());
		}
	}

}
