import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class BinaryViewport extends JPanel{
	Dimension resolution, mean;
	int BLACK = Color.BLACK.getRGB(), WHITE = Color.WHITE.getRGB();
	int grid = 0;
	int mean_x, mean_y, radius, Dradius;
	Color hard = new Color(255, 255, 255, 255);
	Color light = new Color(255, 255, 255, 80);
	
	public BinaryViewport(){
		resolution = new Dimension(200,150);
		radius =  5;
		
		Dradius = radius * 2;
	
		this.setPreferredSize(resolution);
		this.setBackground(Color.BLACK);
	}
	
	public void refresh(int x, int y, int g){
		mean_x = (int)(x / 3.2); mean_y = (int)(y / 2.8);
		grid = g;
		repaint();
	}
    
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
			g.setColor(light);
			g.fillRect(X, Y, 67, 50);
        }
    }
}