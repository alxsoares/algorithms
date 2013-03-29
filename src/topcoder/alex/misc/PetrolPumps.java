package topcoder.alex.misc;

/**
 * 
 Find the first circular tour that visits all petrol pumps 
 * 
 * Suppose there is a circle. There are n petrol pumps on that circle. You are
 * given two sets of data.
 * 
 * 1. The amount of petrol that petrol pump will give. 2. Distance from that
 * petrol pump to the next petrol pump.
 * 
 * Calculate the first point from where a truck will be able to complete the
 * circle (The truck will stop at each petrol pump and it has infinite
 * capacity). Expected time complexity is O(n). Assume for 1 litre petrol, the
 * truck can go 1 unit of distance.
 * 
 * For example, let there be 4 petrol pumps with amount of petrol and distance
 * to next petrol pump value pairs as {4, 6}, {6, 5}, {7, 3} and {4, 5}. The
 * first point from where truck can make a circular tour is 2nd petrol pump.
 * Output should be “start = 1″ (index of 2nd petrol pump).
 * 
 */
public class PetrolPumps {

	public static int printTour(PetrolPump[] pumps) {
		int start = 0;
		int end = 1;
		int currPetrol = pumps[start].petrol - pumps[start].distance;
		while (start != end || currPetrol < 0) {
			while (start != end && currPetrol < 0) {
				currPetrol -= pumps[start].petrol - pumps[start].distance;
				start = (start + 1) % pumps.length;
				if (start == 0)
					return -1;
			}
			currPetrol += pumps[end].petrol - pumps[end].distance;
			end = (end + 1) % pumps.length;
		}
		return start;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PetrolPump[] pumps = PetrolPump.build(new int[] { 6, 3, 7 }, new int[] {
				4, 6, 3 });
		System.out.printf("Start at %d\n", printTour(pumps));
	}

}

class PetrolPump {
	int petrol;
	int distance;

	public PetrolPump(int i, int j) {
		this.petrol = i;
		this.distance = j;
	}

	public static PetrolPump[] build(int petrol[], int distance[]) {
		PetrolPump[] r = new PetrolPump[petrol.length];
		for (int i = 0; i < petrol.length; i++) {
			PetrolPump p = new PetrolPump(petrol[i], distance[i]);
			r[i] = p;
		}
		return r;
	}
}