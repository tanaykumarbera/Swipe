package Swipe;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

public class Processor{
	
	/* Color bounds ------------------------ */
		static int MIN_RED = 200;
		static int MAX_RED = 255;
		
		static int MIN_GREEN = 20;
		static int MAX_GREEN = 225;
		
		static int MIN_BLUE = 20;
		static int MAX_BLUE = 225;
	/* ------------------------------------- */
	
	/* Camera handle ----------------------- */
		static Webcam cam = null; 
	/* ------------------------------------- */
		
	/* Image handles ----------------------- */
		static BufferedImage binaryIMG = null,
				rawIMG = null; 
	/* ------------------------------------- */
		
	/* A boolean flag to control cam feed -- */
		static boolean camFeed = true, 
				viewportActive = true; 
	/* ------------------------------------- */
	
	/* Dimenssion for viewports ------------ */
		static Dimension resRAW = new Dimension(320, 240), 
				resBINARY = new Dimension(200, 150),
				resMAIN = new Dimension(640, 480);
	/* ------------------------------------- */
	
	/* Colors ------------------------------ */
		static int BLACK = Color.BLACK.getRGB(), 
				WHITE = Color.WHITE.getRGB();
	/* ------------------------------------- */
	
	/* Others ------------------------------ */

	/* ------------------------------------- */
	
	public static BufferedImage getARGBImage(BufferedImage img){
		int h = img.getHeight(), w = img.getWidth(), pixData;
		BufferedImage newImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i < w; i++)
			for(int j = 0; j < h; j++){
				pixData = img.getRGB(i, j);
			    newImg.setRGB(i, j, pixData);
			}
		return newImg;				
	}
			
	public static boolean[][] getBinary(BufferedImage img, Dimension resolution){
		boolean[][] binaryImg = new boolean[resolution.width][resolution.height];
		int pixData;
		for(int j = 0; j < resolution.height; j++){
			for(int i = 0; i < resolution.width; i++){
				pixData = img.getRGB(i, j);
				int r = (pixData >> 16) & 0xff;
			    int g = (pixData >> 8) & 0xff;
			    int b = (pixData) & 0xff;
				
			    binaryImg[i][j] = (MIN_RED <= r) && (r <= MAX_RED) &&
			    		(MIN_GREEN <= g) && (g <= MAX_GREEN) &&
			    		(MIN_BLUE <= b) && (b <= MAX_BLUE) ? true : false;				    		
			}
		}
		
		int count0, count1;
		for(int j = 2; j < resolution.height - 2; j++)
			for(int i = 2; i < resolution.width - 2; i++){
				count0 = 0; count1 = 0;
				if(binaryImg[i-2][j-2]) count1++; else count0++;
				if(binaryImg[i-2][j-1]) count1++; else count0++;
				if(binaryImg[i-2][j]) count1++; else count0++;
				if(binaryImg[i-2][j+1]) count1++; else count0++;
				if(binaryImg[i-2][j+2]) count1++; else count0++;
				if(binaryImg[i-1][j-2]) count1++; else count0++;
				if(binaryImg[i-1][j-1]) count1++; else count0++;
				if(binaryImg[i-1][j]) count1++; else count0++;
				if(binaryImg[i-1][j+1]) count1++; else count0++;
				if(binaryImg[i-1][j+2]) count1++; else count0++;
				if(binaryImg[i][j-2]) count1++; else count0++;
				if(binaryImg[i][j-1]) count1++; else count0++;
				if(binaryImg[i][j]) count1++; else count0++;
				if(binaryImg[i][j+1]) count1++; else count0++;
				if(binaryImg[i][j+2]) count1++; else count0++;
				if(binaryImg[i+1][j-2]) count1++; else count0++;
				if(binaryImg[i+1][j-1]) count1++; else count0++;
				if(binaryImg[i+1][j]) count1++; else count0++;
				if(binaryImg[i+1][j+1]) count1++; else count0++;
				if(binaryImg[i+1][j+2]) count1++; else count0++;
				if(binaryImg[i+2][j-2]) count1++; else count0++;
				if(binaryImg[i+2][j-1]) count1++; else count0++;
				if(binaryImg[i+2][j]) count1++; else count0++;
				if(binaryImg[i+2][j+1]) count1++; else count0++;
				if(binaryImg[i+2][j+2]) count1++; else count0++;
				
				binaryImg[i][j] = (count1>count0) ? true : false;
			}
		return binaryImg;
	}
	
	public static boolean[][] getBinary(){
		if(rawIMG == null) return null;
		
		boolean[][] revBinaryImg = new boolean[resMAIN.width][resMAIN.height];
		int pixData;
		for(int j = 0; j < resMAIN.height; j++){
			for(int i = 1; i < resMAIN.width; i++){
				pixData = rawIMG.getRGB(i, j);
				int r = (pixData >> 16) & 0xff;
			    int g = (pixData >> 8) & 0xff;
			    int b = (pixData) & 0xff;
				
			    revBinaryImg[resMAIN.width - i][j] = (MIN_RED <= r) && (r <= MAX_RED) &&
			    		(MIN_GREEN <= g) && (g <= MAX_GREEN) &&
			    		(MIN_BLUE <= b) && (b <= MAX_BLUE) ? true : false;				    		
			}
		}
		
		int count0, count1;
		for(int j = 2; j < resMAIN.height - 2; j++)
			for(int i = 2; i < resMAIN.width - 2; i++){
				count0 = 0; count1 = 0;
				if(revBinaryImg[i-2][j-2]) count1++; else count0++;
				if(revBinaryImg[i-2][j-1]) count1++; else count0++;
				if(revBinaryImg[i-2][j]) count1++; else count0++;
				if(revBinaryImg[i-2][j+1]) count1++; else count0++;
				if(revBinaryImg[i-2][j+2]) count1++; else count0++;
				if(revBinaryImg[i-1][j-2]) count1++; else count0++;
				if(revBinaryImg[i-1][j-1]) count1++; else count0++;
				if(revBinaryImg[i-1][j]) count1++; else count0++;
				if(revBinaryImg[i-1][j+1]) count1++; else count0++;
				if(revBinaryImg[i-1][j+2]) count1++; else count0++;
				if(revBinaryImg[i][j-2]) count1++; else count0++;
				if(revBinaryImg[i][j-1]) count1++; else count0++;
				if(revBinaryImg[i][j]) count1++; else count0++;
				if(revBinaryImg[i][j+1]) count1++; else count0++;
				if(revBinaryImg[i][j+2]) count1++; else count0++;
				if(revBinaryImg[i+1][j-2]) count1++; else count0++;
				if(revBinaryImg[i+1][j-1]) count1++; else count0++;
				if(revBinaryImg[i+1][j]) count1++; else count0++;
				if(revBinaryImg[i+1][j+1]) count1++; else count0++;
				if(revBinaryImg[i+1][j+2]) count1++; else count0++;
				if(revBinaryImg[i+2][j-2]) count1++; else count0++;
				if(revBinaryImg[i+2][j-1]) count1++; else count0++;
				if(revBinaryImg[i+2][j]) count1++; else count0++;
				if(revBinaryImg[i+2][j+1]) count1++; else count0++;
				if(revBinaryImg[i+2][j+2]) count1++; else count0++;
				
				revBinaryImg[i][j] = (count1>count0) ? true : false;
			}
		return revBinaryImg;
	}
}
