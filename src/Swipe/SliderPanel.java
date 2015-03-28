package Swipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
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
	public SliderPanel(Dimension s) {
		frame_size = s;
		this.setPreferredSize(frame_size);
		this.setBackground(Color.WHITE);
		TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(102, 102, 102, 255), 2), " FINE TUNING - Adjust the range slider to control Segmentation Threshold ");
		title.setTitleJustification(TitledBorder.LEFT);
		title.setTitleColor(new Color(102, 102, 102, 255));
		title.setTitleFont(new Font("monospace", Font.BOLD, 10));
		this.setBorder(title);
		
		Red = new RedRangeSlider(0, 255, Processor.MIN_RED, Processor.MAX_RED, new Dimension(frame_size.width - 50, 25));
		Green = new GreenRangeSlider(0, 255, Processor.MIN_GREEN, Processor.MAX_GREEN, new Dimension(frame_size.width - 50, 25));
		Blue = new BlueRangeSlider(0, 255, Processor.MIN_BLUE, Processor.MAX_BLUE, new Dimension(frame_size.width - 50, 25));

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
		
		add(Red);
		add(Green);
		add(Blue);
		
		this.setVisible(true);
	}
	
	
	public void updateRED(int min, int max){
		
	}
	public void updateGREEN(int min, int max){
			
	}
	public void updateBLUE(int min, int max){
		
	}
}
