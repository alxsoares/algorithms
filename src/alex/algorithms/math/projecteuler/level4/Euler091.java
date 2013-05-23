package alex.algorithms.math.projecteuler.level4;

public class Euler091 {
	public static int gcd(int u, int v){
		if(u==v) return u;
		if(u==0) return v;
		if(v==0) return u;
		if((~u & 1)!=0){
			if((~v & 1)!=0){
				return gcd(u>>1, v>>1)<<1;
			}
			return gcd(u>>1,v);
		}
		if((~v&1)!=0){
			return gcd(u, v>>1);
		}
		if(u > v){
			return gcd((u-v)>>1, v);
		}else{
			return gcd((v-u)>>1, u);
		}
	}
	
	public static void main(String[] args) {
		int size = 50;
		int result = size*size*3;
		for (int x = 1; x <= size; x++) {
		    for (int y = 1; y <= size; y++) {
		        int fact = gcd(x, y);
		        result += Math.min(y*fact /x, (size - x)*fact /y) * 2;
		    }
		}
		System.out.printf("%d\n", result);
	}

}
