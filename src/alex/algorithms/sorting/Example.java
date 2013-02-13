package alex.algorithms.sorting;

import java.util.Arrays;

public class Example {

	public static void main(String[] args) {
		String num = "154327763876343";
		int digits[] = new int[num.length()];
		char dCharr[] = num.toCharArray();
		for (int i = 0; i < dCharr.length; i++) {
			digits[i] = dCharr[i] - '0';
		}
		Arrays.sort(digits);
		int toBeSwapped = dCharr[0]-'0';
		for (int i = 0; i < digits.length; i++) {
			if (digits[i] > dCharr[0] - '0') {
				toBeSwapped = digits[i];
				break;
			}
		}
		int n[] = new int[dCharr.length];
		n[0] = toBeSwapped;
		int j=1;
		for(int i=1; i < digits.length; i++){
			if(digits[i]!= toBeSwapped){
				n[j++] = digits[i];
			}
		}
		for (int i = 0; i < n.length; i++) {
			System.out.print(n[i]);
		}
		System.out.println();
	}

}
