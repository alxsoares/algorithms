package topcoder.alex;

public class CubeWalking {

	enum Color  { RED , GREEN, BLUE }
	
	static final Color cubeFace[][] = { //
			{ Color.RED, Color.BLUE, Color.RED },//
			{ Color.BLUE,Color.GREEN,Color.BLUE },//
			{ Color.RED, Color.BLUE, Color.RED } //
	};
	enum Direction {
		LESTE,NORTE,OESTE,SUL;
		public static int X(Direction d){
			switch (d) {
			case LESTE:
				return 1;
			case NORTE:
				return 0;
			case OESTE:
				return -1;
			case SUL:
				return 0;
			default:
				throw new RuntimeException("Direction unknown");
			}
		}
		public static int Y(Direction d){
			switch (d) {
			case LESTE:
				return 0;
			case NORTE:
				return -1;
			case OESTE:
				return 0;
			case SUL:
				return 1;
			default:
				throw new RuntimeException("Direction unknown");
			}
		}
		public static Direction nextDirectionLeft(final Direction d){
			switch (d) {
			case LESTE:
				return NORTE;
			case NORTE:
				return OESTE;
			case OESTE:
				return SUL;
			case SUL:
				return LESTE;
			default:
				throw new RuntimeException("Direction unknown");
			}
		}
		
		public static Direction nextDirectionRight(final Direction d){
			switch (d) {
			case LESTE:
				return SUL;
			case SUL:
				return OESTE;
			case OESTE:
				return NORTE;
			case NORTE:
				return LESTE;
			default:
				throw new RuntimeException("Direction unknown");
			}
		}
		
	};
	
	private static int nextPosition(int x, int dx){
		int nx = x+dx;
		if(nx >= 0) return nx%3;
		return nx=nx+3;
	}
	public static String finalPosition(String movement){
		Direction direction = Direction.NORTE;
		int x=1,y=1;
		int dX = Direction.X(direction);
		int dY = Direction.Y(direction);
		char[] m = movement.toCharArray();
		for (int i = 0; i < m.length; i++) {
			char c = m[i];
			if(c=='L'){
				direction = Direction.nextDirectionLeft(direction);
			}else if(c=='R'){
				direction = Direction.nextDirectionRight(direction);
			}else if(c=='W'){
				dX=Direction.X(direction);
				dY = Direction.Y(direction);
				x = nextPosition(x, dX);
				y = nextPosition(y, dY);
			}
		}
		return cubeFace[x][y].name();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(finalPosition("LLRR"));
		System.out.println(finalPosition("WLWRW"));
	}

}
