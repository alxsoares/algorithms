package alex.algorithms;

public class Kadane {
	
	public static int kadane(int[] numbers){
		int begin =0;
		int beginTemp=0;
		int max=numbers[0];
		int maxTemp = numbers[0];
		int end=0;
		for(int i=1; i < numbers.length; i++){
			if(maxTemp < 0){
				maxTemp = numbers[i];
				beginTemp =i;
			}else{
				maxTemp+=numbers[i];
			}
			
			if(maxTemp > max){
				max = maxTemp;
				begin = beginTemp;
				end=i;
				
			}
		}
		System.out.printf("%d %d\n", begin, end);
		return max;
	}
	
	public static void main(String[] args) {
		System.out.println(kadane(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
	}

}
