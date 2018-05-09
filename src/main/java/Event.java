/**
 * Event of simulation
 */
public class Event {
    private final int time;
    private final int type;
    /**
       Event Constructor
        @param time
        @param type
     */
    public Event(int time, int type) {
        this.time = time;
        this.type = type;

    }

    public int getTime() {
        return time;
    }


    public int getType() {
        return type;
    }

}
