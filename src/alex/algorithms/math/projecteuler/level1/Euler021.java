package alex.algorithms.math.projecteuler.level1;

import java.util.HashMap;
import java.util.Map;

public class Euler021 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Long result = 0L;
	        Map<Integer, Integer> principalSumMap = new HashMap<Integer, Integer>();

	        for(int number = 1; number < 10000; number++) {
	            principalSumMap.put(number, getDivisorsSum(number));
	        }

	        for(Integer key : principalSumMap.keySet()) {
	            for(Integer valueKey : principalSumMap.keySet()) {
	                if(key.intValue() == principalSumMap.get(valueKey).intValue() && principalSumMap.get(key).intValue() == valueKey && key.intValue() != valueKey.intValue())
	                    result += key;
	            }
	        }
	        System.out.printf("%d\n", result);
	}
	
	 static private Integer getDivisorsSum(Integer number) {
	        int sum = 0;

	        for(int divisor = 1; divisor <= (number/2); divisor++ ) {
	            if(number % divisor == 0)
	                sum += divisor;
	        }

	        return sum;
	    }



}
