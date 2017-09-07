package w2t4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Assoziativspeicher {

	public static void main(String[] args) {
		Scanner in = null;
		TreeMap<String, ArrayList<String>> plzMap = null;
		try {
			in = new Scanner(new FileInputStream("C:\\eclipse\\workspace\\JavaKurs\\src\\w2t4\\Orte.txt"));
			String[] line;
			String plz;
			ArrayList<String> orteList = null;
			plzMap = new TreeMap<>(); // TreeMap ist eine sortierte Map
			while (in.hasNext()) {
				line = in.nextLine().split(";", 2);
				plz = line[0];
				if (!plzMap.containsKey(plz)) {
					orteList = new ArrayList<>();
					plzMap.put(plz, orteList);
				}
				orteList.add(line[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
		}
		// Ausgabe
		for (String plz : plzMap.keySet()) {
			ArrayList<String> orteList = plzMap.get(plz);
			for (String ort : orteList) {
				System.out.println(plz + " - " + ort);
			}
		}

	}

}
