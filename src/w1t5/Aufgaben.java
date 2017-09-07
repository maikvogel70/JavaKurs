package w1t5;

public class Aufgaben {

	/**
	 * 
	 * @param a
	 * @param b
	 * @return 0 if a==b
	 */
	public int getMaxValue(int a, int b) {
		if (a > b) {
			return a;
		} else if (b > a) {
			return b;
		} else {
			return 0;
		}
	}

	/**
	 * Gleiche wie getMaxValue(...)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private int getMaxValueTer(int a, int b) {
		return (a == b) ? 0 : (a > b) ? a : b;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return 0 if a==b==c
	 */
	public int getMaxValue(int a, int b, int c) {
		int result;
		result = getMaxValue(a, b);
		result = getMaxValueTer(result, c);
		return result;
	}

	public int printPrimzahlen(int maxValue) {
		long start = System.currentTimeMillis();
		int tabCounter = 0, anzahlPrims = 0, limit = 0;

		for (int pruefling = 2; pruefling <= maxValue; pruefling++) {
			boolean isPrimzahl = true;
			limit = pruefling / 2; // begrenzung, weil über die hälfte nicht geprüft zu werden braucht

			for (int teiler = 2; (teiler <= limit && isPrimzahl); teiler++) {
				if (pruefling % teiler == 0) { // gerade zahl oder teilbar
					isPrimzahl = false;
					break;
				}
			}
			if (isPrimzahl) {
				anzahlPrims++;
				if (tabCounter == 10) {
					System.out.println();
					tabCounter = 0;
				}
				System.out.print("\t" + pruefling);
				tabCounter++;
			}
		}

		System.out.println("\n");
		System.out.println("Laufzeit: " + (System.currentTimeMillis() - start) + " ms");
		return anzahlPrims;
	}

}
