import java.util.List;
import java.util.Scanner;
import com.github.sarxos.webcam.Webcam;

public class Camera{
	public static Webcam init() throws FalseException{
		Webcam webcam = Webcam.getDefault();
		if (webcam != null){
			List avaiableCams = Webcam.getWebcams();		
			System.out.println("\nDefault Webcam: " + webcam.getName());
			
			if(avaiableCams.size() > 1){
				System.out.println("Available Cams :");
				for(int i = 0; i < avaiableCams.size(); i++){
					System.out.println((i+1) + ". " + ((Webcam)avaiableCams.get(i)).getName());
				}
				System.out.print("\nChoose a camera or enter 0 to continue with default:: ");
				try{
					Scanner I = new Scanner(System.in);
					int choice = I.nextInt();
					if(choice == 0)
						System.out.println("Proceeding with default.");
					else
						webcam = (Webcam) avaiableCams.get(choice - 1);
				}catch(Exception e){
					System.out.println("Invalid. Reverting back to default.");
					webcam = Webcam.getDefault();
				}
				System.out.print("\nAssigned Cam :: " + webcam.getName());
			}else{
				System.out.println("No other cams detected. Proceeding with default.");
			}
		} else {
			System.out.println("No cams detected.");
			throw new FalseException("Camera :: No cams detected.");
		}
		
		return webcam;
	}

}
