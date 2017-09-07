package w3t3;

import w3t2.Datum;

public class DatumZeit extends Datum {

	private int stunde, minute, sekunde;
	private static int DEFAULT_STUNDE = 0;
	private static int DEFAULT_MINUTE = 0;
	private static int DEFAULT_SEKUNDE = 0;
	private Datum datum;

	public DatumZeit() {
		this.stunde = DEFAULT_STUNDE;
		this.minute = DEFAULT_MINUTE;
		this.sekunde = DEFAULT_SEKUNDE;
	}

	public DatumZeit(int stunde, int minute, int sekunde, Datum datum) {
		this();
		setTime(stunde, minute, sekunde);
		this.datum = datum;
	}

	public void addStunden(int stunden) {
		stunden += this.getStunde();
		while (stunden >= 24) {
			this.datum.addiereTage(1);
			stunden -= 24;
		}
		setStunde(stunden);
	}

	public void addMinuten(int minuten) {
		minuten += this.getMinute();
		while (minuten >= 59) {
			addStunden(1);
			minuten -= 60;
		}
		setMinute(minuten);
	}

	public void addSekunden(int sekunden) {
		sekunden += this.getSekunde();
		while (sekunden >= 59) {
			addMinuten(1);
			sekunden -= 60;
		}
		setSekunde(sekunden);
	}

	public void setStunde(int stunde) throws NumberFormatException {
		if (checkStunde(stunde)) {
			setTime(stunde, getMinute(), getSekunde());
		} else {
			throw new NumberFormatException("Fehler beim Wert: Es muss im Bereich von 0-23 liegen");
		}
	}

	public void setMinute(int minute) throws NumberFormatException {
		if (checkMinute(minute)) {
			setTime(getStunde(), minute, getSekunde());
		} else {
			throw new NumberFormatException("Fehler beim Wert: Es muss im Bereich von 0-59 liegen");
		}
	}

	public void setSekunde(int sekunde) throws NumberFormatException {
		if (checkSekunde(sekunde)) {
			setTime(getStunde(), getMinute(), sekunde);
		} else {
			throw new NumberFormatException("Fehler beim Wert: Es muss im Bereich von 0-59 liegen");
		}
	}

	private void setTime(int stunde, int minute, int sekunde) throws NumberFormatException {

		if (checkTime(stunde, minute, sekunde)) {
			this.stunde = stunde;
			this.minute = minute;
			this.sekunde = sekunde;
		} else {
			throw new NumberFormatException(
					"Fehler beim Wert: Es muss im Bereich von 0-59 (Minute, Sekunde) und 0-23 (Stunde) liegen");
		}
	}

	private boolean checkTime(int stunde, int minute, int sekunde) {
		return checkStunde(stunde) && checkMinute(minute) && checkSekunde(sekunde);
	}

	private boolean checkSekunde(int sekunde) {
		if (sekunde < 0 || sekunde > 59) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkMinute(int minute) {
		if (minute < 0 || minute > 59) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkStunde(int stunde) {
		if (stunde < 0 || stunde > 23) {
			return false;
		} else {
			return true;
		}
	}

	public int getStunde() {
		return stunde;
	}

	public int getMinute() {
		return minute;
	}

	public int getSekunde() {
		return sekunde;
	}

	@Override
	public String toString() {
		return this.datum + " - " + String.format("%02d:%02d:%02d", stunde, minute, sekunde);
	}
}
