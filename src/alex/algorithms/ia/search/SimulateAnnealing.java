package alex.algorithms.ia.search;

public class SimulateAnnealing {

    public static final int CITY_COUNT = 50;
    public static final double START_TEMPERATURE = 10;
    public static final double TEMPERATURE_DELTA = 0.99;

    protected double temperature;
    protected double pathlength;
    protected double minimallength;
    protected int order[];
    protected int minimalorder[];
    private final City[] cities = new City[CITY_COUNT];

    /**
     * Constructor
     * 
     * @param owner
     *            The TravelingSalesman class that owns this object.
     */
    public SimulateAnnealing() {
        order = new int[CITY_COUNT];
        minimalorder = new int[CITY_COUNT];
    }

    /**
     * Called to determine if annealing should take place.
     * 
     * @param d
     *            The distance.
     * @return True if annealing should take place.
     */
    public boolean anneal(final double d) {
        if (temperature < 1.0E-4) {
            if (d > 0.0)
                return true;
            else
                return false;
        }
        if (Math.random() < Math.exp(d / temperature))
            return true;
        else
            return false;
    }

    /**
     * Used to ensure that the passed in integer is within thr city range.
     * 
     * @param i
     *            A city index.
     * @return A city index that will be less than CITY_COUNT
     */
    public int mod(final int i) {
        return i % CITY_COUNT;
    }

    /**
     * Called to get the distance between two cities.
     * 
     * @param i
     *            The first city
     * @param j
     *            The second city
     * @return The distance between the two cities.
     */
    public double distance(final int i, final int j) {
        int c1 = order[i % CITY_COUNT];
        int c2 = order[j % CITY_COUNT];
        return cities[c1].proximity(cities[c2]);
    }

    /**
     * Run as a background thread. This method is called to perform the
     * simulated annealing.
     */
    public void run() {
        int cycle = 1;
        int sameCount = 0;
        temperature = START_TEMPERATURE;

        initorder(order);
        initorder(minimalorder);

        pathlength = length();
        minimallength = pathlength;

        while (sameCount < 50) {
            // update the screen

            // make adjustments to city order(annealing)
            for (int j2 = 0; j2 < CITY_COUNT * CITY_COUNT; j2++) {
                int i1 = (int) Math.floor(CITY_COUNT * Math.random());
                int j1 = (int) Math.floor(CITY_COUNT * Math.random());
                double d = distance(i1, i1 + 1) + distance(j1, j1 + 1) - distance(i1, j1) - distance(i1 + 1, j1 + 1);
                if (anneal(d)) {
                    if (j1 < i1) {
                        int k1 = i1;
                        i1 = j1;
                        j1 = k1;
                    }
                    for (; j1 > i1; j1--) {
                        int i2 = order[i1 + 1];
                        order[i1 + 1] = order[j1];
                        order[j1] = i2;
                        i1++;
                    }
                }
            }

            // See if this improved anything
            pathlength = length();
            if (pathlength < minimallength) {
                minimallength = pathlength;
                for (int k2 = 0; k2 < CITY_COUNT; k2++)
                    minimalorder[k2] = order[k2];
                sameCount = 0;
            } else
                sameCount++;
            temperature = TEMPERATURE_DELTA * temperature;
            cycle++;
        }

    }

    /**
     * Return the length of the current path through the cities.
     * 
     * @return The length of the current path through the cities.
     */
    public double length() {
        double d = 0.0;
        for (int i = 1; i <= CITY_COUNT; i++)
            d += distance(i, i - 1);
        return d;
    }

    /**
     * Set the specified array to have a list of the cities in order.
     * 
     * @param an
     *            An array to hold the cities.
     */
    public void initorder(final int an[]) {
        for (int i = 0; i < CITY_COUNT; i++)
            an[i] = i;
    }
}

class City {

    private final int height;
    private final int width;

    public City(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    public double proximity(final City city) {
        int dw = city.width - this.width;
        int dh = city.height - this.height;
        return Math.sqrt(dw * dw + dh * dh);
    }

}
