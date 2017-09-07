package w1t4;

import java.util.Random;
import java.util.Scanner;

public class Zahlenraten {

	private static final int MAX_VERSUCHE = 7;

	public static void main(String[] args) {

		Random random = new Random();
		int randZahl = random.nextInt(100);
		System.out.println(randZahl);
		
		Scanner cons = new Scanner(System.in);
		System.out.println("Die Würfel sind gefallen, wie heisst die Zahl?");
		System.out.println("Mein Tipp:");
		int rateZahl = cons.nextInt();
		for (int i = 1; i <= MAX_VERSUCHE; i++) {
			if (rateZahl > randZahl) {
				System.out.println("die Zahl war zu groß - nochmal");
				rateZahl = cons.nextInt();
			} else if (rateZahl < randZahl) {
				System.out.println("die Zahl war zu klein - nochmal");
				rateZahl = cons.nextInt();
			} else {
				System.out.println("RICHTIG!");
				break;
			}
		}
	}

}
