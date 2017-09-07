package w4t4;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SliderFactory implements ISliderConsts {

	private JFrame frame;
	private JTextField textFiledHex;
	private JTextField textFieldNr;
	private JLabel farbeLabel;

	public SliderFactory(JFrame frame) {
		this.frame = frame;
	}

	public JSlider createSlider() {
		Hashtable<Integer, JLabel> sliderLabelTabelle;
		JSlider slider;
		farbeLabel = new JLabel("Grün");
		farbeLabel.setBounds(0, 0, 30, 25);

		slider = new JSlider(JSlider.HORIZONTAL, COLOR_MIN, COLOR_MAX, COLOR_INIT);
		slider.setBounds(0, 0, 300, 50);
		slider.setMajorTickSpacing(15);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true);

		sliderLabelTabelle = new Hashtable<>();
		sliderLabelTabelle = new Hashtable<>();
		JLabel min = new JLabel(Integer.toString(COLOR_MIN));
		JLabel max = new JLabel(Integer.toString(COLOR_MAX));
		min.setForeground(Color.WHITE);
		max.setForeground(Color.WHITE);
		sliderLabelTabelle.put(COLOR_MIN, min);
		sliderLabelTabelle.put(COLOR_MAX, max);

		slider.setLabelTable(sliderLabelTabelle);
		slider.setPaintLabels(true);

		textFiledHex = new JTextField();
		textFiledHex.setBounds(0, 0, 40, 25);
		textFiledHex.setHorizontalAlignment(SwingConstants.CENTER);
		textFiledHex.setEditable(false);

		textFieldNr = new JTextField();
		textFieldNr.setBounds(0, 0, 40, 25);
		textFieldNr.setHorizontalAlignment(SwingConstants.CENTER);

		return slider;
	}

	public void setColors() {
		Component[] components = frame.getContentPane().getComponents();
		List<JSlider> sliderList = new ArrayList<>();
		List<JLabel> labelList = new ArrayList<>();
		for (Component component : components) {
			if (component instanceof JSlider) {
				sliderList.add((JSlider) component);
			} else if (component instanceof JLabel) {
				labelList.add((JLabel) component);
			}
		}
		int value1 = sliderList.get(0).getValue();
		int value2 = sliderList.get(1).getValue();
		int value3 = sliderList.get(2).getValue();

		this.frame.getContentPane().setBackground(new Color(value1, value2, value3));
		for (JLabel label : labelList) {
			label.setForeground(new Color(255 - value1, 255 - value2, 255 - value3));
		}
	}

	/**
	 * @return the textFiledHex
	 */
	public JTextField getTextFiledHex() {
		return textFiledHex;
	}

	/**
	 * @return the textFieldNr
	 */
	public JTextField getTextFieldNr() {
		return textFieldNr;
	}

	/**
	 * @return the farbeLabel
	 */
	public JLabel getFarbeLabel() {
		return farbeLabel;
	}

}