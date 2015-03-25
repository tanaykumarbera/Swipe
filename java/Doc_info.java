import javax.swing.*;

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



class Doc_info extends JFrame implements ActionListener
{
	JPanel com_panel, img_panel,field_panel,but_panel,deg_panel;
	JTextField name,department,fees,timing; 
	JLabel nam,dept,deg,fis,tim,blank;
	JButton submit,reset,cancel;
	JComboBox degree;
	ArrayList<Doctorinfo> list;
	String filelocation;
	File f;
	Doc_info(String fl)
	{
		filelocation=fl;
		f=new File(filelocation);
		setTitle("ADDING A NEW DOCTOR");
		Container c=getContentPane();
		setLocation(300,200);
		ImageIcon imb=new ImageIcon("doc_sidebar.jpg");
		this.setSize((imb.getIconWidth()+300), imb.getIconHeight()+40);
		this.setBackground(Color.WHITE);
		img_panel=new JPanel()
		{
			public void paintComponent(Graphics l)
			{
				ImageIcon im=new ImageIcon("doc_sidebar.jpg");
				this.setSize(im.getIconWidth(), im.getIconHeight());
				Image ig=im.getImage();
				this.setBackground(Color.WHITE);
				l.drawImage(ig,0,0,null);
				this.setBounds(0, 0, im.getIconWidth(), im.getIconHeight());
			}
		};
		
		Font f1=new Font("Arial Rounded MT Bold",Font.PLAIN, 20);
		Font f2=new Font("Times New Roman",Font.PLAIN, 14);
		
		field_panel=new JPanel();
		field_panel.setLayout(new GridLayout(11,1));
		nam=new JLabel("Doctor's Name: "); nam.setFont(f1); nam.setForeground(Color.DARK_GRAY);
		dept=new JLabel("Department: "); dept.setFont(f1); dept.setForeground(Color.DARK_GRAY);
		fis=new JLabel("Visit charges: "); fis.setFont(f1); fis.setForeground(Color.DARK_GRAY);
		tim=new JLabel("Availability: "); tim.setFont(f1); tim.setForeground(Color.DARK_GRAY);
		deg=new JLabel("Degree: "); deg.setFont(f1);	
		blank=new JLabel("         ");
		
		name=new JTextField("e.g. Suresh Mondal",20); name.setFont(f2); //name.setForeground(Color.LIGHT_GRAY);
		department=new JTextField("e.g. ECG, etc",20); department.setFont(f2); //department.setForeground(Color.LIGHT_GRAY);
		fees=new JTextField("e.g. 500.00 or 750.00, etc",20); fees.setFont(f2); //fees.setForeground(Color.LIGHT_GRAY);
		timing=new JTextField("e.g. 7:20am - 2:30pm, etc",20); timing.setFont(f2); //timing.setForeground(Color.LIGHT_GRAY);
		
		
		String s[]={"MBBS","MS","MD"};
		degree=new JComboBox(s);
		degree.addActionListener(this);
		//degree.addItemListener(this);
		deg_panel=new JPanel();
		deg_panel.add(deg);
		deg_panel.add(degree);
		deg_panel.setBackground(Color.WHITE);
		
		but_panel=new JPanel();
		submit=new JButton("SUBMIT");
		reset=new JButton("RESET");
		cancel=new JButton("CANCEL");
		
		
		
		submit.addActionListener(this);
		reset.addActionListener(this);
		cancel.addActionListener(this);
		
		but_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		but_panel.add(submit);
		but_panel.add(reset);
		but_panel.add(cancel);
		
		field_panel.add(nam);
		field_panel.add(name);
		field_panel.add(dept);
		field_panel.add(department);
		field_panel.add(deg_panel);
		field_panel.add(fis);
		field_panel.add(fees);
		field_panel.add(tim);
		field_panel.add(timing);
		field_panel.add(blank);
		field_panel.add(but_panel);
		field_panel.setBackground(Color.WHITE);
		
		com_panel=new JPanel();
		com_panel.setLayout(new FlowLayout(FlowLayout.RIGHT) );
		com_panel.setSize((imb.getIconWidth()+100), imb.getIconHeight());
		com_panel.setBackground(Color.WHITE);
		
		com_panel.add(field_panel);
		com_panel.add(img_panel);
		
		c.add(com_panel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
			
		
	}
	
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{
		
		
		if(e.getSource()==degree)
		{
			JOptionPane.showMessageDialog(this,"Doctor's degree set to "+(String)degree.getSelectedItem(),"Notice",0,new ImageIcon("sucess.jpg"));
		}
		
		
		if(e.getSource()==submit)
		{
			boolean flag=true;
	
			
			String s1=name.getText();
			String reg="^[a-zA-Z]"+"";
			Scanner sc =new Scanner(s1);
			String res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				name.setText("");
				name.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, "The name must contain only letters(a-z or A-Z or blank)");
			}
			
			
			String s2=department.getText();
			reg="^[a-zA-Z]";
			sc=new Scanner(s2);
			 res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				department.setText("");
				department.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, "The department must contain only letters(a-z or A-Z)");
			}
			
			
			String s3=fees.getText();
			reg="^[0-9]";
			sc=new Scanner(s3);
			res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				fees.setText("");
				fees.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, "The price must contain only digits");
			}
			
			
			
			String s4=timing.getText();
			String s5=(String)degree.getSelectedItem();
			
			
			if(flag)
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
					JOptionPane.showMessageDialog(this,"No previous records found!! Created new one..","Writing Error..",0, new ImageIcon("cancel.jpg"));
					list=new ArrayList<Doctorinfo>();
				}
				
				
				
				
				list.add(new Doctorinfo(s1,s2,s5,s3,s4,0));
				
				try
				{
					FileOutputStream fout=new FileOutputStream(f);
					ObjectOutputStream oout=new ObjectOutputStream(fout);
					
					oout.writeObject(list);
					
					oout.close();
					fout.close();
				}catch(Exception err)
				{
					JOptionPane.showMessageDialog(this,"Ooops... error: "+err.getMessage(),"Writing Error..",0, new ImageIcon("cancel.jpg"));
				
				}
				
				if(JOptionPane.showConfirmDialog(this, "Record added sucessfully!!  Want to add another Doctor to the Hospital list??")==JOptionPane.NO_OPTION)
				{
					this.dispose();
				}
				
				name.setText("");	name.setBackground(Color.WHITE);
				department.setText("");	department.setBackground(Color.WHITE);
				fees.setText("");	fees.setBackground(Color.WHITE);
				timing.setText("");	timing.setBackground(Color.WHITE);
				//degree.setSelectedItem("a");
				
			}
		}
			

	
		if(e.getSource()==reset)
		{
			name.setText("");
			department.setText("");
			fees.setText("");
			timing.setText(""); 
		}
		
		
		if(e.getSource()==cancel)
		{
			this.dispose();
		}
	}
	
	
	
	
	public static void main(String g[])
	{
		new Doc_info("d:/indoc.rec");
	}
}