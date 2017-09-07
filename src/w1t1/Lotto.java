package w1t1;

import java.util.ArrayList;

public class Lotto {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int j = 1; j <= 6; j++) {
			list.clear();
			for (int i = 1; i <= 6; i++) {
				list.add((int) ((Math.random()) * 49 + 1));
			}
			
			for (int i = 1; i <= 49; i++) {
				if (list.contains(i)) {
					System.err.printf("%4d", i);
				} else {
					System.out.printf("%4d", i);
				}
				if (i % 6 == 0) {
					System.out.printf("%n");
				}
			}
			System.out.printf("%n");
			System.out.printf("%n");
		}

	}

}
