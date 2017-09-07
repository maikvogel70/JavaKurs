package w5t1;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SuchenErsetzen extends JFrame implements ActionListener, ItemListener, WindowListener, DocumentListener {
	private JTextArea textArea;
	private JScrollPane textAreaScrollPane;

	private JCheckBox checkLineWrap, checkWordWrap;

	private JButton btnBeenden, btnDialog, btnDatei, btnSuchenUndErsetzen;
	private boolean isDocumentChanged;
	private ArrayList<FrameDialogSuchenUndErsetzen> windowList;
	private FrameDialogSuchenUndErsetzen dlg;
	private File fcFile;

	public SuchenErsetzen() {
		initializeComponents();
	}

	private void initializeComponents() {
		this.setWindowList(new ArrayList<>());
		Icon icon;

		this.setTitle("Hauptfenster des Programms");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/Editor.png")));

		this.setSize(760, 420);

		// Keine Grössenänderung des Frames
		this.setResizable(false);

		// Layout Manager ausschalten
		this.setLayout(null);

		// Das Schließen des Frames wird mit dem WindowListener überwacht.
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);

		textArea = new JTextArea();

		// Freien Bereich zwischen Rahmen und Text definieren
		textArea.setMargin(new Insets(10, 10, 10, 10));

		textArea.getDocument().addDocumentListener(this);

		textAreaScrollPane = new JScrollPane(textArea);
		textAreaScrollPane.setBounds(5, 5, 580, 360);
		this.add(textAreaScrollPane);

		checkLineWrap = new JCheckBox("Automatischer Zeilenumbruch");
		checkLineWrap.setFont(checkLineWrap.getFont().deriveFont(Font.PLAIN, 9F));
		checkLineWrap.setBounds(590, 5, 200, 20);
		checkLineWrap.addItemListener(this);
		this.add(checkLineWrap);

		checkWordWrap = new JCheckBox("Auf Wortgrenze");
		checkWordWrap.setFont(checkWordWrap.getFont().deriveFont(Font.PLAIN, 9F));
		checkWordWrap.setBounds(590, 25, 200, 20);
		checkWordWrap.addItemListener(this);
		this.add(checkWordWrap);

		btnDatei = new JButton("Datei öffnen...");
		btnDatei.setFont(btnDatei.getFont().deriveFont(Font.PLAIN, 11F));
		btnDatei.setBounds(595, 230, 150, 30);
		btnDatei.addActionListener(this);
		this.add(btnDatei);

		btnDialog = new JButton("Dialog anzeigen");
		btnDialog.setFont(btnDialog.getFont().deriveFont(Font.PLAIN, 11F));
		btnDialog.setBounds(595, 265, 150, 30);
		btnDialog.addActionListener(this);
		this.add(btnDialog);

		// Suchen und Ersetzen Button mit Image
		icon = new ImageIcon(this.getClass().getResource("/images/Search.png"));

		setBtnSuchenUndErsetzen(new JButton("Suchen/Ersetzen"));
		getBtnSuchenUndErsetzen().setIcon(icon);

		getBtnSuchenUndErsetzen().setFont(getBtnSuchenUndErsetzen().getFont().deriveFont(Font.PLAIN, 11F));
		getBtnSuchenUndErsetzen().setBounds(595, 300, 150, 30);
		getBtnSuchenUndErsetzen().addActionListener(this);
		this.add(getBtnSuchenUndErsetzen());

		btnBeenden = new JButton("Beenden");
		btnBeenden.setFont(btnBeenden.getFont().deriveFont(Font.PLAIN, 11F));
		btnBeenden.setBounds(595, 335, 150, 30);
		btnBeenden.addActionListener(this);
		this.add(btnBeenden);
	}

	private void initFrame() {
		// In der Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);

		this.textArea.setText("Mit dem JTextArea-Steuerelement kann der Benutzer Text in einer Anwendung eingeben. "
				+ "Dieses Steuerelement bietet eine Funktionalität, die über das Standard-JTextField-Steuerelement von Java hinausgeht. "
				+ "Dazu gehören Mehrzeilenbearbeitung und Zeichenmaskierung für Kennwörter. "
				+ "Normalerweise wird ein JTextField-Steuerelement für die Anzeige oder Eingabe einer einzelnen Textzeile verwendet.");

		this.isDocumentChanged = false;
		this.checkWordWrap.setSelected(true);

	}

	private void anzeigeDialog() {
		FrameDialogModal dlg = new FrameDialogModal();
		dlg.showDialog(this);
	}

	private void anzeigeSuchenUndErsetzen() {
		this.dlg = new FrameDialogSuchenUndErsetzen(textArea);
		dlg.showDialog(this);
		// alternativ
		// this.btnSuchenUndErsetzen.setEnabled(false);
	}

	public void showFrame() {
		initFrame();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SuchenErsetzen f = new SuchenErsetzen();
		f.showFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnBeenden) {
			windowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		} else if (e.getSource() == btnDialog) {
			anzeigeDialog();
		} else if (e.getSource() == btnDatei) {
			dateiLesen();
		} else if (e.getSource() == getBtnSuchenUndErsetzen()) {
			if (getWindowList().size() >= 1) {
				return;
			}
			anzeigeSuchenUndErsetzen();
		}

	}

	private void dateiLesen() {

		JFileChooser fc = getFileChooserInst("Textdatei öffnen");

		if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
			return;

		fcFile = fc.getSelectedFile();

		dateiLesenReadAllBytes(fc.getSelectedFile().toString());
		this.isDocumentChanged = false;
	}

	private JFileChooser getFileChooserInst(String title) {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("Textdokument", "txt"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("CSV-Datei", "csv"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Stapeldatei", "bat"));
		fc.setCurrentDirectory(fcFile);
		fc.setDialogTitle(title);
		fc.setAcceptAllFileFilterUsed(false);
		return fc;
	}

	private void dateiLesenReadAllBytes(String Dateiname) {

		long start = 0, ende = 0;

		// Inhalt der TextArea löschen
		textArea.setText(null);

		try {
			start = System.currentTimeMillis();

			byte[] byteArray = Files.readAllBytes(new File(Dateiname).toPath());
			textArea.setText(new String(byteArray));

			ende = System.currentTimeMillis();

		} catch (Exception ex) {

			JOptionPane.showMessageDialog(this, "Fehler beim Einlesen der Datei: " + ex.getMessage(), "Lesefehler",
					JOptionPane.ERROR_MESSAGE);
		}

		JOptionPane.showMessageDialog(this,
				"Dauer: " + NumberFormat.getInstance().format(ende - start) + " Millisekunden.",
				"Lesen mit ReadAllBytes", JOptionPane.INFORMATION_MESSAGE);

		textArea.requestFocusInWindow();

	}

	private void saveFile() {
		if (fcFile == null) {
			JFileChooser fc = getFileChooserInst("Textdokument speichern");
			if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
				return;

			fcFile = fc.getSelectedFile();
		}
		try (FileOutputStream fos = new FileOutputStream(fcFile)) {
			fos.write(textArea.getText().getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		if (e.getSource() == checkLineWrap) {

			textArea.setLineWrap(checkLineWrap.isSelected());

			if (!checkLineWrap.isSelected()) {
				checkWordWrap.setSelected(false);
			}
		} else if (e.getSource() == checkWordWrap) {
			textArea.setWrapStyleWord(checkWordWrap.isSelected());

			if (checkWordWrap.isSelected()) {
				checkLineWrap.setSelected(true);
			}
		}

	}

	private int queryExit() {
		int retValue = 0;

		if (this.isDocumentChanged) {
			// Benutzerdefinierte Buttontexte
			String[] options = { "Ja", "Nein", "Abbrechen" };

			retValue = JOptionPane.showOptionDialog(this,
					"Es sind ungespeicherte Änderungen vorhanden.\nJetzt speichern?", "Programm beenden",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		}
		return retValue;

	}

	// WindowListener
	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		int buttonPressed = queryExit();
		if (buttonPressed == JOptionPane.YES_OPTION) {
			saveFile();
			this.dispose();
		} else if (buttonPressed == JOptionPane.CANCEL_OPTION) {
			return;
		} else {
			this.dispose();
		}

		// alternative
		// if (getWindowList().size() > 0) {
		// getWindowList().get(0).dispose();
		// }

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}

	// DocumentListener
	@Override
	public void insertUpdate(DocumentEvent e) {
		this.isDocumentChanged = true;

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		this.isDocumentChanged = true;

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// nur bei änderugnen der area
	}

	public ArrayList<FrameDialogSuchenUndErsetzen> getWindowList() {
		return windowList;
	}

	public void setWindowList(ArrayList<FrameDialogSuchenUndErsetzen> windowList) {
		this.windowList = windowList;
	}

	public JButton getBtnSuchenUndErsetzen() {
		return btnSuchenUndErsetzen;
	}

	public void setBtnSuchenUndErsetzen(JButton btnSuchenUndErsetzen) {
		this.btnSuchenUndErsetzen = btnSuchenUndErsetzen;
	}

}
