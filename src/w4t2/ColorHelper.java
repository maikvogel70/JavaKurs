package w4t2;

import java.text.DecimalFormat;

public class ColorHelper {

	public static int[] CMYKinRGB(float cyan, float magenta, float yellow, float key) {
		DecimalFormat df = new DecimalFormat("#");
		int[] rgb = new int[3];
		float[] cmyk = { cyan, magenta, yellow, key };
		for (int i = rgb.length - 1; i > -1; i--) {
			rgb[i] = Integer.parseInt(
					df.format((float) (((cmyk[i] == 0) ? (1 - cmyk[3]) * 255 : (1 - cmyk[3]) * 255) * (1 - cmyk[i]))));
		}
		return rgb;
	}

	public static String CMYKinHEX(float cyan, float magenta, float yellow, float key) {

		int[] rgb = CMYKinRGB(cyan, magenta, yellow, key);
		return ColorHelper.RGBinHEX(rgb[0], rgb[1], rgb[2]);
	}

	public static String RGBinHEX(int red, int green, int blue) {

		String[] hex = new String[3];
		hex[0] = Integer.toHexString(red).toUpperCase();
		hex[1] = Integer.toHexString(green).toUpperCase();
		hex[2] = Integer.toHexString(blue).toUpperCase();
		for (int i = hex.length - 1; i > -1; i--) {
			hex[i] = ((hex[i].length() < 2) ? "0" + hex[i] : hex[i]);
		}
		return hex[0] + hex[1] + hex[2];
	}
}
