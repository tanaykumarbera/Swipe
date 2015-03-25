package tutorial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class CamFeedRAW extends JPanel {
	BufferedImage img;
	Dimension resolution;
	
	public CamFeedRAW(Dimension res){
		resolution = res;
		this.setPreferredSize(resolution);
		//this.setLayout(null);
		this.setBackground(Color.black);
		this.setVisible(true);
	}
	
	public void refresh(BufferedImage rawImg){
		img = rawImg;
		repaint();
	}
    
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
    }
}
