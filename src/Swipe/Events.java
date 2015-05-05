package Swipe;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Events {
	public static final int TOP_LEFT = 1,
			TOP = 2,
			TOP_RIGHT = 3,
			LEFT = 4,
			MIDDLE = 5,
			RIGHT = 6,
			BOTTOM_LEFT = 7,
			BOTTOM = 8,
			BOTTOM_RIGHT = 9,
			NONE = 0;
	
	public static final int stTHRESHOLD = 3;
	
	public static Robot mouse;
	
	public static int c = 0, c1 = 1, c3 = 0, c5 = 0, c7 = 0, c9 = 0;
	
	public static int last_alpha = 0;
	public static long trigger_threshold = 750, last_triggered = 0;
	
	public static int trigger(int option){
		
		double alpha = 0.0, step = 255 / (stTHRESHOLD + 3) ;
		// return a float depicting the opacity of selection
		
		if(c++ < stTHRESHOLD){
			last_alpha = 0;
			return 0;
		}
		
		long now = System.currentTimeMillis();
		if((now - last_triggered) < trigger_threshold){
			return last_alpha;
		}
		last_triggered = now;
		
		try {
			mouse = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			return 0;
		}
		
		switch(option){
			case TOP_LEFT:
				
				// Escape
				c3 = c5 = c7 = c9 = 0; c1++; alpha = c1 * step; if(c1 < stTHRESHOLD) break; c1 = 0;
				mouse.keyPress(KeyEvent.VK_ESCAPE); mouse.keyRelease(KeyEvent.VK_ESCAPE);
				break;
			
			case TOP:
				
				c1 = c3 = c5 = c7 = c9 = 0; alpha = stTHRESHOLD * step;
				if(ControlPanel.ctrl.isSelected()) mouse.keyPress(KeyEvent.VK_CONTROL);
				mouse.keyPress(KeyEvent.VK_UP); mouse.keyRelease(KeyEvent.VK_UP);
				if(ControlPanel.ctrl.isSelected()) mouse.keyRelease(KeyEvent.VK_CONTROL);
				break;
				
			case TOP_RIGHT:
				
				// Enter
				c1 = c5 = c7 = c9 = 0; c3++; alpha = c3 * step; if(c3 < stTHRESHOLD) break; c3 = 0;
				mouse.keyPress(KeyEvent.VK_ENTER); mouse.keyRelease(KeyEvent.VK_ENTER);
				break;
				
			case LEFT:
				
				c1 = c3 = c5 = c7 = c9 = 0; alpha = stTHRESHOLD * step;
				if(ControlPanel.reverse.isSelected()){
					// Left + Inverse , Right					
					if(ControlPanel.ctrl.isSelected()) mouse.keyPress(KeyEvent.VK_CONTROL);
					mouse.keyPress(KeyEvent.VK_RIGHT); mouse.keyRelease(KeyEvent.VK_RIGHT);
					if(ControlPanel.ctrl.isSelected()) mouse.keyRelease(KeyEvent.VK_CONTROL);
				}else{
					// Left					
					if(ControlPanel.ctrl.isSelected()) mouse.keyPress(KeyEvent.VK_CONTROL);
					mouse.keyPress(KeyEvent.VK_LEFT); mouse.keyRelease(KeyEvent.VK_LEFT);
					if(ControlPanel.ctrl.isSelected()) mouse.keyRelease(KeyEvent.VK_CONTROL);
				}
				break;
				
			case MIDDLE:
				
				if(!ControlPanel.center.isSelected()){
					c1 = c3 = c7 = c9 = 0; c5++; alpha = c5 * step; if(c5 < stTHRESHOLD) break; c5 = 0;
					mouse.keyPress(KeyEvent.VK_SPACE); mouse.keyRelease(KeyEvent.VK_SPACE);
					break;
				}
				c1 = c3 = c7 = c9 = 0; alpha = 0.0;
				break;
				
			case RIGHT:
				
				c1 = c3 = c5 = c7 = c9 = 0; alpha = stTHRESHOLD * step;
				if(ControlPanel.reverse.isSelected()){
					// Right + Inverse , Left
					if(ControlPanel.ctrl.isSelected()) mouse.keyPress(KeyEvent.VK_CONTROL);
					mouse.keyPress(KeyEvent.VK_LEFT); mouse.keyRelease(KeyEvent.VK_LEFT);
					if(ControlPanel.ctrl.isSelected()) mouse.keyRelease(KeyEvent.VK_CONTROL);
				}else{
					// Right
					if(ControlPanel.ctrl.isSelected()) mouse.keyPress(KeyEvent.VK_CONTROL);
					mouse.keyPress(KeyEvent.VK_RIGHT); mouse.keyRelease(KeyEvent.VK_RIGHT);
					if(ControlPanel.ctrl.isSelected()) mouse.keyRelease(KeyEvent.VK_CONTROL);
				}
				break;
				
			case BOTTOM_LEFT:
				
				// Backspace
				c1 = c3 = c5 = c9 = 0; c7++; alpha = c7 * step; if(c7 < stTHRESHOLD) break; c7 = 0;
				mouse.keyPress(KeyEvent.VK_BACK_SPACE); mouse.keyRelease(KeyEvent.VK_BACK_SPACE);
				break;
				
			case BOTTOM:
				
				c1 = c3 = c5 = c7 = c9 = 0; alpha = stTHRESHOLD * step;
				if(ControlPanel.ctrl.isSelected()) mouse.keyPress(KeyEvent.VK_CONTROL);
				mouse.keyPress(KeyEvent.VK_DOWN); mouse.keyRelease(KeyEvent.VK_DOWN);
				if(ControlPanel.ctrl.isSelected()) mouse.keyRelease(KeyEvent.VK_CONTROL);
				break;
				
			case BOTTOM_RIGHT:
				
				// Right Shortcut Menu
				c1 = c3 = c5 = c7 = 0; c9++; alpha = c9 * step; if(c9 < stTHRESHOLD) break; c9 = 0;
				mouse.keyPress(KeyEvent.VK_CONTEXT_MENU); mouse.keyRelease(KeyEvent.VK_CONTEXT_MENU);
				break;
				
			case NONE:
			default:
				c = c1 = c3 = c5 = c7 = c9 = 0; alpha = 0.0;
		}
		last_alpha = (int) alpha; 
		return last_alpha;
	}
}
