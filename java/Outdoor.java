import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


class Outdoor extends JPanel implements ActionListener 
{
	JPanel out_back,but_panel;
	JButton doctor,diagonastic;
	JLabel blank1,blank2;
	
	
	Outdoor()
	{
		ImageIcon im=new ImageIcon("outdoor_back.jpg");
		setSize(im.getIconWidth(),im.getIconHeight());
		
		blank1=new JLabel("  ");
		blank2=new JLabel(" ");
		doctor=new JButton(new ImageIcon("doc_outdoor.jpg"));
		doctor.setRolloverIcon(new ImageIcon("doc_outdoor_r.jpg"));
		doctor.setPressedIcon(new ImageIcon("doc_outdoor_p.jpg"));
		doctor.setToolTipText("Outdoor Doctor's control panel");
		doctor.setContentAreaFilled(false);
		doctor.setBorderPainted(false);
		doctor.addActionListener(this);
		
		diagonastic=new JButton(new ImageIcon("diag_outdoor.jpg"));
		diagonastic.setRolloverIcon(new ImageIcon("diag_outdoor_r.jpg"));
		diagonastic.setPressedIcon(new ImageIcon("diag_outdoor.jpg"));
		diagonastic.setToolTipText("Diagonastic centre's control panel");
		diagonastic.setContentAreaFilled(false);
		diagonastic.setBorderPainted(false);
		diagonastic.addActionListener(this);
		setBackground(Color.WHITE);
		
		out_back=new JPanel()
		{
			public void paintComponent(Graphics background)
			{
				ImageIcon i=new ImageIcon("outdoor_back.jpg");
				Image image=i.getImage();
				background.drawImage(image,0,0,null);
				setBounds(0,0,i.getIconWidth(),i.getIconHeight());
				setSize(i.getIconWidth(),i.getIconHeight());
			}
		};
		
		out_back.setLayout(new GridLayout(1,3));
		
		but_panel=new JPanel();
		but_panel.setLayout(new GridLayout(2,1));
		
		but_panel.add(diagonastic);
		but_panel.add(doctor);
		
		but_panel.setOpaque(false);
		
		out_back.add(blank1);
		out_back.add(blank2);
		out_back.add(but_panel);
		
		this.add(out_back);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String s="D:/outdoor_doctors.records";
		if(e.getSource()==doctor)
		{
			setVisible(false);
			setEnabled(false);
			new doc_frame(s);
			setEnabled(true);
			setVisible(true);
			
		}
		
		if(e.getSource()==diagonastic)
		{
			setVisible(false);
			setEnabled(false);
			new diagonastic_frame();
			setEnabled(true);
			setVisible(true);
			
		}
		
	}
	
}




		
		
