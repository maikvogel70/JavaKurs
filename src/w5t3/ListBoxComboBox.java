package w5t3;

import java.awt.GraphicsEnvironment;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import util.ListItem;

public class ListBoxComboBox extends JFrame implements ListSelectionListener, ItemListener {

	private JList<ListItem<Integer, String>> listBox, msListBox, typeListBox;
	private JList<ListItem<Float, String>> sizeListBox;
	private DefaultListModel<ListItem<Float, String>> sizeListBoxModel;
	private DefaultListModel<ListItem<Integer, String>> listBoxModel, msListBoxModel, typeListBoxModel;
	private JScrollPane listBoxScrollPane, sizeListBoxScrollPane, typeListBoxScrollPane, msListBoxScrollPane;

	private JComboBox<ListItem<Integer, String>> cboFont;
	private DefaultComboBoxModel<ListItem<Integer, String>> cboFontModel;

	private String[] fontFamilies;

	private JTextArea previewText;

	public ListBoxComboBox() {
		initializeComponents();
	}

	private void initializeComponents() {

		this.setTitle("ListBox und ComboBox Demo");
		this.setSize(510, 460);

		// Layout Manager ausschalten
		this.setLayout(null);

		// Keine Grössenänderung des Frames
		this.setResizable(false);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 1. Zuerst das Datenmodell erstellen
		listBoxModel = new DefaultListModel<>();
		sizeListBoxModel = new DefaultListModel<>();
		typeListBoxModel = new DefaultListModel<>();

		// 2. Die Listbox erstellen und das Datenmodell als Referenz übergebn
		listBox = new JList<>(listBoxModel);
		listBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listBox.addListSelectionListener(this);

		sizeListBox = new JList<>(sizeListBoxModel);
		sizeListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sizeListBox.addListSelectionListener(this);

		typeListBox = new JList<>(typeListBoxModel);
		typeListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		typeListBox.addListSelectionListener(this);
		typeListBox.setBounds(360, 50, 100, 100);
		typeListBox.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.add(typeListBox);

		// 3. Um die Listbox blättern zu können, eine ScrollPane erstellen und die
		// ListBox als Referenz übergeben.
		listBoxScrollPane = new JScrollPane(listBox);
		listBoxScrollPane.setBounds(10, 10, 280, 140);
		this.add(listBoxScrollPane);

		sizeListBoxScrollPane = new JScrollPane(sizeListBox);
		sizeListBoxScrollPane.setBounds(300, 50, 50, 100);
		this.add(sizeListBoxScrollPane);

		previewText = new JTextArea("Franz jagt im komplett verwahrlosten Taxi quer durch Bayern. 1234567890");
		previewText.setWrapStyleWord(true);
		previewText.setLineWrap(true);
		previewText.setEditable(false);
		previewText.setBackground(this.getBackground());

		typeListBoxScrollPane = new JScrollPane(previewText);
		typeListBoxScrollPane.setBounds(300, 180, 160, 100);
		this.add(typeListBoxScrollPane);

		// Listbox mit Mehrfach-Selektion

		// 1. Zuerst das Datenmodell erstellen
		msListBoxModel = new DefaultListModel<>();

		// 2. Die Listbox erstellen und das Datenmodell als Referenz übergebn
		msListBox = new JList<>(msListBoxModel);
		msListBox.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		msListBox.addListSelectionListener(this);

		// 3. Um die Listbox blättern zu können, eine ScrollPane erstellen und die
		// ListBox als Referenz übergeben.
		msListBoxScrollPane = new JScrollPane(msListBox);
		msListBoxScrollPane.setBounds(10, 180, 280, 100);
		this.add(msListBoxScrollPane);

		// Font ComboBox

		cboFontModel = new DefaultComboBoxModel<>();
		cboFont = new JComboBox<>(cboFontModel);
		cboFont.setBounds(300, 10, 180, 25);

		// Anzahl der sichtbaren Einträge in der Liste
		cboFont.setMaximumRowCount(12);

		cboFont.addItemListener(this);
		this.add(cboFont);

	}

	private void initFrame() {
		// In der Mitte des Desktops anzeigen
		this.setLocationRelativeTo(null);

		populateListBox();
		populateMultiSelectListBox();

		populateFontComboBox();
		populateSizeList();
		populateTypeList();

		// Programmatische Auswahl eines ListBox Eintrags
		listBox.setSelectedIndex(8);
		listBox.ensureIndexIsVisible(listBox.getSelectedIndex());

		// Programmatische Mehrfachauswahl
		msListBox.setSelectedIndices(new int[] { 2, 3, 5, 9 });

	}

	public void showFrame() {
		initFrame();
		this.setVisible(true);
	}

	private void populateListBox() {
		for (int i = 1001; i <= 1010; i++) {
			listBoxModel.addElement(new ListItem<Integer, String>(i, "ListBox Eintrag " + i));
		}
	}

	private void populateMultiSelectListBox() {
		for (int i = 2001; i <= 2010; i++) {
			msListBoxModel.addElement(new ListItem<Integer, String>(i, "Multi-Select ListBox Eintrag " + i));
		}
	}

	private void populateFontComboBox() {
		int i = 0;

		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();

		fontFamilies = e.getAvailableFontFamilyNames();

		for (String ff : fontFamilies) {
			cboFontModel.addElement(new ListItem<Integer, String>(++i, ff));
		}
	}

	private void populateSizeList() {
		int[] sizes = new int[] { 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72 };
		for (int i = 0; i < sizes.length; i++) {
			sizeListBoxModel.addElement(new ListItem<Float, String>((float) sizes[i], String.valueOf(sizes[i])));
		}
	}

	private void populateTypeList() {
		String[] types = new String[] { "Standard", "Fett", "Kursiv", "Fettkusiv" };
		for (int i = 0; i < types.length; i++) {
			typeListBoxModel.addElement(new ListItem<Integer, String>(i, types[i]));
		}
	}

	private void showValue(ListItem<Integer, String> listItem) {
		System.out.println(listItem.getValueMember() + " - " + listItem.getDisplayMember());
	}

	private void showMultiSelectItems() {
		System.out.println();

		for (ListItem<Integer, String> li : msListBox.getSelectedValuesList()) {
			showValue(li);
		}

		System.out.println();
	}

	public static void main(String[] args) {
		ListBoxComboBox f = new ListBoxComboBox();
		f.showFrame();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

		// Das Auswählen eines Listbox-Eintrages löst eine Serie
		// von Ereignissen aus. Erst wenn alle Ereignisse abgeschlossen
		// sind (getValueIsAdjusting()), wird der ausgewählte Eintrag
		// übernommen.

		if (e.getSource() instanceof JList && !e.getValueIsAdjusting()) {
			if (e.getSource() == listBox) {
				showValue(listBox.getSelectedValue());
			} else if (e.getSource() == msListBox) {
				showMultiSelectItems();
			} else if (e.getSource() == sizeListBox) {
				setSelectedSize(sizeListBox.getSelectedValue());
			} else if (e.getSource() == typeListBox) {
				setSelectedType(typeListBox.getSelectedValue());
			}
		}

	}

	private void setSelectedType(ListItem<Integer, String> selectedValue) {
		previewText.setFont(previewText.getFont().deriveFont(selectedValue.getValueMember()));
	}

	private void setSelectedSize(ListItem<Float, String> selectedValue) {
		previewText.setFont(previewText.getFont().deriveFont(selectedValue.getValueMember()));
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		ListItem<Integer, String> listItem;

		if (e.getSource() instanceof JComboBox && e.getStateChange() == ItemEvent.SELECTED) {
			if (e.getSource() == cboFont) {
				listItem = (ListItem<Integer, String>) cboFont.getSelectedItem();
				showValue(listItem);
			}
		}
	}

}
