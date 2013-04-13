package alex.datastructures;

public class UnionFind {

	// A utility function to find set of an element i
	// (uses path compression technique)
	public int find(Subset subsets[], int i)
	{
	    // find root and make root as parent of i (path compression)
	    if (subsets[i].parent != i)
	        subsets[i].parent = find(subsets, subsets[i].parent);
	 
	    return subsets[i].parent;
	}
	 
	// A function that does union of two sets of x and y
	// (uses union by rank)
	public void Union(Subset subsets[], int x, int y)
	{
	    int xroot = find(subsets, x);
	    int yroot = find(subsets, y);
	 
	    // Attach smaller rank tree under root of high rank tree
	    // (Union by Rank)
	    if (subsets[xroot].rank < subsets[yroot].rank)
	        subsets[xroot].parent = yroot;
	    else if (subsets[xroot].rank > subsets[yroot].rank)
	        subsets[yroot].parent = xroot;
	 
	    // If ranks are same, then make one as root and increment
	    // its rank by one
	    else
	    {
	        subsets[yroot].parent = xroot;
	        subsets[xroot].rank++;
	    }
	}
	

}
