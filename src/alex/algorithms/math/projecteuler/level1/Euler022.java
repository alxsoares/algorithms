package alex.algorithms.math.projecteuler.level1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Euler022 {

	public static void main(String[] args) throws IOException {

		// open file
		FileInputStream fstream = new FileInputStream("/root/name.txt");

		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			strLine = strLine.replaceAll("\"", "");
			String[] tmpArr = strLine.split(",");
			Arrays.sort(tmpArr);
			ArrayList<Integer> results = new ArrayList<Integer>();
			for (int s = 0; s < tmpArr.length; s++) {
				// list aufbauen
				results.add(letterValue(tmpArr[s]));
			}
			long ultra = 0;
			for (int n = 0; n < results.size(); n++) {
				ultra += ((n + 1) * results.get(n));
			}
			System.out.println(ultra);
		}
		in.close();

	}

	private static Integer letterValue(String string) {
		int value = 0;
		for (char c : string.toCharArray()) {
			int cVal = c;
			value += cVal - 64;
		}
		return value;
	}

}
