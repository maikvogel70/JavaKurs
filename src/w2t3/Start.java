package w2t3;

import java.util.Arrays;

public class Start {

	public static void main(String[] args) {

		int[] lottoZahlen = new int[6];

		int superZahl = (int) ((Math.random() * 9));
		int c = 0; // counter
		int lottozahl;
		while (c < lottoZahlen.length) {
			lottozahl = (int) ((Math.random() * 49 + 1));
			for (int i = 0; i < lottoZahlen.length; i++) {
				if (lottozahl == lottoZahlen[i]) {
					break;
				} else if (i == c) {
					lottoZahlen[c] = lottozahl;
					c++;
					break;
				}
			}
		}

		Arrays.sort(lottoZahlen);

		System.out.println("Lottozahlen: ");
		for (int j = 0; j < lottoZahlen.length; j++) {
			if (j == lottoZahlen.length - 1) {
				System.out.println(lottoZahlen[j]);
			} else {
				System.out.print(lottoZahlen[j] + " ");
			}
		}
		System.out.println("Superzahl: " + superZahl);
	}

}
