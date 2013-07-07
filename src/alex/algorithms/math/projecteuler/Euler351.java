package alex.algorithms.math.projecteuler;

public class Euler351 {
	static final int maxn = 100000001;

	static int a[] = new int[maxn];
	public static void main(String[] args) {
		
	    int n = 100000000;
	    for (int i = 1; i <= n; i++)
	        a[i] = i;
	    long ret = 0;
	    for (int i = 1; i <= n; i++) {
	        ret += (n / i - 1) * a[i];
	        for (int j = i + i; j <= n; j += i)
	            a[j] -= a[i];
	    }
	    System.out.printf("%d\n", ret * 6);
	}
	
}
