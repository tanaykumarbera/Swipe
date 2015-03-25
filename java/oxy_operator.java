import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import java.io.*;

public class oxy_operator extends JFrame implements ActionListener
{
	JPanel img,fld,but,fld1,fld2;
	JLabel stock,price,blank;
	JButton register,cancel;
	JTextField nof;
	File f=new File("D:/oxy_bank.records");
	ArrayList<Oxy> rec;
	Oxy record;
	
	
	void readRec()
	{
		try
		{
			FileInputStream fin=new FileInputStream(f);
			ObjectInputStream oin=new ObjectInputStream(fin);
			rec=(ArrayList<Oxy>) oin.readObject();
			record= (Oxy) rec.get(0);
			fin.close();
			oin.close();

			
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "oops.."+e.getMessage(),"ERROR",0,new ImageIcon("error.png"));
		}
		
		
	}
	void updateFile(int r)
	{
		try
		{
			FileOutputStream fout=new FileOutputStream(f);
			ObjectOutputStream out=new ObjectOutputStream(fout);
			record.setox(r);
			rec.set(0, record);
			out.writeObject(rec);
			
			out.close();
			fout.close();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"oops..."+ e.getMessage());
		}
	}
	

	oxy_operator()
	{
		
		setSize(300,300);
		setLocation(310,320);
		setTitle("OXYGEN BANK");
		Container c=getContentPane();
				
		readRec();
		blank=new JLabel("    ");
		price=new JLabel("Cost per cylinder: "+record.getprice());
		stock=new JLabel("Cylinders in stock: "+record.getox());
		
		
		register=new JButton("REGISTER A CYLINDER");
		register.addActionListener(this);
		
		cancel=new JButton("CANCEL");
		cancel.addActionListener(this);
		
		fld=new JPanel();
		fld.setLayout(new GridLayout(3,1));
		
		but=new JPanel();
		but.add(register);
		but.add(blank);
		but.add(cancel);
		
		fld1=new JPanel();
		fld1.add(stock);
		fld1.add(price);
		fld1.setLayout(new GridLayout(2,1));
		
		nof=new JTextField("NUMBER OF CYLINDER",20);
		
		fld2=new JPanel();
		fld2.add(nof);
		
		
		img=new JPanel()
		{
			public void paintComponent(Graphics background)

			{
			ImageIcon i=new ImageIcon("op_oxy_back.jpg");
			Image image=i.getImage();
			background.drawImage(image,0,0,null);
			setBounds(0,0,i.getIconWidth(),i.getIconHeight());
			setSize(i.getIconWidth(),i.getIconHeight());
			}

		};
		
			
	
		fld.add(fld1);
		fld.add(fld2);
		fld.add(but);
		
		img.setLayout(new BorderLayout());
		img.add(fld,BorderLayout.SOUTH);
		
		c.add(img);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		
		if(ae.getSource()==register)
		{
			
			
			Scanner sc=new Scanner(nof.getText());
			if(sc.findInLine("^[0-9]")==null)
			{
				JOptionPane.showMessageDialog(this,"Number of cylinders must be integers!");
				nof.setText("");
				nof.setBackground(Color.RED);
			}
			
			else
			{		
			
			int stk= Integer.parseInt(record.getox());
			int no=Integer.parseInt(nof.getText());
			if(stk>=no)
			{
				if((JOptionPane.showConfirmDialog(this,"TOTAL AMOUNT TO COLLECT : Rs "+(no*(Integer.parseInt(record.getprice())))+"  Confirm order??"))==JOptionPane.YES_OPTION)            
				{
				
				updateFile(no);
				JOptionPane.showMessageDialog(this,"Records updated sucessully..","OXYGEN BANK",0,new ImageIcon("sucess.jpg"));
				
				
				JOptionPane.showMessageDialog(this,"REQUEST GRANTED..enter customer details below..","OXYGEN BANK",0,new ImageIcon("sucess.jpg"));
				new Gen("oxygen cylinder",(""+no*(Integer.parseInt(record.getprice()))));
				this.dispose();
				}
				
				
			}
			else
				JOptionPane.showMessageDialog(this,"Soory...out of stock!! only "+record.getox()+" cylinders are left in stock!","OXYGEN BANK",0,new ImageIcon("error.png"));
		}
			
		}
		
		if(ae.getSource()==cancel)
		{
			this.dispose();
		}
	}
	
	
	
	public static void main(String s[])
	{
		new oxy_operator();
	}
}
