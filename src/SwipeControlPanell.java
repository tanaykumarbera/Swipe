//
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JComboBox;
//import javax.swing.JPanel;
//
//public class SwipeControlPanell extends JPanel{
//	
//	BufferedImage rawImage, binaryImage;
//	
//	public SwipeControlPanell(){
//		this.setLayout(null);
//		this.setPreferredSize(new Dimension(820, 310));
//		this.setBackground(Color.WHITE);
//		
//		
//		/* Creates a default background for the area streaming video */
//		int ImgBack = Color.LIGHT_GRAY.getRGB();
//		rawImage = new BufferedImage(400, 300, BufferedImage.TYPE_BYTE_GRAY);
//		for(int j = 0; j < 300; j++)
//			for(int i = 0; i < 400; i++)
//				rawImage.setRGB(i, j, ImgBack);
//			
//		ImgBack = Color.DARK_GRAY.getRGB();
//		binaryImage = new BufferedImage(200, 150, BufferedImage.TYPE_BYTE_GRAY);
//		for(int j = 0; j < 150; j++)
//			for(int i = 0; i < 200; i++)
//				binaryImage.setRGB(i, j, ImgBack);
//		
//		/* Create a dropdown for available cameras. */
//		DefaultComboBoxModel cams = new DefaultComboBoxModel();
//
//	      fruitsName.addElement("Apple");
//	      fruitsName.addElement("Grapes");
//	      fruitsName.addElement("Mango");
//	      fruitsName.addElement("Peer");
//
//	      final JComboBox fruitCombo = new JComboBox(fruitsName);    
//	      fruitCombo.setSelectedIndex(0);
//
//	      JScrollPane fruitListScrollPane = new JScrollPane(fruitCombo);    
//
//	      JButton showButton = new JButton("Show");
//
//	      showButton.addActionListener(new ActionListener() {
//	         public void actionPerformed(ActionEvent e) { 
//	            String data = "";
//	            if (fruitCombo.getSelectedIndex() != -1) {                     
//	               data = "Fruits Selected: " 
//	                  + fruitCombo.getItemAt
//	                    (fruitCombo.getSelectedIndex());             
//	            }              
//	            statusLabel.setText(data);
//	         }
//	      }); 
//	      controlPanel.add(fruitListScrollPane);          
//	      controlPanel.add(showButton);    
//		
//		
//		this.setVisible(true);
//		System.out.println("SwipeControlPanel.SwipeControlPanel()");
//	}
//	
//	protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//		g.drawImage(rawImage, 10, 10, null);
//		g.drawImage(binaryImage, 420, 160, null);
//    }
//	
//}
