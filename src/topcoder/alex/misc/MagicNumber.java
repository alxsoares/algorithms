package topcoder.alex.misc;

import java.util.LinkedList;
import java.util.Queue;

public class MagicNumber {

	public static int getKthMagicNumber(int k) {
		int val = 1;
		if(k<=0) return -1;
		Queue<Integer> Q3 = new LinkedList<Integer>();
		Queue<Integer> Q5 = new LinkedList<Integer>();
		Queue<Integer> Q7 = new LinkedList<Integer>();
		Q3.add(3);
		Q5.add(5);
		Q7.add(7);
		for(int i=1; i < k; i++){
			val = Math.min(Math.min(Q3.peek(), Q5.peek()),Q7.peek());
			if(val == Q7.peek()){
				Q7.remove();
			}else{
				if(val==Q5.peek()){
					Q5.remove();
				}else{
					Q3.remove();
					Q3.add(val*3);
				}
				Q5.add(val*5);
			}
			Q7.add(val*7);
		}
		return val;
	}

	public static void main(String[] args) {
		System.out.println(getKthMagicNumber(10));
		System.out.println(Integer.MAX_VALUE);
	}

}
