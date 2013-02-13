package topcoder.alex.misc;

public class Fib {

	public static long nfib(int n){
		long fibn_1=1, fibn_2=0;
		long fibn =0;
		for(int i=2; i <=n ; i++){
			fibn = fibn_1 + fibn_2;
			fibn_2 = fibn_1;
			fibn_1 = fibn;
		}
		return fibn;
	}
	//{{1,1},{1,0}}^n = {{Fn+1,Fn},{Fn,Fn-1}}
	public static long fastFib(int n){
		long[][] b = {{1,1},{1,0}};
		long[][] a = {{1,1},{1,0}};
		int e =2;
		while(e  <= n){
			mutiply(b);
			e=e*2;
		}
		if(n%2==1){
			b = mutiply(b,a );
		}
		return b[0][1];
	}
	private static void mutiply(long[][] b) {
		long a00=b[0][0];
		long a01=b[0][1];
		long a10=b[1][0];
		long a11=b[1][1];
		b[0][0] = a00*a00+a01*a10;
		b[0][1] = a00*a01+a01*a11;
		b[1][0] = a10*a00+a11*a10;
		b[1][1] = a10*a01+a11*a11;
	}
	private static long[][] mutiply(long[][] b, long[][] a) {
		long multiply[][] = new long[2][2];
		 for (int  c = 0 ; c < 2 ; c++ )
         {
            for (int d = 0 ; d < 2 ; d++ )
            {   long sum=0;
               for ( int k = 0 ; k < 2 ; k++ )
               {
                  sum = sum + b[c][k]*a[k][d];
               }
 
               multiply[c][d] = sum;
               sum = 0;
            }
         }
		 return multiply;
	}
	//F2m      =  Fm×F2m-m+1  + Fm-1×F2m-m     = Fm×Fm+1 + Fm-1×Fm       = Fm×Fm + Fm×Fm-1 + Fm×Fm-1 
	  //F2m-1    =  Fm×F2m-1-m+1 + Fm-1×F2m-1-m                            = Fm×Fm + Fm-1×Fm-1
	public static long fibR(int m){
		if(m <= 2) return 1;
		if(m%2==0){
			long fm = fibR(m/2);
			long fm_1 = fibR(m/2 -1);
			return fm*fm + 2*fm*fm_1; 
		}else{
			long fm = fibR((m+1)/2);
			long fm_1 = fibR((m+1)/2 -1);
			return fm*fm + fm_1*fm_1; 
		}
	}
	public static void main(String[] args) {
		System.out.println(nfib(3));
		System.out.println(nfib(4));
		System.out.println(nfib(5));
		System.out.println(nfib(6));
		System.out.println(nfib(50));
		System.out.println();
		System.out.println(fibR(3));
		System.out.println(fibR(4));
		System.out.println(fibR(5));
		System.out.println(fibR(6));
		System.out.println(fibR(50));
	}

}
