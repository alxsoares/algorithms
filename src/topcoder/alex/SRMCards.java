package topcoder.alex;

import java.util.Arrays;

public class SRMCards {

	public int maxTurns(int[] cards) {
		Arrays.sort(cards);
		int ans = 0;
		for (int i = 0; i < cards.length; i++)
			if (i < cards.length - 1 && cards[i] == cards[i + 1] - 1) {
				ans++;
				i++;
			} else
				ans++;
		return ans;

	}

	public static void main(String[] args) {
		int cards[] = {491, 492,493, 495, 497, 498, 499};
		System.out.println(new SRMCards().maxTurns(cards));
	}

}
