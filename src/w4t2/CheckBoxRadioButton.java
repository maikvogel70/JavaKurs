package w4t2;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import w4t2.test.RoundedPanel;

public class CheckBoxRadioButton extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = -143587238938806227L;
	private static final String PANEL_BG = "#CBd1D4";
	private static final String GREEN = "#6EA634";
	private static final String BLUE = "#0065A9";
	private static final String YELLOW = "#FFEA64";
	private static final String RED = "#CA535E";
	private static final String GRAY = "#858686";
	private static final String PINK = "#DE5D94";
	private static final String WHITE = "#FFF2EC";

	private JPanel backGroundPanel;
	private JLabel backGroundLabel;
	private JPanel messagePanel;

	private Color foregroundColor, backColor;

	private JRadioButtonCust rbHGGruen, rbHGBlau, rbHGRot, rbHGGelb, rbHGGrau;
	private JRadioButtonCust rbVGBlau, rbVGRot, rbVGGelb, rbVGPink, rbVGWhite;

	private ButtonGroup hgButtonGroup, vgButtonGroup;
	private ItemListener hgRadioButtonListener, vgRadioButtonListener;

	public CheckBoxRadioButton() {
		initializeComponents();
	}

	private void initializeComponents() {

		this.setTitle("CheckBox und RadioButton");
		this.setSize(480, 280);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);

		hgRadioButtonListener = new HGRadioButtonItemListener();
		createPnlBgRadio("Hintergrund", new Rectangle(20, 20, 120, 160), new GridLayout(6, 1));
		hgButtonGroup = new ButtonGroup();
		createGreenCbx(hgButtonGroup);
		createBlueCbx(hgButtonGroup);
		createRedCbx(hgButtonGroup);
		createYellowCbx(hgButtonGroup);
		createGrayCbx(hgButtonGroup);
		this.add(backGroundPanel);

		vgRadioButtonListener = new VGRadioButtonItemListener();
		createPnlVgRadio("Vordergrund", new Rectangle(150, 20, 120, 160), new GridLayout(6, 1));
		vgButtonGroup = new ButtonGroup();
		createBlueVGCbx(vgButtonGroup);
		createRedVGCbx(vgButtonGroup);
		createYellowVGCbx(vgButtonGroup);
		createWhiteVGCbx(vgButtonGroup);
		createPinkVGCbx(vgButtonGroup);
		this.add(backGroundPanel);

		addMessagePanel();
		addExitBtn();

	}

	private void addExitBtn() {
		JButton exitButton = new JButton("exit");
		exitButton.setBounds(new Rectangle(400, 220, 70, 30));
		exitButton.addActionListener(new ButtonBeendenActionListener());
		this.add(exitButton);
	}

	private void addMessagePanel() {
		JLabel messageLabel = new JLabel(" Diese Farben passen nicht zusammen!");
		messagePanel = new RoundedPanel();
		((RoundedPanel) messagePanel).setShady(false);
		((RoundedPanel) messagePanel).setStrokeSize(0);
		messagePanel.add(messageLabel);
		messagePanel.setBounds(new Rectangle(20, 200, 250, 30));
		messagePanel.setVisible(false);
		this.add(messagePanel);
	}
	//
	// private void addMessagePanel() {
	// JLabel messageLabel = new JLabel(" Diese Farben passen nicht zusammen!");
	// messagePanel = new JPanel();
	// messagePanel.add(messageLabel);
	// messagePanel.setBounds(new Rectangle(20, 190, 230, 30));
	// messagePanel.setVisible(false);
	// this.add(messagePanel);
	// }

	private void createPnlVgRadio(String title, Rectangle r, GridLayout layout) {
		createPnlBgRadio(title, r, layout);
	}

	private void createPnlBgRadio(String title, Rectangle r, GridLayout layout) {
		// Panel für die Hintergrundfarbe erstellen
		backGroundPanel = new RoundedPanel();
		((RoundedPanel) backGroundPanel).setShady(false);
		((RoundedPanel) backGroundPanel).setStrokeSize(0);
		backGroundPanel.setBounds(r);
		backGroundPanel.setBackground(Color.decode(PANEL_BG));
		backGroundPanel.setLayout(layout);

		backGroundLabel = new JLabel(title);
		backGroundLabel.setHorizontalAlignment(JLabel.CENTER);
		backGroundPanel.add(backGroundLabel);
	}

	private void createGrayCbx(ButtonGroup hgButtonGroup) {
		rbHGGrau = new JRadioButtonCust("Grau");
		hgButtonGroup.add(rbHGGrau);
		rbHGGrau.addItemListener(hgRadioButtonListener);
		backGroundPanel.add(rbHGGrau);
	}

	private void createYellowCbx(ButtonGroup hgButtonGroup) {
		rbHGGelb = new JRadioButtonCust("Gelb");
		hgButtonGroup.add(rbHGGelb);
		rbHGGelb.addItemListener(hgRadioButtonListener);
		backGroundPanel.add(rbHGGelb);
	}

	private void createRedCbx(ButtonGroup hgButtonGroup) {
		rbHGRot = new JRadioButtonCust("Rot");
		hgButtonGroup.add(rbHGRot);
		rbHGRot.addItemListener(hgRadioButtonListener);
		backGroundPanel.add(rbHGRot);
	}

	private void createBlueCbx(ButtonGroup hgButtonGroup) {
		rbHGBlau = new JRadioButtonCust("Blau");
		hgButtonGroup.add(rbHGBlau);
		rbHGBlau.addItemListener(hgRadioButtonListener);
		backGroundPanel.add(rbHGBlau);
	}

	private void createGreenCbx(ButtonGroup hgButtonGroup) {
		rbHGGruen = new JRadioButtonCust("Grün");
		hgButtonGroup.add(rbHGGruen);
		rbHGGruen.addItemListener(hgRadioButtonListener);
		backGroundPanel.add(rbHGGruen);
	}

	private void createWhiteVGCbx(ButtonGroup hgButtonGroup) {
		rbVGWhite = new JRadioButtonCust("Weiss");
		hgButtonGroup.add(rbVGWhite);
		rbVGWhite.addItemListener(vgRadioButtonListener);
		backGroundPanel.add(rbVGWhite);
	}

	private void createPinkVGCbx(ButtonGroup hgButtonGroup) {
		rbVGPink = new JRadioButtonCust("Pink");
		hgButtonGroup.add(rbVGPink);
		rbVGPink.addItemListener(vgRadioButtonListener);
		backGroundPanel.add(rbVGPink);
	}

	private void createYellowVGCbx(ButtonGroup hgButtonGroup) {
		rbVGGelb = new JRadioButtonCust("Gelb");
		hgButtonGroup.add(rbVGGelb);
		rbVGGelb.addItemListener(vgRadioButtonListener);
		backGroundPanel.add(rbVGGelb);
	}

	private void createRedVGCbx(ButtonGroup hgButtonGroup) {
		rbVGRot = new JRadioButtonCust("Rot");
		hgButtonGroup.add(rbVGRot);
		rbVGRot.addItemListener(vgRadioButtonListener);
		backGroundPanel.add(rbVGRot);
	}

	private void createBlueVGCbx(ButtonGroup hgButtonGroup) {
		rbVGBlau = new JRadioButtonCust("Blau");
		hgButtonGroup.add(rbVGBlau);
		rbVGBlau.addItemListener(vgRadioButtonListener);
		backGroundPanel.add(rbVGBlau);
	}

	private void initFrame() {
		// In der Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);
		rbHGGruen.setSelected(true);
		rbVGBlau.setSelected(true);
	}

	private void setBackColor() {

		backColor = Color.decode(CheckBoxRadioButton.GRAY);

		if (rbHGGruen.isSelected()) {
			backColor = Color.decode(CheckBoxRadioButton.GREEN);
		} else if (rbHGBlau.isSelected()) {
			backColor = Color.decode(CheckBoxRadioButton.BLUE);
		} else if (rbHGRot.isSelected()) {
			backColor = Color.decode(CheckBoxRadioButton.RED);
		} else if (rbHGGelb.isSelected()) {
			backColor = Color.decode(CheckBoxRadioButton.YELLOW);
		}
		this.getContentPane().setBackground(backColor);
		checkColorKompatibility();
	}

	private void setForegroundColor() {
		foregroundColor = Color.decode(CheckBoxRadioButton.WHITE);

		if (rbVGBlau.isSelected()) {
			foregroundColor = Color.decode(CheckBoxRadioButton.BLUE);
		} else if (rbVGGelb.isSelected()) {
			foregroundColor = Color.decode(CheckBoxRadioButton.YELLOW);
		} else if (rbVGPink.isSelected()) {
			foregroundColor = Color.decode(CheckBoxRadioButton.PINK);
		} else if (rbVGRot.isSelected()) {
			foregroundColor = Color.decode(CheckBoxRadioButton.RED);
		}

		setFgColor(foregroundColor, vgButtonGroup.getElements());
		setFgColor(foregroundColor, hgButtonGroup.getElements());
		checkColorKompatibility();
	}

	private void checkColorKompatibility() {
		// alternativ könnte man auch die radiobuttons deaktivieren
		if (this.foregroundColor != null && this.foregroundColor.equals(this.backColor)) {
			messagePanel.setVisible(true);
		} else {
			messagePanel.setVisible(false);
		}
	}

	private void setFgColor(Color foregroundColor, Enumeration<AbstractButton> elements) {
		AbstractButton nextElement;
		while (elements.hasMoreElements()) {
			nextElement = elements.nextElement();
			if (nextElement instanceof JRadioButtonCust) {
				((JRadioButtonCust) nextElement).setForeground(foregroundColor);
			}
		}
	}

	public void showFrame() {
		initFrame();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		CheckBoxRadioButton f = new CheckBoxRadioButton();
		f.showFrame();
	}

	private class HGRadioButtonItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				setBackColor();
			}
		}
	}

	private class VGRadioButtonItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				setForegroundColor();
			}
		}
	}

	private class ButtonBeendenActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("AE");

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println("ISC");

	}

}
