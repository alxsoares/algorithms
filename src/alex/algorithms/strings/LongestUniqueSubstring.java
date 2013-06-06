package alex.algorithms.strings;

import java.util.Arrays;

public class LongestUniqueSubstring {

	public static int longestUniqueSubsttr(String s){
		int maxLen = Integer.MIN_VALUE;
		int n = s.length();
		int visited[] = new int[256];
		Arrays.fill(visited, -1);
		visited[s.charAt(0)]=0;
		int currLen =1;
		for(int i=1;i<n;i++){
			int prevIndex = visited[s.charAt(i)];
			if(prevIndex==-1 || i - currLen > prevIndex){
				currLen++;
			}else{
				if(currLen > maxLen){
					maxLen = currLen;
				}
				currLen = i - prevIndex;
			}
			visited[s.charAt(i)]=i;
		}
		if(currLen > maxLen){
			maxLen = currLen;
		}
		
		return maxLen;
	}
	public static void main(String[] args) {
		System.out.println(longestUniqueSubsttr("ABDEFGABEF"));
	}

}
