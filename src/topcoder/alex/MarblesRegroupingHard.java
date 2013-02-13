package topcoder.alex;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * Problem Statement
 * 
 * 
 * John is (as you probably know) a marble collector. He keeps his marbles in
 * boxes. He also likes to keep things in order - in each box there are only
 * marbles of the same color (some boxes may be empty).
 * 
 * One day, his younger brother was playing with the marbles. After he was done,
 * he put all the marbles back in boxes, but he did it randomly, so certain
 * boxes might now contain marbles of different colors. You are given a String[]
 * boxes, where each element is a space separated list of integers and the j-th
 * integer of the i-th element is the number of marbles of color j in the i-th
 * box. John wants him to regroup the marbles so that each box is either empty
 * or contains only marbles of the same color, and all marbles of the same color
 * are in the same box. Return the minimal number of moves necessary to do this,
 * where each move consists of taking exactly one marble from any box and
 * putting it into another.
 * 
 * Definition
 * 
 * Class: MarblesRegroupingHard Method: minMoves Parameters: String[] Returns:
 * int Method signature: int minMoves(String[] boxes) (be sure your method is
 * public)
 * 
 * 
 * Constraints - boxes will contain between 1 and 50 elements, inclusive. - Each
 * element of boxes will contain only digits ('0'-'9') and spaces (' '). - Each
 * element of boxes will be a single space separated list of integers without
 * leading or trailing spaces. - Each integer in boxes will not contain leading
 * zeros and will be between 0 and 99, inclusive. - Each element of boxes will
 * contain between 1 and 14 integers, inclusive (that's the number of different
 * colors used). - All elements of boxes will contain the same number of
 * integers. - The number of different colors used will be less then or equal to
 * N, where N is the number of elements in boxes.
 * 
 * 
 */
public class MarblesRegroupingHard {

	int dp[][] = new int[51][1 << 15];
	int size;
	int numberOfColors;
	ArrayList<ArrayList<Integer>> graph;
	int totalColors[] = new int[14];

	int func(int box, int mask) {
		int res = 0;
		if (box == size) {
			// todas as cores já foram colocadas em uma caixa.
			return mask == (1 << numberOfColors) - 1 ? 0 : 1 << 20;
		}
		if (dp[box][mask] != -1)
			return dp[box][mask];
		res = func(box + 1, mask);
		// decide a cor a ser colocada em uma caixa
		for (int color = 0; color < numberOfColors; color++) {
			if ((mask & (1 << color)) > 0) {
				// jah foi colocada em uma caixa
				continue;
			}
			res = Math.min(res, func(box + 1, mask | (1 << color))
					+ (totalColors[color] - graph.get(box).get(color)));
		}
		return dp[box][mask] = res;
	}

	public int minMoves(String[] boxes) {
		Arrays.fill(totalColors, 0);
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < boxes.length; i++) {
			String[] box = boxes[i].split(" ");
			graph.add(i, new ArrayList<Integer>());
			int idxC = 0;
			for (int j = 0; j < box.length; j++) {
				Integer val = Integer.valueOf(box[j]);
				graph.get(i).add(val);
				totalColors[idxC++]+=val ;
			}
			numberOfColors = idxC;
		}
		size = boxes.length;
		return func(0, 0);
	}

	public static void main(String[] args) {
		MarblesRegroupingHard m = new MarblesRegroupingHard();
		String boxes[] = { "38 0 0 45 77 61 0 0 8 87 65 53 0 2",
				 "0 97 79 37 70 0 69 35 95 11 75 96 77 29",
				 "39 32 0 24 63 22 91 71 0 63 92 59 12 0",
				 "34 0 0 71 88 13 60 56 0 22 29 89 81 53",
				 "69 95 65 0 94 98 84 37 0 87 16 0 58 18",
				 "82 0 36 88 0 54 82 40 6 0 34 44 80 2",
				 "33 58 7 95 83 87 0 0 79 35 0 51 22 84",
				 "7 0 30 57 33 4 0 26 44 55 67 31 88 42",
				 "62 58 62 93 91 0 1 0 44 23 95 0 55 57",
				 "35 8 22 26 76 0 78 54 0 46 42 0 0 64",
				 "93 54 58 0 89 89 0 0 57 0 98 3 24 0",
				 "9 0 0 23 82 18 0 46 0 0 94 84 19 18",
				 "78 12 6 45 0 80 16 69 59 76 35 0 66 0",
				 "0 68 77 13 15 0 52 72 0 0 18 65 23 0",
				 "0 0 73 53 0 95 91 44 27 96 85 0 99 85",
				 "93 29 4 89 27 44 27 17 21 6 0 8 3 91",
				 "0 46 73 94 60 59 15 50 18 75 74 88 46 93",
				 "0 0 0 94 93 26 21 83 54 62 0 89 59 77",
				 "32 98 0 44 34 38 56 20 0 61 29 94 52 57",
				 "52 60 0 22 0 5 38 0 50 98 12 75 0 81",
				 "23 0 41 18 36 87 49 32 62 43 22 74 0 97",
				 "0 0 30 79 16 73 61 0 75 51 64 13 45 0",
				 "11 0 14 2 0 1 2 16 84 37 95 45 48 33",
				 "10 0 0 35 0 85 28 76 99 74 76 32 52 8",
				 "60 0 96 0 95 26 59 13 0 0 44 42 97 48",
				 "34 7 77 25 91 85 35 78 32 99 7 29 18 15",
				 "61 50 43 22 42 63 64 50 9 94 42 22 21 33",
				 "58 0 41 10 16 0 27 67 83 27 14 37 98 47",
				 "37 60 60 76 71 2 84 32 27 39 82 84 0 94",
				 "15 98 69 82 36 66 0 97 62 39 0 65 62 67",
				 "0 41 0 43 0 0 94 0 0 58 0 0 27 33",
				 "53 90 71 91 85 0 32 86 40 60 11 0 99 28",
				 "79 62 0 0 79 0 14 62 87 76 35 0 70 0",
				 "0 40 73 48 0 63 0 0 63 5 30 18 47 51",
				 "75 6 58 69 33 57 66 0 12 0 46 0 65 10"};
		System.out.printf("%d", m.minMoves(boxes));
	}

}
