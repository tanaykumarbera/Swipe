package Swipe;

import com.github.sarxos.webcam.Webcam;

public class Photographer extends Thread {
	Webcam cam;
	static boolean battery;
	
	public Photographer() {
		battery = true;
	}
	
	public void run(){
		cam = Camera.getChoosenCam();

		// try to close previous instance of webcam
		try{
			cam.close();
			Photographer.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		// Even if it persists, keep on try with a delay of few seconds
		while(cam.isOpen()){
			System.out.println("aa");
			try {
				Photographer.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Change resolution to 640 x 480
		cam.setViewSize(Processor.resMAIN);
		
		// Start Cam
		cam.open();
		
		// Keep updating the raw image in separate threads
		while(battery){
			Processor.rawIMG = cam.getImage();
		}
		
		// When done, make an attempt to close the camera
		cam.close();
	}
	
	protected void finalize() throws Throwable {
		super.finalize();
		battery = false;
		if(cam.isOpen()) cam.close();
		cam = null;
	}
}
