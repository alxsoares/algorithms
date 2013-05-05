package alex.algorithms;

public class EightQueens {

	public static int solve(){
		int colIndex[] = {0,1,2,3,4,5,6,7};
		return permutations(colIndex,0);
	}
	
	private static int permutations(int[] colIndex, int index) {
		int count =0;
		if(index == colIndex.length){
			if(check(colIndex)){
				count++;
			}
		}else{
			for(int i=index;i <colIndex.length;i++){
				int temp = colIndex[i];
				colIndex[i] = colIndex[index];
				colIndex[index] = temp;
				count+=permutations(colIndex, index+1);
				colIndex[index] = colIndex[i];
				colIndex[i] = temp;
			}
		}
		return count;
	}

	private static boolean check(int[] colIndex) {
		for (int i = 0; i < colIndex.length; i++) {
			for (int j = i+1; j < colIndex.length; j++) {
				if(Math.abs(i-j)==Math.abs(colIndex[i] - colIndex[j])){
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.printf("%d\n",solve());
	}

}
