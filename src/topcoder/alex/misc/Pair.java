package topcoder.alex.misc;

public class Pair<T, D> {
	public D s;
	public T m;

	public Pair(T m, D s){
		this.m = m;
		this.s = s;
	}

	public T first() {
		return m;
	}

	public D second() {
		return s;
	}
}
