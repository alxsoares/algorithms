package alex.algorithms.ia.fifteenpuzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class IDAStar {

	int MaxDepth;
	int nextMaxDepth = 0;
	long visited[] = new long[50];
	int BOUND = 50;

	public void teste() {
		byte puzz1[] = { 2, 3, 4, 0, 1, 5, 7, 8, 9, 6, 10, 12, 13, 14, 11, 15 };
		byte puzz2[] = { 2, 3, 4, 8, 1, 5, 7, 12, 6, 0, 10, 15, 9, 13, 14, 11 };
		byte puzz3[] = { 1, 0, 3, 4, 10, 2, 6, 8, 5, 9, 7, 11, 13, 14, 15, 12 };
		byte puzz4[] = { 2, 4, 8, 12, 1, 3, 7, 15, 6, 13, 5, 11, 9, 0, 10, 14 };
		byte puzz5[] = { 2, 4, 8, 12, 1, 3, 7, 0, 6, 5, 10, 15, 9, 13, 14, 11 };
		byte puzz6[] = { 2, 4, 8, 12, 1, 5, 7, 15, 0, 10, 3, 11, 6, 9, 13, 14 };
		byte puzz7[] = { 13, 1, 2, 4, 5, 0, 3, 7, 9, 6, 10, 12, 15, 8, 11, 14 };
		IDA(createStartNode(puzz1));

		IDA(createStartNode(puzz2));

		IDA(createStartNode(puzz3));

		IDA(createStartNode(puzz4));

		IDA(createStartNode(puzz5));

		IDA(createStartNode(puzz6));

		IDA(createStartNode(puzz7));

		System.out.println("1000");
		for (int i = 0; i < 1000; i++) {
			byte puzz[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0 };
			shuffle(puzz);
			System.out.println("Teste:" + i);
			IDA(createStartNode(puzz));
		}

	}

	void shuffle(byte puzz[]) {
		int pos;
		int l;
		int c;
		Random rand = new Random(System.currentTimeMillis());
		int ant = -1;
		for (int i = 0; i < 45;) {
			int x = Math.abs(rand.nextInt() % 4);
			switch (x) {
			// direita
			case 0:
				if (ant != 1) {
					pos = getPosBlank(puzz);
					l = pos / 4;
					c = pos % 4;
					c++;
					if (l >= 0 && c >= 0 && l < 4 && c < 4) {
						int pos2 = 4 * l + c;
						byte aux = puzz[pos];
						puzz[pos] = puzz[pos2];
						puzz[pos2] = aux;
					}
					i++;
					ant = 0;
				}
				break;
			// esquerda
			case 1:
				if (ant != 0) {
					pos = getPosBlank(puzz);
					l = pos / 4;
					c = pos % 4;
					c--;
					if (l >= 0 && c >= 0 && l < 4 && c < 4) {
						int pos2 = 4 * l + c;
						byte aux = puzz[pos];
						puzz[pos] = puzz[pos2];
						puzz[pos2] = aux;
					}
					i++;
					ant = 1;
				}
				break;
			// baixo
			case 2:
				if (ant != 3) {
					pos = getPosBlank(puzz);
					l = pos / 4;
					c = pos % 4;
					l++;
					if (l >= 0 && c >= 0 && l < 4 && c < 4) {
						int pos2 = 4 * l + c;
						byte aux = puzz[pos];
						puzz[pos] = puzz[pos2];
						puzz[pos2] = aux;
					}
					i++;
					ant = 2;
				}
				break;
			// cima
			case 3:
				if (ant != 2) {
					pos = getPosBlank(puzz);
					l = pos / 4;
					c = pos % 4;
					l--;
					if (l >= 0 && c >= 0 && l < 4 && c < 4) {
						int pos2 = 4 * l + c;
						byte aux = puzz[pos];
						puzz[pos] = puzz[pos2];
						puzz[pos2] = aux;
					}
					i++;
					ant = 3;
				}
				break;
			}

		}

	}

	void solve(byte puzz[]) {
		Node start = createStartNode(puzz);
		IDA(start);
	}

	public void IDA(Node start) {
		MaxDepth = estimate(start.puzz);
		if (!isSolvable(start.puzz)) {
			System.out.println("This puzzle is not solvable.");
			return;
		}
		while (!solve(start, 0)) {
			MaxDepth = nextMaxDepth;
			nextMaxDepth = 0;
		}

	}

	Node createStartNode(byte puzz[]) {
		Node start = new Node();
		start.path = "";
		start.puzz = puzz;
		start.pos = getPosBlank(puzz);
		heuristica(start);
		return start;
	}

	int getPosBlank(byte puzz[]) {

		for (int i = 0; i < puzz.length; i++) {

			if (puzz[i] == 0) {

				return i;
			}
		}

		return -1;
	}

	public boolean isSolvable(byte puzz[]) {
		int inv = calculaInversoes(puzz);
		int pos = getPosBlank(puzz);
		int zero_r = pos / 4;
		if (((inv + zero_r + 1) % 2) != 0) {
			return false;
		}

		return true;
	}

	public int calculaInversoes(byte puzz[]) {
		int inv = 0;
		for (int i = 0; i < 16; i++) {
			int n = puzz[i];
			if (n != 0) {
				for (int j = i + 1; j < 16; j++) {
					int n2 = puzz[j];
					if (n2 != 0 && n > n2) {
						inv++;
					}
				}
			}
		}
		return inv;
	}

	void sort(ArrayList<Node> vet) {

		for (int i = vet.size() - 1; i > 0; i--) {

			for (int j = 0; j < i; j++) {
				Node e1 = vet.get(j);
				Node e2 = vet.get(j + 1);
				if (e1.compareTo(e2) == 1) {
					vet.set(j + 1, e1);
					vet.set(j, e2);

				}

			}

		}

	}

	boolean solve(Node node, int depth) {
		int h = estimate(node.puzz);
		if (depth + h > MaxDepth) {
			if (nextMaxDepth == 0) {
				nextMaxDepth = depth + h;
			} else {
				if (nextMaxDepth > depth + h) {
					nextMaxDepth = depth + h;
				}
			}
			return false;
		}
		if (h == 0) {
			System.out.println(node.path);
			return true;
		} else {
			visited[depth] = node.getState();
			ArrayList<Node> sucs = sucessors(node, depth);
			Collections.sort(sucs);
			for (int i = 0; i < sucs.size(); i++) {
				Node s = sucs.get(i);
				if (s != null) {
					if (solve(s, depth + 1)) {
						return true;
					}

				}

			}

			return false;
		}

	}

	/**
	 * Manhattam distance
	 * 
	 * @param puzz
	 * @return
	 */
	private int estimate(byte[] puzz) {
		int func = 0;
		for (int i = 0; i < 16; i++) {
			byte n = puzz[i];
			if (n != 0) {
				n--;
				int x1 = n / 4;
				int y1 = n % 4;
				int x2 = i / 4;
				int y2 = i % 4;
				func += Math.abs(x1 - x2) + Math.abs(y1 - y2);
			}
		}
		return func;
	}

	ArrayList<Node> sucessors(Node node, int depth) {
		depth++;

		ArrayList<Node> s = new ArrayList<Node>();
		Node rigth = getRightSucessor(node);
		prune(depth, s, rigth);
		Node left = getLeftSucessor(node);
		prune(depth, s, left);
		Node up = getUpSucessor(node);
		prune(depth, s, up);
		Node down = getDownSucessor(node);
		prune(depth, s, down);
		return s;
	}

	boolean contains(long state, int depth) {
		boolean r = false;
		for (int i = 0; i < depth; i++) {
			long s = visited[i];
			if (s==state)
				return true;
		}
		return r;
	}

	private void prune(int depth, ArrayList<Node> s, Node node) {

		if (node != null && !contains(node.getState(), depth)
				&& !(node.step > BOUND)) {
			s.add(node);
		}

	}

	Node getRightSucessor(Node n) {
		Node s = null;
		int pos = n.pos;
		int linha = pos / 4;
		int coluna = pos % 4;
		coluna++;
		if (linha >= 0 && coluna >= 0 && coluna < 4 && linha < 4) {
			s = new Node();
			int pos2 = 4 * linha + coluna;
			s.pos = pos2;
			byte puzz[] = (byte[]) n.puzz.clone();
			byte aux = puzz[pos];
			puzz[pos] = puzz[pos2];
			puzz[pos2] = aux;
			s.puzz = puzz;
			s.step = n.step + 1;
			s.path = n.path + "R";
			heuristica(s);

		}
		return s;
	}

	Node getLeftSucessor(Node n) {
		Node s = null;
		int pos = n.pos;
		int linha = pos / 4;
		int coluna = pos % 4;
		coluna--;

		if (linha >= 0 && coluna >= 0 && coluna < 4 && linha < 4) {
			s = new Node();

			int pos2 = 4 * linha + coluna;
			s.pos = pos2;
			byte puzz[] = (byte[]) n.puzz.clone();
			byte aux = puzz[pos];
			puzz[pos] = puzz[pos2];
			puzz[pos2] = aux;

			s.puzz = puzz;
			s.step = n.step + 1;
			s.path = n.path + "L";
			heuristica(s);

		}

		return s;
	}

	Node getUpSucessor(Node n) {
		Node s = null;
		int pos = n.pos;
		int linha = pos / 4;
		int coluna = pos % 4;
		linha--;
		if (linha >= 0 && coluna >= 0 && coluna < 4 && linha < 4) {
			s = new Node();
			int pos2 = 4 * linha + coluna;
			s.pos = pos2;
			byte puzz[] = (byte[]) n.puzz.clone();
			byte aux = puzz[pos];
			puzz[pos] = puzz[pos2];
			puzz[pos2] = aux;
			s.puzz = puzz;
			s.step = n.step + 1;
			s.path = n.path + "U";
			heuristica(s);

		}

		return s;
	}

	Node getDownSucessor(Node n) {
		Node s = null;
		int pos = n.pos;
		int linha = pos / 4;
		int coluna = pos % 4;
		linha++;
		if (linha >= 0 && coluna >= 0 && coluna < 4 && linha < 4) {
			s = new Node();
			int pos2 = 4 * linha + coluna;
			s.pos = pos2;
			byte puzz[] = (byte[]) n.puzz.clone();
			byte aux = puzz[pos];
			puzz[pos] = puzz[pos2];
			puzz[pos2] = aux;
			s.puzz = puzz;
			s.step = n.step + 1;
			s.path = n.path + "D";
			heuristica(s);
		}
		return s;
	}

	private void heuristica(Node s) {
		s.g = ((float) (s.step)) + (estimate(s.puzz));
	}

	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		IDAStar main = new IDAStar();

		main.teste();
		long b = System.currentTimeMillis();

		System.out.println("Tempo :" + (b - a));

	}

}

class Node implements Comparable<Node> {

	public float g;

	public int step;

	public String path = "";

	public byte puzz[];

	public int pos;

	public boolean equals(Node obj) {
		Node n = obj;

		if (n == null) {
			return true;
		}

		if (this.getState() == (n.getState())) {
			return true;
		}

		return false;
	}

	public int compareTo(Node o) {
		Node n = (Node) o;
		if (this.g > n.g) {
			return 1;
		}

		if (this.g < n.g) {
			return -1;
		}
		if (this.g == n.g) {
			if (this.step > n.step) {
				return 1;
			}
			if (this.step < n.step) {
				return -1;
			}
		}
		return 0;
	}

	public long getState() {
		long state =0;
		for(int i=0; i < puzz.length;i++){
			state =state<<4;
			state=state|puzz[i];
		}
		return state;
//		StringBuffer buff = new StringBuffer();
//		for (int i = 0; i < puzz.length; i++) {
//			buff.append(Long.toString(puzz[i], 16));
//		}
//		return buff.toString();
	}

}
