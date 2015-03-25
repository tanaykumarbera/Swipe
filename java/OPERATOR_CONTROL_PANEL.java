import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class OPERATOR_CONTROL_PANEL extends JFrame implements ActionListener
{
	JPanel outdoor,indoor,extras,emergency,but_panel,outp,inp,exp,emp;
	JButton out_doc,in_doc,diag,ward,amb,oxy,med,blood,emg, outbut,inbut,extrabut,emgbut,logout;
	
	
	OPERATOR_CONTROL_PANEL()
	
	{
		
	
		setTitle("OPERATOR CONTROL PANEL");
		setLocation(100,120);
		
		out_doc=new JButton(new ImageIcon("od.jpg"));
		out_doc.setRolloverIcon(new ImageIcon("od_r.jpg"));
		out_doc.setPressedIcon(new ImageIcon("od.jpg"));
		out_doc.setToolTipText("OUTDOOR SECTION");
		
		out_doc.setContentAreaFilled(false);
		out_doc.setBorderPainted(false);
		out_doc.addActionListener(this);
			
		
		in_doc=new JButton(new ImageIcon("id.jpg"));
		in_doc.setRolloverIcon(new ImageIcon("id_r.jpg"));
		in_doc.setPressedIcon(new ImageIcon("id.jpg"));
		in_doc.setToolTipText("INDOOR SECTION");
		in_doc.setContentAreaFilled(false);
		in_doc.setBorderPainted(false);
		in_doc.addActionListener(this);
		
		
		diag=new JButton(new ImageIcon("dg.jpg"));
		diag.setRolloverIcon(new ImageIcon("dg_r.jpg"));
		diag.setPressedIcon(new ImageIcon("dg.jpg"));
		diag.setToolTipText("DIAGONASIS");
		diag.setContentAreaFilled(false);
		diag.setBorderPainted(false);
		diag.addActionListener(this);
		
		
		ward=new JButton(new ImageIcon("wd.jpg"));
		ward.setRolloverIcon(new ImageIcon("wd_r.jpg"));
		ward.setPressedIcon(new ImageIcon("wd.jpg"));
		ward.setToolTipText("INDOOR WARDS");
		ward.setContentAreaFilled(false);
		ward.setBorderPainted(false);
		ward.addActionListener(this);
		
		amb=new JButton(new ImageIcon("ab.jpg"));
		amb.setRolloverIcon(new ImageIcon("ab_r.jpg"));
		amb.setPressedIcon(new ImageIcon("ab.jpg"));
		amb.setToolTipText("AMBULANCE SERVICE");
		amb.setContentAreaFilled(false);
		amb.setBorderPainted(false);
		amb.addActionListener(this);
		
		oxy=new JButton(new ImageIcon("oy.jpg"));
		oxy.setRolloverIcon(new ImageIcon("oy_r.jpg"));
		oxy.setPressedIcon(new ImageIcon("oy.jpg"));
		oxy.setToolTipText("OXYGEN BANK");
		oxy.setContentAreaFilled(false);
		oxy.setBorderPainted(false);
		oxy.addActionListener(this);
		
		med=new JButton(new ImageIcon("md.jpg"));
		med.setRolloverIcon(new ImageIcon("md_r.jpg"));
		med.setPressedIcon(new ImageIcon("md.jpg"));
		med.setToolTipText("MEDICAL PHARMACY");
		med.setContentAreaFilled(false);
		med.setBorderPainted(false);
		med.addActionListener(this);
		
		blood=new JButton(new ImageIcon("bd.jpg"));
		blood.setRolloverIcon(new ImageIcon("bd_r.jpg"));
		blood.setPressedIcon(new ImageIcon("bd.jpg"));
		blood.setToolTipText("BLOOD BANK");
		blood.setContentAreaFilled(false);
		blood.setBorderPainted(false);
		blood.addActionListener(this);
		
		emg=new JButton("EMERGENCY REGISTRATION",new ImageIcon("em.jpg"));
		emg.setRolloverIcon(new ImageIcon("em_r.jpg"));
		emg.setPressedIcon(new ImageIcon("em.jpg"));
		emg.setToolTipText("EMERGENCY PATIENT REGISTRATION");
		emg.setContentAreaFilled(false);
		emg.setBorderPainted(false);
		emg.addActionListener(this);
		
		
		logout=new JButton(new ImageIcon("log.png"));
		logout.setRolloverIcon(new ImageIcon("log_r.png"));
		logout.setPressedIcon(new ImageIcon("log.png"));
		logout.setContentAreaFilled(false);
		logout.setOpaque(false);
		logout.setBorderPainted(false);
		logout.setToolTipText("LOGOUT..close this session!");
		logout.addActionListener(this);
		
		
		setSize(600,200);
		
	outdoor=new JPanel()
	{
		public void paintComponent(Graphics background)

			{
			ImageIcon i=new ImageIcon("op_outdoor_back.jpg");
			Image image=i.getImage();
			background.drawImage(image,0,0,null);
			setBounds(0,0,i.getIconWidth(),i.getIconHeight());
			setSize(i.getIconWidth(),i.getIconHeight());
			}

	};
	
	
	outbut=new JButton("OUTDOOR");	outbut.addActionListener(this);
	inbut=new JButton("INDOOR");	inbut.addActionListener(this);
	extrabut=new JButton("EXTRAS");	extrabut.addActionListener(this);
	emgbut=new JButton("EMERGENCY");	emgbut.addActionListener(this);
	
	
	but_panel=new JPanel();
	but_panel.setLayout(new GridLayout(0,1));
	but_panel.add(outbut);
	but_panel.add(inbut);
	but_panel.add(extrabut);
	but_panel.add(emgbut);
	
	
	
	indoor=new JPanel()
	{
		public void paintComponent(Graphics background)

			{
			ImageIcon i=new ImageIcon("op_outdoor_back.jpg");
			Image image=i.getImage();
			background.drawImage(image,0,0,null);
			setBounds(0,0,i.getIconWidth(),i.getIconHeight());
			setSize(i.getIconWidth(),i.getIconHeight());
			}

	};
	
	
	extras=new JPanel();
	
	emergency=new JPanel()
	{
		public void paintComponent(Graphics background)

			{
			ImageIcon i=new ImageIcon("op_emergency_back.jpg");
			Image image=i.getImage();
			background.drawImage(image,0,0,null);
			setBounds(0,0,i.getIconWidth(),i.getIconHeight());
			setSize(i.getIconWidth(),i.getIconHeight());
			}

	};
	
	outp=new JPanel();	outp.setLayout(new GridLayout(2,1));  outp.setBackground(Color.WHITE);
	inp=new JPanel();	inp.setLayout(new GridLayout(2,1));	inp.setBackground(Color.WHITE);
	exp=new JPanel();	exp.setLayout(new GridLayout(1,4));	exp.setBackground(Color.WHITE);
	//emp=new JPanel();	outp.setLayout(new FlowLayout(FlowLayout.CENTER));
	
	
	outdoor.setLayout(new FlowLayout(FlowLayout.RIGHT));	outp.add(out_doc);	outp.add(diag);		outdoor.add(outp,BorderLayout.EAST);	//outdoor.add(diag,BorderLayout.EAST);
	indoor.setLayout(new FlowLayout(FlowLayout.RIGHT));	inp.add(in_doc);	inp.add(ward);	indoor.add(inp);	//indoor.add(ward,BorderLayout.EAST);
	extras.setLayout(new BorderLayout());
	exp.add(amb);	exp.add(blood);	exp.add(oxy);	exp.add(med);	extras.add(exp);	//extras.add(blood,BorderLayout.SOUTH);	extras.add(oxy,BorderLayout.SOUTH);	extras.add(med,BorderLayout.SOUTH);
	emergency.setLayout(new FlowLayout(FlowLayout.RIGHT));
	emergency.add(emg);
	

	
	
	
	Container c=getContentPane();
	c.setLayout(new BorderLayout());
	
	c.add(but_panel,BorderLayout.WEST);	c.add(logout,BorderLayout.EAST);
	c.add(outdoor);
	setVisible(true);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	
	
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==outbut)
		{
			outdoor.setVisible(false);
			indoor.setVisible(false);
			extras.setVisible(false);
			emergency.setVisible(false);
			
			
			remove(outdoor);
			remove(indoor);
			remove(extras);
			remove(emergency);
			
			
			outdoor.setVisible(true);
			add(outdoor);
			
		}
		
		
		if(e.getSource()==inbut)
		{
			outdoor.setVisible(false);
			indoor.setVisible(false);
			extras.setVisible(false);
			emergency.setVisible(false);
			
			
			remove(outdoor);
			remove(indoor);
			remove(extras);
			remove(emergency);
			
			
			indoor.setVisible(true);
			add(indoor);
			
		}
		
		if(e.getSource()==extrabut)
		{
			outdoor.setVisible(false);
			indoor.setVisible(false);
			extras.setVisible(false);
			emergency.setVisible(false);
			
			
			remove(outdoor);
			remove(indoor);
			remove(extras);
			remove(emergency);
			
			
			extras.setVisible(true);
			add(extras);
			
		}
		
		
		if(e.getSource()==emgbut)
		{
			outdoor.setVisible(false);
			indoor.setVisible(false);
			extras.setVisible(false);
			emergency.setVisible(false);
			
			
			remove(outdoor);
			remove(indoor);
			remove(extras);
			remove(emergency);
			
			
			emergency.setVisible(true);
			add(emergency);
			
		}
		
		
		if(e.getSource()==out_doc)
		{
			new doctor_operator("OUTDOOR","D:/outdoor_doctors.records");
		}
		
		if(e.getSource()==in_doc)
		{
			new doctor_operator("INDOOR","D:/indoor_doctors.records");
		}
		
		if(e.getSource()==ward)
		{
			new indoor_beds();
		}
		
		if(e.getSource()==diag)
		{
			new operator_diagonastic();
		}
		
		if(e.getSource()==amb)
		{
			new op_ambulance();
		}
		
		if(e.getSource()==oxy)
		{
			new oxy_operator();
		}
		
		if(e.getSource()==blood)
		{
			new blood_operator();
		}
		
		if(e.getSource()==med)
		{
			new operator_pharmacy();
		}
		
		if(e.getSource()==emg)
		{
			new Gen("","");
		}
		
		if(e.getSource()==logout)
		{
			JOptionPane.showMessageDialog(this,"You have been sucessfully loged out...","OPERATOR",0,new ImageIcon("sucess.jpg"));
			new Hospital_Frame();
			this.dispose();
		}
	}
	
	
	
	public static void main(String s[])
	{
		new OPERATOR_CONTROL_PANEL();
	}
	

}
