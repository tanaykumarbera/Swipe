package kachra;

import java.io.IOException;
import java.util.List;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;


/**
 * Detect motion. This example demonstrates how to use build-in motion detector
 * and motion listener to fire motion events.
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class DetectMotionExample implements WebcamMotionListener {
	static int wmei =0;
	public DetectMotionExample() {
		List cams = Webcam.getWebcams(); 
		WebcamMotionDetector detector = new WebcamMotionDetector((Webcam)cams.get(1));
		detector.setInterval(100); // one check per 500 ms
		detector.addMotionListener(this);
		detector.start();
	}

	
	public void motionDetected(WebcamMotionEvent wme) {
		System.out.println("Detected motion! :: " + wmei);
		wmei++;
	}

	public static void main(String[] args) throws IOException {
		new DetectMotionExample();
		System.in.read(); // keep program open
	}
}