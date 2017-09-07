package w3t1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateHelper {

	public static boolean istSchaltjahr(int jahr) {
		if (jahr % 4 == 0) {
			if (jahr % 100 != 0 || jahr % 400 == 0) {
				return true;
			}
		}
		return false;
	}

	public static List<Integer> listAllSchaltjahre(int minJahr) {
		Calendar calRoll = Calendar.getInstance();
		Calendar calCurr = Calendar.getInstance();
		calRoll.set(Calendar.DAY_OF_MONTH, 1);
		calRoll.set(Calendar.MONTH, 1);
		calRoll.set(Calendar.YEAR, 1583);
		calCurr.setTimeInMillis(System.currentTimeMillis());
		List<Integer> schaltjahrList = new ArrayList<>();

		while (calRoll.before(calCurr)) {
			if (DateHelper.istSchaltjahr(calRoll.get(Calendar.YEAR))) {
				schaltjahrList.add(calRoll.get(Calendar.YEAR));
			}
			calRoll.roll(Calendar.YEAR, true);
		}
		return schaltjahrList;
	}

	public static String getEreignisImJahr(int jahr) {
		switch (jahr) {
		case 1900:
			return "1900: Kaiser Wilhelm II. war Staatsoberhaupt in Deutschland";
		case 1901:
			return "27. Januar 1901: Giuseppe Verdi stirbt in Mailand";
		case 1902:
			return "Im Mai 1902: Der Instrumentenhersteller Gibson wird gegründet";
		case 1903:
			return "3. März 1903: Der US-Kongress genehmigt ein neues Einwanderungsgesetz ";
		case 1904:
			return "1. Januar 1904: Leistungsänderung auf 26 Wochen Leistung im Krankenversicherungsgesetz tritt in Kraft ";
		case 1905:
			return "6. August 1905: Frankreich – Der 1. Welt-Esperanto-Kongress hatte in Boulogne-sur-Mer begonnen.";
		case 1906:
			return "2. Januar 1906: Das bulgarische Parlament genehmigt die Zollunion mit dem Königreich Serbien ";
		default:
			return "Kein Ereignis gelistet";
		}
	}

	public static String getDateFormatKurz(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd. MM. yyyy");
		return sdf.format(date);
	}

	public static String getDateFormatLang(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd. MMMM yyyy");
		return sdf.format(date);
	}

	public static String getDateFormatLangLocale(Date date, Locale locale) {
		DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.FULL, locale);
		return dateInstance.format(date);
	}
}
