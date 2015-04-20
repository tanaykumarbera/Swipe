package Swipe;
import java.awt.Color;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

public class SwipeConsole extends JFrame{

	private static final long serialVersionUID = 1L;
	static SwipeConsole handler;
	static SwipeControlPanel backPanel;
	static BinaryViewport smallWindow;
	
	public void initFrame(){
		backPanel = new SwipeControlPanel();
		setContentPane(backPanel);
		
		setTitle("Swipe - A Color Segmentation based gesture recognition tool");
		
		URL iconURL = getClass().getResource("/images/icon32.png");
		ImageIcon icon = new ImageIcon(iconURL);
		setIconImage(icon.getImage());
		
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// Set up look & feel for tooltip text
		UIManager.put("ToolTip.background", new ColorUIResource(255, 255, 255)); // The color is #fff7c8.
		Border border = BorderFactory.createLineBorder(new Color(76,79,83)); // The color is #4c4f53.
		UIManager.put("ToolTip.border", border);
		
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
		GridMapper.active = false;
	}
	
	public static void restore(){
		smallWindow.dispose();
		handler.setVisible(true);
		backPanel.startCam();
	}
	
	public static void pop(String s){
		System.out.println("Error: " + s);
	}
	public static void main(String[] args) {
		handler = new SwipeConsole();
		handler.initFrame();
	}
}
