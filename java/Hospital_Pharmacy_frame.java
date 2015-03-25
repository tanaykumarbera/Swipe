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

class Hospital_Pharmacy_frame extends JFrame implements ActionListener
{
	JPanel img_panel,fd_panel,bt_panel;
	JLabel LABEL,genre,name,stock,price,blank1,blank2;
	
	JTextField genf,namf,stkf,pf;
	JButton update;
	ArrayList<Availmed> list;
	Availmed r;
	File f;
	
	Hospital_Pharmacy_frame()
	{
		
		Container c=getContentPane();
		
		ImageIcon i=new ImageIcon("phm_back.jpg");
		setSize(i.getIconWidth(),i.getIconHeight());
		
		LABEL=new JLabel("---ADD A NEW MEDICINE---");
		genre=new JLabel("Medicine Genre:");
		name=new JLabel("Medicine Name");
		stock=new JLabel("Medicine in Stock:");
		price=new JLabel("Price: (INR)");
		blank1=new JLabel("");
		blank2=new JLabel("");
		
		genf=new JTextField(15);
		namf=new JTextField(15);
		stkf=new JTextField(15);
		pf=new JTextField(15);
		
		
		fd_panel=new JPanel();
		fd_panel.add(LABEL);
		fd_panel.add(genre);
		fd_panel.add(genf);
		fd_panel.add(name);
		fd_panel.add(namf);
		fd_panel.add(stock);
		fd_panel.add(stkf);
		fd_panel.add(price);
		fd_panel.add(pf);
		
		update=new JButton("UPDATE", new ImageIcon("update.png"));
		update.setRolloverIcon(new ImageIcon("update_r.png"));
		update.setPressedIcon(new ImageIcon("update.png"));
		update.addActionListener(this);
		
		bt_panel=new JPanel();
		bt_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bt_panel.add(update);
		
		fd_panel.add(bt_panel);
		
		img_panel=new JPanel()
		{
			public void paintComponent(Graphics background)
			{
			ImageIcon i=new ImageIcon("phm_back.jpg");
			Image image=i.getImage();
			background.drawImage(image,0,0,null);
			setBounds(0,0,i.getIconWidth(),i.getIconHeight());
			setSize(i.getIconWidth(),i.getIconHeight());
			}
		};
		
		img_panel.setLayout(new GridLayout(1,2));
		img_panel.add(blank1);
		img_panel.add(fd_panel);
		
		add(img_panel);
		
		setResizable(false);
		setLocation(120,132);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==update)
		{
			boolean flag=true;
			
			
			String s1=genf.getText();
			String reg="^[a-z A-Z]";
			Scanner sc =new Scanner(s1);
			String res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				genf.setBackground(Color.RED);
				genf.setText("");
				JOptionPane.showMessageDialog(this, "The genre must contain only letters(a-z or A-Z)");
			}
			
			
			String s2=namf.getText();
			reg="^[a-z A-Z]";
			sc =new Scanner(s2);
		    res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				namf.setText("");
				namf.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, "The name must contain only letters(a-z or A-Z)");
			}
			
			String s3=stkf.getText();
			 reg="^[0-9]";
			 sc =new Scanner(s3);
			 res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				stkf.setText("");
				stkf.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, "Enter only digits(0-9) for stock!");
			}
			
			String s4=pf.getText();
			reg="^[0-9]";
			sc =new Scanner(s4);
			 res=sc.findInLine(reg);
			if(res==null)
			{
				flag=false;
				pf.setText("");
				pf.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this, "Enter only digits(0-9) for price!");
			}
			
			
			
				if(flag)
				{	
					
					
					try
					{
						f=new File("D:/medical_stores.records");
						FileInputStream fin=new FileInputStream(f);
						ObjectInputStream oin=new ObjectInputStream(fin);
						
						list=(ArrayList<Availmed>)oin.readObject();
								
					}catch(Exception er)
					{
						
						JOptionPane.showMessageDialog(this,"Error: "+er.getMessage()+" Creating a new file..","READING ERROR",0,new ImageIcon("error.png"));
						list= new ArrayList<Availmed>();
					
					}
					
					
					
					
					list.add(new Availmed(s1,s2,s3,s4));
					
					try
					{
						FileOutputStream fout=new FileOutputStream(f);
						ObjectOutputStream oout=new ObjectOutputStream(fout);
						
						oout.writeObject(list);
					}catch(Exception er)
					{
						
						JOptionPane.showMessageDialog(this,"Error: "+er.getMessage(),"WRITTING ERROR",0,new ImageIcon("error.png"));
					
					}
					
					if(JOptionPane.showConfirmDialog(this, "RECORD ADDED SUCESSFULLY...Want to another??")==JOptionPane.NO_OPTION)
					{
						this.dispose();
					}
					else
					{
						genf.setText("");	genf.setBackground(Color.WHITE);
						namf.setText("");	namf.setBackground(Color.WHITE);
						stkf.setText("");	stkf.setBackground(Color.WHITE);
						pf.setText("");	pf.setBackground(Color.WHITE);
					}
				}
		
			

		}	
	}	
		
	
	
	public static void main(String s[])
	{
		new Hospital_Pharmacy_frame();
	}
}