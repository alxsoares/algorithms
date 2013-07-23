package alex.acm;

import java.io.PrintWriter;
import java.util.Scanner;

public class UVA10229 {

	// a simple matrix class
	public class Matrix {
		private long[][] data;
		private int rows;
		private int cols;

		public Matrix(int m, int n) {
			data = new long[m][n];
			rows = m;
			cols = n;
		}

		public int getRows() {
			return rows;
		}

		public int getCols() {
			return cols;
		}

		public void set(int i, int j, long v) {
			data[i][j] = v;
		}

		public long get(int i, int j) {
			return data[i][j];
		}

		// multiply the matrix with another matrix
		public Matrix multiply(Matrix other) {
			Matrix res = new Matrix(getRows(), other.getCols());

			for (int i = 0; i < getRows(); i++)
				for (int j = 0; j < other.getCols(); j++)
					for (int k = 0; k < getCols(); k++)
						res.set(i, j,
								res.get(i, j) + get(i, k) * other.get(k, j));

			return res;
		}

		// raise the matrix to the n-th power
		public Matrix pow(int n) {
			if (n == 0) {

				// if n is 0, we return the identity matrix
				Matrix res = new Matrix(getRows(), getCols());
				for (int i = 0; i < getRows() && i < getCols(); i++)
					res.set(i, i, 1);

				return res;

			} else if (n % 2 == 0) {

				// if n is even, return the square root, squared
				Matrix res = pow(n / 2);
				return res.multiply(res);

			} else {

				// if n is even, return the matrix multiplied by the matrix to
				// the (n - 1)th power
				return multiply(pow(n - 1));
			}
		}

		// multiply the matrix with another matrix (modulo m)
		public Matrix multiplyMod(Matrix other, int m) {
			Matrix res = new Matrix(getRows(), other.getCols());

			for (int i = 0; i < getRows(); i++)
				for (int j = 0; j < other.getCols(); j++)
					for (int k = 0; k < getCols(); k++)
						res.set(i, j,
								(res.get(i, j) + get(i, k) * other.get(k, j))
										% m);

			return res;
		}

		// raise the matrix to the n-th power (modulo m)
		public Matrix powMod(int n, int m) {
			if (n == 0) {
				Matrix res = new Matrix(getRows(), getCols());
				for (int i = 0; i < getRows() && i < getCols(); i++)
					res.set(i, i, 1);

				return res;
			} else if (n % 2 == 0) {
				Matrix res = powMod(n / 2, m);
				return res.multiplyMod(res, m);
			} else {
				return multiplyMod(powMod(n - 1, m), m);
			}
		}
	}

	public void run(Scanner in, PrintWriter out) {

		// initialize the matrix to:
		// [ 1 1 ]
		// [ 1 0 ]

		Matrix fib = new Matrix(2, 2);
		fib.set(0, 0, 1);
		fib.set(0, 1, 1);
		fib.set(1, 0, 1);
		fib.set(1, 1, 0);

		// loop through each test case
		while (in.hasNextInt()) {

			// read n and m
			int n = in.nextInt(), m = in.nextInt();

			// calculate fib^n (modulo 2^m)
			Matrix fib2 = fib.powMod(n, (int) Math.pow(2, m));

			// output the n-th Fibonacci number (modulo 2^m)
			out.println(fib2.get(1, 0));
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, true);
		new UVA10229().run(in, out);
	}
}