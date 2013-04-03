package alex.bits;

public class CountSetBits {
	public static int countSetBits(int n){
		int count =0;
		while(n != 0){
			n = n & (n-1);//1100 & 1011=1000
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		System.out.printf("%d bits set in number %d\n",countSetBits(16),16);
	}

}
