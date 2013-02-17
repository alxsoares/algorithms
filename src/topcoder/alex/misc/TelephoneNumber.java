package topcoder.alex.misc;

public class TelephoneNumber {

	private static final int PHONE_NUMBER_LENGTH = 7;
	private final int[] phoneNum;
	private char[] result = new char[PHONE_NUMBER_LENGTH];
	public final String keys = "ABCDEFGHIJKLMNOPRSTUVWXY";
	public TelephoneNumber(int[] n) {
		phoneNum = n;
	}

	public void printWords() {
		printWords(0);
	}

	private void printWords(int curDigit) {
		if (curDigit == PHONE_NUMBER_LENGTH) {
			System.out.println(new String(result));
			return;
		}
		for (int i = 1; i <= 3; ++i) {
			result[curDigit] = getCharKey(phoneNum[curDigit], i);
			printWords(curDigit + 1);
			if (phoneNum[curDigit] == 0 || phoneNum[curDigit] == 1)
				return;
		}
	}

	private char getCharKey(int d, int i) {
		if(d <2) return (char)(d+'0');
		int index = (d-2)+(i-1);
		return keys.charAt(index);
	}

	public static void main(String[] args) {
		TelephoneNumber t = new TelephoneNumber(new int[]{4,9,7,1,9,2,7});
		t.printWords();
	}

}
