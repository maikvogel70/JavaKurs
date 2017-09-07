package w2t1;

import java.util.Random;
import java.util.Scanner;

public class Aufgaben {

	private static final String alphabet = "abcdefghijklmnopqrstuvwxyzäüö";

	public int getDigitCount(String string) {
		int count = 0;
		if (string != null) {
			for (int i = 0; i < string.length(); i++) {
				try {
					Integer.parseInt(String.valueOf(string.charAt(i)));
					count++;
				} catch (NumberFormatException nfe) {
					continue;
				}
			}
		}
		return count;
	}

	public String getStringReverse(String string) {
		StringBuffer sb = new StringBuffer();
		for (int i = string.length() - 1; i >= 0; i--) { // -1 weil charAt ist 0 basiert
			sb.append(String.valueOf(string.charAt(i)));
		}
		return sb.toString();
	}

	public String getBeforeCleanString(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (string.startsWith(" ")) {
				string = string.substring(1, string.length());
			}
		}
		return string;
	}

	public String getAfterCleanString(String string) {
		string = getStringReverse(string);
		string = getBeforeCleanString(string);
		return getStringReverse(string);
	}

	public String getBeforAfterCleanString(String string) {
		string = getBeforeCleanString(string);
		return getAfterCleanString(string);
	}

	public String getAnyCharCleanString(String string, char c, boolean removeBASpaces) {
		if (removeBASpaces) {
			string = getBeforeCleanString(string);
			string = getAfterCleanString(string);
		}

		String temp;
		for (int i = 0; i < string.length(); i++) {
			if (c == string.charAt(i)) {
				temp = string.substring(0, i);
				string = temp + string.substring(i + 1, string.length());
			}
		}
		return string;
	}

	public int getCountSubstring(String string, String suchstring) {
		String temp = new String();
		char charAt;
		int count = 0;
		for (int i = 0; i < string.length(); i++) {
			charAt = string.charAt(i);
			if (' ' == charAt) {
				temp = "";
			} else {
				temp += String.valueOf(charAt);
				if (temp.equalsIgnoreCase(suchstring)) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Rate den versteckten Satz - das '?' ermittelt einen Zufallsbuchstaben, der
	 * noch nicht eingegeben wurde.
	 * 
	 * @param cons
	 */
	public void playLuckyWheel(Scanner cons) {
		String eingabe, hiddenSatz = "";
		String rateString = "Hanna hat Pferde gern".toLowerCase();
		// Zeichen maskieren
		for (int i = 0; i < rateString.length(); i++) {
			if (' ' == rateString.charAt(i)) {
				hiddenSatz += " ";
			} else {
				hiddenSatz += "_";
			}
		}

		StringBuffer sb = new StringBuffer(); // für hilfe - sammelt bisherige eingaben
		StringBuilder tempRatesatz = new StringBuilder(hiddenSatz);
		while (cons.hasNext()) {
			eingabe = cons.nextLine();
			if (eingabe.equals("?")) {
				eingabe = getRandomChar(sb);
				System.out.println("ich helfe mit:  " + eingabe);
			}
			sb.append(eingabe);
			for (int i = 0; i < rateString.length(); i++) {
				if (eingabe.equals(String.valueOf(rateString.charAt(i)))) {
					tempRatesatz.setCharAt(i, rateString.charAt(i));
				}
			}
			System.out.println(tempRatesatz);
			if (tempRatesatz.toString().equals(rateString)) {
				System.out.println("Geschafft!!!");
				break;
			}
		}
		cons.close();
	}

	private String getRandomChar(StringBuffer sb) {
		Random r = new Random();
		char helperChar = '-';
		helperChar = alphabet.charAt(r.nextInt(alphabet.length()));
		sb.append(helperChar);
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == helperChar) {
				getRandomChar(sb);
				break;
			}
		}
		return String.valueOf(helperChar);
	}

}
