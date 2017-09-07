package w3t1;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyDate {

	private int tag, monat, jahr;

	public MyDate() {
		this.tag = 1;
		this.monat = 1;
		this.jahr = 2000;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getMonat() {
		return monat;
	}

	public void setMonat(int monat) {
		this.monat = monat;
	}

	public int getJahr() {
		return jahr;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	public List<Integer> listAllSchaltjahre() {
		return DateHelper.listAllSchaltjahre(1583);
	}

	public boolean istSchaltjahr(int jahr) {
		return DateHelper.istSchaltjahr(jahr);
	}

	public String getEreignisImJahr(int jahr) {
		return DateHelper.getEreignisImJahr(jahr);
	}

	public String getDateFormatKurz(Calendar cal) {
		return DateHelper.getDateFormatKurz(cal.getTime());
	}

	public String getDateFormatLang(Calendar cal) {
		return DateHelper.getDateFormatLang(cal.getTime());
	}

	public String getDateFormatLangLocale(Date date, Locale locale) {
		return DateHelper.getDateFormatLangLocale(date, locale);
	}
}
