package Swipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ControlPanel extends JPanel{
	Dimension frame_size;
	Insets insets;
	
	JLabel redLabel, blueLabel, greenLabel, redRange, blueRange, greenRange;
	Font bold10;
	Image controlImage;
	static JCheckBox reverse, ctrl, center;
	static JButton start;
	
	public ControlPanel(Dimension s) {
		
		this.setLayout(null);
		insets = this.getInsets();
		frame_size = s;
		this.setPreferredSize(frame_size);
		this.setBackground(Color.WHITE);
		
		bold10 = new Font("monospace", Font.BOLD, 10);
		TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(102, 102, 102, 255), 2), " CORNER CONTROLS ");
		title.setTitleJustification(TitledBorder.RIGHT);
		title.setTitleColor(new Color(102, 102, 102, 255));
		title.setTitleFont(bold10);
		this.setBorder(title);
		
		
		// Set the right image, explaining control
		URL iconURL = getClass().getResource("/images/controlImage.PNG");
		ImageIcon icon = new ImageIcon(iconURL);
		controlImage = icon.getImage();
		
		
		// The transparent grids over the image to show tooltiptext over operations		
		Dimension grid_dimenssion = new Dimension(33, 24);
		Color  trans = new Color(120, 120, 120, 0);
		
		JPanel esc = new JPanel();
		esc.setPreferredSize(grid_dimenssion);
		esc.setBackground(trans);
		esc.setToolTipText("ESC");
		
		JPanel enter = new JPanel();
		enter.setPreferredSize(grid_dimenssion);
		enter.setBackground(trans);
		enter.setToolTipText("ENTER");
		
		JPanel bck = new JPanel();
		bck.setPreferredSize(grid_dimenssion);
		bck.setBackground(trans);
		bck.setToolTipText("BACKSPACE");
		
		JPanel menu = new JPanel();
		menu.setPreferredSize(grid_dimenssion);
		menu.setBackground(trans);
		menu.setToolTipText("MENU");
		
		JPanel space = new JPanel();
		space.setPreferredSize(grid_dimenssion);
		space.setBackground(trans);
		space.setToolTipText("SPACEBAR");
		
		JPanel up = new JPanel();
		up.setPreferredSize(grid_dimenssion);
		up.setBackground(trans);
		up.setToolTipText("UP");
		
		JPanel down = new JPanel();
		down.setPreferredSize(grid_dimenssion);
		down.setBackground(trans);
		down.setToolTipText("DOWN");
		
		JPanel left = new JPanel();
		left.setPreferredSize(grid_dimenssion);
		left.setBackground(trans);
		left.setToolTipText("LEFT");
		
		JPanel right = new JPanel();
		right.setPreferredSize(grid_dimenssion);
		right.setBackground(trans);
		right.setToolTipText("RIGHT");
		
		
		this.add(esc);
		esc.setBounds(154 + insets.left, 22 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		
		this.add(up);
		up.setBounds(187 + insets.left, 22 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		
		this.add(enter);
		enter.setBounds(219 + insets.left, 22 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		
		this.add(left);
		left.setBounds(154 + insets.left, 44 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		
		this.add(space);
		space.setBounds(187 + insets.left, 44 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		
		this.add(right);
		right.setBounds(219 + insets.left, 44 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		
		this.add(bck);
		bck.setBounds(154 + insets.left, 67 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		
		this.add(down);
		down.setBounds(187 + insets.left, 67 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		
		this.add(menu);
		menu.setBounds(219 + insets.left, 67 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		
		// Adds checkbox for some extra customization
		ImageIcon checkTRUE = new ImageIcon(getClass().getResource("/images/checkTRUE.png"));
		ImageIcon checkFALSE = new ImageIcon(getClass().getResource("/images/checkFALSE.png"));
		
		reverse = new JCheckBox("Invert Horizontal Axis");
		reverse.setFont(bold10);
		reverse.setOpaque(false);
		reverse.setSelected(false);
		reverse.setIcon(checkFALSE);
		reverse.setSelectedIcon(checkTRUE);
		this.add(reverse);
		reverse.setBounds(5 + insets.left, 30 + insets.top, 150, reverse.getPreferredSize().height);
		
		
		ctrl = new JCheckBox("Enable 'Ctrl' with arrows");
		ctrl.setFont(bold10);
		ctrl.setOpaque(false);
		ctrl.setSelected(false);
		ctrl.setIcon(checkFALSE);
		ctrl.setSelectedIcon(checkTRUE);
		this.add(ctrl);
		ctrl.setBounds(5 + insets.left, 50 + insets.top, 150, reverse.getPreferredSize().height);
		
		center = new JCheckBox("Disable grid in center");
		center.setFont(bold10);
		center.setOpaque(false);
		center.setSelected(false);
		center.setIcon(checkFALSE);
		center.setSelectedIcon(checkTRUE);
		this.add(center);
		center.setBounds(5 + insets.left, 70 + insets.top, 150, reverse.getPreferredSize().height);
		
		start = new JButton("Start Rolling");
		start.setPreferredSize(new Dimension(100, 30));
		start.setFont(bold10);
		start.setBackground(Color.WHITE);
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Start Rolling Actions according to preferences
				SwipeConsole.handler.setVisible(false);
				ScreenFeeder.camFeed = false;				
				BinaryViewport.initOperation();
			}          
		});
		
		this.add(start);
		start.setBounds(20 + insets.left, 105 + insets.top, 100, 30);
		
		
		this.setVisible(true);
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		g.drawImage(controlImage, 139, 0, null);
    }
}
