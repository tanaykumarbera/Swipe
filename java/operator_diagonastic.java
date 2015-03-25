import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.*;

public class operator_diagonastic extends JFrame implements ActionListener
{
	
	ArrayList<String> options;
	ArrayList<Diginfo> list;
	JComboBox dropdn;
	JButton ok;
	JLabel cap;
	JPanel cp;
	
	operator_diagonastic()
	{
		Container c=getContentPane();
		setSize(410,130);
		setTitle("DIAGONASTIC CENTRE");
		setLocation(200,100);
		
		c.setBackground(Color.WHITE);
		cap=new JLabel("Choose Diagonastic field below to proceed..");
		
		try
		{
			File f=new File("D:/diagonastic_fields.records");
			FileInputStream fin=new FileInputStream(f);
			ObjectInputStream oin=new ObjectInputStream(fin);
			
			list=(ArrayList<Diginfo>)oin.readObject();
					
		}catch(Exception er)
		{
			JOptionPane.showMessageDialog(this,"Error: "+er.getMessage(),"READING ERROR",0,new ImageIcon("error.png"));
		}
		
		options=new ArrayList<String>();
		for(int i=0;i<list.size();i++)
		{
			boolean flag=true;
			
			for(int j=0;j<options.size();j++)
				if(((String)options.get(j)).equalsIgnoreCase(((Diginfo)list.get(i)).getnam()))
						flag=false;
			if(flag)
			options.add(((Diginfo)list.get(i)).getnam());
		}
		
		
		dropdn=new JComboBox();
		dropdn.setBackground(Color.WHITE);
		
		
		for(int i=0;i<options.size();i++)
		dropdn.addItem((String)options.get(i));
		
		ok=new JButton(new ImageIcon("diag_ok.jpg"));
		ok.setOpaque(false);
		ok.setContentAreaFilled(false);
		ok.setBorderPainted(false);
		
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		cp=new JPanel();
		cp.setLayout(new GridLayout(2,1));
		cp.setBackground(Color.WHITE);
		
		cp.add(cap);
		cp.add(dropdn);
		
		c.add(cp);
		c.add(ok);
		
		setVisible(true);
		dropdn.addActionListener(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==dropdn)
		{
			this.setVisible(false);
			new diag_details(((String)dropdn.getSelectedItem()));
			this.dispose();
		}
	}
	
	public static void main(String s[])
	{
		new operator_diagonastic();
	}
}



