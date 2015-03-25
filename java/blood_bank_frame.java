import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;

class blood_bank_frame extends JFrame implements ActionListener
{
	JLabel op,on,ap,an,bp,bn,abp,abn,  p1,p2,p3,p4,p5,p6,p7,p8,	LABEL, blank;
	JTextField opf,onf,apf,anf,bpf,bnf,abpf,abnf,p1f,p2f,p3f,p4f,p5f,p6f,p7f,p8f;
	JPanel opp,onp,app,anp,bpp,bnp,abpp,abnp,but,fld,img;
	JButton update;
	ArrayList<blood> list;
	File file=new File("D:/blood_bank.records");
	
	
	blood_bank_frame()
	{
		Container c=getContentPane();
		setLocation(200,300);
		setTitle("BLOOD BANK CONTROL PANEL");
		setSize(530,380);
		
		op=new JLabel("[O+] :");
		on=new JLabel("[O-] :");
		ap=new JLabel("[A+] :");
		an=new JLabel("[A-] :");
		bp=new JLabel("[B+] :");
		bn=new JLabel("[B-] :");
		abp=new JLabel("[AB+] :");
		abn=new JLabel("[AB-] :");
		
		p1=new JLabel("Price[A+]: ");
		p2=new JLabel("Price[A-]: ");
		p3=new JLabel("Price[B+]: ");
		p4=new JLabel("Price[B-]: ");
		p5=new JLabel("Price[AB+]: ");
		p6=new JLabel("Price[AB-]: ");
		p7=new JLabel("Price[O+]: ");
		p8=new JLabel("Price[O-]: ");
		
		blank=new JLabel(" ");
		
		
		opf=new JTextField(5);
		onf=new JTextField(5);
		apf=new JTextField(5);
		anf=new JTextField(5);
		bpf=new JTextField(5);
		bnf=new JTextField(5);
		abpf=new JTextField(5);
		abnf=new JTextField(5);
		
		p1f=new JTextField(5);
		p2f=new JTextField(5);
		p3f=new JTextField(5);
		p4f=new JTextField(5);
		p5f=new JTextField(5);
		p6f=new JTextField(5);
		p7f=new JTextField(5);
		p8f=new JTextField(5);
		
		LABEL=new JLabel("   ------------BLOOD BANK DATABASE-------------");
		
		
		opp=new JPanel();	opp.add(op);	opp.add(opf);	opp.add(p7);	opp.add(p7f);	opp.setBackground(Color.WHITE);
		onp=new JPanel();	onp.add(on);	onp.add(onf);	onp.add(p8);	onp.add(p8f);	onp.setBackground(Color.WHITE);
		app=new JPanel();	app.add(ap);	app.add(apf);	app.add(p1);	app.add(p1f);	app.setBackground(Color.WHITE);
		anp=new JPanel();	anp.add(an);	anp.add(anf);	anp.add(p2);	anp.add(p2f);	anp.setBackground(Color.WHITE);
		bpp=new JPanel();	bpp.add(bp);	bpp.add(bpf);	bpp.add(p3);	bpp.add(p3f);	bpp.setBackground(Color.WHITE);
		bnp=new JPanel();	bnp.add(bn);	bnp.add(bnf);	bnp.add(p4);	bnp.add(p4f);	bnp.setBackground(Color.WHITE);
		abpp=new JPanel();	abpp.add(abp);	abpp.add(abpf);	abpp.add(p5);	abpp.add(p5f);	abpp.setBackground(Color.WHITE);
		abnp=new JPanel();	abnp.add(abn);	abnp.add(abnf);	abnp.add(p6);	abnp.add(p6f);	abnp.setBackground(Color.WHITE);
	
		update= new JButton("UPDATE", new ImageIcon("but.jpg"));
		update.setRolloverIcon(new ImageIcon("but_r.jpg"));
		update.setPressedIcon(new ImageIcon("but.jpg"));
		update.addActionListener(this);
		
		but=new JPanel();	but.setBackground(Color.WHITE);
		but.add(update);	
		
		fld=new JPanel();
		
		fld.setLayout(new GridLayout(11,1));
		fld.setBackground(Color.WHITE);
		fld.add(LABEL);
		fld.add(app);
		fld.add(anp);
		fld.add(bpp);
		fld.add(bnp);
		fld.add(abpp);
		fld.add(abnp);
		fld.add(opp);
		fld.add(onp);
		fld.add(but);
		
		img=new JPanel()
		{
			public void paintComponent(Graphics background)
			{
			ImageIcon i=new ImageIcon("bb_back.jpg");
			Image image=i.getImage();
			background.drawImage(image,0,0,null);
			setBounds(0,0,i.getIconWidth(),i.getIconHeight());
			setSize(i.getIconWidth(),i.getIconHeight());
			}
		};
		
		img.setLayout(new GridLayout(1,2));
		
		img.add(blank);
		img.add(fld);
		
		add(img);
		
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==update)
		{
			boolean flag=true;
			list=new ArrayList<blood>();
			
			try
			{
				list.add(new blood("A+",Integer.parseInt(apf.getText()),Integer.parseInt(p1f.getText())));
				list.add(new blood("A-",Integer.parseInt(anf.getText()),Integer.parseInt(p2f.getText())));
				list.add(new blood("B+",Integer.parseInt(bpf.getText()),Integer.parseInt(p3f.getText())));
				list.add(new blood("B-",Integer.parseInt(bnf.getText()),Integer.parseInt(p4f.getText())));
				list.add(new blood("AB+",Integer.parseInt(abpf.getText()),Integer.parseInt(p5f.getText())));
				list.add(new blood("AB-",Integer.parseInt(abnf.getText()),Integer.parseInt(p6f.getText())));
				list.add(new blood("O+",Integer.parseInt(opf.getText()),Integer.parseInt(p7f.getText())));
				list.add(new blood("O-",Integer.parseInt(onf.getText()),Integer.parseInt(p8f.getText())));
			}catch(Exception err)
			{
				JOptionPane.showMessageDialog(this,"Oops...Error: "+err.getMessage());
				flag=false;
				JOptionPane.showMessageDialog(this,"INVALID ENTRY IN FIELDS..all must be integers..","BLOOD BANK",0,new ImageIcon("error.png"));
			}
			
			if(flag)
				{	
					try
					{
						FileOutputStream fout=new FileOutputStream(file);
						ObjectOutputStream oout=new ObjectOutputStream(fout);
						
						oout.writeObject(list);
						
						fout.close();
						oout.close();
						
					}catch(Exception er)
					{
						JOptionPane.showMessageDialog(this,"Oops...Error: "+er.getMessage());
					}
				
					JOptionPane.showMessageDialog(this,"RCORDS UPDATED SUCESSFULLY!!!","BLOOD BANK",0,new ImageIcon("sucess.jpg"));
					this.dispose();
				}
		}
	}
	
	public static void main(String s[])
	{
		new blood_bank_frame();
	}
}