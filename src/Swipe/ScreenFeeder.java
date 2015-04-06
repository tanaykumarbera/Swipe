package Swipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

public class ScreenFeeder extends Thread {
	static Webcam cam = null;
	static boolean camFeed;
	
	static int BLACK = Color.BLACK.getRGB(), 
			WHITE = Color.WHITE.getRGB();
	
	public void run(){
		
		cam = Camera.getOpenCam();
		camFeed = true;
		
		Dimension resMAIN = Processor.resMAIN,
				resRAW = Processor.resRAW,
				resBINARY = Processor.resBINARY;
		
		double scaleX_raw = (double)resRAW.width/resMAIN.width;
	    double scaleY_raw = (double)resRAW.height/resMAIN.height;
	    
	    double scaleX = (double)resBINARY.width/resRAW.width;
	    double scaleY = (double)resBINARY.height/resRAW.height;
	    
	    AffineTransform scaleTransform_raw = AffineTransform.getScaleInstance(scaleX_raw, scaleY_raw);
	    AffineTransformOp bilinearScaleOp_raw = new AffineTransformOp(scaleTransform_raw, AffineTransformOp.TYPE_BILINEAR);
	    
		AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
	    AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
	    
	    try{
	    	while(camFeed){
	    		
	    		// Click a frame @ 640 x 480
	    		BufferedImage rawImg = Processor.getARGBImage(cam.getImage());
	    		
	    		// Resize to 320 x 240
	    		SwipeControlPanel.rawImage = bilinearScaleOp_raw.filter(rawImg, new BufferedImage(resRAW.width, resRAW.height, BufferedImage.TYPE_INT_RGB));
			    
			    // Get the binary image @ 320 x 240
				boolean[][] binMatrix = Processor.getBinary(SwipeControlPanel.rawImage, resRAW); // Get binary  
				BufferedImage binaryImg = new BufferedImage(resRAW.width, resRAW.height, BufferedImage.TYPE_BYTE_BINARY);
				for(int j = 0; j < resRAW.height; j++)
					for(int i = 0; i < resRAW.width; i++){
						if(binMatrix[i][j])
							binaryImg.setRGB(i, j, WHITE);
						else
							binaryImg.setRGB(i, j, BLACK);
					}
				
				// Scale the binary image to 200 x 150
			    SwipeControlPanel.binaryImage = bilinearScaleOp.filter(binaryImg, new BufferedImage(resBINARY.width, resBINARY.height, BufferedImage.TYPE_BYTE_BINARY));
				
			    SwipeConsole.backPanel.repaint();
			}
	    }catch(Exception e){
	    	SwipeConsole.pop(e.getMessage());
	    }
	}
}
