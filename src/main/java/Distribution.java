public class Distribution {

    public int time;
    public double probability;
    public Distribution(int time, double probability) {
        this.time = time;
        this.probability = probability;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
