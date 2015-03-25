import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


class Extras extends JPanel implements ActionListener
{
	JPanel ex_back,but_panel;
	JButton ambulance,blood_bank,ox_cl,phm;
	JLabel blank1,blank2,blank3;
	
	
	Extras()
	{
		
		
		ImageIcon im=new ImageIcon("extras_back.jpg");
		setSize(im.getIconWidth(),im.getIconHeight());
		
		blank1=new JLabel("  ");
		blank2=new JLabel("  ");
		blank3=new JLabel("  ");
		
		ambulance=new JButton(new ImageIcon("amb_extras.jpg"));
		ambulance.setRolloverIcon(new ImageIcon("amb_extras_r.jpg"));
		ambulance.setPressedIcon(new ImageIcon("amb_extras.jpg"));
		ambulance.setToolTipText("Ambulance control panel");
		//ambulance.setContentAreaFilled(false);
		//ambulance.setBorderPainted(false);
		ambulance.addActionListener(this);
		
		blood_bank=new JButton(new ImageIcon("bb_extras.jpg"));
		blood_bank.setRolloverIcon(new ImageIcon("bb_extras_r.jpg"));
		blood_bank.setPressedIcon(new ImageIcon("bb_extras_p.jpg"));
		blood_bank.setToolTipText("Blood Bank's control panel");
		//blood_bank.setContentAreaFilled(false);
		//blood_bank.setBorderPainted(false);
		blood_bank.addActionListener(this);
		
		ox_cl=new JButton(new ImageIcon("ox_extras.jpg"));
		ox_cl.setRolloverIcon(new ImageIcon("ox_extras_r.jpg"));
		ox_cl.setPressedIcon(new ImageIcon("ox_extras_p.jpg"));
		ox_cl.setToolTipText("Oxygen Bank's control panel");
		//ox_cl.setContentAreaFilled(false);
		//ox_cl.setBorderPainted(false);
		ox_cl.addActionListener(this);
		
		phm=new JButton(new ImageIcon("phm_extras.jpg"));
		phm.setRolloverIcon(new ImageIcon("phm_extras_r.jpg"));
		phm.setPressedIcon(new ImageIcon("phm_extras_p.jpg"));
		phm.setToolTipText("Hospital Pharmacy control panel");
		//phm.setContentAreaFilled(false);
		//phm.setBorderPainted(false);
		phm.addActionListener(this);
				
		setBackground(Color.WHITE);
		
		ex_back=new JPanel()
		{
			public void paintComponent(Graphics background)
			{
				ImageIcon i=new ImageIcon("extras_back.jpg");
				Image image=i.getImage();
				background.drawImage(image,0,0,null);
				setBounds(0,0,i.getIconWidth(),i.getIconHeight());
				setSize(i.getIconWidth(),i.getIconHeight());
			}
		};
		
		ex_back.setLayout(new GridLayout(4,1));
		
		but_panel=new JPanel();
		but_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		but_panel.setBackground(Color.BLUE);
		
		but_panel.add(ambulance);
		but_panel.add(blood_bank);
		but_panel.add(ox_cl);
		but_panel.add(phm);
		
		but_panel.setOpaque(false);
		
		ex_back.add(blank1);
		ex_back.add(blank2);
		ex_back.add(blank3);
		ex_back.add(but_panel);
		
		add(ex_back);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String s[]={" "," "};
		if(e.getSource()==ambulance)
		{
			setVisible(false);
			setEnabled(false);
			ambulance_frame.main(s);
			setEnabled(true);
			setVisible(true);
			
		}
		
		if(e.getSource()==blood_bank)
		{
			setVisible(false);
			setEnabled(false);
			blood_bank_frame.main(s);
			setEnabled(true);
			setVisible(true);
			
		}
		
		if(e.getSource()==phm)
		{
			setVisible(false);
			setEnabled(false);
			Hospital_Pharmacy_frame.main(s);
			setEnabled(true);
			setVisible(true);
			
		}
		
		if(e.getSource()==ox_cl)
		{
			setVisible(false);
			setEnabled(false);
			oxy_cylinder_frame.main(s);
			setEnabled(true);
			setVisible(true);
			
		}
		
	}
	
	
	
}




		
		
