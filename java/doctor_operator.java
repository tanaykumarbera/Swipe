import java.io.*;
import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class doctor_operator extends JFrame implements ActionListener
{

	String fl,tit;
	ArrayList<String> options;
	File f;
	ArrayList list;
	Doctorinfo r;
	
	JLabel doc;
	JTable table;
	JComboBox dropdn;
	
	doctor_operator(String s,String flc)
	{
		fl=flc;
		tit=s;
		doc=new JLabel("Choose a department to proceed :");
		readdoc();
		
		Container c=getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER));
		c.add(doc);
		c.add(dropdn);
		c.setBackground(Color.WHITE);
		dropdn.addActionListener(this);
		setSize(350,70);
		setLocation(224,245);
		setTitle(s);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	
	void readdoc()
	{
		try
		{
			f=new File(fl);
			FileInputStream fin=new FileInputStream(f);
			ObjectInputStream oin=new ObjectInputStream(fin);
			
			list= (ArrayList<Doctorinfo>) oin.readObject();
			
			oin.close();
			fin.close();
	
		}catch(Exception er)
		{
			JOptionPane.showMessageDialog(this, "Error while reading file: "+er.getMessage());
		}
		
		
		options=new ArrayList<String>();
		
		
		
		for(int i=0;i<list.size();i++)
		{
			boolean flag=true;
			
			for(int j=0;j<options.size();j++)
				if(((String)options.get(j)).equalsIgnoreCase(((Doctorinfo)list.get(i)).getdept()))
						flag=false;
			if(flag)
			options.add(((Doctorinfo)list.get(i)).getdept());
		}
		
		
		dropdn=new JComboBox();
		dropdn.setBackground(Color.WHITE);
		
		
		for(int i=0;i<options.size();i++)
		dropdn.addItem((String)options.get(i));
		
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==dropdn)
		{
			ArrayList<Integer> ind=new ArrayList<Integer>(1);
			int ii=0;
			for(int i=0;i<list.size();i++)
			{
				if(((String)dropdn.getSelectedItem()).equalsIgnoreCase(((Doctorinfo)list.get(i)).getdept()))
						{
							ind.add(ii++,i);
						}
			}
			
			
			
			new doc_details(tit, fl, ind);
			this.dispose();
			
		}
	}
	
	public static void main(String s[])
	{
		new doctor_operator("Indoor","D:/indoor_doctors.records");
	}
	
	
}
