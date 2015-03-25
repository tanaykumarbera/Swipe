import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;


class Available_beds_frame extends JFrame implements ActionListener
{
	JLabel icu,iccu,gen,price1,price2,price3,nobed1,nobed2,nobed3,blank;
	JTextField icuf,icu_pf,iccuf,iccu_pf,genf,gen_pf;
	JButton submit, reset;
	JPanel icup,iccup,genp,butp,box;
	ArrayList<Availbeds> l;
	Available_beds_frame()
	{
		
		Container c=getContentPane();
		setLocation(300,200);
		setSize(290, 300);
		c.setBackground(Color.WHITE);
		setTitle("INDOOR BEDS");
		
		icu=new JLabel("-------------------------ICU WARD:------------------------- ");
		iccu=new JLabel("-------------------------ICCU WARD:------------------------ ");
		gen=new JLabel("------------------------GENERAL WARD:-------------------- ");
		price1=new JLabel("PRICE: Rs. ");
		price2=new JLabel("PRICE: Rs. ");
		price3=new JLabel("PRICE: Rs. ");
		nobed1=new JLabel("No. of Beds: ");
		nobed2=new JLabel("No. of Beds: ");
		nobed3=new JLabel("No. of Beds: ");
		blank=new JLabel("                                  ");
		
		icuf=new JTextField(04);
		icu_pf=new JTextField(05);
		iccuf=new JTextField(04);
		iccu_pf=new JTextField(05);
		genf=new JTextField(04);
		gen_pf=new JTextField(05);
		submit= new JButton("SUBMIT");  submit.addActionListener(this);
		reset=new JButton("RESET");		reset.addActionListener(this);
		
		icup=new JPanel(); 		icup.setLayout(new FlowLayout(FlowLayout.LEFT));
		iccup=new JPanel(); 	iccup.setLayout(new FlowLayout(FlowLayout.LEFT));
		genp=new JPanel();	 	genp.setLayout(new FlowLayout(FlowLayout.LEFT));
		butp=new JPanel();		butp.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		icup.add(nobed1); icup.add(icuf); icup.add(price1); icup.add(icu_pf);
		iccup.add(nobed2); iccup.add(iccuf); iccup.add(price2); iccup.add(iccu_pf);
		genp.add(nobed3); genp.add(genf); genp.add(price3); genp.add(gen_pf);
		butp.add(submit);	butp.add(blank);	butp.add(reset);
		
		
		box=new JPanel();
		box.setLayout(new GridLayout(7,1));
		box.add(icu);
		box.add(icup);
		box.add(iccu);
		box.add(iccup);
		box.add(gen);
		box.add(genp);
		box.add(butp);
		
		c.add(box);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==submit)
		{
		
			boolean flag=true;
			String s1=icuf.getText();
			String reg="[0-9]";
			Scanner sc =new Scanner(s1);
			String res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				icuf.setText(" ");
				icuf.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, "Enter only digits (0-9)");
			}
			
			String s2=icu_pf.getText();
			reg="[0-9]";
			sc=new Scanner(s2);	
			res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				icu_pf.setText(" ");
				icu_pf.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, "Enter only digits");
			}
			
			String s3=iccuf.getText();
			reg="^[0-9]";
			sc=new Scanner(s3);	
			res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				iccuf.setText(" ");
				iccuf.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, "Enter only digits");
			}
			String s4=iccu_pf.getText();
			 reg="[0-9]";
			 sc=new Scanner(s4);	
			 res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				iccu_pf.setText(" ");
				iccu_pf.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, "Enter only digits");
			}
			String s5=genf.getText();
			 reg="[0-9]";
			 sc=new Scanner(s5);	
			 res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				genf.setText(" ");
				genf.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, "Enter only digits");
			}
			String s6=gen_pf.getText();
			 reg="[0-9]";
			 sc=new Scanner(s6);	
			 res=sc.findInLine(reg);
			if(res==null)
			{	
				flag=false;
				gen_pf.setText(" ");
				gen_pf.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, "Enter only digits");
			}
			
			
			if(flag)
			{
				l=new ArrayList<Availbeds>();
			
				l.add(new Availbeds(s1,s3,s5,s2,s4,s6));
			
			try
				{
				File f=new File("D:/indoor_beds.records");
				FileOutputStream fout=new FileOutputStream(f);
				ObjectOutputStream oout=new ObjectOutputStream(fout);
				
				oout.writeObject(l);
				
				oout.close();
				fout.close();
				}catch(Exception er)
					{
					JOptionPane.showMessageDialog(this,"Ooops! Error: "+er.getMessage(),"Error writing the file..",0,new ImageIcon("cancel.jpg"));
						flag=false;
					
					}
			
				JOptionPane.showMessageDialog(this,"Records updated successfully..","SUCESSFULLL!!",0,new ImageIcon("sucess.jpg"));

				this.dispose();
			}	
		}
		if(e.getSource()==reset)
		{
			icuf.setText("");
			icu_pf.setText("");
			iccuf.setText("");
			iccu_pf.setText("");
			genf.setText("");
			gen_pf.setText("");
		}
		
	}

	public static void main(String s)
	{
		new Available_beds_frame();
	}
}