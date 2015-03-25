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

class diagonastic_frame extends JFrame implements ActionListener
{
	JLabel type,doctor, lab,regl,price,timing;
	JTextField tp,doc,lb,prc,tim,reg;
	JPanel coml,comp,tpp,docp,compp,comlp,timp;
	JButton submit, cancel;
	ArrayList<Diginfo> l1;
	int ch=5;
	
	diagonastic_frame()
	{
		Container c=getContentPane();
		setTitle("DIAGONASTIC FIELDS");
		setSize(350,250);
		setLocation(300,200);
		type=new JLabel("TYPE: ");
		doctor=new JLabel("ASSIGNED DOCTOR: ");
		lab=new JLabel("ASSIGNED LAB: ");
		price=new JLabel("PRICE: Rs.");
		timing=new JLabel("TIMING: ");
		regl=new JLabel("Regulations to be applied: ");
		
		tim= new JTextField("2:00pm-3:00pm",8);
		reg=new JTextField("-empty stomach\n-,etc",15);
		
		tp= new JTextField("e.g. ECG, etc",15);
		doc= new JTextField("e.g. Mr. Somnath Kar",15);
		lb= new JTextField("e.g.L-IV",5);
		prc= new JTextField("600.00",5);
		coml=new JPanel();	coml.add(lab); coml.add(lb);
		comp=new JPanel(); comp.add(price); comp.add(prc);
		
		tpp=new JPanel();
		tpp.add(tp);
		
		docp=new JPanel();
		docp.add(doc);
		
		compp=new JPanel();
		compp.add(comp);
		
		comlp=new JPanel();
		comlp.add(coml);
		
		timp=new JPanel();
		timp.add(tim);
		
		
		
		
		submit=new JButton("SUBMIT");	submit.addActionListener(this);
		cancel=new JButton("CANCEL");	cancel.addActionListener(this);
		
		setLayout(new GridLayout(6,2));
		
		add(type);	add(tpp);
		add(doctor);	add(docp);
		add(comlp);	add(compp);
		add(timing);	add(timp);
		add(regl);	add(reg);
		add(submit);	add(cancel);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		File f=new File("d:/diagonastic_fields.records");
		
		
		if(e.getSource()==submit)
		{
			boolean flag=true;
			try
			{
				
				FileInputStream fin=new FileInputStream(f);
				ObjectInputStream oin=new ObjectInputStream(fin);
				l1= (ArrayList<Diginfo>) oin.readObject();
				oin.close();
				fin.close();
			} catch(Exception err)
			
				{
					l1=new ArrayList<Diginfo>();
				}
			
			
			
				String s1=tp.getText();
				String rege="^[a-zA-Z]";
				Scanner sc =new Scanner(s1);
				String res=sc.findInLine(rege);
				if(res==null)
				{
				flag=false;
				tp.setBackground(Color.RED);
				tp.setText("");
				JOptionPane.showMessageDialog(this, "The name must contain only letters(a-z or A-Z)");
				}
				String s2=doc.getText();
				 rege="^[a-zA-Z]";
				 sc=new Scanner(s2);
				res=sc.findInLine(rege);
				
				if(res==null)
				{
					flag=false;
					doc.setText("");
					doc.setBackground(Color.RED);
					JOptionPane.showMessageDialog(this,"Invalid input.(name may contain a-z & A-Z only)");
				}
				String s3=lb.getText();
				rege="^[0-9]";
				sc=new Scanner(s3);
				res=sc.findInLine(rege);
		        if(res==null)
		        {	
					flag=false;
		        	lb.setText("");
		        	lb.setBackground(Color.RED);
		        	JOptionPane.showMessageDialog(this,"Invalid input.(lab. no. may contain 0-9 only)");
		        }
				String s4=prc.getText();
				
				 rege="^[0-9]";
				 sc=new Scanner(s4);
				res=sc.findInLine(rege);
				if(res==null)
				{	
					flag=false;
					prc.setText("");
					prc.setBackground(Color.RED);
					JOptionPane.showMessageDialog(this,"Invalid input.(price may contain 0-9 only)");
				}
				String s5=reg.getText();
				String s6=tim.getText();
				
			if(flag)
			{
				l1.add(new Diginfo(s1,s2,s3,s5,s4,s6,0));
	
				try
				{
				FileOutputStream fout=new FileOutputStream(f);
				ObjectOutputStream oout=new ObjectOutputStream(fout);
			
				oout.writeObject(l1);
				oout.close();
				fout.close();
				} catch(Exception er)
					{
						JOptionPane.showMessageDialog(this,"Ooops!! error: "+er.getMessage());
					}
			
			ch=JOptionPane.showConfirmDialog(this,"Record Taken..Add another Record??");
			}
			
			if(ch==JOptionPane.NO_OPTION)
			{
				this.dispose();
			}
			
			if(ch==JOptionPane.YES_OPTION)
			{
				tp.setText("");	tp.setBackground(Color.WHITE);
				doc.setText("");	doc.setBackground(Color.WHITE);
				lb.setText("");	lb.setBackground(Color.WHITE);
				prc.setText("");	prc.setBackground(Color.WHITE);
				reg.setText("");	reg.setBackground(Color.WHITE);
				tim.setText("");	tim.setBackground(Color.WHITE);
			}
			
		}
		
				
		if(e.getSource()==cancel)
		{
			this.dispose();
		}
		
	
		
		
		
	}
	
	public static void main(String s[])
	{
		new diagonastic_frame();
	}
}