package w4t4;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

public class BlueSlider extends Slider {
	private SliderFactory sliderFactory;
	private JSlider slider;

	public BlueSlider(JFrame frame) {
		this.sliderFactory = new SliderFactory(frame);
		this.slider = this.sliderFactory.createSlider();
		this.slider.addChangeListener(this);
		this.slider.setLocation(120, 210);
		this.sliderFactory.getFarbeLabel().setLocation(30, 210);
		this.sliderFactory.getFarbeLabel().setText("Blau");
		this.sliderFactory.getTextFieldNr().setLocation(440, 235);
		this.sliderFactory.getTextFiledHex().setLocation(440, 210);
		frame.add(slider);
		frame.add(this.sliderFactory.getTextFieldNr());
		frame.add(this.sliderFactory.getTextFiledHex());
		frame.add(this.sliderFactory.getFarbeLabel());
	}

	@Override
	public void scrollSlider() {
		int val = slider.getValue();
		slider.setBackground(new Color(0, 0, val));
		sliderFactory.getTextFiledHex().setText(String.format("X'%02X'", val));
		sliderFactory.getTextFieldNr().setText(Integer.toString(val));
		sliderFactory.setColors();
	}

	@Override
	public JSlider getSlider() {
		return slider;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		scrollSlider();

	}
}
