package w5t1;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FrameDialogSuchenUndErsetzen extends JDialog implements ActionListener, WindowListener {

	private JLabel lbl1, lbl2;
	private JTextField tfSuche, tfErsetze;
	private JCheckBox checkGrossKlein;
	private JButton btnAbbrechen, btnWeitersuchen, btnErsetzen, btnAlleErsetzen;

	// Wir bekommen vom aufrufenden Fenster eine Referenz auf seine TextArea
	// übergeben
	private JTextArea textArea;

	private Component owner;
	private int posOfOccours;

	public FrameDialogSuchenUndErsetzen(JTextArea textArea) {
		initializeComponents();
		this.textArea = textArea;

	}

	private void initializeComponents() {
		this.setTitle("Suchen und Ersetzen");
		this.setSize(480, 155);

		// Layout Manager ausschalten
		this.setLayout(null);

		// Keine Grössenänerung des Dialogs
		this.setResizable(false);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		posOfOccours = 0;

		lbl1 = new JLabel("Suchen nach:");
		lbl1.setBounds(5, 5, 90, 25);
		this.add(lbl1);

		tfSuche = new JTextField();
		tfSuche.setBounds(110, 5, 240, 25);
		this.add(tfSuche);

		lbl2 = new JLabel("Ersetzen durch:");
		lbl2.setBounds(5, 35, 90, 25);
		this.add(lbl2);

		tfErsetze = new JTextField();
		tfErsetze.setBounds(110, 35, 240, 25);
		this.add(tfErsetze);

		checkGrossKlein = new JCheckBox("Groß-/Kleinschreibung beachten");
		checkGrossKlein.setBounds(5, 90, 300, 25);
		this.add(checkGrossKlein);

		btnWeitersuchen = new JButton("Weitersuchen");
		btnWeitersuchen.setFont(btnWeitersuchen.getFont().deriveFont(Font.PLAIN, 11.0f));
		btnWeitersuchen.setBounds(350, 5, 120, 25);
		btnWeitersuchen.addActionListener(this);
		this.add(btnWeitersuchen);

		btnErsetzen = new JButton("Ersetzen");
		btnErsetzen.setFont(btnErsetzen.getFont().deriveFont(Font.PLAIN, 11.0f));
		btnErsetzen.setBounds(350, 35, 120, 25);
		btnErsetzen.addActionListener(this);
		this.add(btnErsetzen);

		btnAlleErsetzen = new JButton("Alle Ersetzen");
		btnAlleErsetzen.setFont(btnAlleErsetzen.getFont().deriveFont(Font.PLAIN, 11.0f));
		btnAlleErsetzen.setBounds(350, 65, 120, 25);
		btnAlleErsetzen.addActionListener(this);
		this.add(btnAlleErsetzen);

		btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setFont(btnAbbrechen.getFont().deriveFont(Font.PLAIN, 11.0f));
		btnAbbrechen.setBounds(350, 95, 120, 25);
		btnAbbrechen.addActionListener(this);
		this.add(btnAbbrechen);
		this.addWindowListener(this);

	}

	private void initDialog() {
		this.setModal(false);

		this.setLocationRelativeTo(owner);

		if (textArea.getSelectedText() != null) {
			tfSuche.setText(textArea.getSelectedText());
		}

		tfSuche.selectAll();

		// Wichtig!
		// Durch den Fokusverlust ist der markierte Text im Hauptfenster
		// nicht mehr sichtbar (gilt nur für modalen Dialog).
		// Durch das erneute Setzen des Eingabefokus wird die Markierung wieder
		// angezeigt.
		textArea.requestFocusInWindow();

		// Beispiel für die Selektion eines Textes
		// textArea.setSelectionStart(12);
		// textArea.setSelectionEnd(20);

		// textArea.select(12, 20);

	}

	private void showDialog() {
		initDialog();
		this.setVisible(true);
	}

	public void showDialog(Component owner) {
		this.owner = owner;
		showDialog();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == this.btnWeitersuchen) {
			this.search(posOfOccours);
		} else if (source == this.btnErsetzen) {
			this.replace();
		} else if (source == this.btnAlleErsetzen) {
			this.replaceAll();
		} else if (source == this.btnAbbrechen) {
			this.dispose();
		}

	}

	private void search(int lastPos) {
		boolean isBigSmallChecked = this.checkGrossKlein.isSelected();
		String searchText = this.tfSuche.getText();
		String textAreaText = this.textArea.getText();
		int selectedTextLength = 0;

		if (textArea.getSelectedText() != null) {
			selectedTextLength = textArea.getSelectedText().length();
		}

		if (!isBigSmallChecked) {
			posOfOccours = textAreaText.toLowerCase().indexOf(searchText, lastPos + selectedTextLength);
		} else {
			posOfOccours = textAreaText.indexOf(searchText, lastPos + selectedTextLength);
		}
		if (posOfOccours > -1) {// habe was gefunden
			int lengthSearchText = searchText.length();
			textArea.select(posOfOccours, posOfOccours + lengthSearchText);
		} else {
			JOptionPane.showMessageDialog(this, "Keine weiteren Suchergebnisse", "Suche beendet",
					JOptionPane.OK_OPTION);
		}

	}

	private void replace() {
		String replaceText = this.tfErsetze.getText();
		String textAreaText = this.textArea.getText();
		search(0);
		String selectedText = this.textArea.getSelectedText();
		int index = textAreaText.indexOf(selectedText);
		String startText = textAreaText.substring(0, index);
		String endText = textAreaText.substring(index + selectedText.length(), textAreaText.length());
		String newText = startText + replaceText + endText;
		this.textArea.setText(newText);
	}

	private void replaceAll() {
		String searchText = this.tfSuche.getText();
		String replaceText = this.tfErsetze.getText();
		String textAreaText = this.textArea.getText();
		if (this.checkGrossKlein.isSelected()) {
			this.textArea.setText(textAreaText.replaceAll(searchText, replaceText));
		} else {
			// (?i) = regulärer Ausdruck für caseinsensitive
			this.textArea.setText(textAreaText.replaceAll("(?i)" + Pattern.quote(searchText), replaceText));
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// ((SuchenErsetzen)
		// owner).getWindowList().remove((FrameDialogSuchenUndErsetzen) e.getSource());
		((SuchenErsetzen) owner).getBtnSuchenUndErsetzen().setEnabled(true);
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {

		((SuchenErsetzen) owner).getBtnSuchenUndErsetzen().setEnabled(false);

		// if (((SuchenErsetzen) owner).getWindowList().size() >= 1) {
		// return;
		// } else {
		// ((SuchenErsetzen) owner).getWindowList().add((FrameDialogSuchenUndErsetzen)
		// e.getSource());
		// }
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	public FrameDialogSuchenUndErsetzen getDialog() {
		return this;
	}
}
