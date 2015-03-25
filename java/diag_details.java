import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class diag_details extends JFrame implements ActionListener
{
	JLabel name, doctors, lab,price, timings, regulations,blank;
	ArrayList<Diginfo> list;
	String docs, lbs,tims,regs,sc,pc;
	JButton req,cancel;
	JPanel fld, but,img;
	int appointment,prc=0;
	File f;
	
	diag_details(String s)
	{
		sc=s;
		Container c=getContentPane();
		setTitle(s+" Details..");
		setLocation(100,130);
		ImageIcon ig=new ImageIcon("diag_op_back.jpg");
		setSize(ig.getIconWidth(),ig.getIconHeight()+40);
		
		try
		{
			f=new File("D:/diagonastic_fields.records");
			
			if(!f.exists())	JOptionPane.showMessageDialog(this, "FILE NOT FOUND...");
			FileInputStream fin=new FileInputStream(f);
			ObjectInputStream oin=new ObjectInputStream(fin);
			
			list=(ArrayList<Diginfo>)oin.readObject();
					
		}catch(Exception er)
		{
			JOptionPane.showMessageDialog(this,"Error: "+er.getMessage(),"READING ERROR",0,new ImageIcon("error.png"));
		}
		
		docs="";
		lbs="";
		tims="";
		regs="";
		
		for(int i=0;i<list.size();i++)
		{
			
			if(((Diginfo)list.get(i)).getnam().equalsIgnoreCase(s))
			{
			docs=docs+" "+((list.get(i)).getdoc().trim())+",";
			lbs=lbs+" "+((list.get(i)).getlab().trim())+",";
			tims=tims+" ["+((list.get(i)).gettim().trim())+"],";
			regs=regs+" "+((list.get(i)).getreg().trim())+",";
			if(prc<Integer.parseInt(((list.get(i)).getprice().trim()))) pc=((list.get(i)).getprice().trim());
			appointment+=(list.get(i)).getap();
			}
		}
		
		blank=new JLabel("             ");
		name=new JLabel("*******"+s+" DETAILS*******");
		doctors=new JLabel("Doctors Available: "+docs);
		lab=new JLabel("Diagonastic labs: "+lbs);
		timings=new JLabel("Timings: "+tims);
		price=new JLabel("Price of Diagonasis: Rs. "+pc);
		regulations=new JLabel("Regulations to be maintained: "+regs);
		
		req=new JButton("REQUEST AN APPOINTMENT");
		req.addActionListener(this);
		cancel=new JButton("CANCEL");
		cancel.addActionListener(this);
		
		but=new JPanel();
		but.add(req);
		but.add(blank);
		but.add(cancel);
		
		fld=new JPanel();
		fld.setLayout(new GridLayout(0,1));
		fld.add(name);
		fld.add(doctors);
		fld.add(lab);
		fld.add(price);
		fld.add(timings);
		fld.add(regulations);
		fld.add(but);
		fld.setBackground(Color.WHITE);
		but.setBackground(Color.WHITE);
		
		
		img=new JPanel()
		{
			public void paintComponent(Graphics background)
			{
			ImageIcon i=new ImageIcon("diag_op_back.jpg");
			Image image=i.getImage();
			background.drawImage(image,0,0,null);
			setBounds(0,0,i.getIconWidth(),i.getIconHeight());
			setSize(i.getIconWidth(),i.getIconHeight());
			}
		};
		
		img.setLayout(new FlowLayout(FlowLayout.RIGHT));
		img.add(fld);
		
		c.add(img);
		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==req)
		{
			if(appointment<=10)
			{
				
				
			if(JOptionPane.showConfirmDialog(this, "You appointment will be queued after "+appointment+" patients..Confirm appointment??")==JOptionPane.YES_OPTION)						

			 {
				
				new Gen("Diagonastic",pc);
				++appointment;
				
				
				for(int i=0;i<list.size();i++)
				{
					
					if(((Diginfo)list.get(i)).getnam().equalsIgnoreCase(sc))
					{
						Diginfo r=list.get(i);
						r.setap(appointment);
						
						list.set(i,r);
					}
				}
				
				
			}
			try
			{
			
			FileOutputStream fout=new FileOutputStream(f);
			ObjectOutputStream oout=new ObjectOutputStream(fout);
			oout.writeObject(list);
			oout.close();
			fout.close();
			}catch(Exception err)
			{
				JOptionPane.showMessageDialog(this, "Error: "+err.getMessage());
			}
			
			}
			this.dispose();
			
		}
		
		if(e.getSource()==cancel)
		{
			this.dispose();
		}
	}
	
	public static void main(String s[])
	{
		new diag_details("ECG");
	}
	
}
