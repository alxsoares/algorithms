package alex.algorithms.math;

public class UglyNumbers {

	public static int maxDivide(int a, int b) {
		while(a%b==0){
			a=a/b;
		}
		return a;
	}
	public static boolean isUgly(int a){
		int num = maxDivide(a, 2);
		num = maxDivide(num, 3);
		num = maxDivide(num, 5);
		if(num==1) return true;
		return false;
	}
	public static int getNthUglyNumber(int n){
		int count=1;
		int i=1;
		while(count < n){
			i++;
			if(isUgly(i)) count++;
		}
		return i;
	}
	public static int getNthUglyNumberDynamicProgramming(int n){
		int [] ugly = new int[n+1];
		ugly[0] = 1;
		int multiple2 = 2;
		int multiple3 = 3;
		int multiple5 = 5;
		int i2=0;
		int i3=0;
		int i5=0;
		for(int i=1; i < n;i++){
			int nextUgly = min(multiple2, min(multiple3,multiple5));
			ugly[i] = nextUgly;
			if(nextUgly==multiple2){
				i2++;
				multiple2 =ugly[i2]*2;
			}
			if(nextUgly==multiple3){
				i3++;
				multiple3 = ugly[i3]*3;
			}
			if(nextUgly==multiple5){
				i5++;
				multiple5 =ugly[i5]*5;
			}
		}
		return ugly[n-1];
	}
	public static int min(int a, int b){
		if(a < b) return a;
		return b;
	}
	public static void main(String[] args) {
		System.out.println(getNthUglyNumber(150));
		System.out.println(getNthUglyNumberDynamicProgramming(150));
	}

}
