package alex.datastructures;

public class Edge implements Comparable<Edge>{

	public int src, dest, weight;

	@Override
	public int compareTo(Edge o) {
		return this.weight - o.weight;
	}

}
