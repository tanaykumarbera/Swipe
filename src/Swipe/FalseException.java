package Swipe;
public class FalseException extends Exception {
	String source;
	FalseException(String str){
		source = str;
	}
	public String toString(){
		return source;
	}
}
