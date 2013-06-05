package alex.algorithms;

public class Kadane {

	public static int kadane(int[] numbers) {
		int begin = 0;
		int beginTemp = 0;
		int max = numbers[0];
		int maxTemp = numbers[0];
		int end = 0;
		for (int i = 1; i < numbers.length; i++) {
			if (maxTemp < 0) {
				maxTemp = numbers[i];
				beginTemp = i;
			} else {
				maxTemp += numbers[i];
			}

			if (maxTemp > max) {
				max = maxTemp;
				begin = beginTemp;
				end = i;

			}
		}
		System.out.printf("%d %d\n", begin, end);
		return max;
	}

	public static int kadane2(int[] numbers) {
		int begin = 0;
		int end = 0;
		int beginAux = numbers[0];
		int max = numbers[0];
		int maxTemp = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			if (maxTemp < 0) {
				maxTemp = numbers[i];
				beginAux = i;
			} else {
				maxTemp += numbers[i];
			}
			if (maxTemp > max) {
				max = maxTemp;
				begin = beginAux;
				end = i;
			}
		}
		System.out.printf("%d %d\n", begin, end);
		return max;
	}
	public static int kadane3(int a[]){
		int max = Integer.MIN_VALUE;
		int currentSum=0;
		int begin=0;int end =0;
		for(int i=0; i< a.length;i++){
			currentSum+=a[i];
			if(currentSum<0){
				currentSum=0;
				begin=i+1;
			}else if(currentSum >max){
				max = currentSum;
				end = i;
			}
		}
		System.out.printf("%d %d %d\n", begin, end, max);
		return max;
	}

	public static void main(String[] args) {
		System.out.println(kadane(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 8 }));
		System.out
				.println(kadane2(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 8 }));
		System.out
		.println(kadane3(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 8 }));
	}

}
