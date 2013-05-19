package alex.algorithms.math.projecteuler.level4;

public class Euler076 {

	public static void main(String[] args) {
		int [] numbers = new int[99];
		for(int i=0; i< numbers.length;i++) numbers[i] =i+1;
		int [] nway = new int[101];
		int n=100;
		nway[0] = 1;
		for (int i = 0; i < numbers.length; i++) {
			int c = numbers[i];
			for (int j = c; j <= n; j++)
				nway[j] += nway[j - c];
		}
		System.out.printf("%d\n", nway[n]);
	}

}
