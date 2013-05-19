package alex.algorithms.strings;

public class BruteForceSearch {

	public static int seach(String text, String pat){
		int N = text.length();
		int M = pat.length();
		int i=0;int j=0;
		for(;i < N && j < M; i++){
			if(text.charAt(i) == pat.charAt(j)) j++;
			else{
				i-=j; j=0;
			}
		}
		if(j==M){
			return i-M;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		System.out.println(seach("ZASASAALex", "ALex"));
	}

}
