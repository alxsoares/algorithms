package alex.algorithms.math.projecteuler.level4;

import java.io.BufferedReader;
import java.io.FileReader;

public class Euler098 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(
				new FileReader("/root/words.txt"));
		try {
			String [] words = br.readLine().replaceAll("\"","").split(",");
			//TODO: finish
			
		} finally {
			br.close();
		}
	}

}
