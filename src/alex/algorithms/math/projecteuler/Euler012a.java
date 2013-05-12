package alex.algorithms.math.projecteuler;


public class Euler012a {

	public static void main(String[] args) {
		int counter =0;
		int n = 10;
		int triang=1;
		for(int k=1; counter < 500; triang+=++k){
			counter=0;
			n++;
			int t = (int) Math.sqrt(triang);
			for(int i=1; i<=t;i++){
				if(triang%i==0){
					counter++;
				}
			}
			counter*=2;
			if(t*t == triang){
				counter--;
			}
		}
		System.out.printf("%d %d\n", triang,n-1);
	}

}
