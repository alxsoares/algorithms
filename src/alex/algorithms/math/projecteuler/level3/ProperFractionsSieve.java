package alex.algorithms.math.projecteuler.level3;

public class ProperFractionsSieve {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] phi = new int[1000001];
		for(int i=0;i<phi.length;i++){
			phi[i] =i;
		}
		long sum=0;
		for(int i=2;i<=1000000;i++){
			if(phi[i]==i){
				for(int j=i;j<=1000000;j+=i){
					phi[j] = (phi[j]/i)*(i-1);
				}
			}
			sum+=phi[i];
		}
		System.out.printf("%d\n", sum);
	}

}
