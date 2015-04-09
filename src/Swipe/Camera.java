package Swipe;
import java.awt.Dimension;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import com.github.sarxos.webcam.Webcam;

public class Camera{
	static List avaiableCams;
	static Webcam webcam;
	static boolean camOpen;
	static Dimension swipeDefault = new Dimension(640, 480);
	
	public static DefaultComboBoxModel enquireCams(){
		DefaultComboBoxModel cams = null;
		
		/* Checks even if a camera exists or not! */ 
		webcam = Webcam.getDefault();
		if (webcam == null) return null;
		
		avaiableCams = Webcam.getWebcams();
		cams = new DefaultComboBoxModel();
		cams.addElement(webcam.getName());
		if(avaiableCams.size() > 1){
			for(int i = 1; i < avaiableCams.size(); i++){
				cams.addElement(((Webcam)avaiableCams.get(i)).getName());
			}
		}
		return cams;
	}
	
	public static Webcam getCam(int index){
		if(avaiableCams.size() > 0 && index < avaiableCams.size())
			return (Webcam) avaiableCams.get(index);
		return null;
	}
	
	public static Webcam getChoosenCam(){
		return (Webcam) avaiableCams.get(SwipeControlPanel.camMenu.getSelectedIndex());
	}
	
	public static Webcam getOpenCam(){
		if(!camOpen){
			if(webcam != null)
				webcam.close();
			
			webcam = getChoosenCam();
			webcam.setViewSize(swipeDefault);
			webcam.open();
			camOpen = true;
		}
		return webcam;
	}
	
	public static void dispose(){
		if(camOpen)
			webcam.close();
	}
}
