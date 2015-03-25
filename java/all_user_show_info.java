import java.util.*;
import java.awt.*;
import javax.swing.*;

import java.io.*;

public class all_user_show_info extends JFrame
{
	JTable ind;
	JScrollPane sp;
	JLabel label,rev,user;
	JPanel fld;
	int p=0;
	
	File f=new File("D:/user_databases.records");
	ArrayList<Appoinfo> list;
	
	
	all_user_show_info()
	{
		
		setLocation(300,300);
		try
		{
			FileInputStream fin=new FileInputStream(f);
			ObjectInputStream oin=new ObjectInputStream(fin);
			
			list=(ArrayList<Appoinfo>)oin.readObject();
			
			oin.close();
			fin.close();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
		
		String data[][]=new String[list.size()][8];
		String head[]={"PATIENT ID","NAME","MOBILE","SECTION","DOCTOR","AMOUNT","DATE","TIME"};
		
		for(int k=0;k<list.size();k++)
		{
			
			data[k][0]=""+(((Appoinfo)list.get(k)).getid());
			data[k][1]=((Appoinfo)list.get(k)).getn();
			data[k][2]=((Appoinfo)list.get(k)).getmob();
			data[k][3]=((Appoinfo)list.get(k)).getsec();
			data[k][4]=((Appoinfo)list.get(k)).getdoc();
			data[k][5]=""+(((Appoinfo)list.get(k)).geticost());
			data[k][6]=((Appoinfo)list.get(k)).getdate();
			data[k][7]=((Appoinfo)list.get(k)).gettime();
			p+=((Appoinfo)list.get(k)).geticost();
		}
		
		ind=new JTable(data,head);
		sp=new JScrollPane(ind);
		
		
		label=new JLabel("Showing whole user database...");
		user=new JLabel("Total user enries made in the database = "+(list.size()));
		rev=new JLabel("Total revenue collected = Rs "+p);
		
		fld=new JPanel();
		fld.setLayout(new GridLayout(3,1));
		fld.add(label);
		fld.add(user);
		fld.add(rev);
		fld.setBackground(Color.LIGHT_GRAY);
		
		Container c=getContentPane();
		c.setLayout(new GridLayout(2,1));
		setTitle("USER DATABASE:");
		
		
		c.add(fld);
		c.add(sp);
		
		setVisible(true);
		setSize(700,250);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static void main(String s[])
	{
		new all_user_show_info();
	}
}
