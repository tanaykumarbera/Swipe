package Swipe;

import com.github.sarxos.webcam.Webcam;

public class Photographer extends Thread {
	Webcam cam;
	static boolean battery;
	
	public void run(){

		cam = Camera.getOpenCam();
		battery = true;
		
		// Keep updating the raw image in separate threads
		while(battery){
			Processor.rawIMG = cam.getImage();
		}
	}
	
	protected void finalize() throws Throwable {
		super.finalize();
		battery = false;
		cam = null;
	}
}
