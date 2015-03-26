package Swipe;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class SliderPanel extends JPanel{
	Dimension frame_size;	
	public SliderPanel(Dimension s) {
		frame_size = s;
		this.setPreferredSize(frame_size);
		this.setBackground(Color.ORANGE);
		this.setVisible(true);
	}
}
