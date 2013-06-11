package alex.algorithms.math.projecteuler.level5;

public class Euler112a {
	private static boolean isBouncy(int number) {

		boolean inc = false;
		boolean dec = false;

		int last = number % 10;
		number /= 10;

		while (number > 0) {
			int next = number % 10;
			number /= 10;
			if (next < last)
				inc = true;
			else if (next > last)
				dec = true;

			last = next;

			if (dec && inc)
				return true;
		}

		return dec && inc;
	}

	public static void main(String[] args) {
		 int i = 99;
         int bouncies = 0;
                     
         while(100*bouncies < 99*i) {
             i++;
             if(isBouncy(i))
                 bouncies++;                
         }
         System.out.println(i);
	}

}
