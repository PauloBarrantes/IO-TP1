/*



 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;


class Simulator {
    /* Simulation variables */
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
    private PriorityQueue<Event> tableOfEvents = new PriorityQueue<Event>(100,comparator);

    //State variables
    private int busy;
    private int queueLength;
    private static final int servers = 2;
    //Statistical variables
    private int clientsServed;



    //Other variables
    private Random rnd = new Random(System.currentTimeMillis());
    private ArrayList<Distribution> a;
    private ArrayList<Distribution> d;

    private enum Distribution2{
        A (1,0.40),
        B (2,0.35),
        C (3,0.25);

        private final int time;
        private final double probability;
        Distribution2(int time, double probability){
            this.time = time;
            this.probability = probability;
        }

        public int getTime() {
            return time;
        }

        public double getProbability() {
            return probability;
        }
    }
    //Constructor
    Simulator(ArrayList<Distribution> a, ArrayList<Distribution> d) {
        this.a = a;
        this.d = d;
        this.clock = 0;
        this.busy = 0;

        this.queueLength =0;
        this.clientsServed =0;

    }

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
                this.processArrive();
            }else{
               this.processDepart();
            }
             //Move the clock

            assert tableOfEvents.peek() != null;
            clock = tableOfEvents.peek().getTime();

        }

        timeAvg = (float)clock/clientsServed;
        System.out.printf("%-20s%-20s%-20s%-20s%-20s-\n",id,clock,queueLength,clientsServed,timeAvg);



    }
    private void processArrive(){
        if(busy < servers){
            busy++;
            ++clientsServed;
            generateDeparture();
        }else{
            queueLength++;
        }
        generateArrival();
    }
    private void processDepart(){
        if(queueLength > 0 ){
            -- queueLength;
            ++ clientsServed;
            generateDeparture();
        }else{
                --busy;
        }
    }

    private void generateDeparture(){
        int i = fGrand(d);
        Event depart = new Event(d.get(i).getTime()+this.clock, 1); //1 --> Depart
        tableOfEvents.add(depart);
    }

    private void generateArrival(){
        int i = fGrand(a);
        Event depart = new Event(a.get(i).getTime() + this.clock, 0); //1 --> Depart
        tableOfEvents.add(depart);
    }
    private int fGrand(ArrayList<Distribution> c){
        int counter = 0;
        boolean found = false;
        double bigF = 0;
        double randomNumber = rnd.nextDouble();
        while (counter < c.size() && !found){
            bigF += c.get(counter).getProbability() ;
            if(randomNumber < bigF){
                found = true;
            }else{
                ++counter;
            }

        }
        return  counter;
    }


}
