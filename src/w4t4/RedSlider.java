package w4t4;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

public class RedSlider extends Slider {

	private static final long serialVersionUID = -2413440087533531555L;
	private SliderFactory sliderFactory;
	private JSlider slider;

	public RedSlider(JFrame frame) {
		this.sliderFactory = new SliderFactory(frame);
		this.slider = this.sliderFactory.createSlider();
		this.slider.addChangeListener(this);
		this.slider.setLocation(120, 10);
		this.sliderFactory.getFarbeLabel().setLocation(30, 10);
		this.sliderFactory.getFarbeLabel().setText("Rot");
		this.sliderFactory.getTextFieldNr().setLocation(440, 35);
		this.sliderFactory.getTextFiledHex().setLocation(440, 10);
		frame.add(slider);
		frame.add(this.sliderFactory.getTextFieldNr());
		frame.add(this.sliderFactory.getTextFiledHex());
		frame.add(this.sliderFactory.getFarbeLabel());
	}

	@Override
	public JSlider getSlider() {
		return slider;
	}

	@Override
	public void scrollSlider() {
		int val = slider.getValue();
		slider.setBackground(new Color(val, 0, 0));
		this.sliderFactory.getTextFiledHex().setText(String.format("X'%02X'", val));
		this.sliderFactory.getTextFieldNr().setText(Integer.toString(val));
		sliderFactory.setColors();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		scrollSlider();
	}

}
