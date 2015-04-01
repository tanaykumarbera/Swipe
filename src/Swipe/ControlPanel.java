package Swipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ControlPanel extends JPanel{
	Dimension frame_size;
	
	JLabel redLabel, blueLabel, greenLabel, redRange, blueRange, greenRange;
	Font bold10;
	
	public ControlPanel(Dimension s) {
		frame_size = s;
		this.setPreferredSize(frame_size);
		this.setBackground(Color.WHITE);
		
		bold10 = new Font("monospace", Font.BOLD, 10);
		TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(102, 102, 102, 255), 2), " CORNER CONTROLS ");
		title.setTitleJustification(TitledBorder.RIGHT);
		title.setTitleColor(new Color(102, 102, 102, 255));
		title.setTitleFont(bold10);
		this.setBorder(title);
		
		this.setVisible(true);
	}
}
