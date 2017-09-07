package w4t4;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

public class GreenSlider extends Slider {
	private SliderFactory sliderFactory;
	private JSlider slider;

	public GreenSlider(JFrame frame) {
		this.sliderFactory = new SliderFactory(frame);
		this.slider = this.sliderFactory.createSlider();
		this.slider.addChangeListener(this);
		this.slider.setLocation(120, 110);
		this.sliderFactory.getFarbeLabel().setLocation(30, 110);
		this.sliderFactory.getFarbeLabel().setText("Grün");
		this.sliderFactory.getTextFieldNr().setLocation(440, 135);
		this.sliderFactory.getTextFiledHex().setLocation(440, 110);
		frame.add(slider);
		frame.add(this.sliderFactory.getTextFieldNr());
		frame.add(this.sliderFactory.getTextFiledHex());
		frame.add(this.sliderFactory.getFarbeLabel());
	}

	@Override
	public void scrollSlider() {
		int val = this.slider.getValue();
		this.slider.setBackground(new Color(0, val, 0));
		sliderFactory.getTextFiledHex().setText(String.format("X'%02X'", val));
		sliderFactory.getTextFieldNr().setText(Integer.toString(val));
		sliderFactory.setColors();
	}

	@Override
	public JSlider getSlider() {
		return this.slider;
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
