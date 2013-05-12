package alex.algorithms.math.projecteuler;

public class SpecialPythagoreanTriplet {

	public static void main(String[] args) {
		for(int i=1; i <=1000; i++){
			for(int j=1;j<=1000-i;j++){
				int a = i;
				int b = j;
				int c = 1000-(a+b);
				if(a*a + b*b == c*c){
					System.out.printf("%d %d %d\n",a,b,c);
					System.out.printf("%d", a*b*c);
					return;
				}
			}
		}
	}

}
