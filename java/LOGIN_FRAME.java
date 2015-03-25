import javax.swing.*;
import java.awt.*;


class LOGIN_FRAME extends JFrame 
{
	JButton LOGIN, CANCEL;
	JLabel LABEL1,LABEL2,LABEL3;
	private JPanel FIELD,BUTTONS,UFIELD,PFIELD;
	JTextField USERNAME;
	JPasswordField PASSWORD;
	
	LOGIN_FRAME()
	{
		Container con=getContentPane();
			
		BUTTONS=new JPanel();
		UFIELD=new JPanel();
		PFIELD=new JPanel();
		setSize(250,150);	

		FIELD=new JPanel();
		BUTTONS=new JPanel();
		UFIELD=new JPanel();
		PFIELD=new JPanel();
		
		BUTTONS.setLayout(new FlowLayout(FlowLayout.CENTER));
		FIELD.setLayout(new GridLayout(3,1));
		FIELD.setBackground(Color.WHITE);
		
		Font fo=new Font("Arial",Font.BOLD,10);
		
		
		
		LABEL1=new JLabel("USERNAME: ");
		LABEL1.setFont(fo);
		USERNAME=new JTextField(10);
		UFIELD.add(LABEL1);
		UFIELD.add(USERNAME);
		
		LABEL2=new JLabel("PASSWORD: ");
		LABEL2.setFont(fo);
		PASSWORD=new JPasswordField(10);
		PFIELD.add(LABEL2);
		PFIELD.add(PASSWORD);
		
		
		LOGIN=new JButton("LOGIN");
		LABEL3=new JLabel("     ");
		CANCEL=new JButton("CANCEL");
		BUTTONS.add(LOGIN);
		BUTTONS.add(LABEL3);
		BUTTONS.add(CANCEL);
		
		FIELD.add(UFIELD);
		FIELD.add(PFIELD);
		FIELD.add(BUTTONS);
		
		con.add(FIELD);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	
	
}