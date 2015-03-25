import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class binaryViewer extends JPanel{
	BufferedImage img;
	Dimension resolution;
	int BLACK = Color.BLACK.getRGB(), WHITE = Color.WHITE.getRGB();
	int grid = 0;
	
	public binaryViewer(Dimension res){
		resolution = res;
		img = new BufferedImage(resolution.width, resolution.height, BufferedImage.TYPE_BYTE_BINARY);
		this.setPreferredSize(resolution);
	}
	
	public void refresh(boolean[][] binary){
		for(int j = 0; j < resolution.height; j++)
			for(int i = 0; i < resolution.width; i++){
				if(binary[i][j])
					img.setRGB(i, j, WHITE);
				else
					img.setRGB(i, j, BLACK);
			}
		grid = 0;
		repaint();
	}
	
	public void refresh(int g){
		grid = g;
		repaint();
	}
    
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int X, Y;
		g.drawImage(img, 0, 0, null);
		if(grid != 0){
			switch(grid){
				case 1: X = 0; Y = 0; break;//return "TOP RIGHT";
				case 2: X = 213; Y = 0; break;//return "TOP";
				case 3: X = 426; Y = 0; break;//return "TOP LEFT";
				case 4: X = 0; Y = 160; break;//return "RIGHT";
				case 5: X = 213; Y = 160; break;//return "MIDDLE";
				case 6: X = 426; Y = 160; break;//return "LEFT";
				case 7: X = 0; Y = 320; break;//return "BOTTOM RIGHT";
				case 8: X = 213; Y = 320; break;//return "BOTTOM";
				case 9: X = 426; Y = 320; break;//return "BOTTOM LEFT";
				default: X = 0; Y = 0;
			}
			g.setColor(new Color(255, 255, 255, 80));
			g.fillRect(X, Y, 213, 160);
		}
    }
}