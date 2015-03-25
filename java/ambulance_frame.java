import java.awt.Color;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class ambulance_frame extends JFrame implements ActionListener
{
	
	JButton save;
	JTextField noamb, cost;
	JLabel l1,l2,l3,blank;
	JPanel but,img,fld;
	ArrayList <ambulanceinfo> l;
	
		ambulance_frame()
		{
			
			Container c=getContentPane();
			setTitle("HOSPITAL AMBULANCE CONTROL PANEL");
			
			setLocation(300,200);
			
			
			ImageIcon i=new ImageIcon("amb_back.jpg");
			setSize(i.getIconWidth(),i.getIconHeight());
			
			l1=new JLabel("HOSPITAL AMBULANCE DATABASE:");
			l2=new JLabel("No.of Running Ambulance: ");
			l3=new JLabel("Cost for Hiring: (INR) ");
			blank=new JLabel("");
			
			noamb=new JTextField(15);
			cost=new JTextField(15);
			
			save=new JButton("SAVE");
			save.addActionListener(this);
			
			but=new JPanel();
			but.add(save);
			but.setBackground(Color.WHITE);
			
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

			img.setLayout(new GridLayout(1,2));
			
			
			fld=new JPanel();
			fld.setLayout(new GridLayout(6,1));
			fld.setBackground(Color.WHITE);
			
			fld.add(l1);
			fld.add(l2);
			fld.add(noamb);
			fld.add(l3);
			fld.add(cost);
			fld.add(but);
			
			img.add(blank);
			img.add(fld);
			
			add(img);
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==save)
			{
			
				boolean flag=true;
				String s1=noamb.getText();
				String reg="^[0-9]";
				Scanner sc=new Scanner(s1);
				String res=sc.findInLine(reg);
				if(res==null)
				 {
					flag=false;
					noamb.setText("");
					noamb.setBackground(Color.RED);
					JOptionPane.showMessageDialog(this,"Invalid input.Enter a number");
				 }
				String s2=cost.getText();
				String rega="^[0-9]";
				sc=new Scanner(s2);
				String resi=sc.findInLine(rega);
				if(resi==null)
				 {
					flag=false;
					cost.setText("");
					cost.setBackground(Color.RED);
					JOptionPane.showMessageDialog(this,"Invalid input.Enter a number");
				 }
				 
				 
				if(flag)
				{
					l=new ArrayList<ambulanceinfo>();
					l.add(new ambulanceinfo(s1,s2));
				
				
					try
					{
					File f=new File("D:/ambulance_field.records");
					FileOutputStream fout=new FileOutputStream(f);
					ObjectOutputStream oout=new ObjectOutputStream(fout);
					oout.writeObject(l);
					}catch(Exception er)
						{
							JOptionPane.showMessageDialog(this, "Ooopss... error: "+er.getMessage(),"Writing Error", 0,new ImageIcon("error.png"));
						}	
			
					JOptionPane.showMessageDialog(this, "Data saved to file successfully!", "Writing Success", 1, new ImageIcon("sucess.jpg"));
					this.dispose();
				
				}
			}	
		}
		public static void main(String s[])
		{
			new ambulance_frame();
		
		}

}
