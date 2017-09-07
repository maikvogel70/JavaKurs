package w3t1;

import java.util.Calendar;
import java.util.Locale;

public class Start {

	public static void main(String[] args) {
		MyDate myDate = new MyDate();

		// Prüfe ein bestimmtes Jahr, ob es ein Schaltjahr ist
		int jahr = 1900;
		System.out
				.println("Das Jahr " + jahr + " ist " + (myDate.istSchaltjahr(jahr) ? "ein" : "kein") + " Schaltjahr");
		System.out.println();

		System.out.println("------------------ Alle Schaltjahre ------------------ \n" + myDate.listAllSchaltjahre());
		System.out.println();

		System.out.println("------------------ Ein Ereignis ------------------ \n" + myDate.getEreignisImJahr(1904));

		System.out.println();
		System.out.println("------------------ Datumformate ------------------");
		System.out.println("Datumformat kurz: " + myDate.getDateFormatKurz(Calendar.getInstance()));

		System.out.println("Datumformat lang: " + myDate.getDateFormatLang(Calendar.getInstance()));

		System.out.println(
				"Datumformat US: " + myDate.getDateFormatLangLocale(Calendar.getInstance().getTime(), Locale.US));

		System.out.println("Datumformat CANADA: "
				+ myDate.getDateFormatLangLocale(Calendar.getInstance().getTime(), Locale.CANADA));

		System.out.println(
				"Datumformat ITALY: " + myDate.getDateFormatLangLocale(Calendar.getInstance().getTime(), Locale.ITALY));

	}

}
