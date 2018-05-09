/**
 * Tuple or Row of the Tables of Distribution
 */

public class Tuple {

    private final int time;
    private final double probability;

    /**
     * Tuple Constructor
     * @param time
     * @param probability
     */
    Tuple(int time, double probability) {
        this.time = time;
        this.probability = probability;
    }

    int getTime() {
        return time;
    }
    double getProbability() {
        return probability;
    }

}
