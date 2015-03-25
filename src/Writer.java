import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Writer {
	public static void pgm(int[] imgArr, Dimension resolution, String fileName) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		writer.println("P2");
		writer.println("#Generated as part of project Swipe");
		writer.println(resolution.width + " " + resolution.height);
		writer.println("255");
		for(int i = 0; i< imgArr.length; i++){
			writer.println(imgArr[i]);
		}
		writer.close();
	}
	
	public static void pgm(int[][] imgArr, Dimension resolution, String fileName) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		writer.println("P2");
		writer.println("#Generated as part of project Swipe");
		writer.println(resolution.width + " " + resolution.height);
		writer.println("255");
		for(int j = 0; j< resolution.height; j++){
			for(int i = 0; i< resolution.width; i++){
				writer.print(imgArr[i][j] + " ");
			}
			writer.println(" ");
		}
		writer.close();
	}
}
