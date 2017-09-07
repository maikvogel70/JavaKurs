package w4t2;

import javax.swing.JRadioButton;

public class JRadioButtonCust extends JRadioButton {

	private static final long serialVersionUID = 1L;

	public JRadioButtonCust(String text) {
		super(text, null, false);
		setOpaque(false);
	}

	public JRadioButtonCust(String text, boolean isOpaque) {
		super(text, null, false);
		setOpaque(isOpaque);
	}

	@Override
	public void setOpaque(boolean isOpaque) {
		super.setOpaque(isOpaque);
	}
}
