package w1t4;

import java.util.Scanner;

public class Bmi {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int gewicht = 0;
		int bmi = 0;
		float groesse = 0;
		
		System.out.println("Wir berechnen Ihren BMI...");
		System.out.println("Geben Sie zuerst Ihr Gewicht in KG (Ganzzahl) an:");
		gewicht = scan.nextInt();
		System.out.println("Geben Sie zuerst Ihre Größe in Meter an:");
		groesse = scan.nextFloat();
		scan.close();
		
		bmi = (int) (gewicht / (groesse*groesse));
		System.out.print("Ihr BMI beträgt: " + bmi + "; damit haben Sie ");
		if(bmi < 10) {
			System.out.println("Überprüfen Sie ihre Eingabe");
		}else if(bmi < 15) {
			System.out.println("Magersucht");
		}else if(bmi < 20) {
			System.out.println("Untergewicht");
		}else if(bmi < 25) {
			System.out.println("Normalgewicht");
		}else if(bmi < 30) {
			System.out.println("Übergewicht");
		}else if(bmi < 40) {
			System.out.println("Fettsucht");
		}else if(bmi < 50) {
			System.out.println("morbide Fettsucht");
		}else {
			System.out.println("Überprüfen Sie ihre Eingabe");
		}
	}

}
