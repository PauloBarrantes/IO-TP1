/**
 * Class that executes a simulation of a call center.

 */

import java.util.*;

class Simulator {
    /* Simulation variables */
    /**
     * Represents the number of server available
     */
    private static final int servers = 2;
    /**
     * Represents the simulation clock
     */
    private int clock;

    private Comparator<Event> comparator = new Comparator<Event>() {
        public int compare(Event o1, Event o2) {
            int cmp = 0;
            if(o1.getType()!= o2.getType() && o1.getTime() == o2.getTime()){
                if(o1.getType() == 1){
                    cmp = -1;
                }else{ // o2 type-> 1 depart
                    cmp = 1;
                }

            }else{
                return o1.getTime()-o2.getTime();

            }

            return cmp;

        }
    };
    /** Represents an event priority queue.*/
    private PriorityQueue<Event> tableOfEvents = new PriorityQueue<Event>(100,comparator);
    /** Represents the status of the servers.*/
    private int busy;
    /** Queue length. */
    private int queueLength;
    /** Number of clients that have been served.  */

    private int clientsServed;
    /**Variable to generate random numbers. */
    private Random rnd = new Random(System.currentTimeMillis());
    /**Represents the table of time between calls*/
    private ArrayList<Tuple> timeBetweenCalls;
    /**Represents the table of call duration*/
    private ArrayList<Tuple> callDuration;

    /**Class constructor.
     * @param timeBetweenCalls Time Between Calls Table.
     * @param callDuration Call Duration Table.
     */
    Simulator(ArrayList<Tuple> timeBetweenCalls, ArrayList<Tuple> callDuration) {
        this.timeBetweenCalls = timeBetweenCalls;
        this.callDuration = callDuration;
        this.clock = 0;
        this.busy = 0;
        this.queueLength =0;
        this.clientsServed =0;

    }
    /** Start a simulation process.
     * @param id The id of simulation.
     */
    void simulate(int id){
        float timeAvg;
        Event initialArrive = new Event(0,0);
        tableOfEvents.add(initialArrive);
        Event actualEvent;
        while(clientsServed < 5000){
            //Get next event
            actualEvent =  tableOfEvents.poll();

            //Process that event
            assert actualEvent != null;
            if(actualEvent.getType()==0){
                this.processArrival();
            }else{
               this.processDeparture();
            }
             //Move the clock

            assert tableOfEvents.peek() != null;
            clock = tableOfEvents.peek().getTime();

        }

        timeAvg = (float)clock/clientsServed;
        System.out.printf("%-20s%-20s%-20s%-20s%-20s-\n",id,clock,queueLength,clientsServed,timeAvg);
    }
    /** Process an arrival
     *
     */
    private void processArrival(){
        if(busy < servers){
            busy++;
            ++clientsServed;
            generateDeparture();
        }else{
            queueLength++;
        }
        generateArrival();
    }
    /** Process a Departure
     *
     */
    private void processDeparture(){
        if(queueLength > 0 ){
            -- queueLength;
            ++ clientsServed;
            generateDeparture();
        }else{
                --busy;
        }
    }
    /** Generate a new departure
     */
    private void generateDeparture(){
        int i = cumulativeDistributionFunction(callDuration);
        Event depart = new Event(callDuration.get(i).getTime()+this.clock, 1); //1 --> Depart
        tableOfEvents.add(depart);
    }
    /** Generate a new arrival
     */
    private void generateArrival(){
        int i = cumulativeDistributionFunction(timeBetweenCalls);
        Event depart = new Event(timeBetweenCalls.get(i).getTime() + this.clock, 0); //1 --> Depart
        tableOfEvents.add(depart);
    }
    /** CumulativeDistributionFunction
     * @param table distribution
     * @return an integer that represents an index of an array
     */
    private int cumulativeDistributionFunction(ArrayList<Tuple> table){

        int counter = 0;
        boolean found = false;
        double bigF = 0;
        double randomNumber = rnd.nextDouble();
        while (counter < table.size() && !found){
            bigF += table.get(counter).getProbability() ;
            if(randomNumber < bigF){
                found = true;
            }else{
                ++counter;
            }
        }

        return  counter;
    }


}
