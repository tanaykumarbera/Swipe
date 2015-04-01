import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JWindow;

import com.github.sarxos.webcam.Webcam;


public class Swipe{
	Webcam cam;
	Dimension resolution;
	int grid;
	public static binaryViewer viewport;
	public static BinaryViewport smallwindow;
	
	public Swipe(){
		try{
			/* Initialize Webcam */
			cam = Camera.init();
			resolution = new Dimension(640, 480);
			cam.setViewSize(resolution);
			cam.open();
			
			
			/* Initialize Binary Viewer */
//			viewport = new binaryViewer(resolution);
//			JFrame window = new JFrame("Swipe Binary Viwer");
//			window.getContentPane().add(viewport);
//			window.setResizable(false);
//			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			window.pack();
//			window.setLocationRelativeTo(null);
//			window.setVisible(true);
			
			
			smallwindow = new BinaryViewport();
			JWindow smallwindowFrame = new JWindow();
			smallwindowFrame.getContentPane().add(smallwindow);
			//smallwindowFrame.setResizable(false);
			//smallwindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			smallwindowFrame.pack();
			smallwindowFrame.setLocationRelativeTo(null);
			smallwindowFrame.setAlwaysOnTop(true);
			smallwindowFrame.setOpacity(0.8f);
			
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
	        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
	        int x = (int) rect.getMaxX() - 250;
	        int y = (int) rect.getMaxY() - 200;
	        smallwindowFrame.setLocation(x, y);
			
	        smallwindowFrame.setVisible(true);
	        
			while(true){ //while
				try{
					grid = Processor.enquireGrid(cam.getImage(), resolution);
					System.out.println("IMAGE PROCESSED :: " + grid + " | ACTION : " + Processor.enquireAction(grid));
					//Processor.performMouseAction(grid);
					Processor.performAction(grid);
				}catch(Exception e){
		  			System.out.println("ERROR ENCOUNTERED :: " + e.toString());
					break;
				}
			}
			cam.close();
		}catch(Exception err){
			System.out.println(err.toString());
		}
	}
	
	/* Main Method - the Opening candidate */ 
	public static void main(String[] args) throws IOException{
		new Swipe();
		System.in.read();
	}
}
