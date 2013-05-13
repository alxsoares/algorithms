package alex.algorithms.math.projecteuler;

public class Euler017 {
	static Long totalLettersUsedSoFar = 0L;
	public static void main(String[] args) {

		for (int number = 1; number < 1000; number++) {

			if (number >= 100) {
				String firstDigit = Integer.toString(number).substring(0, 1);
				addLettersFromSingleDigitNumber(Integer.parseInt(firstDigit));
				totalLettersUsedSoFar += NumberEnum.HUNDRED.name().length();
				totalLettersUsedSoFar += NumberEnum.AND.name().length();
				String secondTwoDigits = Integer.toString(number).substring(1,
						3);
				addLettersFromDoubleDigitNumber(Integer
						.parseInt(secondTwoDigits));
				if (Integer.parseInt(secondTwoDigits) > 19
						|| Integer.parseInt(secondTwoDigits) <= 10) {
					String lastDigit = Integer.toString(number).substring(2, 3);
					addLettersFromSingleDigitNumber(Integer.parseInt(lastDigit));
				}
			} else if (number > 10) {
				addLettersFromDoubleDigitNumber(number);
				if (number > 10 && number < 20) {
					// do nothing
				} else {
					String lastDigit = Integer.toString(number).substring(1, 2);
					addLettersFromSingleDigitNumber(Integer.parseInt(lastDigit));
				}
			} else {
				addLettersFromSingleDigitNumber(number);
			}
		}

		totalLettersUsedSoFar += "onethousand".length();

		System.out.printf("%d\n",totalLettersUsedSoFar);
	}

	private static void addLettersFromSingleDigitNumber(int number) {
		if (number == 1)
			totalLettersUsedSoFar += NumberEnum.ONE.name().length();
		if (number == 2)
			totalLettersUsedSoFar += NumberEnum.TWO.name().length();
		if (number == 3)
			totalLettersUsedSoFar += NumberEnum.THREE.name().length();
		if (number == 4)
			totalLettersUsedSoFar += NumberEnum.FOUR.name().length();
		if (number == 5)
			totalLettersUsedSoFar += NumberEnum.FIVE.name().length();
		if (number == 6)
			totalLettersUsedSoFar += NumberEnum.SIX.name().length();
		if (number == 7)
			totalLettersUsedSoFar += NumberEnum.SEVEN.name().length();
		if (number == 8)
			totalLettersUsedSoFar += NumberEnum.EIGHT.name().length();
		if (number == 9)
			totalLettersUsedSoFar += NumberEnum.NINE.name().length();
		if (number == 10)
			totalLettersUsedSoFar += NumberEnum.TEN.name().length();
	}

	private static void addLettersFromDoubleDigitNumber(int number) {
		if (number >= 90)
			totalLettersUsedSoFar += NumberEnum.NINETY.name().length();
		else if (number >= 80)
			totalLettersUsedSoFar += NumberEnum.EIGHTY.name().length();
		else if (number >= 70)
			totalLettersUsedSoFar += NumberEnum.SEVENTY.name().length();
		else if (number >= 60)
			totalLettersUsedSoFar += NumberEnum.SIXTY.name().length();
		else if (number >= 50)
			totalLettersUsedSoFar += NumberEnum.FIFTY.name().length();
		else if (number >= 40)
			totalLettersUsedSoFar += NumberEnum.FORTY.name().length();
		else if (number >= 30)
			totalLettersUsedSoFar += NumberEnum.THIRTY.name().length();
		else if (number >= 20)
			totalLettersUsedSoFar += NumberEnum.TWENTY.name().length();
		else {
			if (number == 11)
				totalLettersUsedSoFar += NumberEnum.ELEVEN.name().length();
			if (number == 12)
				totalLettersUsedSoFar += NumberEnum.TWELVE.name().length();
			if (number == 13)
				totalLettersUsedSoFar += NumberEnum.THIRTEEN.name().length();
			if (number == 14)
				totalLettersUsedSoFar += NumberEnum.FOURTEEN.name().length();
			if (number == 15)
				totalLettersUsedSoFar += NumberEnum.FIFTEEN.name().length();
			if (number == 16)
				totalLettersUsedSoFar += NumberEnum.SIXTEEN.name().length();
			if (number == 17)
				totalLettersUsedSoFar += NumberEnum.SEVENTEEN.name().length();
			if (number == 18)
				totalLettersUsedSoFar += NumberEnum.EIGHTEEN.name().length();
			if (number == 19)
				totalLettersUsedSoFar += NumberEnum.NINETEEN.name().length();
		}
	}

	private enum NumberEnum {

		AND(), ONE(), TWO(), THREE(), FOUR(), FIVE(), SIX(), SEVEN(), EIGHT(), NINE(), TEN(), ELEVEN(), TWELVE(), THIRTEEN(), FOURTEEN(), FIFTEEN(), SIXTEEN(), SEVENTEEN(), EIGHTEEN(), NINETEEN(), TWENTY(), THIRTY(), FORTY(), FIFTY(), SIXTY(), SEVENTY(), EIGHTY(), NINETY(), HUNDRED();
	}
}
