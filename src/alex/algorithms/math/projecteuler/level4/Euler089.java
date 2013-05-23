package alex.algorithms.math.projecteuler.level4;

public class Euler089 {

	public static void main(String[] args) {
		// MMMMCMLXXXXVIII
		String start = "MMMMCMLXXXXVIII";
		int startVal = start.length();
		start = start.replaceAll("IIII", "IV").replaceAll("XXXX", "XL")
				.replaceAll("CCCC", "CD").replaceAll("LXL", "XC")
				.replaceAll("DCD", "CM").replaceAll("VIV", "IX");
		int endVal = start.length();
		System.out.printf("%d\n", startVal - endVal);
	}

}
