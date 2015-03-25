import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class welcome_screen extends JWindow 
{
	JPanel img,fld;
	JProgressBar bar=null;
	Timer tm=new Timer(20,new actn());
	JLabel pl=new JLabel();
	int c=0;
	boolean ff=false;
	
	welcome_screen()
	{
		ImageIcon pic =new ImageIcon("welcome_back.jpg");
		setSize(pic.getIconWidth(),pic.getIconHeight());
	
		img=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				ImageIcon pic=new ImageIcon("welcome_back.jpg");
				Image image=pic.getImage();
				
				g.drawImage(image,0,0, null);
				setBounds(0,0,pic.getIconWidth(),pic.getIconHeight());
				setSize(pic.getIconWidth(),pic.getIconHeight());
			}
		};
		
		
		img.setLayout(new BorderLayout());
		
		bar=new JProgressBar();
		bar.setMaximum(0);
		bar.setOpaque(false);
		bar.setMaximum(100);
		bar.setBorderPainted(false);
		bar.setForeground(Color.GREEN);
		bar.setSize(400,10);
		
		
		fld=new JPanel();
		fld.setLayout(new FlowLayout(FlowLayout.RIGHT));
		fld.add(bar);
		fld.add(pl);
		fld.setOpaque(false);
		
		
		
		Container c=getContentPane();
		
		img.add(fld,BorderLayout.SOUTH);
		
		tm.start();
		c.add(img);
		setVisible(true);
		setAlwaysOnTop(true);
		
		setLocation(100,180);
		
		
		
			
		
	}
	
	
	class actn implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(c<100)
			{ bar.setValue(c++);	pl.setText(c+"%");}
			else
				{	tm.stop(); new Hospital_Frame();	close();}
		}
		

	}
	
	
	void close()
	{
		this.dispose();
	}
	
	public static void main(String s[])
	{
		new welcome_screen();
	}

	
}
