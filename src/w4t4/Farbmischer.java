package w4t4;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Farbmischer extends JFrame implements ISliderConsts {

	private JButton btnBeenden;
	private Slider redSlider, blueSlider, greenSlider;

	public Farbmischer() {
		initializeComponents();
	}

	private void initializeComponents() {

		redSlider = new RedSlider(this);
		blueSlider = new BlueSlider(this);
		greenSlider = new GreenSlider(this);

		this.setTitle("Farbmischer");
		this.setSize(520, 340);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initFrame() {
		this.setLocationRelativeTo(null);
		redSlider.scrollSlider();
		blueSlider.scrollSlider();
		greenSlider.scrollSlider();
	}

	public void showFrame() {
		initFrame();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Farbmischer f = new Farbmischer();
		f.showFrame();

		File fi = new File(f.getClass().getResource("/").getFile());
		fi = fi.getParentFile();
		fi = fi.getParentFile();
		fi = fi.getParentFile();
		fi = fi.getParentFile();
		fi = fi.getParentFile();
	}

}
