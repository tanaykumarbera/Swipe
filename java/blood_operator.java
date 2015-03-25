import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class blood_operator extends JFrame implements ActionListener
{
	JComboBox options;
	JLabel bgroup;
	
	
	blood_operator()
	{
		Container c= getContentPane();
		setSize(270,70);
		setLocation(200,200);
		c.setBackground(Color.WHITE);
		String opt[]={"A+","A-","B+","B-","AB+","AB-","O+","O-"};
		options=new JComboBox(opt);
		options.setBackground(Color.WHITE);
		bgroup=new JLabel("ENTER REQUIRED BLOOD GROUP: ");
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		c.add(bgroup);
		c.add(options);
		setVisible(true);
		options.addActionListener(this);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==options)
		{
			this.setVisible(false);
			new blood_details((String)options.getSelectedItem());
			this.dispose();
		}
	}
	
	public static void main(String s[])
	{
		new blood_operator();
	}
}