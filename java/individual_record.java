import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

class individual_record extends JFrame implements ActionListener
{
	JLabel name, mobile;
	JTextField nam,mob;
	JPanel fld;
	JButton search;
	ArrayList<Appoinfo> list;
	
	individual_record()
	{
		Container c=getContentPane();
		
		setSize(300,150);
		setLocation(200,200);
		setTitle("PATIENT SEARCH");
		name=new JLabel("NAME: ");
		mobile=new JLabel("MOBILE No.:"); 
		
		nam=new JTextField(12);
		mob=new JTextField(12);
		
		search=new JButton(new ImageIcon("patient_search.gif"));
		search.setBackground(Color.WHITE);
		search.setToolTipText("Click to search about a single patient..");
		search.addActionListener(this);
		
		fld=new JPanel();
		fld.setBackground(Color.WHITE);
		fld.setLayout(new GridLayout(0,1));
		fld.add(name);
		fld.add(nam);
		fld.add(mobile);
		fld.add(mob);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(fld);
		add(search);
		
		c.setBackground(Color.WHITE);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==search)
		{
			ArrayList<Integer> ind_list=new ArrayList<Integer>();
			boolean fl=true, flag=true;
			int index=-1,j=0;
			String n=nam.getText();
			  String reg="^[a-zA-Z]";
				Scanner sc=new Scanner(n);
			  String res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				nam.setText("");
				JOptionPane.showMessageDialog(this, "The name must contain only letters(a-z or A-Z)");
			}
			
			String m=mob.getText();
			  reg="^[0-9]";
			  sc=new Scanner(m);
				
			 res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				mob.setText("");
				JOptionPane.showMessageDialog(this, "The mobile number must contain only digits and must be min 10 digits)");
			}
			
			if(flag)
			
			{
			File f=new File("D:/user_databases.records");
			if(!f.exists())
				JOptionPane.showMessageDialog(this,"Oops...patients record file not found!!");
			else
				{
					try
					{
						FileInputStream fin =new FileInputStream(f);
						ObjectInputStream oin=new ObjectInputStream(fin);
						
						list=(ArrayList<Appoinfo>) oin.readObject();
						
						oin.close();
						fin.close();
					}catch(Exception er)
					{
						JOptionPane.showMessageDialog(this,"Error: "+er.getMessage());
					}
					
					
					for(int i=0;i<list.size();i++)
						if	(((((Appoinfo)list.get(i)).getn()).equals(n))	&&	((((Appoinfo)list.get(i)).getmob()).equals(m)))
						{
							ind_list.add(i);
							++j;
							fl=false;
						}
					
					if(fl)
						JOptionPane.showMessageDialog(this,"Sorry..patient named "+n+" with mobile no: "+m+" was not found in the record list!","NO RESULT FOUND",0,new ImageIcon("error.png"));	
					else
					{
						
						new ind_show_info(ind_list,j);
						this.dispose();
						
					}
				}	
					
					
			}			
		
		}
		
		
	}
	
	
	public static void main(String s[])
	{
		new individual_record();
	}
	
}