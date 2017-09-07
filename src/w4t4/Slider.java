package w4t4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public abstract class Slider implements ActionListener, ChangeListener, ISliderConsts {

	public abstract void scrollSlider();

	public abstract JSlider getSlider();

	@Override
	public abstract void actionPerformed(ActionEvent e);

	@Override
	public abstract void stateChanged(ChangeEvent e);

}
