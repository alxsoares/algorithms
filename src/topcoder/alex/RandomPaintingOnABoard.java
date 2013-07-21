package topcoder.alex;

public class RandomPaintingOnABoard {


	//http://apps.topcoder.com/wiki/display/tc/SRM+583
	// Assume we already processed the prob String[], we made an
	//  int[][] a, with the translated cell values and we rotated
	//  it in case w > h, so w is now guaranteed to be at most 12:
	public double expectedScoresSmallWidth(int[][] a){
	    int h = a.length, w = a[0].length, T = 0;
	    
	    for (int i=0; i<w; i++) {
	        for(int j=0; j<h; j++) {
	            T += a[j][i];
	        }
	    }
	    // cnt[x] : Subtraction between the number of odd sets and even sets that
	    //           have a sum of x for the outside cells.
	    long cnt[] = new long[T+1];
	    
	    for (int mask=0; mask<(1<<w); mask++) {
	        int sign = -1;
	        for (int i=0;i<w;i++) {
	            if ( (mask&(1<<i)) != 0) {
	                sign = -sign;
	            }
	        }
	        // base case: (if mask has an even number of elements -1, else 1)
	        long f[][] = new long[h+1][T+1];
	        f[0][0] = sign;
	        
	        // precalculate b[i]: Elements in column i outside of the mask subset:
	        int b[] = new int[h];
	        for (int i=0; i < h; i++) {
	            for(int j=0; j < w; j++) {
	                if ((mask&(1<<j)) == 0) {
	                    b[i] += a[i][j];
	                }
	            }
	        }
	               
	        for(int i=0; i<h; i++) for(int x=0; x<=T; x++) {
	            f[i+1][x] -= f[i][x];
	            if (x + b[i] <= T) {
	                f[i+1][x + b[i]] += f[i][x];
	            }
	        }
	        
	        for (int x=0; x < T; x++) {
	            cnt[x] += f[h][x];
	        }
	    }
	    
	    double ans = 0.0;
	    for (int x=0; x<T; x++) {
	        ans += (double)cnt[x] * T / (T-x);
	    }
	    return ans;
	}
	//-->



}
