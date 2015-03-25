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

class operator_pharmacy extends JFrame implements ActionListener
{
	JLabel name, genere,price;
	JTextField nam,gen,unit;
	JPanel flds,fldt,res,but_p,img,c;
	JButton search,but;
	ArrayList<Availmed> list;
	Availmed r;
	int index=-1;
	static int prc;
	File f;
	
	operator_pharmacy()
	{
		Container con=getContentPane();
		prc=0;
		//setSize(300,150);
		setLocation(200,200);
		setTitle("MEDICAL SHOP");
		 
		
		nam=new JTextField("Enter Medicine Name",13);
		gen=new JTextField("Enter Medicine Genere",13);
		unit=new JTextField("Enter no. of units",12);
		
		search=new JButton("SEARCH",new ImageIcon("med_src.jpg"));
		search.setBackground(Color.WHITE);
		search.setToolTipText("Click to search medicine database..");
		search.addActionListener(this);
		
		flds=new JPanel();
		flds.setBackground(Color.WHITE);
		flds.setLayout(new GridLayout(0,1));
		flds.add(nam);
		flds.add(gen);
		
		fldt=new JPanel();
		fldt.setLayout(new FlowLayout(FlowLayout.LEFT));
		fldt.add(flds);
		fldt.add(search);
		fldt.setBackground(Color.WHITE);
		
		Font f1=new Font("Consolas",Font.BOLD,10);
		name=new JLabel(""); name.setFont(f1);
		genere=new JLabel("");	genere.setFont(f1);
		price=new JLabel("");	price.setFont(f1);
		
		c=new JPanel();
		img=new JPanel()
		{
			public void paintComponent(Graphics background)
			{
				
				
				ImageIcon i=new ImageIcon("op_pham_back.jpg");
				Image image=i.getImage();
				background.drawImage(image,0,0,null);
				setBounds(0,0,i.getIconWidth(),i.getIconHeight());
				setSize(i.getIconWidth(),i.getIconHeight());
			}
		};
		
		
		
		
		
		but=new JButton("CONFIRM ORDER");
	//	but.setVisible(false);
		
		but_p=new JPanel();
		but_p.add(unit);
		but_p.add(but);
		but_p.setBackground(Color.WHITE);
		but.addActionListener(this);
		
		
		res=new JPanel();
		res.setLayout(new GridLayout(0,1));
		res.add(name);
		res.add(genere);
		res.add(price);
		res.add(but_p);
		res.setBackground(Color.WHITE);
		res.setVisible(false);
		
		c.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		c.add(fldt);
		c.add(res);
		
		c.setBackground(Color.WHITE);
		
		img.setLayout(new FlowLayout(FlowLayout.RIGHT));
		img.add(c);
		
		ImageIcon i=new ImageIcon("op_pham_back.jpg");
		setSize(i.getIconWidth()-40,i.getIconHeight()-60);
		
		con.add(img);
		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==search)
		{
			
			boolean fl=true,flag=true;
			//int j=0;
			String n=nam.getText();
			String g=gen.getText();
			
			
			String reg="^[a-zA-Z]";
			Scanner sc=new Scanner(n);
			Scanner st=new Scanner(g);
			
			if(sc.findInLine(reg)==null)
			{
				flag=false;
				nam.setText("");
				nam.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this,"Medicine Name must start with an alphabet");
				
			}
			
			if(st.findInLine(reg)==null)
			{
				flag=false;
				gen.setText("");	gen.setBackground(Color.RED);
				JOptionPane.showMessageDialog(this,"Genere Name must start with an alphabet");
							
			}
			
			
		if(flag)
		{
			
			f=new File("D:/medical_stores.records");
			if(!f.exists())
				JOptionPane.showMessageDialog(this,"Oops...medical data record file not found!!");
			else
				{
					try
					{
						FileInputStream fin =new FileInputStream(f);
						ObjectInputStream oin=new ObjectInputStream(fin);
						
						list=(ArrayList<Availmed>) oin.readObject();
						
						oin.close();
						fin.close();
					}catch(Exception er)
					{
						JOptionPane.showMessageDialog(this,"Error: "+er.getMessage());
					}
					
					
					for(int i=0;i<list.size();i++)
						if	(((((Availmed)list.get(i)).getmedname()).equals(n))	&&	((((Availmed)list.get(i)).getmedgenre()).equals(g)))
						{
							index=i;
							fl=false;
						}
					
					if(fl)
						JOptionPane.showMessageDialog(this,"Sorry..medicine named "+n+" with genere "+g+" was not found in the record list!","NO RESULT FOUND",0,new ImageIcon("error.png"));	
					else
					{
						fldt.setVisible(false);
						r=(Availmed)list.get(index);
						name.setText("MEDICINE NAME: "+r.getmedname());
						genere.setText("GENERE: "+r.getmedgenre());
						price.setText("PRICE PER UNIT: "+r.getmedprice());
						
						res.setVisible(true);
					}
					
					
					
				}
		
		}
		}
		
		if(e.getSource()==but)
		{
			
			Scanner sc=new Scanner(unit.getText());
	if(sc.findInLine("^[0-9]")==null)
		{JOptionPane.showMessageDialog(this,"Units must be in Integers.."); unit.setText(""); unit.setBackground(Color.RED);	}
	else
	{	
			int un= Integer.parseInt(unit.getText());
			
	if(un<=	Integer.parseInt(r.getmedstock()))
				
	{	
			if((JOptionPane.showConfirmDialog(this,"Rs "+(un*Integer.parseInt(r.getmedprice()))+" for "+un+" units of "+r.getmedname()+" Confirm??")==JOptionPane.YES_OPTION))
					{
						
						JOptionPane.showMessageDialog(this,"ORDER PROCESSED SUCESSFULLY..","HOSPITAL PHARMACY",0,new ImageIcon("sucess.jpg"));
						
						try
						{
							FileOutputStream fout=new FileOutputStream(f);
							ObjectOutputStream oout=new ObjectOutputStream(fout);
							
							r.setmedstock(un);
							list.set(index, r);
							
							oout.writeObject(list);
							oout.close();
							fout.close();
						}catch(Exception er)
						{
							JOptionPane.showMessageDialog(this,"Error.."+er.getMessage(),"Updating error",0,new ImageIcon("error.png"));
						}
						
						JOptionPane.showMessageDialog(this,"RECORDS UPDATED SUCESSFULLY..","HOSPITAL PHARMACY",0,new ImageIcon("sucess.jpg"));
						
						prc+=(un*Integer.parseInt(r.getmedprice()));
						
					if((JOptionPane.showConfirmDialog(this,"Want to place a new order?"))==JOptionPane.NO_OPTION)
					{
						
						JOptionPane.showMessageDialog(this,"Enter cusyomer's details below..","HOSPITAL PHARMACY",0,new ImageIcon("sucess.jpg"));

						new Gen("Pharmacy",(""+prc));
						this.dispose();
						
						
					}
					else
					{
						res.setVisible(false);
						nam.setBackground(Color.WHITE);
						gen.setBackground(Color.WHITE);
						unit.setBackground(Color.WHITE);
						fldt.setVisible(true);
						
						
					}
						
					}
			
			
		}
		else JOptionPane.showMessageDialog(this,"Sorry...we ran out of stock.."+r.getmedstock()+" units are left!","OUT OF STOCK ERROR",0,new ImageIcon("error.png"));

		}
		}
	}
	
	
	public static void main(String s[])
	{
		new operator_pharmacy();
	}
	
}