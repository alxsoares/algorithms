package alex.algorithms.graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class Dijkstra {

	public static Integer[] dijkstra(int source, ArrayList<ArrayList<Integer>> G) {
		
		final Integer dist[] = new Integer[G.size()];
		
		Integer prev[] = new Integer[G.size()];
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(G.size(),
				new Comparator<Integer>() {
					public int compare(Integer o1, Integer o2) {
						return dist[o1].compareTo(dist[o2]);
					}
				});
		for (int v = 0; v < G.size(); v++) {
			dist[v] = Integer.MAX_VALUE;
			prev[v] = null;
//			minHeap.add(v);
		}
//		minHeap.remove(source);
		dist[source] = 0;
		minHeap.add(source);
		while (!minHeap.isEmpty()) {
			int u = minHeap.remove();
			if (dist[u] == Integer.MAX_VALUE) {
				break;
			}
			ArrayList<Integer> adj = G.get(u);
			for(int v=0; v < adj.size(); v++){
				Integer alt = dist[u] + G.get(u).get(v);
				if (alt < dist[v]) {
					dist[v] = alt;
					prev[v] = u;
					minHeap.remove(v);
					minHeap.add(v);
				}
			}

		}
		for (int i = 0; i < prev.length; i++) {
			Integer integer = prev[i];
			System.out.printf("Prev de %d = %d\n", i,integer);
		}
		return dist;
	}

	public static void main(String[] args) {
		Random rand = new Random(System.currentTimeMillis());
		ArrayList<ArrayList<Integer>> G = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 100; i++) {
				G.add( new ArrayList<Integer>());
			for (int j = 0; j < 100; j++) {
				if(i==0 && j ==99){
					G.get(i).add(j,1);
				}else
				if(i==j){
					G.get(i).add(j,0);
				}
				else if(j < i){
					G.get(i).add(j, G.get(j).get(i));
				}
				else{
					G.get(i).add(j,(Math.abs(rand.nextInt())%50)+10);
				}
			}
		}
		
		for(int i=0 ; i < G.size(); i++){
			ArrayList<Integer> v = G.get(i);
			for(int j=0; j < v.size(); j++){
				System.out.printf("%d %d %d \n", i,j,v.get(j));
			}
			
		}
		System.out.println();
		
		Integer[] dijkstra = dijkstra(0, G);
		for (int i = 0; i < dijkstra.length; i++) {
			System.out.printf("distancia de 0 até %d = %d \n", i, dijkstra[i]);
		}
	}

}
