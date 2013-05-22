package alex.algorithms.math.projecteuler.level4;

public class Euler085 {
	 public static int rectangles(int x, int y) {
	      int counter = 0;
	      for (int i=1; i<=x; i++) {
	         for (int j=1; j<=y; j++) {
	            counter += (x - i + 1)*(y - j + 1);
	         }
	      }
	      return counter;
	   }
	   
	   public static void main(String[] args) throws Exception {
	      int difference = 2000000;
	      int x=0;
	      int y=0;
	      
	      for (int i=1; i<100; i++) {
	         for (int j=1; j<100; j++) {
	            int rectangles = rectangles(i,j);
	            if ( Math.abs(2000000 - rectangles) < difference ) {
	               difference = Math.abs(2000000 - rectangles);
	               x = i;
	               y = j;
	            }
	         }
	      }
	      System.out.printf("%d\n",x*y);
	   }

}
