package w4t5;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;

public class FrameDialog extends JFrame implements ActionListener {

	private JTextArea textArea;
	private JScrollPane textAreaScrollPane;

	private JButton btnBeenden, btnDialog, btnDatei;
	private JCheckBox cbSetWordWrap, cbSetLineWrap;
	long start = 0, ende = 0;
	private StringBuilder sb = new StringBuilder();
	private List<String> readAllLines;
	DefaultStyledDocument document;

	private File fcFile = new File("C:\\");
	// private File fcFile = new File(System.getProperty("user.dir")); // Aktuelles
	// Arbeitsverzeichnis
	// private File fcFile = new File(System.getProperty("user.home")); // Home
	// Verzeichnis des Benutzers
	// private File fcFile = new File(System.getProperty("user.name")); // Name des
	// angemeldetete Benutzers
	// private File fcFile = new File(System.getProperty("java.io.tmpdir")); // Pfad
	// für temporäre Dateien

	public FrameDialog() {
		initializeComponents();
	}

	private void initializeComponents() {

		this.setTitle("Hauptfenster des Programms");
		this.setSize(760, 420);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		textArea = new JTextArea();

		// Freien Bereich zwischen Rahmen und Text definieren
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);

		textAreaScrollPane = new JScrollPane(textArea);
		textAreaScrollPane.setBounds(5, 5, 580, 360);
		this.add(textAreaScrollPane);

		btnDatei = new JButton("Datei öffnen...");
		btnDatei.setBounds(595, 200, 150, 30);
		btnDatei.addActionListener(this);
		this.add(btnDatei);

		btnDialog = new JButton("Dialog anzeigen");
		btnDialog.setBounds(595, 300, 150, 30);
		btnDialog.addActionListener(this);
		this.add(btnDialog);

		btnBeenden = new JButton("Beenden");
		btnBeenden.setBounds(595, 335, 150, 30);
		btnBeenden.addActionListener(this);
		this.add(btnBeenden);

		cbSetLineWrap = new JCheckBox("Line wrap");
		cbSetLineWrap.setName("linewrap");
		cbSetLineWrap.addActionListener(this);
		cbSetLineWrap.setBounds(595, 235, 100, 20);
		cbSetLineWrap.setSelected(true);
		this.add(cbSetLineWrap);

		cbSetWordWrap = new JCheckBox("Word wrap");
		cbSetWordWrap.setName("wordwrap");
		cbSetWordWrap.addActionListener(this);
		cbSetWordWrap.setBounds(595, 260, 100, 20);
		cbSetWordWrap.setSelected(true);
		this.add(cbSetWordWrap);

	}

	private void initFrame() {
		// In der Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);

		textArea.setText("Mit dem JTextArea-Steuerelement kann der Benutzer Text in einer Anwendung eingeben. "
				+ "Dieses Steuerelement bietet eine Funktionalität, die über das Standard-JTextField-Steuerelement von Java hinausgeht. "
				+ "Dazu gehören Mehrzeilenbearbeitung und Zeichenmaskierung für Kennwörter. "
				+ "Normalerweise wird ein JTextField-Steuerelement für die Anzeige oder Eingabe einer einzelnen Textzeile verwendet.");
	}

	private void anzeigeDialog() {
		FrameDialogModal dlg = new FrameDialogModal();
		dlg.showDialog(this);
	}

	private void dateiLesen() {

		JFileChooser fc = new JFileChooser();
		// fc.setFileFilter(new FileNameExtensionFilter("Textdokument", "txt", "bat",
		// "csv"));

		fc.setFileFilter(new FileNameExtensionFilter("Textdokument", "txt"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("CSV-Datei", "csv"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Stapeldatei", "bat"));

		// Anfangsverzeich setzen
		fc.setCurrentDirectory(fcFile);

		fc.setDialogTitle("Textdokument öffnen");

		// Alle Dateien (*.*) als Dateifilter wird nicht angeboten.
		// Standard = true.
		fc.setAcceptAllFileFilterUsed(false);

		if (fc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
			return;

		// Ausgewählte Dateinamen merken
		fcFile = fc.getSelectedFile();
		textArea.setText(null);
		dateiLesen(fcFile);
	}

	private void dateiLesen(File file) {

		try {
			start = System.currentTimeMillis();
			document = new DefaultStyledDocument();
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						readAllLines = Files.readAllLines(file.toPath(), Charset.forName("windows-1252"));
						int s = readAllLines.size();
						for (int i = 0; i < s; i++) {
							document.insertString(document.getLength(), readAllLines.get(i) + "\n", null);
						}
					} catch (IOException e) {
						e.printStackTrace();
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
				}
			};
			new Thread(runnable).start();

			textArea.setDocument(this.document);
			ende = System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JOptionPane.showMessageDialog(this,
					"Dauer: " + NumberFormat.getInstance().format(ende - start) + " Millisekunden.",
					"Lesen zeilenweise mit Vollgas", JOptionPane.INFORMATION_MESSAGE);

			textArea.requestFocusInWindow();
		}
	}

	public void showFrame() {
		initFrame();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		FrameDialog f = new FrameDialog();
		f.showFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		if (source == btnBeenden) {
			this.dispose();
		} else if (source == btnDialog) {
			anzeigeDialog();
		} else if (source == btnDatei) {
			dateiLesen();
		} else if (source == cbSetWordWrap) {
			textArea.setWrapStyleWord(((JCheckBox) source).isSelected());
		} else if (source == cbSetLineWrap) {
			textArea.setLineWrap(((JCheckBox) source).isSelected());
		}
	}

}
