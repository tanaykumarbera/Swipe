import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


class Hospital_Frame extends JFrame implements ActionListener
{
	
	Container c=getContentPane();
	Dimension d=new Dimension();
	JPanel background, buton_panel;
	JButton ADMINISTRATOR,OPERATOR;

	Calendar cal;
	
	JLabel date, time;
	SimpleDateFormat tim=new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat yr=new SimpleDateFormat("dd mmm yyyy");
		
	Hospital_Frame()
	{
		
		
		setTitle("WELCOME...we serve the best!");
		ImageIcon img=new ImageIcon("back.jpg");
		this.setSize(img.getIconWidth(),img.getIconHeight());
		background= new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				ImageIcon img1=new ImageIcon("back.jpg");
				this.setBackground(Color.WHITE);
				Image img2=img1.getImage();
				g.drawImage(img2, 0, 0, null);
				d.width=img2.getWidth(null);
				d.height=img2.getHeight(null);
				this.setSize(d);
				
			}
		};
		
		ADMINISTRATOR= new JButton(new ImageIcon("ADMIN.jpg"));
		ADMINISTRATOR.setBackground(Color.WHITE);
		ADMINISTRATOR.addActionListener(this);
		ADMINISTRATOR.setToolTipText("ADMINISTRATOR");
		
		OPERATOR=new JButton(new ImageIcon("OPT.jpg"));
		OPERATOR.setBackground(Color.WHITE);
		OPERATOR.addActionListener(this);
		OPERATOR.setToolTipText("OPERATOR");
		
		date=new JLabel("");
		time=new JLabel("");
		
		buton_panel=new JPanel();
		background.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		buton_panel.add(OPERATOR);
		buton_panel.add(ADMINISTRATOR);
		background.add(buton_panel);
		background.add(date);
		background.add(time);
		
		
		c.add(background);
		
	
		
		setResizable(false);
		setVisible(true);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
		
	public void actionPerformed(ActionEvent act)
		{
			
			if(act.getSource()==ADMINISTRATOR)
			{
				new ADMIN_LOGIN_FRAME();
				this.dispose();
			}
			else if(act.getSource()==OPERATOR)
			{
				new OPERATOR_LOGIN_FRAME();
				this.dispose();
			}
			
			
			
		}
		

		
	
	public static void main(String a[])
	{
		new Hospital_Frame();
	}
	
}