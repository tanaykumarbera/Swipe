package Swipe;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class SwipeConsole extends JFrame{
	// Main 400, 300
	// viewport 200, 150
	
	static SwipeControlPanel backPanel;
	SwipeConsole(){
		backPanel = new SwipeControlPanel();
		setContentPane(backPanel);
		
		setTitle("Swipe - A Color Segmentation based gesture recognition tool");
		
		URL iconURL = getClass().getResource("/images/icon32.png");
		ImageIcon icon = new ImageIcon(iconURL);
		setIconImage(icon.getImage());
		
		setLocationRelativeTo(null);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		// All set! Start initial Operation :: Start the last attached camera
		try{
			backPanel.startCam();
		}catch(Exception e){
			SwipeConsole.pop("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	protected void finalize() throws Throwable {
		super.finalize();
		Processor.camFeed = false;
	}
	
	public static void pop(String s){
		System.out.println("Error: " + s);
	}
	public static void main(String[] args) {
		new SwipeConsole();
	}
}
