package alex.algorithms.math.projecteuler.level3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Euler067 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("/root/triangle.txt"));
		long t[][] = new long[100][];
		int row =-1;
		String line;
		while((line = br.readLine())!= null){
			String[] s = line.trim().split("\\s+");
			t[++row] = new long[s.length];
			for(int j=0; j < s.length;j++){
				t[row][j] = Long.valueOf(s[j]);
			}
		}
		for (int i = t.length - 2; i >= 0; i--)
			for (int j = 0; j <= i; j++)
				t[i][j] += Math.max(t[i + 1][j], t[i + 1][j + 1]);
		System.out.printf("%d\n", t[0][0]);
		br.close();
	}
}
