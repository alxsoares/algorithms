package nullcon.ctf;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class CTF3 {

	public static String seq(int n) {
		BigDecimal r = new BigDecimal(0);
		for (int i = 0; i < n; i++) {
			System.out.println("q:=>"+(n-i));
			System.out.println("d:=>"+(i+1));
			BigDecimal q = new BigDecimal(n - i);
			BigDecimal d = new BigDecimal(i+1);
			r.add(q.divide(d,RoundingMode.CEILING));
		}
		return r.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(seq(10));
	}

}
