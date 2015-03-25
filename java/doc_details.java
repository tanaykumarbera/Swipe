import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class doc_details extends JFrame implements ActionListener
{
	JLabel lab,nam,deg,sec,dept,totd;
	JTextField name,degree;
	JButton butt;
	JPanel tab,fldd,fldu;
	JTable table;
	JScrollPane sp;
	File f;
	String ss;
	Doctorinfo r;
	ArrayList<Doctorinfo> list;
	ArrayList<Integer> indc;
	
	
	void read()
	{
		try
		{
			
			FileInputStream fin=new FileInputStream(f);
			ObjectInputStream oin=new ObjectInputStream(fin);
			
			list= (ArrayList<Doctorinfo>) oin.readObject();
			
			oin.close();
			fin.close();
	
		}catch(Exception er)
		{
			JOptionPane.showMessageDialog(this, "Error while reading file: "+er.getMessage());
		}
		
	}
	
	
	
	doc_details(String s,String fl,ArrayList<Integer> ind)
	{
		ss=s;
		indc=ind;
		f=new File(fl);
		setTitle(s);
		lab=new JLabel("LIST OF ALL AVAILABLE DOCTORS...");
		read();
		String head[]={"DOCTOR NAME","DEPARTMENT","DEGREE","VISIT CHRGs","TIMINGS","VACCANCY"};
		String data[][]=new String[ind.size()][6];
		
		int k=0;
		for(int j=0;j<ind.size();j++)
		{
			r=(Doctorinfo) list.get(ind.get(j));
			data[j][0]=r.getname();
			data[j][1]=r.getdept();
			data[j][2]=r.getdegr();
			data[j][3]=r.getprice();
			data[j][4]=r.gettiming();
			data[j][5]=""+(10-r.getapp());
			++k;
		}
		
		sec=new JLabel("SECTION : "+s);
		dept=new JLabel("DEPARTMENT : "+r.getdept());
		totd=new JLabel("TOTAL DOCTORS AVAILABLE = "+k);
		
		
		fldu=new JPanel();
		fldu.setLayout(new GridLayout(4,1));
		fldu.add(lab);
		fldu.add(sec);
		fldu.add(dept);
		fldu.add(totd);
		
		table=new JTable(data,head);
		sp=new JScrollPane(table);
		
		fldd=new JPanel();
		fldd.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		nam=new JLabel("NAME OF DOCTOR: ");
		deg=new JLabel("DEGREE: ");
		
		name=new JTextField(10);
		degree=new JTextField(8);
		butt=new JButton("GET APPOINTMENT!");
		butt.addActionListener(this);
		
		fldd.add(nam);
		fldd.add(name);
		fldd.add(deg);
		fldd.add(degree);
		fldd.add(butt);
		
		Container c=getContentPane();
		c.setLayout(new GridLayout(3,1));
		c.add(fldu);
		c.add(sp);
		c.add(fldd);
		
		setSize(700,270);
		setLocation(20,30);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==butt)
		{
			
			boolean fg=true;
			int k=0,index=-1;
			for(int i=0;i<indc.size();i++)
			{
				r= (Doctorinfo) list.get(indc.get(i));
				
				if ( (name.getText()).equalsIgnoreCase(r.getname()) &&   (degree.getText()).equalsIgnoreCase(r.getdegr()) 	)		
				{
					fg=false;
					index=(i);
					break;
					
				}
			}
			
			if(fg)
			{
				JOptionPane.showMessageDialog(this,"INVALID ENTRY..check for name and degree field","WARNING",0,new ImageIcon("error.png"));
			}
			
			else
			{
				if(JOptionPane.showConfirmDialog(this, "You will be after "+r.getapp()+" patient(s)..Are you sure to appoint a meeting??")==JOptionPane.YES_OPTION)
				{
					r.setapp();
					
					list.remove(index);
					list.add(index,r);
					
				try
					{
					FileOutputStream fout=new FileOutputStream(f);
					ObjectOutputStream oout=new ObjectOutputStream(fout);
					
					oout.writeObject(list);
					
					oout.close();
					fout.close();
					}catch(Exception err)
					{
						JOptionPane.showMessageDialog(this,"Error: "+err.getMessage(),"WARNING",0,new ImageIcon("error.png"));
					}
				
				JOptionPane.showMessageDialog(this,"APPOINTMENT FIXED!! BILLED AMOUNT: Rs "+r.getprice(),"SUCCESS",0,new ImageIcon("sucess.jpg"));
				JOptionPane.showMessageDialog(this,"Enter patients details below..");
				new Gen(ss,r.getprice());
				this.dispose();
				
				}
				else this.dispose();
			}
			
		
		}	
			
		
		
	}
	
}
