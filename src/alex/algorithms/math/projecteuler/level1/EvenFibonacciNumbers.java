package alex.algorithms.math.projecteuler.level1;

public class EvenFibonacciNumbers {

	
	public static void main(String[] args) {
		int f1=1;
		int f2 =2;
		int sum = 2;
		while(f2 <= 4000000){
			int next = f1+f2;
			f1= f2;
			f2=next;
			if(next%2==0) sum+=next;
		}
		System.out.printf("%d\n", sum);
	}

}
