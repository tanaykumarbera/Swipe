import java.awt.Color;
import java.awt.Container;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;


class Gen extends JFrame implements ActionListener
{
	

   
    String fileLocation="D:/user_databases.records";
    
    Appoinfo r;
    ArrayList list;
    File f;
	int p,a1,id,s;
	JLabel name, doctor, mobile, amount,section,blank;
	JComboBox sec;
	JTextField nam,doc,mob,amt;
	JButton confirm, cancel;
	JPanel img,fld;
	
	
	Gen(String dt,String at)
	{
		
		boolean flag=false;
		int ind=0;
		Container c=getContentPane();
		c.setBackground(Color.WHITE);
		setTitle("Patient Database");
		setLocation(200,200);
		
		fld= new JPanel();
		fld.setBackground(Color.WHITE);
		setSize(400,400);
		
		String st[]={"Outdoor","Indoor","Diagonastic","Pharmacy","Blood Bank","Oxygen Cylinder","Ambulance",};
		sec=new JComboBox(st);
		
		name=new JLabel("Name of patient: ");
		doctor=new JLabel("Reffering Doctor: ");
		section=new JLabel("Section: ");
		amount=new JLabel("Amount due: ");
		mobile=new JLabel("Mobile no.: ");
		
		for(int i=0;i<7;i++)
		{
			if(dt.equalsIgnoreCase(st[i]))
			{
				flag=true; ind=i;
			}
			
		}
		
		
		nam=new JTextField(15);
		doc=new JTextField(15);
		mob=new JTextField(10);
		amt=new JTextField(5);
		

		if(flag)
		{
		sec.setSelectedIndex(ind);
		amt.setText(at);
		amt.setEditable(false);
		sec.setEnabled(false);
		
		}
		
		
		
		blank=new JLabel(" ");
		
		img=new JPanel()
		{

			public void paintComponent(Graphics background)
			{
				
				
				ImageIcon i=new ImageIcon("gen_back.jpg");
				Image image=i.getImage();
				background.drawImage(image,0,0,null);
				setBounds(0,0,i.getIconWidth(),i.getIconHeight());
				setSize(i.getIconWidth(),i.getIconHeight());
			}
		};
		setLayout(new GridLayout(2,1));
		
		cancel=new JButton("CANCEL");
		cancel.addActionListener(this);
		
		confirm=new JButton("CONFIRM");
		confirm.addActionListener(this);
		
		fld.setLayout(new GridLayout(0,2));
		
		fld.add(name);	fld.add(nam);
		fld.add(doctor);	fld.add(doc);
		fld.add(mobile);	fld.add(mob);
		fld.add(section);	fld.add(sec);
		fld.add(amount);	fld.add(amt);
		
		fld.add(confirm);	fld.add(cancel);
		
		add(img);
		add(fld);
		sec.addActionListener(this);
	
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==sec)
			JOptionPane.showMessageDialog(this, "Section set to "+((String)sec.getSelectedItem()));
		
		if(ae.getSource()==confirm)
		{
			try
			{
			check();
			}catch(Exception e3)
			{
		        JOptionPane.showMessageDialog(this, "error: "+e3.getMessage(),"ERROR!!",0,new ImageIcon("error.jpg") );
			}
		
		}
	
		if(ae.getSource()==cancel)
		{
			this.dispose();
		}
	}
	
	
	
    public void check() throws Exception
	{
    	
    	boolean flag1=true;

    	
        String s1=nam.getText();
        String reg="^[a-zA-Z]";
		Scanner sc=new Scanner(s1);
	  String res=sc.findInLine(reg);
	if(res==null)
	{
		flag1=false;
		nam.setText(""); nam.setBackground(Color.RED);
		JOptionPane.showMessageDialog(this, "The name must contain only letters(a-z or A-Z)");
	}
          String s2=mob.getText();
          reg="^[0-9]";
		  sc=new Scanner(s2);
	     res=sc.findInLine(reg);
	if(res==null)
	{
		flag1=false;
		mob.setText("");	mob.setBackground(Color.RED);
		JOptionPane.showMessageDialog(this, "The mobile number must contain only digits");
	}
        String s4=doc.getText();
        reg="^[a-zA-Z]";
		  sc=new Scanner(s4);
	     res=sc.findInLine(reg);
	if(res==null)
	{
		flag1=false;
		
		doc.setText("");	doc.setBackground(Color.RED);
		JOptionPane.showMessageDialog(this, "The  doctor's name must contain only letters(a-z or A-Z");
	}
        
        
        String samt=amt.getText();
        reg="^[0-9 ]";
		  sc=new Scanner(samt);
	     res=sc.findInLine(reg);
	if(res==null)
	{ 
		flag1=false;
		amt.setText("");	amt.setBackground(Color.RED);
		JOptionPane.showMessageDialog(this, "The Amount field must contain only digits");
	}
    
   
	if(flag1)
	{
        try
        { 
        f=new File(fileLocation);
        FileInputStream fin=new FileInputStream(f);
        ObjectInputStream ois=new ObjectInputStream(fin);
        
        list=(ArrayList<Appoinfo>)ois.readObject();
        fin.close();
        ois.close();
        }
        catch(Exception e2)
        {
        JOptionPane.showMessageDialog(this, "FILE NOT FOUND!! creating file for the first time and proceeding..","ERROR!!",0,new ImageIcon("error.jpg") );
		list=new ArrayList<Appoinfo>();
        }
        
	  
	  
	        JOptionPane.showMessageDialog(this, "YOUR REQUEST HAS BEEN GRANTED" ,"SUCCESS!!",0,new ImageIcon("success.jpg") );
	  
	   			FileOutputStream fout=new FileOutputStream(f);
        		ObjectOutputStream oos=new ObjectOutputStream(fout);
			
        		Random idr= new Random();
        		id=idr.nextInt(6000);
          	
        
                
                int ico=Integer.parseInt(samt);
                String s3=(String) sec.getSelectedItem();
         
                
                Calendar c=Calendar.getInstance();
                SimpleDateFormat format1=new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat format2=new SimpleDateFormat("dd MMM yyyy");
                String s6= format1.format(c.getTime());
                String s5= format2.format(c.getTime());
                
                JOptionPane.showMessageDialog(this,"Your randomly generated Customer ID: "+id);
                list.add(new Appoinfo(id,s1,s2,s3,s4,s5,s6,ico));
                
                oos.writeObject(list);
                oos.close();
                fout.close();
                
        
                JOptionPane.showMessageDialog(this,"Entry sucessfully updated!!","SUCESS!!",0,new ImageIcon("success.jpg"));
                this.dispose();
                
	}   
	}

	
	public static void main(String s[])
	{
		new Gen("","");
	}
	
	
   }            
