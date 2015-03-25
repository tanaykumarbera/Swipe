import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


import javax.swing.*;



public class blood_details extends JFrame implements ActionListener
{
	
JLabel stock,price,blank;
String bg;
int index;
ArrayList<blood> rec;
blood record;
File f=new File("D:/blood_bank.records");
static JTextField nob;
JButton cnfm,cancel,img;
JPanel lb,but, fld;

void readRec()
{
	try
	{
		FileInputStream fin=new FileInputStream(f);
		ObjectInputStream oin=new ObjectInputStream(fin);
		rec=(ArrayList<blood>) oin.readObject();
		fin.close();
		oin.close();

		
	}catch(Exception e)
	{
		JOptionPane.showMessageDialog(this, "oops.."+e.getMessage(),"ERROR",0,new ImageIcon("error.png"));
	}
	
	for(int i=0;i<rec.size();i++)
	{
		if((((blood)rec.get(i)).gettype()).equalsIgnoreCase(bg))
		{
			record= (blood) rec.get(i);
			index=i;
			break;
		}
	}
	
	
	
}
void updateFile(int r)
{
	try
	{

		FileOutputStream fout=new FileOutputStream(f);
		ObjectOutputStream oout=new ObjectOutputStream(fout);
		
		record.setstock(r);
		rec.set(index,record);
		
		oout.writeObject(rec);
		
		oout.close();
		fout.close();
	}catch(Exception e)
	{
		JOptionPane.showMessageDialog(this,"oops..."+ e.getMessage());
	}
}




	blood_details(String s)
	{
		bg=s;
		Container c=getContentPane();
		
		setTitle("BLOOD GROUP ["+s+"]");
		setSize(400,160);
		setLocation(200,100);
		readRec();
		stock=new JLabel(s+" BOTTLES IN STOCK: "+record.getstock());
		price=new JLabel("PRICE PER BOTTLE OF ["+s+"] :Rs "+record.getprice());
		
		nob=new JTextField("Enter number of bottles here..",10);
		
		
		cnfm=new JButton("CONFIRM");
		cnfm.addActionListener(this);
		cancel=new JButton("CANCEL");
		cancel.addActionListener(this);
		
		
		img=new JButton(new ImageIcon("donate_blood.jpg"));
		
		
		
		blank=new JLabel("               ");
		but=new JPanel();
		but.add(cnfm);
		but.add(blank);
		but.add(cancel);
		
		fld=new JPanel();
		fld.setLayout(new GridLayout(3,1));
		
		lb=new JPanel();
		lb.setLayout(new GridLayout(2,1));
		lb.add(stock);
		lb.add(price);
		
		fld.add(lb);
		fld.add(nob);
		fld.add(but);
		
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		c.add(img);
		c.add(fld);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==cnfm)
		{
			Scanner sc =new Scanner(nob.getText());
			
			
			if(sc.findInLine("^[0-9]"+"")==null)
			{
				nob.setBackground(Color.RED);
				nob.setText("");
				JOptionPane.showMessageDialog(this, "No of bottles should be an integer..");
			}
			
			int stk= Integer.parseInt(nob.getText());
			
			if(stk<=record.getstock())
			{
				if(JOptionPane.showConfirmDialog(this,"TOTAL AMOUNT TO PAY: Rs "+(stk*record.getprice())+" Confirm??")==JOptionPane.YES_OPTION)
				{
					JOptionPane.showMessageDialog(this,"ORDER PROCEEDED..Enter customer details below..","BLOOD BANK",0,new ImageIcon("sucess.jpg"));
					new Gen("blood bank",""+(stk*record.getprice()));
					this.dispose();
				}
				
				else 
					JOptionPane.showMessageDialog(this,"YOUR ORDER CANCELED..","BLOOD BANK",0,new ImageIcon("error.png"));

			}
			
			else
				JOptionPane.showMessageDialog(this,"SORRY...ran out of stock..only "+record.getstock()+" bottles of "+bg+" are left!","BLOOD BANK",0,new ImageIcon("error.png"));

		}
		
		if(e.getSource()==cancel)
		{
			this.dispose();
		}

	}
	
	public static void main(String s[])
	{
		new blood_details("B+");
	}
	
}
