package Swipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
		static BufferedImage binaryIMG = null; 
	/* ------------------------------------- */
		
	/* A boolean flag to control cam feed -- */
		static boolean camFeed = true; 
	/* ------------------------------------- */
	
	/* Dimenssion for viewports ------------ */
		static Dimension resRAW = new Dimension(320, 240), 
				resBINARY = new Dimension(200, 150);
	/* ------------------------------------- */
	
	/* Colors ------------------------------ */
		static int BLACK = Color.BLACK.getRGB(), 
				WHITE = Color.WHITE.getRGB();
	/* ------------------------------------- */
	
	public static void feedCam(int index){
		
		if(cam == null){
			// Check to see if using any previous camera instance
		}else{
			// If so, close it before, choosing a new one.
			if(cam.isOpen()){
				cam.close();
			}
		}
		
		cam = (Webcam) Camera.avaiableCams.get(index);
		cam.setViewSize(resRAW);
		cam.open();
		camFeed = true;
		
		double scaleX = (double)resBINARY.width/resRAW.width;
	    double scaleY = (double)resBINARY.height/resRAW.height;
	    
	    try{
	    	while(camFeed){
				SwipeControlPanel.rawImage = cam.getImage(); // Click a frame
				
				boolean[][] binMatrix = Processor.getBinary(SwipeControlPanel.rawImage, resRAW); // Get binary  
				BufferedImage binaryImg = new BufferedImage(resRAW.width, resRAW.height, BufferedImage.TYPE_BYTE_BINARY);
				for(int j = 0; j < resRAW.height; j++)
					for(int i = 0; i < resRAW.width; i++){
						if(binMatrix[i][j])
							binaryImg.setRGB(i, j, WHITE);
						else
							binaryImg.setRGB(i, j, BLACK);
					}
				
				// Scale the obtained binary image to new resolution
				AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
			    AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
			    SwipeControlPanel.binaryImage = bilinearScaleOp.filter(binaryImg, new BufferedImage(resBINARY.width, resBINARY.height, BufferedImage.TYPE_BYTE_BINARY));
				
			    SwipeConsole.backPanel.repaint();
			}
	    }catch(Exception e){
	    	SwipeConsole.pop(e.getMessage());
	    }finally{
	    	cam.close();
	    }	
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
}
