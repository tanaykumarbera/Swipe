package Swipe;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SwipeControlPanel extends JPanel{
	
	static BufferedImage rawImage, binaryImage;
	static JComboBox camMenu;
	static JScrollPane camScrollPane;
	static Insets insets;
	static Dimension frame_size;
	static Font sFont;
	SliderPanel sliderPanel;
	
	int DIFF = 10;
	
	public SwipeControlPanel(){
		this.setLayout(null);
		insets = this.getInsets();
		frame_size = new Dimension(820, 290);
		this.setPreferredSize(frame_size);
		this.setBackground(Color.WHITE);
		
		/* Creates a default background for the area streaming video */
		int ImgBack = Color.LIGHT_GRAY.getRGB();
		rawImage = new BufferedImage(320, 240, BufferedImage.TYPE_BYTE_GRAY);
		for(int j = 0; j < 240; j++)
			for(int i = 0; i < 320; i++)
				rawImage.setRGB(i, j, ImgBack);
		
		/* Add a blank transparent layer of panel over the raw image to bind Mouse event */
		JPanel transparentLayer = new JPanel();
		transparentLayer.setPreferredSize(new Dimension(320, 240));
		transparentLayer.setBackground(new Color(120, 120, 120, 0));
		transparentLayer.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e){
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseClicked(MouseEvent e) {
            	try {
            		/* Get pointer location within the image, get color, set Range */
					Robot mouse = new Robot();
					//Point p = e.getPoint();
					Point p = e.getLocationOnScreen();
					Color c = mouse.getPixelColor(p.x, p.y);
					int red = c.getRed(), green = c.getGreen(), blue = c.getBlue();
					sliderPanel.Red.setValue(red - DIFF); sliderPanel.Red.setUpperValue(red + DIFF);
					sliderPanel.Green.setValue(green - DIFF); sliderPanel.Green.setUpperValue(green + DIFF);
					sliderPanel.Blue.setValue(blue - DIFF); sliderPanel.Blue.setUpperValue(blue + DIFF);
					
				} catch (Exception ex) {
					SwipeConsole.pop(ex.getMessage());
				}
            }
        });
		this.add(transparentLayer);
		transparentLayer.setBounds(10 + insets.left, 50 + insets.top, 320, 240);
		
		
		
		ImgBack = Color.DARK_GRAY.getRGB();
		binaryImage = new BufferedImage(200, 150, BufferedImage.TYPE_BYTE_GRAY);
		for(int j = 0; j < 150; j++)
			for(int i = 0; i < 200; i++)
				binaryImage.setRGB(i, j, ImgBack);
		
		/* Create a dropdown for available cameras. */
		DefaultComboBoxModel cams = Camera.enquireCams();
		if(cams != null){
			
			/* Has a camera and other alternatives */
			camMenu = new JComboBox(cams);
			camScrollPane = new JScrollPane(camMenu);
			camScrollPane.setLocation(100, 100);
			Dimension camScrollPaneSize = new Dimension(320, 30);
			
			camScrollPane.setPreferredSize(camScrollPaneSize);
			camMenu.setBackground(Color.WHITE);
			camMenu.setForeground(Color.DARK_GRAY);
			sFont = new Font("monospace", Font.BOLD, 12);
			camMenu.setFont(sFont);
			
			camMenu.setSelectedIndex(cams.getSize() - 1); // sets the last camera in the list as default selection.
			this.add(camScrollPane);
			camScrollPane.setBounds(10 + insets.left, 10 + insets.top, camScrollPaneSize.width, camScrollPaneSize.height);
		    
			camMenu.addActionListener(new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			    	Processor.feedCam(camMenu.getSelectedIndex());
			    }
			});
			
			Dimension sp_size = new Dimension(480, 120);
			sliderPanel = new SliderPanel(sp_size);
			this.add(sliderPanel);
			sliderPanel.setBounds(340 + insets.left, 10 + insets.top, sp_size.width, sp_size.height);
			
		}else{
			/* No Camera found	*/
			
			/* Add a panel to overlay current wrapper. */
			JPanel cover = new JPanel();
			cover.setLayout(null);
			cover.setBackground(Color.WHITE);
			cover.setPreferredSize(frame_size);
			this.add(cover);
			cover.setBounds(insets.left,insets.top, frame_size.width, frame_size.height);
			
			/* Add Error msg and similie to the panel. */
			JLabel similie = new JLabel(":(", JLabel.CENTER);
			sFont = new Font("monospace", Font.BOLD, 100);
			similie.setFont(sFont);
			similie.setForeground(Color.GRAY);
			similie.setPreferredSize(frame_size);
			cover.add(similie);
			similie.setBounds(insets.left, insets.top - 25, frame_size.width, frame_size.height);
			
			JLabel alertTxt = new JLabel("Looks like something went haywired!  Error: No cams detected.", JLabel.CENTER);
			sFont = new Font("monospace", Font.BOLD, 12);
			alertTxt.setFont(sFont);
			alertTxt.setForeground(Color.GRAY);
			alertTxt.setPreferredSize(frame_size);
			cover.add(alertTxt);
			alertTxt.setBounds(insets.left, insets.top + 100, frame_size.width, frame_size.height);
		}

		this.setVisible(true);
		System.out.println("SwipeControlPanel.SwipeControlPanel()");
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		g.drawImage(rawImage, 10, 50, null);
		g.drawImage(binaryImage, 340, 140, null);
    }

	public void startCam(){
		Processor.feedCam(camMenu.getSelectedIndex()); 
	}
}
