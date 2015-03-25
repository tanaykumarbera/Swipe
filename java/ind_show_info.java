import java.util.*;
import java.awt.*;

import javax.swing.*;

import java.io.*;

public class ind_show_info extends JFrame
{
	JTable ind;
	int index,b=0,p=0;
	JScrollPane sp;
	JPanel fld;
	JLabel name, mobile,cost;
	
	File f=new File("D:/user_databases.records");
	ArrayList<Appoinfo> list;
	
	
	ind_show_info(ArrayList<Integer> ind_list,int j)
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
		
		
		String data[][]=new String[j][8];
		String head[]={"PATIENT ID","NAME","MOBILE","SECTION","DOCTOR","AMOUNT","DATE","TIME"};
		
		for(int k=0;k<j;k++)
		{
			index=(int)ind_list.get(b++);
			
			data[k][0]=""+(((Appoinfo)list.get(index)).getid());
			data[k][1]=((Appoinfo)list.get(index)).getn();
			data[k][2]=((Appoinfo)list.get(index)).getmob();
			data[k][3]=((Appoinfo)list.get(index)).getsec();
			data[k][4]=((Appoinfo)list.get(index)).getdoc();
			data[k][5]=""+(((Appoinfo)list.get(k)).geticost());
			data[k][6]=((Appoinfo)list.get(k)).getdate();
			data[k][7]=((Appoinfo)list.get(k)).gettime();
			p+=((Appoinfo)list.get(index)).geticost();
		}
		
		ind=new JTable(data,head);
		sp=new JScrollPane(ind);
		
		Container c=getContentPane();
		c.setLayout(new GridLayout(2,1));
		
		name=new JLabel("NAME: "+data[0][1]);
		mobile=new JLabel("MOBILE No: "+data[0][2]);
		cost=new JLabel("TOTAL BILL AMOUNT = Rs "+p);
		
		fld=new JPanel();
		fld.setLayout(new GridLayout(3,1));
		fld.setBackground(Color.LIGHT_GRAY);
		fld.add(name);
		fld.add(mobile);
		fld.add(cost);
		
		setTitle("SEARCH RESULT ::"+data[0][1]);
		
		
		c.add(fld);
		c.add(sp);
		
		setVisible(true);
		setSize(700,200);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
}
