package w1t5;

import java.util.Scanner;

public class Start {

	private static final String GEBEN_SIE_DIE_1_ZAHL_EIN = "Geben Sie die 1. Zahl ein:";
	private static final String GEBEN_SIE_DIE_2_ZAHL_EIN = "Geben Sie die 2. Zahl ein:";
	private static final String GEBEN_SIE_DIE_3_ZAHL_EIN = "Geben Sie die 3. Zahl ein:";

	public static void main(String[] args) {
		Aufgaben aufgaben = new Aufgaben();
		Scanner cons = new Scanner(System.in);
		int zahl1, zahl2, zahl3;

		// System.out.println("Welches ist die größere aus zwei Zahlen");
		// System.out.println(GEBEN_SIE_DIE_1_ZAHL_EIN);
		// zahl1 = cons.nextInt();
		// System.out.println(GEBEN_SIE_DIE_2_ZAHL_EIN);
		// zahl2 = cons.nextInt();
		// System.out.println("Die Zahl " + aufgaben.getMaxValue(zahl1, zahl2) + " ist
		// die größere der Beiden");
		//
		// System.out.println("Welches ist die größere aus drei Zahlen");
		// System.out.println(GEBEN_SIE_DIE_1_ZAHL_EIN);
		// zahl1 = cons.nextInt();
		// System.out.println(GEBEN_SIE_DIE_2_ZAHL_EIN);
		// zahl2 = cons.nextInt();
		// System.out.println(GEBEN_SIE_DIE_3_ZAHL_EIN);
		// zahl3 = cons.nextInt();
		// System.out.println("Die Zahl " + aufgaben.getMaxValue(zahl1, zahl2, zahl3) +
		// " ist die größere der Drei");

		System.out.println("Gib mir die Primzahlen bis Wert x");
		System.out.println("Geben Sie einen Wert an, bis zu dem die Primzahlen ermittelt werden sollen: ");
		int maxVal = cons.nextInt();
		System.out.println("\nEs gibt " + aufgaben.printPrimzahlen(maxVal) + " Primzahlen bis " + maxVal);

		cons.close();
	}

}
