import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;


public class doc_remove extends JFrame implements ActionListener
{
	JLabel name,deg;
	JTextField nam;
	JComboBox degree;
	String fl;
	File f;
	JPanel img,fld,fld2,but;
	JButton del,cancel;
	ArrayList<Doctorinfo> list;
	
	doc_remove(String s)
	{
		fl=s;
		
		Container con=getContentPane();
		
		name=new  JLabel("Enter name of doctor: ");
		nam=new JTextField(15);
		
		deg=new JLabel("Degree: ");
		
		ImageIcon i=new ImageIcon("doc_rem_back,jpg");
		setSize(i.getIconWidth(),i.getIconHeight());
		
		img=new JPanel()
		{
			public void paintComponent(Graphics background)
			{
			ImageIcon i=new ImageIcon("amb_back.jpg");
			Image image=i.getImage();
			background.drawImage(image,0,0,null);
			setBounds(0,0,i.getIconWidth(),i.getIconHeight());
			setSize(i.getIconWidth(),i.getIconHeight());
			}
		};
		
		String st[]={"MBBS","MS","MD"};
		degree=new JComboBox(st);
		
		del=new JButton("DELETE");
		del.addActionListener(this);
		
		cancel= new JButton("CANCEL");
		cancel.addActionListener(this);
		
		
		
		but=new JPanel();
		but.add(del);
		but.add(cancel);
		
		fld=new JPanel();
		fld.setLayout(new GridLayout(5,1));
		fld.add(name);
		fld.add(nam);
		fld.add(deg);
		fld.add(degree);
		fld.add(but);
		
		
		con.add(fld);
		setVisible(true);
		
		setResizable(false);
		setSize(250,200);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		f=new File(fl);
		
		if(e.getSource()==del)
		{
			
			String n=nam.getText();
			Scanner sc=new Scanner(n);
			String reg="^[a-zA-Z]";
			boolean flag=true;	
			 String res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				name.setText("");
				name.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, "The name must contain only letters(a-z or A-Z)");
			}
			
			
		if(flag)
		{
			String d=(String) degree.getSelectedItem();
			
			try
			{
			
			FileInputStream fin=new FileInputStream(f);
			ObjectInputStream oin=new ObjectInputStream(fin);
			
			list=(ArrayList<Doctorinfo>) oin.readObject();
			oin.close();
			fin.close();
			}catch(Exception er)
			{
				if(er.getMessage()==null)
				{
					JOptionPane.showMessageDialog(this, "Ooopss... theres no doctor in your list!!","ERRORRRR.. ", 0,new ImageIcon("error.png"));
				}
				
				else JOptionPane.showMessageDialog(this, "Ooopss... error: "+er.getMessage(),"Writing Error.. ", 0,new ImageIcon("error.png"));

			}
			
			boolean fg=true,fga=true;;
			for(int i=0;i<list.size();i++)
			{
				if((n.equals(list.get(i).getname()))&&(d.equals(list.get(i).getdegr())))
				{
					fga=false;
					if(JOptionPane.showConfirmDialog(this,"Doctor "+n+" ["+d+"] found!! Are you sure you want to delete??")==JOptionPane.YES_OPTION)
					{
						list.remove(i);
						fg=false; 
						break;
					}
				}
				
			}
			
			if(fga)
				JOptionPane.showMessageDialog(this, "Ooopss... theres no doctor named "+n+" with degree "+d+" in your list!!");

			if(!fg)
			{
				try
				{
					FileOutputStream fout=new FileOutputStream(f);
					ObjectOutputStream oout=new ObjectOutputStream(fout);
					
					oout.writeObject(list);
					oout.close();
					fout.close();
					
				}catch(Exception err)
				{
					JOptionPane.showMessageDialog(this, "Ooopss...Error: "+err.getMessage(),"ERROR WRITING BACK TO FILE",0,new ImageIcon("error.jpg"));

				}
			}	
			
			if(!fg)
			JOptionPane.showMessageDialog(this, "Records updated Successfully!!","NOTICE",0,new ImageIcon("sucess.jpg"));

			this.dispose();
			
			
		}	
			
		}
		
		if(e.getSource()==cancel)
		{
			this.dispose();
		}
	}
	
	
	public static void main(String s[])
	{
		new doc_remove("d:/indoc.rec");
	}
}
