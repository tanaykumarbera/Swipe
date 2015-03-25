import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
//import java.io.File;
//import javax.imageio.ImageIO;
import java.awt.Robot;

public class Processor {
	/*
	static int MIN_RED = 80;
	static int MAX_RED = 215;
	
	static int MIN_GREEN = 10;
	static int MAX_GREEN = 50;
	
	static int MIN_BLUE = 5;
	static int MAX_BLUE = 50;
	*/
	
	static int MIN_RED = 250;
	static int MAX_RED = 255;
	
	static int MIN_GREEN = 20;
	static int MAX_GREEN = 225;
	
	static int MIN_BLUE = 20;
	static int MAX_BLUE = 225;
	
	/*
	static int MIN_RED = 100;
	static int MAX_RED = 160;
	
	static int MIN_GREEN = 100;
	static int MAX_GREEN = 140;
	
	static int MIN_BLUE = 0;
	static int MAX_BLUE = 50;
	*
	static int MIN_RED = 160;
	static int MAX_RED = 250;
	
	static int MIN_GREEN = 150;
	static int MAX_GREEN = 230;
	
	static int MIN_BLUE = 150;
	static int MAX_BLUE = 240;
	*/
	
	static Robot mouse;
	
	public static int enquireGrid(BufferedImage img, Dimension resolution){
		/* Exract a binary image */
		int pixData; 
		boolean[][] binaryImg = new boolean[resolution.width][resolution.height];
		boolean[][] revImg = new boolean[resolution.width][resolution.height];
		
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
		
		/* Some preprocessing */
			//Gauss
			//Open
			//Close
		
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
				
				//binaryImg[i][j] = (count1>count0) ? true : false;
				binaryImg[i][j]= revImg[resolution.width - i][j] = (count1>count0) ? true : false;
			}
				
		
		
		/* Generate the mean coordinate of cluster */
		/*
		int imin = resolution.width, imax = 0, jmin = resolution.height, jmax=0, mean_x, mean_y;
		for(int j = 0; j < resolution.height; j++)
			for(int i = 0; i < resolution.width; i++){
				if(binaryImg[i][j] == 255){
					if(i<imin)			imin=i;
					else if(imax<i)		imax=i;
			 
					if(j<jmin)			jmin=j;
					else if(jmax<j)		jmax=j;
				}
			}
		mean_y = (imax+imin)/2;
		mean_x = (jmin+jmax)/2;
		*/
		
		int meanx = 0, meany = 0, countx = 0, county = 0;
		int[] mean_y = new int[resolution.width];
		int[] mean_x = new int[resolution.height];
		//System.out.println("0");
		for(int j = 0; j < resolution.height; j++){ 
			int count = 0, sum = 0;
			for(int i = 0; i < resolution.width; i++){
				//if(binaryImg[i][j]){
				if(revImg[i][j]){
	 			   count++;
				   sum = sum + i;					
				}
			}
			mean_x[j] = (count == 0) ? 0 : (sum/count);
		}
		
		//System.out.println("1");
		for(int i = 0; i < resolution.height; i++)
		{
			if(mean_x[i] != 0){
				countx++;
				meanx = meanx + mean_x[i];
			}
		}
		
		meanx = (countx == 0)? -1 : (int) meanx / countx;
		//System.out.println("2");
		
		for(int i = 0; i < resolution.width; i++){
			int count = 0, sum = 0;
			for(int j = 0; j < resolution.height; j++){
				//if(binaryImg[i][j]){
				if(revImg[i][j]){
	 			   count++;
				   sum = sum + j;					
				}
			}
			mean_y[i] = (count == 0) ? 0: (sum/count);
		}
			
		//System.out.println("3");
		for(int j = 0; j < resolution.width; j++){
			if(mean_y[j]!=0){
				county++;
				meany= meany + mean_y[j];
			}
		}
		meany = (county == 0) ? -1 : (int)meany/county;
		
//		/* Optional Write */

		
		/* Display in frame */
		//Swipe.viewport.refresh(revImg);
		
		/* Determine Grid no. */
		int X_1 = resolution.width / 3, X_2 = X_1 + X_1;
		int Y_1 = resolution.height / 3, Y_2 = Y_1 + Y_1;
		int grid;
		
		if(meanx == -1 || meany == -1){
			grid = 0;
		}else{
			if(meanx < X_1){ // [x| | ]
				if(meany < Y_1)
					grid = 1;
				else if(meany < Y_2)
					grid = 4;
				else grid = 7;
			}else if(meanx < X_2){ // [ |x| ]
				if(meany < Y_1)
					grid = 2;
				else if(meany < Y_2)
					grid = 5;
				else grid = 8;
			}else{ // [ | |x]
				if(meany < Y_1)
					grid = 3;
				else if(meany < Y_2)
					grid = 6;
				else grid = 9;
			}
			
			/* Mark grid in frame */
			//Swipe.viewport.refresh(grid);
		}
		Swipe.smallwindow.refresh(meanx, meany, grid);
		return grid;
	}
	
	public static String enquireAction(int grid_no){
		switch(grid_no){
			case 1: return "TOP RIGHT";
			case 2: return "TOP";
			case 3: return "TOP LEFT";
			case 4: return "RIGHT";
			case 5: return "MIDDLE";
			case 6: return "LEFT";
			case 7: return "BOTTOM RIGHT";
			case 8: return "BOTTOM";
			case 9: return "BOTTOM LEFT";
		}
		return "UNDEFINED";
	}
	
	public static void performMouseAction(int grid_no){
		try {
			mouse = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			return;
		}
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();
		switch(grid_no){
			case 1: mouse.mouseMove(x - 25, y - 25); break;
			case 2: mouse.mouseMove(x, y - 25); break;
			case 3: mouse.mouseMove(x + 25, y - 25); break;
			case 4: mouse.mouseMove(x - 25, y); break;
			case 5: mouse.mouseMove(x, y); break;
			case 6: mouse.mouseMove(x + 25, y); break;
			case 7: mouse.mouseMove(x - 25, y + 25); break;
			case 8: mouse.mouseMove(x, y + 25); break;
			case 9: mouse.mouseMove(x + 25, y + 25); break;
		}	
		System.out.println("done : "+x+", "+y);
	}
	
	static int c = 0, c1 = 1, c3 = 0, c7 = 0, c9 = 0;
	
	public static void performAction(int grid_no){
		try {
			mouse = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			return;
		}
		
		switch(grid_no){
			case 1: /* TOP LEFT */ 
				c3 = c7 = c9 = 0; c1++;
				if(c1>5){
					mouse.mousePress(InputEvent.BUTTON1_MASK);
					mouse.mouseRelease(InputEvent.BUTTON1_MASK);
					mouse.mousePress(InputEvent.BUTTON1_MASK);
					mouse.mouseRelease(InputEvent.BUTTON1_MASK);
				}
				
				break;
				
			case 2: /* TOP */
				c1 = c3 = c7 = c9=0;
				mouse.keyPress(KeyEvent.VK_UP); mouse.keyRelease(KeyEvent.VK_UP); 
				break;
				
			case 3: /* TOP RIGHT */
				c1 = c7 = c9=0; c3++;
				if(c3 > 5){
					mouse.mousePress(InputEvent.BUTTON2_MASK);
					mouse.mouseRelease(InputEvent.BUTTON2_MASK);
				}
				break;
			
			case 4: /* LEFT */
				c1 = c3 = c7 = c9 = 0;
				mouse.keyPress(KeyEvent.VK_LEFT); mouse.keyRelease(KeyEvent.VK_LEFT); 
				break;
				
			case 5:  /* MIDDLE */ 
				c1 = c3 = c7 = c9 = 0;
				break;
				
			case 6:  /* RIGHT */
				c1 = c3 = c7 = c9 = 0;
				mouse.keyPress(KeyEvent.VK_RIGHT); mouse.keyRelease(KeyEvent.VK_RIGHT); 
				break;
			
			case 7:  /* BOTTOM LEFT */
				c1 = c3 = c9 = 0; c7++;
				mouse.keyPress(KeyEvent.VK_SPACE); mouse.keyRelease(KeyEvent.VK_SPACE);
				break;
				
			case 8:  /* BOTTOM */
				c1 = c3 = c7 = c9 = 0;
				mouse.keyPress(KeyEvent.VK_DOWN); mouse.keyRelease(KeyEvent.VK_DOWN); 
				break;
			case 9:  /* BOTTOM RIGHT */
				c1 = c3 = c7 = 0; c9++;
				//mouse.keyPress(KeyEvent.VK_ALT); mouse.keyPress(KeyEvent.VK_F4);
				//mouse.keyRelease(KeyEvent.VK_ALT); mouse.keyRelease(KeyEvent.VK_F4);
				break;
		}
	}
}