package w3t3;

import w3t2.Datum;

public class DatumZeitMain {

	public static void main(String[] args) {
		DatumZeit dz = new DatumZeit(10, 23, 33, new Datum(24, 8, 2017));

		System.out.println("Default:\t " + dz);

		dz.setMinute(330);

		// wirft NFE, die waber nicht abgefeangen werden MUSS - kann aber
		dz.addStunden(5);
		System.out.println("addStunden:\t " + dz);

		// wirft NFE, die waber nicht abgefeangen werden MUSS - kann aber
		dz.addMinuten(7);
		System.out.println("addMinuten:\t " + dz);

		// wirft NFE, die waber nicht abgefeangen werden MUSS - kann aber
		dz.addSekunden(127);
		System.out.println("addSekunden:\t " + dz);

	}

}
