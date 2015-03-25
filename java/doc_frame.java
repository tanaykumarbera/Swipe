import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class doc_frame extends JFrame implements ActionListener
{
	JButton doc_add,doc_del;
	JPanel but_pan;
	String filelocation;
	doc_frame(String s)
	{
		filelocation=s;
		setTitle("DOCTORS MENU: ");
		Container c=getContentPane();
		setLocation(300,200);
		c.setBackground(Color.WHITE);
		doc_add=new JButton(new ImageIcon("add_doc.jpg"));
		doc_add.setRolloverIcon(new ImageIcon("add_doc_r.jpg"));
		doc_add.setPressedIcon(new ImageIcon("add_doc.jpg"));
		doc_add.setBorderPainted(false);
		doc_add.setContentAreaFilled(false);
		doc_add.setToolTipText("Add a new Doctor");
		doc_add.addActionListener(this);
		
		
		doc_del=new JButton(new ImageIcon("del_doc.jpg"));
		doc_del.setRolloverIcon(new ImageIcon("del_doc_r.jpg"));
		doc_del.setPressedIcon(new ImageIcon("del_doc.jpg"));
		doc_del.setBorderPainted(false);
		doc_del.setContentAreaFilled(false);
		doc_del.setToolTipText("Delete an existing Doctor");
		doc_del.addActionListener(this);
		
	
		but_pan=new JPanel();
		but_pan.add(doc_add);
		but_pan.add(doc_del);
		but_pan.setBackground(Color.WHITE);
			
		c.add(but_pan);
		setSize(310,160);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		
		if(e.getSource()==doc_add)
		{
			setEnabled(false);
			new Doc_info(filelocation);
			setEnabled(true);
		}
		
		if(e.getSource()==doc_del)
		{
			setEnabled(false);
			new doc_remove(filelocation);
			setEnabled(true);
		}
	}
	
	public static void main(String s[])
	{
		new doc_frame(s[0]);
	}
}