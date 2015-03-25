import java.util.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class indoor_beds extends JFrame implements ActionListener
{
	String type;
	File f=new File("D:/indoor_beds.records");
	ArrayList<Availbeds> list;
	Availbeds r;
	JRadioButton icu,iccu,gen;
	JPanel but,c,img;
	JButton confirm;
	JLabel icul,iculb,iculp,iccul,icculb,icculp,genl,genlb,genlp;
	
	
	void readfile()
	{
		try
		{
		FileInputStream fin=new FileInputStream(f);
		ObjectInputStream oin=new ObjectInputStream(fin);
		list= (ArrayList<Availbeds>)oin.readObject();
		r=(Availbeds) list.get(0);
		oin.close();
		fin.close();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error: "+e.getMessage(),"READING ERROR",0,new ImageIcon("error.png"));
		}
		
	}
	
	void updatefile(int a)
	{
		switch (a)
		{
		case 1: r.seticunum(); break;
		case 2: r.seticcunum(); break;
		case 3: r.setgennum(); break;
		}

		try
		{
			FileOutputStream fout= new FileOutputStream(f);
			ObjectOutputStream oout=new ObjectOutputStream(fout);
			
			list.set(0, r);
			oout.writeObject(list);
			
			oout.close();
			fout.close();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Error: "+e.getMessage(),"WRITING ERROR",0,new ImageIcon("error.png"));
		}
	}
	
	
	
	indoor_beds()
	{
		//type=s;
		
		setTitle("INDOOR WARD BOOKING");
		icu=new JRadioButton("ICU"); icu.setOpaque(false);
		iccu=new JRadioButton("ICCU"); iccu.setOpaque(false);
		gen=new JRadioButton("GENERAL");	gen.setOpaque(false);
		setLocation(120,130);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(icu);
		bg.add(iccu);
		bg.add(gen);
		
		confirm=new JButton("CONFIRM");
		
		but=new JPanel();
		but.add(icu);
		but.add(iccu);
		but.add(gen);
		but.add(confirm);but.setBackground(Color.WHITE);
		
		readfile();
		
		icul=new JLabel("ICU WARD----------------------------------------");
		iccul=new JLabel("ICCU WARD-----------------------------------------");
		genl=new JLabel("GENERAL WARD-----------------------------------------");
		
		iculb=new JLabel("No. of beds available in the ward: "+r.geticunum());
		iculp=new JLabel("Cost per ward: "+r.geticucost());
		
		icculb=new JLabel("No. of beds available in the ward: "+r.geticcunum());
		icculp=new JLabel("Cost per ward: "+r.geticcucost());
		
		genlb=new JLabel("No. of beds available in the ward: "+r.getgennum());
		genlp=new JLabel("Cost per ward: "+r.getgencost());
		
		Container ca=getContentPane();
		
		c=new JPanel();
		c.setLayout(new GridLayout(10,1));
		confirm.addActionListener(this);
		
		c.add(icul);
		c.add(iculb);
		c.add(iculp);
		c.add(iccul);
		c.add(icculb);
		c.add(icculp);
		c.add(genl);
		c.add(genlb);
		c.add(genlp);
		c.add(but);
		c.setBackground(Color.WHITE);
		
		img=new JPanel()
		{
			public void paintComponent(Graphics background)
			{
			ImageIcon i=new ImageIcon("indoor_beds_back.jpg");
			Image image=i.getImage();
			background.drawImage(image,0,0,null);
			setBounds(0,0,i.getIconWidth(),i.getIconHeight());
			setSize(i.getIconWidth(),i.getIconHeight());
			}
		};
		
		img.setLayout(new FlowLayout(FlowLayout.RIGHT));
		img.add(c);
		
		ca.add(img);
		setSize(650,410);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==confirm)
		{
			if(icu.isSelected())
			{
				if(Integer.parseInt(r.geticunum())>=1)
				{
					if(JOptionPane.showConfirmDialog(this,"ARE YOU SURE TO BOOK AN ICU WARD??")==JOptionPane.YES_OPTION)
					{
						updatefile(1);
						JOptionPane.showMessageDialog(this, "YOUR REQUEST GRANTED!! PAY Rs"+r.geticucost()+"  Enter customer details below..","ICU WARD",0,new ImageIcon("sucess.jpg"));
						new Gen("INDOOR",r.geticucost());
					}
				}
				else
					JOptionPane.showMessageDialog(this, "SORRY NO MORE BEDS AVAILABLE!!","ICU WARD",0,new ImageIcon("error.png"));

			}
			
			if(iccu.isSelected())
			{
				if(Integer.parseInt(r.geticcunum())>=1)
				{
					if(JOptionPane.showConfirmDialog(this,"ARE YOU SURE TO BOOK AN ICCU WARD??")==JOptionPane.YES_OPTION)
					{
						updatefile(2);
						JOptionPane.showMessageDialog(this, "YOUR REQUEST GRANTED!! PAY Rs"+r.geticucost()+"  Enter customer details below..","ICCU WARD",0,new ImageIcon("sucess.jpg"));
						new Gen("INDOOR",r.geticcucost());
					}
				}
				else
					JOptionPane.showMessageDialog(this, "SORRY NO MORE BEDS AVAILABLE!!","ICCU WARD",0,new ImageIcon("error.png"));

			}
			
			if(gen.isSelected())
			{
				if(Integer.parseInt(r.getgennum())>=1)
				{
					if(JOptionPane.showConfirmDialog(this,"ARE YOU SURE TO BOOK A GENERAL WARD??")==JOptionPane.YES_OPTION)
					{
						updatefile(1);
						JOptionPane.showMessageDialog(this, "YOUR REQUEST GRANTED!! PAY Rs"+r.getgencost()+"  Enter customer details below..","GENERAL WARD",0,new ImageIcon("sucess.jpg"));
						new Gen("INDOOR",r.getgencost());
					}
						
				}
				else
					JOptionPane.showMessageDialog(this, "SORRY NO MORE BEDS AVAILABLE!!","GENERAL WARD",0,new ImageIcon("error.png"));

			}
			
			this.dispose();
			
		}
		
	}
	
	public static void main(String s[])
	{
		new indoor_beds();
	}
}
