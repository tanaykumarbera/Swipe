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

class oxy_cylinder_frame extends JFrame implements ActionListener
{
	
	JButton save;
	JTextField nooxcl, cost;
	JLabel l1,l2,l3,blank;
	JPanel but,img,fld;
	ArrayList<Oxy> l;
	
		oxy_cylinder_frame()
		{
			setTitle("OXYGEN BANK CONTROL PANEL");
			Container c=getContentPane();
			
			setLocation(300,200);
		
			ImageIcon i=new ImageIcon("ox_back.jpg");
			setSize(i.getIconWidth(),i.getIconHeight());
			
			
			l1=new JLabel("HOSPITAL OXYGEN BANK DATABASE:");
			l2=new JLabel("No.of Oxygen Cylinder in stock: ");
			l3=new JLabel("Cost per Cylinder: (INR) ");
			
			nooxcl=new JTextField(15);
			cost=new JTextField(15);
			save=new JButton("SAVE",new ImageIcon("save.jpg"));
			save.setRolloverIcon(new ImageIcon("save_r.png"));
			save.setPressedIcon(new ImageIcon("save.jpg"));
			save.setToolTipText("UPLOAD to database..");
			save.addActionListener(this);
			
			but=new JPanel();
			but.add(save);
			but.setBackground(Color.WHITE);
			
			img=new JPanel()
			{
				public void paintComponent(Graphics background)
				{
				ImageIcon i=new ImageIcon("ox_back.jpg");
				Image image=i.getImage();
				background.drawImage(image,0,0,null);
				setBounds(0,0,i.getIconWidth(),i.getIconHeight());
				setSize(i.getIconWidth(),i.getIconHeight());
				}

			};
			
			blank=new JLabel(" ");
			
			
			
			
			fld=new JPanel();
			
			fld.setBackground(Color.WHITE);
			fld.add(l1);
			fld.add(l2);
			fld.add(nooxcl);
			fld.add(l3);
			fld.add(cost);
			fld.add(but);
			
			
			img.setLayout(new GridLayout(1,2));
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
				
				String s1= nooxcl.getText();
				String reg="^[0-9]";
				Scanner sc=new Scanner(s1);	
				 String res=sc.findInLine(reg);
				if(res==null)
				{
					flag=false;
					nooxcl.setText("");	nooxcl.setBackground(Color.RED);
					JOptionPane.showMessageDialog(this, "The number of Oxygen Cylinders must contain only numbers");
				}
				
				
				String s2=cost.getText();
				reg="^[0-9]";
				sc=new Scanner(s2);	
				res=sc.findInLine(reg);
				if(res==null)
				{
					flag=false;
					cost.setText("");	cost.setBackground(Color.RED);
					JOptionPane.showMessageDialog(this, "The cost must contain only digits(0-9)");
				}
				
				if(flag)
				{
				l=new ArrayList<Oxy>();
				l.add(new Oxy(s1,s2));
				
				try
				{
					File f=new File("D:/oxy_bank.records");
					
					FileOutputStream fout=new FileOutputStream(f);
					ObjectOutputStream oout=new ObjectOutputStream(fout);
					
					oout.writeObject(l);
					
					oout.close();
					fout.close();
				}catch(Exception er)
				{
					JOptionPane.showMessageDialog(this, "Ooopss... error: "+er.getMessage(),"Writing Error.. :(", 0,new ImageIcon("error.png"));
				}
				
				JOptionPane.showMessageDialog(this, "Data saved to file successfully!", "Writing Success", 1, new ImageIcon("sucess.jpg"));
				this.dispose();
				}
			}
		
		}
		
		
		
		public static void main(String s[])
		{
			new oxy_cylinder_frame();
		
		}

}
