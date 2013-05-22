package alex.algorithms.math.projecteuler.level4;

import java.text.DecimalFormat;

public class Euler084 {

	final int jail = 10;
	final int g2j = 30;
	final int cc1 = 2;
	final int cc2 = 17;
	final int cc3 = 33;
	final int ch1 = 7;
	final int ch2 = 22;
	final int ch3 = 36;
	final int go = 0;
	final int e3 = 24;
	final int c1 = 11;
	final int h2 = 39;
	final int r1 = 5;
	final int r2 = 15;
	final int r3 = 25;
	final int u1 = 12;
	final int u2 = 28;

	final int nSquares = 40;
	final int nResults = 5;

	double[][] markov = new double[nSquares][nSquares];
	String[] names = { "go", "a1", "cc1", "a2", "t1", "r1", "b1", "ch1", "b2",
			"b3", "jail", "c1", "u1", "c2", "c3", "r2", "d1", "cc2", "d2",
			"d3", "fp", "e1", "ch2", "e2", "e3", "r3", "f1", "f2", "u2", "f3",
			"g2j", "g1", "g2", "cc3", "g3", "r4", "ch3", "h1", "t2", "h2" };

	double eps = 1.0E-6;
	DecimalFormat decform = new DecimalFormat("0.000");

	int nSides;

	public Euler084(int sides) {
		nSides = sides;
		for (int i = 0; i < nSquares; i++) {
			throwDice(i, i);
		}
		// square 30 is go to jail (10)
		for (int i = 0; i < markov.length; i++) {
			markov[i][jail] += markov[i][g2j];
			markov[i][g2j] = 0;
		}
		doCH(ch1);
		doCH(ch2);
		doCH(ch3);
		doCC(cc1);
		doCC(cc2);
		doCC(cc3);
		run();
	}

	private void throwDice(int i, int j0) {
		double p = 1.0;
		for (int doubles = 0; doubles < 3; doubles++) {
			p /= sqr(nSides);
			double pp = 0.0;
			for (int j = j0 + 2; j - j0 <= nSides + 1; j++) {
				pp += p;
				markov[i][j % nSquares] += pp;
			}
			for (int j = j0 + nSides + 2; j - j0 < 2 * nSides; j++) {
				pp -= p;
				markov[i][j % nSquares] += pp;
			}
			j0 += 2 * nSides;
		}
		markov[i][jail] += p;
	}

	private void doCC(int cc) {
		for (int i = 0; i < markov.length; i++) {
			markov[i][go] += markov[i][cc] / 16;
			markov[i][jail] += markov[i][cc] / 16;
			markov[i][cc] *= 7.0 / 8.0;
		}
	}

	private void doCH(int ch) {
		for (int i = 0; i < markov.length; i++) {
			markov[i][go] += markov[i][ch] / 16.0;
			markov[i][jail] += markov[i][ch] / 16.0;
			markov[i][c1] += markov[i][ch] / 16.0;
			markov[i][e3] += markov[i][ch] / 16.0;
			markov[i][h2] += markov[i][ch] / 16.0;
			markov[i][r1] += markov[i][ch] / 16.0;
			markov[i][ch - 3] += markov[i][ch] / 16.0;
			switch (ch) {
			case 7:
				markov[i][r2] += markov[i][ch] / 8.0;
				markov[i][u1] += markov[i][ch] / 16.0;
				break;
			case 22:
				markov[i][r3] += markov[i][ch] / 8.0;
				markov[i][u2] += markov[i][ch] / 16.0;
				break;
			case 36:
				markov[i][r1] += markov[i][ch] / 8.0;
				markov[i][u1] += markov[i][ch] / 16.0;
			}
			markov[i][ch] *= 3.0 / 8.0;
		}
	}

	private void run() {
		int count = 0;
		double[] current = new double[nSquares];
		for (int j = 0; j < current.length; j++) {
			current[j] = 0.025;
		}
		double[] previous;
		do {
			previous = current;
			// writeVec(current);
			current = mul(current, markov);
			normalize(current);
			count++;
		} while (distance(current, previous) >= eps);
		System.out.println(nSides + " sides:");
		System.out.println("Number of iterations: " + count);
		int[] indices = new int[nSquares];
		for (int i = 0; i < indices.length; i++)
			indices[i] = i;
		sort(current, indices);
		System.out.print("Answer:      ");
		for (int i = 0; i < nResults; i++)
			System.out.print(String.valueOf(indices[i]));
		System.out.println();
		System.out.print("Percentages: ");
		for (int i = 0; i < nResults; i++)
			System.out.print(" " + decform.format(100 * current[indices[i]]));
		System.out.println();
		System.out.print("Names:       ");
		for (int i = 0; i < nResults; i++)
			System.out.print(names[indices[i]]);
		System.out.println();
	}

	private void normalize(double[] x) {
		double sum = 0.0;
		for (int j = 0; j < x.length; j++)
			sum += x[j];
		for (int j = 0; j < x.length; j++)
			x[j] /= sum;
	}

	private double[] mul(double[] vec, double[][] mat) {
		double[] result = new double[mat[0].length];
		for (int j = 0; j < result.length; j++) {
			// result[j] = sum over k {vec[k]*mat[k][j]
			for (int k = 0; k < mat.length; k++) {
				result[j] += vec[k] * mat[k][j];
			}
		}
		return result;
	}

	private double distance(double[] x, double[] y) {
		double result = 0;
		int l = Math.min(x.length, y.length);
		for (int j = 0; j < l; j++) {
			result += sqr(x[j] - y[j]);
		}
		return Math.sqrt(result);
	}

	private double sqr(double x) {
		return x * x;
	}

	private void writeVec(double[] vec) {
		for (int i = 0; i < vec.length; i++) {
			System.out.print(decform.format(vec[i]) + " ");
		}
		System.out.println();
	}

	private void sort(double[] x, int[] ind) {
		for (int i = 0; i < x.length - 1; i++) {
			int max = i;
			double maxX = x[ind[i]];
			for (int j = i + 1; j < x.length; j++) {
				if (x[ind[j]] > maxX) {
					maxX = x[ind[j]];
					max = j;
				}
			}
			int temp = ind[i];
			ind[i] = ind[max];
			ind[max] = temp;
		}
	}

	public static void main(String[] args) {
		Euler084 mp = new Euler084(2); // a coin, rather
		mp = new Euler084(4);
		mp = new Euler084(6);
		mp = new Euler084(12);
		mp = new Euler084(20);
	}

}
