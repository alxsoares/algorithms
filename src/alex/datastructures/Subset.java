package alex.datastructures;

public class Subset {

	int parent;
	int rank;

	public Subset() {
	}

	public Subset(int parent, int rank) {
		this.parent = parent;
		this.rank = rank;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
