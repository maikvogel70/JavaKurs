package w2t1;

import java.util.Scanner;

public class Start {

	public static void main(String[] args) {
		Aufgaben aufgaben = new Aufgaben();
		// StringBuilder sb = new StringBuilder();
		// sb.append("");
		//
		//
		// /**
		// * 1. Aufgabe
		// */
		// String string = "honulullu33323www435";
		// System.out.println("1. Der String " + string + " enth�lt " +
		// aufgaben.getDigitCount(string) + " Ziffern.");
		//
		// /**
		// * 2. Aufgabe
		// */
		// System.out.println();
		// string = "Der Kasper hat ne M�tze uff.";
		// String reverseString = aufgaben.getStringReverse(string);
		// System.out.println("2. Der umgekehrte String von '" + string + "' ist: " +
		// reverseString);
		//
		// /**
		// * 3. Aufgabe
		// */
		// System.out.println();
		// string = " Der Kasper hat ne M�tze uff";
		// String cleanString = aufgaben.getBeforeCleanString(string);
		// System.out.println("3. Der von f�hrenden Leerzeichen versuchte String '" +
		// string + "' sieht jetzt so aus: "
		// + cleanString);
		//
		// /**
		// * 4. Aufgabe
		// */
		// System.out.println();
		// string = "Der Kasper hat ne M�tze uff ";
		// cleanString = aufgaben.getAfterCleanString(string);
		// System.out.println("4. Der von nachfolgenden Leerzeichen versuchte String '"
		// + string + "' sieht jetzt so aus: "
		// + cleanString);
		//
		// /**
		// * 5. Aufgabe
		// */
		// System.out.println();
		// string = " Der Kasper hat ne M�tze uff ";
		// cleanString = aufgaben.getBeforAfterCleanString(string);
		// System.out.println("5. Der von f�hrenden und nachfolgenden Leerzeichen
		// versuchte String '" + string
		// + "' sieht jetzt so aus: " + cleanString);
		//
		// /**
		// * 6. Aufgabe
		// */
		// System.out.println();
		// string = " Der Kasper hat ne M�tze uff ";
		// char c = 'e';
		// cleanString = aufgaben.getAnyCharCleanString(string, c, true);
		// System.out.println("6. Das Zeichen " + c + " wird aus dem String entfernt'" +
		// string
		// + "' und sieht jetzt so aus: " + cleanString);
		//
		// /**
		// * 7. Aufgabe
		// */
		// System.out.println();
		// string = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam
		// nonumy sit eirmod tempor \n\tinvidunt ut labore et dolore magna aliquyam
		// erat, sed diam voluptua. \n\tAt vero eos et accusam et justo duo dolores et
		// ea rebum. Stet \n\tclita kasd gubergren, no sea takimata sanctus est Lorem
		// ipsum dolor sit amet.";
		// String suchstring = "sit";
		// int count = aufgaben.getCountSubstring(string, suchstring);
		// System.out.println("7. Der Substring '" + suchstring + "' taucht in dem
		// String \n\t'" + string + "'\n " + count
		// + " mal auf.");

		/**
		 * Gl�cksrad
		 */
		System.out.println();
		Scanner cons = new Scanner(System.in);
		System.out.println("Wir spielen Gl�cksrad\nGeben Sie nacheinander, Enter getrennt, Buchstaben ein.");
		aufgaben.playLuckyWheel(cons);

	}

}
