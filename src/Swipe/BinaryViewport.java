package Swipe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class BinaryViewport extends JWindow{
	static Dimension resolution;
	static int BLACK = Color.BLACK.getRGB(), WHITE = Color.WHITE.getRGB();
	static int grid = 0, xpos, ypos, alpha;
	static int mean_x, mean_y, radius, Dradius;
	static Color hard = new Color(255, 255, 255, 255);
	static Color light;
	static JPanel back;
	static BufferedImage rawIMAGE;
	static JPanel bar, sleekBar;
	static JButton console;
	
	public BinaryViewport(){
		resolution = new Dimension(200,150);
		radius =  5;
		
		Dradius = radius * 2;
	
		back = new JPanel(){
			protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        int X, Y;
		        if(grid == 0){
		        	g.setColor(Color.BLACK);
		        	g.fillRect(0, 0, 200, 150);
		        }else{
		        	g.setColor(hard);
		    		g.fillOval(mean_x - radius, mean_y - radius, Dradius, Dradius);
		    		
		    		switch(grid){
						case 1: X = 0; Y = 0; break;//return "TOP RIGHT";
						case 2: X = 67; Y = 0; break;//return "TOP";
						case 3: X = 133; Y = 0; break;//return "TOP LEFT";
						case 4: X = 0; Y = 50; break;//return "RIGHT";
						case 5: X = 67; Y = 50; break;//return "MIDDLE";
						case 6: X = 133; Y = 50; break;//return "LEFT";
						case 7: X = 0; Y = 100; break;//return "BOTTOM RIGHT";
						case 8: X = 67; Y = 100; break;//return "BOTTOM";
						case 9: X = 133; Y = 100; break;//return "BOTTOM LEFT";
						default: X = 0; Y = 0;
					}
		    		
		    		light = new Color(255, 255, 255, alpha);
					g.setColor(light);
					g.fillRect(X, Y, 67, 50);
		        }
		    }
		};
		back.setPreferredSize(resolution);
		back.setBackground(Color.BLACK);
		this.setContentPane(back);
		
		bar = new JPanel(); sleekBar = new JPanel();
		bar.setVisible(false); sleekBar.setVisible(false);
		bar.setPreferredSize(new Dimension(200, 30));
		sleekBar.setPreferredSize(new Dimension(200, 2));
		bar.setBackground(Color.RED);
		sleekBar.setBackground(Color.RED);
		
		bar.setLayout(null);
		Insets i = bar.getInsets();
		
		JLabel swipe = new JLabel("Swipe");
		swipe.setFont(new Font("monospace", Font.BOLD, 11));
		swipe.setForeground(Color.WHITE);
		bar.add(swipe);
		swipe.setBounds(5 + i.left, 8 + i.top, 50, 15);
		
		URL iconURL = getClass().getResource("/images/console.png");
		ImageIcon icon = new ImageIcon(iconURL);
		console = new JButton();
		console.setPreferredSize(new Dimension(14, 14));
		console.setIcon(icon);
		console.setOpaque(false);
		console.setBackground(new Color(255, 0, 0, 0));
		console.setBorder(null);
		bar.add(console);
		console.setBounds(175 + i.left, 10 + i.top, 14, 14);
		console.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		console.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Stop Ongoing operations & Return back to Console.
				GridMapper.active = false;
				Photographer.battery = false;
				SwipeConsole.restore();
			}          
		});
		
		back.setLayout(new BorderLayout());
		back.add(bar, BorderLayout.SOUTH);
		back.add(sleekBar, BorderLayout.NORTH);
		
		back.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e){
            	Point p = e.getPoint();
            	if(p.x < 0 || p.y < 0 || p.x > 200 || p.y > 150){
            		// In case it is actually out of this area, and not due to button
            		bar.setVisible(false);
                	sleekBar.setVisible(false);
            	}
            }
            public void mouseEntered(MouseEvent e) {
            	bar.setVisible(true);
            	sleekBar.setVisible(true);
            }
            public void mouseClicked(MouseEvent e) {
            }
        });
		
		
		pack();
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setOpacity(0.8f);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        xpos = (int) rect.getMaxX() - 250;
        ypos = (int) rect.getMaxY() - 200;
        setLocation(xpos, ypos);
		setVisible(true);
	}
	
	public static void refresh(int x, int y, int g, int a){
		mean_x = (int)(x / 3.2); mean_y = (int)(y / 2.8);
		grid = g;
		alpha = a;
		back.repaint();
	}
	
	public static void initOperation(){
		SwipeConsole.smallWindow = new BinaryViewport();
		Photographer p = new Photographer();
		GridMapper g = new GridMapper();
		
		p.start();
		g.start();
	}
	
	public static void main(String s[]){
		new BinaryViewport();
	}
}