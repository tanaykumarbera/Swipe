package tutorial;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwipeBackPanel extends JPanel{
	JPanel rawFeed, processedFeed;
	public SwipeBackPanel(){	
		rawFeed = new CamFeedRAW(new Dimension(400, 300));
		processedFeed = new CamFeedBINARY(new Dimension(200, 150));
		
		this.setLayout(null);
		
		rawFeed.setLocation(50, 50);
		rawFeed.setOpaque(true);
		rawFeed.setToolTipText("cwfcefewfe");
		this.add(rawFeed);
		
		//this.add(processedFeed);

		
		JLabel label = new JLabel("This JPanel uses Absolute Positioning", JLabel.CENTER);
        label.setSize(300, 30);
        label.setLocation(100, 5);
        label.setForeground(Color.red);
        
        this.add(label);
        JLabel label2 = new JLabel("This JPanel uses Absolute Positioning", JLabel.CENTER);
        label2.setSize(300, 30);
        label2.setLocation(200, 5);
        label2.setForeground(Color.green);
        this.add(label2);
	        
		
		rawFeed.setLocation(100, 100);
		processedFeed.setLocation(100, 100);
		
		rawFeed.setVisible(true);
		
		this.setPreferredSize(new Dimension(840, 320));
		this.setBackground(Color.yellow);
	}
}
