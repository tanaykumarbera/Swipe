package Swipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
	static JCheckBox reverse, ctrl;
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
		
		URL iconURL = getClass().getResource("/images/controlImage.PNG");
		ImageIcon icon = new ImageIcon(iconURL);
		controlImage = icon.getImage();
		
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
		this.add(enter);
		this.add(space);
		this.add(menu);
		this.add(bck);
		this.add(up);
		this.add(down);
		this.add(left);
		this.add(right);
		
		esc.setBounds(154 + insets.left, 22 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		up.setBounds(187 + insets.left, 22 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		enter.setBounds(219 + insets.left, 22 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		left.setBounds(154 + insets.left, 44 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		space.setBounds(187 + insets.left, 44 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		right.setBounds(219 + insets.left, 44 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		bck.setBounds(154 + insets.left, 67 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		down.setBounds(187 + insets.left, 67 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		menu.setBounds(219 + insets.left, 67 + insets.top, grid_dimenssion.width, grid_dimenssion.height);
		
		reverse = new JCheckBox("Reverse Horizontal Axis");
		ctrl = new JCheckBox("Enable 'Ctrl' with arrows");
		reverse.setFont(bold10);
		reverse.setSelected(false);
		ctrl.setFont(bold10);
		ctrl.setSelected(false);
		
		reverse.setBounds(33 + insets.left, 40 + insets.top,15 ,15 );
		ctrl.setBounds(33 + insets.left, 70 + insets.top, 15, 15);
		
		this.add(reverse);
		this.add(ctrl);
		
		this.setVisible(true);
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		g.drawImage(controlImage, 139, 0, null);
    }
}
