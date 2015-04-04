package Swipe;

import Swipe;

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
	
	public static boolean[][] getRevBinary(BufferedImage img, Dimension resolution){
		boolean[][] revBinaryImg = new boolean[resolution.width][resolution.height];
		int pixData;
		for(int j = 0; j < resolution.height; j++){
			for(int i = 0; i < resolution.width; i++){
				pixData = img.getRGB(i, j);
				int r = (pixData >> 16) & 0xff;
			    int g = (pixData >> 8) & 0xff;
			    int b = (pixData) & 0xff;
				
			    revBinaryImg[resolution.width - i][j] = (MIN_RED <= r) && (r <= MAX_RED) &&
			    		(MIN_GREEN <= g) && (g <= MAX_GREEN) &&
			    		(MIN_BLUE <= b) && (b <= MAX_BLUE) ? true : false;				    		
			}
		}
		
		int count0, count1;
		for(int j = 2; j < resolution.height - 2; j++)
			for(int i = 2; i < resolution.width - 2; i++){
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

	public static void startProcessing(int index){
		if(cam == null){
			// Check to see if using any previous camera instance
		}else{
			// If so, close it before, choosing a new one.
			if(cam.isOpen()){
				cam.close();
			}
		}
		
		cam = (Webcam) Camera.avaiableCams.get(index);
		cam.setViewSize(resMAIN);
		cam.open();
		viewportActive = true;
		
		int X_1 = resMAIN.width / 3, X_2 = X_1 + X_1;
		int Y_1 = resMAIN.height / 3, Y_2 = Y_1 + Y_1;
		
		while(viewportActive){
			SwipeControlPanel.rawImage = cam.getImage(); // Click a frame
			boolean[][] binMatrix = Processor.getRevBinary(SwipeControlPanel.rawImage, resMAIN); // Get binary
			
			// Calculate mean coordinate
			int meanx = 0, meany = 0, countx = 0, county = 0, grid = 0;
			int[] mean_y = new int[resMAIN.width];
			int[] mean_x = new int[resMAIN.height];
			
			{
				// Calculates the horizontal mean along x axis
				for(int j = 0; j < resMAIN.height; j++){ 
					int count = 0, sum = 0;
					for(int i = 0; i < resMAIN.width; i++){
						if(binMatrix[i][j]){
			 			   count++;
						   sum = sum + i;					
						}
					}
					mean_x[j] = (count == 0) ? 0 : (sum/count);
				}
				for(int i = 0; i < resMAIN.height; i++)
				{
					if(mean_x[i] != 0){
						countx++;
						meanx = meanx + mean_x[i];
					}
				}
				
				meanx = (countx == 0)? -1 : (int) meanx / countx;
			}
			
			{
				// Calculates the vertical mean along y axis
				for(int i = 0; i < resMAIN.width; i++){
					int count = 0, sum = 0;
					for(int j = 0; j < resMAIN.height; j++){
						//if(binaryImg[i][j]){
						if(binMatrix[i][j]){
			 			   count++;
						   sum = sum + j;					
						}
					}
					mean_y[i] = (count == 0) ? 0: (sum/count);
				}
				for(int j = 0; j < resMAIN.width; j++){
					if(mean_y[j]!=0){
						county++;
						meany= meany + mean_y[j];
					}
				}
			
				meany = (county == 0) ? -1 : (int)meany/county;
			}
			
			{
				// Determine Grid no. 
				if(meanx == -1 || meany == -1){
					grid = 0;
				}else{
					if(meanx < X_1){ 		// [x| | ]
						if(meany < Y_1)
							grid = 1;
						else if(meany < Y_2)
							grid = 4;
						else grid = 7;
					}else if(meanx < X_2){	// [ |x| ]
						if(meany < Y_1)
							grid = 2;
						else if(meany < Y_2)
							grid = 5;
						else grid = 8;
					}else{ 					// [ | |x]
						if(meany < Y_1)
							grid = 3;
						else if(meany < Y_2)
							grid = 6;
						else grid = 9;
					}
				}
			}
			// refresh the small action window to show action
			Swipe.smallwindow.refresh(meanx, meany, grid);
		}
	}
}
