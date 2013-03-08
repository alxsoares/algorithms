package topcoder.alex.misc;

public class PrintBinary {

	public static String printBinary(String n) {
		int intPart = Integer.parseInt(n.substring(0, n.indexOf('.')));
		double decPart = Double.parseDouble(n.substring(n.indexOf('.'),
				n.length()));
		String int_string = "";
		while (intPart > 0) {
			int r = intPart % 2;
			intPart >>= 1;
			int_string = r + int_string;
		}
		StringBuffer dec_string = new StringBuffer();
		while (decPart > 0) {
//			if (dec_string.length() > 32)
//				return "ERROR";
			if (decPart == 1) {
				dec_string.append((int) decPart);
				break;
			}
			double r = decPart * 2;
			if (r >= 1) {
				dec_string.append(1);
				decPart = r - 1;
			} else {
				dec_string.append(0);
				decPart = r;
			}

		}

		return int_string + "." + dec_string.toString();
	}

	public static void main(String[] args) {
		System.out.printf("%s\n", printBinary("1.51"));
	}

}
