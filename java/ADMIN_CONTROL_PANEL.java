import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


class ADMIN_CONTROL_PANEL extends JFrame 
{
	JPanel pic_panel, field_panel, combo_panel;
	JTabbedPane tabs;
	JButton logout;
	ADMIN_CONTROL_PANEL()
	{
		logout=new JButton("LOGOUT");
		tabs=new JTabbedPane();
		tabs.add("Outdoor", new Outdoor());
		tabs.add("Indoor", new Indoor());
		tabs.add("Extras", new Extras());
		tabs.add("Patients", new view_patient());
		tabs.add("Administrative Account Settings", new Administrative_Account_Settings());
		
		Container con= getContentPane();
		setTitle("ADMINISTRATOR CONTROL PANEL");
		setLocation(100,100);
		setSize(620,400);
		
		con.add(tabs);
		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	class Administrative_Account_Settings extends JPanel implements ActionListener
	{
		JPanel img_panel,fld_panel,log_p,pass_p;
		JButton logout,pass_change,opert;
		JLabel blank1,blank2,blank3;
		Administrative_Account_Settings()
		{
			
			
			blank1=new JLabel(" ");
			blank2=new JLabel(" ");
			
			img_panel=new JPanel()
			{
				public void paintComponent(Graphics background)
				{
					
					
					ImageIcon i=new ImageIcon("admin_img.jpg");
					Image image=i.getImage();
					background.drawImage(image,0,0,null);
					setBounds(0,0,i.getIconWidth(),i.getIconHeight());
					setSize(i.getIconWidth(),i.getIconHeight());
				}
			};
			img_panel.setLayout(new GridLayout(1,2));
			
			fld_panel =new JPanel();
			fld_panel.setBackground(Color.WHITE);
			fld_panel.setOpaque(false);
			
			fld_panel.setLayout(new GridLayout(2,1));
			
			logout=new JButton(new ImageIcon("logout.png"));
			logout.setRolloverIcon(new ImageIcon("logout_r.png"));
			logout.setPressedIcon(new ImageIcon("logout.png"));
			logout.setOpaque(false);
			logout.setContentAreaFilled(false);
			logout.setToolTipText("LOGOUT...click to close your current session");
			logout.addActionListener(this);
			
			pass_change=new JButton("CHANGE PASSWORD",new ImageIcon("ch_pass.png"));
			pass_change.setRolloverIcon(new ImageIcon("ch_pass_r.png"));
			pass_change.setPressedIcon(new ImageIcon("ch_pass_r.png"));
			pass_change.setToolTipText("Click to change your ACCOUNT PASSWORD!");
			pass_change.addActionListener(this);
			
			log_p=new JPanel();
			log_p.setLayout(new FlowLayout(FlowLayout.LEFT));
			log_p.add(logout);
			log_p.setOpaque(false);
			
			opert=new JButton("OPERATIVE CONTROL",new ImageIcon("ad_op.png"));
			opert.setRolloverIcon(new ImageIcon("ad_op_r.jpg"));
			opert.setToolTipText("Extend present control to operator control..");
			opert.addActionListener(this);
			
			blank3=new JLabel(" ");
				
			pass_p=new JPanel();
			pass_p.setLayout(new GridLayout(3,1));
			pass_p.add(opert);
			pass_p.add(blank3);
			pass_p.add(pass_change);
			
			
			pass_p.setOpaque(false);
			
			
			fld_panel.add(log_p);
			//fld_panel.add(blank2);
			fld_panel.add(pass_p);
			
			//img_panel.add(blank1);
			img_panel.add(fld_panel);
			img_panel.add(blank1);

			
			add(img_panel);
			
			
			
			}
		
		public void actionPerformed(ActionEvent e)
		{
			String s[]={"",""};
			if(e.getSource()==pass_change)
			{
				setEnabled(false);
				pass_change_frame.main(s);
				setEnabled(true);
			}
			
			if(e.getSource()==opert)
			{
				new OPERATOR_CONTROL_PANEL();
			}
			if(e.getSource()==logout)
			{
				
				JOptionPane.showMessageDialog(this, "YOU HAVE BEEN LOGED OUT SUCESSFULLY!!","ADMINISTRATOR",0,new ImageIcon("sucess.jpg"));
				close();
			}
		}
		
		

	}
	
	void close()
	{
		this.dispose();
		new Hospital_Frame();
	}
	
	public static void main(String arg[])
	{
		
		new ADMIN_CONTROL_PANEL();
	}
	
}