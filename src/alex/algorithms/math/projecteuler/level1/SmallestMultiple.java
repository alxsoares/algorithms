package alex.algorithms.math.projecteuler.level1;

public class SmallestMultiple {
	public static long gcd(long v, long u){
		if (u == v)
			return u;
		if (u == 0)
			return v;
		if (v == 0)
			return u;
		if ((~u & 1) > 0) {// u é par
			if ((v & 1) > 0) {// v impar
				return gcd(u >> 1, v);// v impar e u par
			}
			return gcd(u >> 1, v >> 1) << 1;// ambos são pares
		}
		if ((~v & 1) > 0)
			return gcd(u, v >> 1);// v par e u impar
		if (u > v) {
			return gcd((u - v) >> 1, v);
		} else {
			return gcd(u, (v - u) >> 1);
		}
		
	}
	public static long lcm(long u, long v){
		return u*v/gcd(v, u);
	}
	public static void main(String[] args) {
		long prod =1;
		for(int i=2;i<=20;i++){
			prod = lcm(prod, i);
		}
		System.out.printf("%d\n", prod);
	}

}
