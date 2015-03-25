package Swipe;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SwipeControlPanel extends JPanel implements ActionListener{
	
	static BufferedImage rawImage, binaryImage;
	static JComboBox camMenu;
	static JScrollPane camScrollPane;
	static Insets insets;
	static Dimension frame_size;
	static Font sFont;
	
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

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
