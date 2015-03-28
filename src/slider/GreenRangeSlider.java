package slider;

import java.awt.Color;
import java.awt.Dimension;

public class GreenRangeSlider extends RangeSlider {
	public GreenRangeSlider(int min, int max, int initialMIN, int initialMAX, Dimension size) {
		super(min, max);
		//initSlider();
        setBackground(Color.WHITE);
        setPreferredSize(size);
        setValue(initialMIN);
        setUpperValue(initialMAX);
	}

	/**
     * Overrides the superclass method to install the UI delegate to draw two
     * thumbs.
     */
    
    public void updateUI() {
    	setUI(new RangeSliderUI(this, Color.GREEN));
        // Update UI for slider labels.  This must be called after updating the
        // UI of the slider.  Refer to JSlider.updateUI().
        updateLabelUIs();
    }
}
