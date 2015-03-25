import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


class view_patient extends JPanel implements ActionListener
{
	JPanel pt_back,but_panel;
	JButton individual,list;
	JLabel blank,blank1,blank2,blank3,blank4,blank5;
	
	
	view_patient()
	{
		ImageIcon im=new ImageIcon("view_patient_back.jpg");
		setSize(im.getIconWidth(),im.getIconHeight());
		setLocation(120,200);
		
		blank=new JLabel("                                                  ");
		blank1=new JLabel("  ");
		blank2=new JLabel(" ");
		blank3=new JLabel(" ");
		blank4=new JLabel(" ");
		blank5=new JLabel(" ");
		
		
		individual=new JButton("CHECK INDIVIDUAL RECORDS",new ImageIcon("ind_view_patient.png"));
		individual.setRolloverIcon(new ImageIcon("ind_view_patient_r.gif"));
		individual.setPressedIcon(new ImageIcon("ind_view_patient_r.gif"));
		individual.setToolTipText("Individual patient's record...view one at a time");
		//individual.setContentAreaFilled(false);
		individual.setBorderPainted(false);
		
		list=new JButton("CHECK ALL RECORDS",new ImageIcon("list_view_patient.png"));
		list.setRolloverIcon(new ImageIcon("list_view_patient_r.png"));
		list.setPressedIcon(new ImageIcon("list_view_patient_r.png"));
		list.setToolTipText("List wise records..choose it for viewing all records at a time");
		//list.setContentAreaFilled(false);
		list.setBorderPainted(false);
		setBackground(Color.WHITE);
		
		pt_back=new JPanel()
		{
			public void paintComponent(Graphics background)
			{
				ImageIcon i=new ImageIcon("view_patient_back.jpg");
				Image image=i.getImage();
				background.drawImage(image,0,0,null);
				setBounds(0,0,i.getIconWidth(),i.getIconHeight());
				setSize(i.getIconWidth(),i.getIconHeight());
			}
		};
		
		pt_back.setLayout(new GridLayout(6,1));
		
		but_panel=new JPanel();
		but_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		but_panel.add(individual);
		but_panel.add(blank);
		but_panel.add(list);
		
		individual.addActionListener(this);
		list.addActionListener(this);
		
		
		but_panel.setOpaque(false);
		
		pt_back.add(blank1);
		pt_back.add(blank2);
		pt_back.add(blank3);
		pt_back.add(blank4);
		pt_back.add(blank5);
		pt_back.add(but_panel);
		
		this.add(pt_back);
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==individual)
		{
			setVisible(false);
			new individual_record();
			
		}
		
		if(e.getSource()==list)
		{
			setVisible(false);
			new all_user_show_info();
		}
	}
	
}




		
		
