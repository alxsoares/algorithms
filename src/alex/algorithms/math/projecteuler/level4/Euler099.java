package alex.algorithms.math.projecteuler.level4;

import java.io.BufferedReader;
import java.io.FileReader;

public class Euler099 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(
				new FileReader("/root/base_exp.txt"));
		try {
			String line;
			int maxLine =0;
			double max = Integer.MIN_VALUE;
			int i=0;
			while((line=br.readLine())!= null){
				String[] split = line.split(",");
				double a = Math.log10(Integer.valueOf(split[0]));
				int e = Integer.valueOf(split[1]);
				double curr = a*e;
				if(curr > max){
					max = curr;
					maxLine=i;
				}
				i++;
			}
			System.out.printf("%d\n", maxLine+1);
		} finally {
			br.close();
		}
	}

}
