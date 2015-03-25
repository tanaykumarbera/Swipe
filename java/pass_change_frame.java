import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;


public class pass_change_frame extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,blank;
	JPasswordField p_old,p_new,p_cnfm;
	JButton change,cancel;
	JPanel btn;
	pass_change_frame()
	{
		Container c= getContentPane();
		setSize(270,270);
		setLocation(300,200);
		setTitle("PASSWORD CHANGE");
		l1=new JLabel("Your old password:");
		l2=new JLabel("Enter new password:");
		l3=new JLabel("Confirm new password:");
		blank=new JLabel("      ");
		
		change=new JButton("CHANGE");	change.addActionListener(this);
		cancel=new JButton("CANCEL");	cancel.addActionListener(this);
		
		p_old=new JPasswordField(5);
		p_new=new JPasswordField(5);
		p_cnfm=new JPasswordField(5);
		
		btn=new JPanel();
		btn.add(change);
		btn.add(blank);
		btn.add(cancel);
		
		
		
		c.setLayout(new GridLayout(7,1));
		c.add(l1);
		c.add(p_old);
		c.add(l2);
		c.add(p_new);
		c.add(l3);
		c.add(p_cnfm);
		c.add(btn);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		Acess_File af=new Acess_File();;
		ArrayList<Login_Info> rec;
		Login_Info r;
		if(e.getSource()==change)
		{
			
			try
			{
						
			rec=af.read_from_file("D:/admin.records");
			r=(Login_Info) rec.get(0);
			
			if(r.password_check(p_old.getText()))
			{
				
				if(p_new.getText().equals(p_cnfm.getText()))
				{
					r.set_password(p_new.getText());
					
					rec.set(0, r);
					try
					{
						af.write_to_file("D:/admin.records",rec);
						
					}catch(Exception err)
					{
						JOptionPane.showMessageDialog(this, err.getMessage());
					}
					
					
					
					JOptionPane.showMessageDialog(this,"PASSWORD CHANGED SUCESSFULLY!!!","ADMINISTRATOR",0,new ImageIcon("sucess.jpg"));
					this.dispose();
				}
				else	JOptionPane.showMessageDialog(this, "PASSWORD Confirmation Missmatched!! ");
			}
			
			else
				JOptionPane.showMessageDialog(this, "Soory... Wrong Password.. Enter old Admin Password to proceed..");
			}catch(Exception er)
			{
				JOptionPane.showMessageDialog(this,"Error: "+er.getMessage());
			}
			
			
		}
		
		if(e.getSource()==cancel)
		{
			this.dispose();
		}
		
	}
	
	
	public static void main(String s[])
	{
		new pass_change_frame();
	}
}
