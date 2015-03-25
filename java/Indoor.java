import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


class Indoor extends JPanel implements ActionListener
{
	JPanel in_back,but_panel;
	JButton doctor,av_beds;
	JLabel blank1,blank2,blank3;
	String filelocation;
	
	Indoor()
	{
		ImageIcon im=new ImageIcon("indoor_back.jpg");
		setSize(im.getIconWidth(),im.getIconHeight());
		
		blank1=new JLabel("  ");
		blank2=new JLabel(" ");
		doctor=new JButton(new ImageIcon("doc_indoor.jpg"));
		doctor.setRolloverIcon(new ImageIcon("doc_indoor_r.jpg"));
		doctor.setPressedIcon(new ImageIcon("doc_indoor.jpg"));
		doctor.setToolTipText("Indoor Doctor's control panel");
		doctor.setContentAreaFilled(false);
		doctor.setBorderPainted(false);
		doctor.addActionListener(this);
		
		av_beds=new JButton(new ImageIcon("av_beds_indoor.jpg"));
		av_beds.setRolloverIcon(new ImageIcon("av_beds_indoor_r.jpg"));
		av_beds.setPressedIcon(new ImageIcon("av_beds_indoor.jpg"));
		av_beds.setToolTipText("Available Bed's control panel");
		av_beds.setContentAreaFilled(false);
		av_beds.setBorderPainted(false);
		av_beds.addActionListener(this);
		
		setBackground(Color.WHITE);
		
		in_back=new JPanel()
		{
			public void paintComponent(Graphics background)
			{
				ImageIcon i=new ImageIcon("indoor_back.jpg");
				Image image=i.getImage();
				background.drawImage(image,0,0,null);
				setBounds(0,0,i.getIconWidth(),i.getIconHeight());
				setSize(i.getIconWidth(),i.getIconHeight());
			}
		};
		
		in_back.setLayout(new GridLayout(2,1));
		
		but_panel=new JPanel();
		but_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		but_panel.add(doctor);
		but_panel.add(av_beds);
		but_panel.setOpaque(false);
		//but_panel.setBackground(Color.LIGHT_GRAY);
		in_back.add(blank1);
		
		in_back.add(but_panel);
		
		this.add(in_back);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		filelocation="D:/indoor_doctors.records";
		if(e.getSource()==doctor)
		{
			setVisible(false);
			setEnabled(false);
			new doc_frame(filelocation);
			setEnabled(true);
			setVisible(true);
		}
		
		if(e.getSource()==av_beds)
		{
			setVisible(false);
			setEnabled(false);
			new Available_beds_frame();
			setEnabled(true);
			setVisible(true);
			
		}
		
	}
	
}
		
		
