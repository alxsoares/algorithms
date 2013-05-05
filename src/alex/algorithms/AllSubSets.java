package alex.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class AllSubSets {
	public ArrayList<ArrayList<Integer>> allSubsets(ArrayList<Integer> set){
		ArrayList<ArrayList<Integer>> all = new ArrayList<ArrayList<Integer>>();
		int max = (1 << set.size());//2^n
		for(int i=0; i < max; i++){
			ArrayList<Integer> subSet = new ArrayList<Integer>();
			int k=i;
			int index =0;
			while(k>0){
				if((k&1)>0){
					subSet.add(set.get(index));
				}
				k=k>>1;
				index++;
			}
			all.add(subSet);
		}
		
		return all;
	}
	public static ArrayList<ArrayList<Integer>> allsubs(ArrayList<Integer> set){
		ArrayList<ArrayList<Integer>> all = new ArrayList<>();
		int max = 1<<set.size();
		for(int i=0; i < max; i++){
			ArrayList<Integer> s = new ArrayList<>();
			int k=i;
			int index =0;
			while(k >0){
				if((k&1)>0){
					s.add(set.get(index));
				}
				k=k>>1;
				index++;
			}
			if(!s.isEmpty()){
				all.add(s);
			}
		}
		return all;
	}
	
	public static void main(String[] args) {
		Integer arr[] = {1,2,3,4,5,6};
		ArrayList<Integer> set = new ArrayList<Integer>(Arrays.asList(arr));
		ArrayList<ArrayList<Integer>> all = allsubs(set);
		for (Iterator<ArrayList<Integer>> iterator = all.iterator(); iterator.hasNext();) {
			ArrayList<Integer> sub =  iterator.next();
			for (Iterator<Integer> iterator2 = sub.iterator(); iterator2.hasNext();) {
				Integer el = iterator2.next();
				System.out.printf("%d ", el);
			}
			System.out.println();
		}
	}

}
