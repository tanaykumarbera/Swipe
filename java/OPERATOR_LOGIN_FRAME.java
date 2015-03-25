import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.File;

import javax.swing.*;


class OPERATOR_LOGIN_FRAME extends LOGIN_FRAME implements ActionListener
{
	private Acess_File af;
	ArrayList rec; 
	OPERATOR_LOGIN_FRAME()
	{
		
		super.setTitle("OPERATOR");
		setLocation(300,250);
		LOGIN.addActionListener(this);
		CANCEL.addActionListener(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent ac)
	{
		ImageIcon ficon=new ImageIcon("failed.png");
		boolean fe=true, f=true;
		af=new Acess_File();
		
		if(!(new File("D:/operator.records")).exists())
		{
			JOptionPane.showMessageDialog(this, "OPERATOR RECORD FILE NOT FOUND!","ADMINISTRATOR", 3, ficon);
			fe=false;
		}

		if(fe)
		{
					
		if(ac.getSource()==LOGIN)
		{
			
			try
			{
			rec=(ArrayList<Login_Info>)(af.read_from_file("D:/operator.records"));
			
			}catch(Exception e)
			{

				JOptionPane.showMessageDialog(this, "Oops! ERROR!	File corrupted.. request a new one.","OPERATOR", 3, ficon);
				f=false;
			}
			
		
			
			if(f)
			{
				for(int i=0;i<rec.size();i++)
				{
				Login_Info l= (Login_Info)rec.get(i);
				if((l.get_userid()).equals(USERNAME.getText()) && l.password_check(PASSWORD.getText()))
				{
					ImageIcon sicon=new ImageIcon("sucess.png");
					JOptionPane.showMessageDialog(this, "YOU HAVE BEEN LOGED IN SUCESSFULLY","OPERATOR", 3, sicon);
					f=false; new OPERATOR_CONTROL_PANEL(); break;			
				}
				}
				
				
				
			}
			if(f)
			{
				JOptionPane.showMessageDialog(this, "SORRY! WRONG USERNAME OR PASSWORD!","OPERATOR", 3, ficon);
				this.dispose();
				new Hospital_Frame();
			}
			
			this.dispose();
		}
		
		
		if(ac.getSource()==CANCEL)
		{
			new Hospital_Frame();
			this.dispose();
		}
	}
	}
	
	public static void main(String st[])
	{
		new OPERATOR_LOGIN_FRAME();
	}
}
