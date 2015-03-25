import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import java.io.*;

public class op_ambulance extends JFrame implements ActionListener
{
	JPanel img,fld,but,fld1;
	JLabel stock,price,blank;
	JButton register,cancel;
	JTextField nof;
	File f=new File("D:/ambulance_field.records");
	ArrayList<ambulanceinfo> rec;
	ambulanceinfo record;
	
	
	void readRec()
	{
		try
		{
			FileInputStream fin=new FileInputStream(f);
			ObjectInputStream oin=new ObjectInputStream(fin);
			rec=(ArrayList<ambulanceinfo>) oin.readObject();
			record= (ambulanceinfo) rec.get(0);
			fin.close();
			oin.close();

			
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "oops.."+e.getMessage(),"ERROR",0,new ImageIcon("error.png"));
		}
		
		
	}
	void updateFile()
	{
		try
		{
			FileOutputStream fout=new FileOutputStream(f);
			ObjectOutputStream out=new ObjectOutputStream(fout);
			record.setamb();
			rec.set(0, record);
			out.writeObject(rec);
			
			out.close();
			fout.close();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"oops..."+ e.getMessage());
		}
	}
	
	
	op_ambulance()
	{
		
		setSize(450,130);
		setLocation(300,300);
		Container c=getContentPane();
		
		setTitle("AMBULANCE OPERATOR");
				
		readRec();
		blank=new JLabel("    ");
		price=new JLabel("Rent per AMBULANCE: Rs "+record.getcost());
		stock=new JLabel("TOTAL NUMBER OF RUNNING AMBULANCE AVAILABLE: "+record.getamb());
		
		
		register=new JButton("REQUEST AN AMNULANCE",new ImageIcon("but.jpg"));
		register.setRolloverIcon(new ImageIcon("but_r.jpg"));
		register.addActionListener(this);
		
		cancel=new JButton("CANCEL");
		cancel.addActionListener(this);
		
		fld=new JPanel();
		fld.setLayout(new GridLayout(3,1));
		fld.setBackground(Color.WHITE);
		
		but=new JPanel();
		but.add(register);
		but.add(blank);
		but.add(cancel);
		but.setBackground(Color.WHITE);
		
		fld1=new JPanel();
		fld1.add(stock);
		fld1.add(price);
		fld1.setLayout(new GridLayout(2,1));
		fld1.setBackground(Color.WHITE);
		
		
		img=new JPanel()
		{
			public void paintComponent(Graphics background)
			{
			ImageIcon i=new ImageIcon("op_amb_back.jpg");
			Image image=i.getImage();
			background.drawImage(image,0,0,null);
			setBounds(0,0,i.getIconWidth(),i.getIconHeight());
			setSize(i.getIconWidth(),i.getIconHeight());
			}

		};
		
	
		fld.add(fld1);
		fld.add(but);
		
		img.setLayout(new FlowLayout(FlowLayout.RIGHT));
		img.add(fld);
		
		
		c.add(img);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		
		if(ae.getSource()==register)
		{
			
			
			
			if(Integer.parseInt(record.getamb())>0)
			{
				if((JOptionPane.showConfirmDialog(this,"TOTAL AMOUNT TO COLLECT : Rs "+record.getcost()+"  Confirm order??"))==JOptionPane.YES_OPTION)            
				{
				
				updateFile();
				JOptionPane.showMessageDialog(this,"Records updated sucessully..","AMBULANCE",0,new ImageIcon("sucess.jpg"));
				
				
				JOptionPane.showMessageDialog(this,"REQUEST GRANTED..enter customer details below..","AMBULANCE",0,new ImageIcon("sucess.jpg"));
				new Gen("ambulance",(""+record.getcost()));
				this.dispose();
				}
				
				
			}
			else
				{
				JOptionPane.showMessageDialog(this,"Soory..no Ambulance service available now..already all are on service!!","AMBULANCE",0,new ImageIcon("error.png"));
				this.dispose();
				}
		}
		
		if(ae.getSource()==cancel)
		{
			this.dispose();
		}
	}
	
	
	
	public static void main(String s[])
	{
		new op_ambulance();
	}
}
