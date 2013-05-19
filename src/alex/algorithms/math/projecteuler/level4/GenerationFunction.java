package alex.algorithms.math.projecteuler.level4;

import java.util.ArrayList;
import java.util.List;

public class GenerationFunction {

	public static void main(String[] args) {
		List<Integer> p = new ArrayList<Integer>();
		p.add(1);
		             
		int n = 1;           
		while(true){
		    int i = 0;
		    int pentagonal = 1;
		    p.add(0);
		 
		    while (pentagonal <= n){                   
		        int sign = (i % 4 > 1) ? -1 : 1;
		        p.set(n,  (p.get(n) + sign * p.get(n - pentagonal))%1000000);
		        i++;
		                  
		        int j = (i % 2 == 0) ? i / 2 + 1 : -(i / 2 + 1);
		        pentagonal = j * (3 * j - 1) / 2;
		    }
		                 
		    if (p.get(n) == 0) break;
		    n++;
		}
		System.out.printf("%d\n", n);
	}

}
