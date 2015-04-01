package Swipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import slider.BlueRangeSlider;
import slider.GreenRangeSlider;
import slider.RangeSlider;
import slider.RedRangeSlider;

public class SliderPanel extends JPanel{
	Dimension frame_size;
	RangeSlider Red, Green, Blue;
	JLabel redLabel, blueLabel, greenLabel, redRange, blueRange, greenRange;
	Font bold10; 
	Dimension colorLabel, colorSlider;
	TitledBorder title;
	
	public SliderPanel(Dimension s) {
		frame_size = s;
		this.setPreferredSize(frame_size);
		this.setBackground(Color.WHITE);
		
		bold10 = new Font("monospace", Font.BOLD, 10);
		colorLabel = new Dimension(70, 25);
		colorSlider = new Dimension(320, 25);
		
		title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(102, 102, 102, 255), 2), " FINE TUNING - Adjust the range sliders to control Segmentation Threshold ");
		title.setTitleJustification(TitledBorder.LEFT);
		title.setTitleColor(new Color(102, 102, 102, 255));
		title.setTitleFont(bold10);
		this.setBorder(title);
		
		redLabel = new JLabel("[000] RED", JLabel.LEFT); redLabel.setFont(bold10); redLabel.setPreferredSize(colorLabel);
		Red = new RedRangeSlider(0, 255, Processor.MIN_RED, Processor.MAX_RED, colorSlider);
		redRange = new JLabel(); updateRED(Processor.MIN_RED, Processor.MAX_RED); redRange.setFont(bold10);
		
		greenLabel = new JLabel("[000] GREEN"); greenLabel.setFont(bold10); greenLabel.setPreferredSize(colorLabel);
		Green = new GreenRangeSlider(0, 255, Processor.MIN_GREEN, Processor.MAX_GREEN, colorSlider);
		greenRange = new JLabel(); updateGREEN(Processor.MIN_GREEN, Processor.MAX_GREEN); greenRange.setFont(bold10);
		
		blueLabel = new JLabel("[000] BLUE"); blueLabel.setFont(bold10); blueLabel.setPreferredSize(colorLabel);
		Blue = new BlueRangeSlider(0, 255, Processor.MIN_BLUE, Processor.MAX_BLUE, colorSlider);
		blueRange = new JLabel(); updateBLUE(Processor.MIN_BLUE, Processor.MAX_BLUE); blueRange.setFont(bold10);
		
		Red.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                updateRED(slider.getValue(), slider.getUpperValue());
            }
        });
		Green.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                updateGREEN(slider.getValue(), slider.getUpperValue());
            }
        });
		Blue.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                updateBLUE(slider.getValue(), slider.getUpperValue());
            }
        });
		
		add(redLabel); add(Red); add(redRange);
		add(greenLabel); add(Green); add(greenRange);
		add(blueLabel); add(Blue); add(blueRange);
		
		this.setVisible(true);
	}
	
	
	public void updateRED(int min, int max){
		Processor.MIN_RED = min; 
		Processor.MAX_RED = max;
		redRange.setText("[" + str_eq_len(min) + ", " + str_eq_len(max) + "]");
	}
	public void updateGREEN(int min, int max){
		Processor.MIN_GREEN = min; 
		Processor.MAX_GREEN = max;
		greenRange.setText("[" + str_eq_len(min) + ", " + str_eq_len(max) + "]");
	}
	public void updateBLUE(int min, int max){
		Processor.MIN_BLUE = min;
		Processor.MAX_BLUE = max;
		blueRange.setText("[" + str_eq_len(min) + ", " + str_eq_len(max) + "]");
	}
	
	public String str_eq_len(int i){
		String zero_i;
		if(i < 10) zero_i = "00" + i;
		else if(i < 100) zero_i = "0" + i;
		else zero_i = "" + i;
		
		return zero_i;
	}
}
