package Swipe;

import java.awt.Dimension;

public class GridMapper extends Thread {
	static boolean active;
	static Dimension resMAIN;
	
	public GridMapper() {
		active = true;
	}
	
	public void run(){
		boolean binMatrix[][];
		resMAIN = Processor.resMAIN;
		int X_1 = resMAIN.width / 3, X_2 = X_1 + X_1;
		int Y_1 = resMAIN.height / 3, Y_2 = Y_1 + Y_1;
		
		while(active){
			binMatrix = Processor.getBinary();
			if (binMatrix == null) continue; //nothing to calculate yet. Cam's not ready.
			
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
			BinaryViewport.refresh(meanx, meany, grid);
		}
	}
}
