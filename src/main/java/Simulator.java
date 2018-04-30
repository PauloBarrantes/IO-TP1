/*



 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.ArrayList;
public class Simulator {
    // Simulation variables
    private int clock;
    private Comparator<Event> comparator = new Comparator<Event>() {
        public int compare(Event o1, Event o2) {
            return o1.getTime()-o2.getTime();
        }
    };
    private PriorityQueue<Event> tableOfEvents = new PriorityQueue<Event>(100,comparator);

    //State variables
    private int busy;
    private int queueLength;
    private static final int servers = 2;
    //statistical variables
    private int clientsServed;

    //Other variables
    public Random rnd = new Random(System.currentTimeMillis());
    double randomNumber;
    public ArrayList<Distribution> a;
    public ArrayList<Distribution> d;


    //Constructor
    public Simulator(ArrayList<Distribution> a, ArrayList<Distribution> d) {
        this.a = a;
        this.d = d;
        this.setClock(0);
        this.setBusy(0);
        this.setQueueLength(0);
        this.setClientsServed(0);
    }

    //Getter and Setter
    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public int getBusy() {
        return busy;
    }

    public void setBusy(int busy) {
        this.busy = busy;
    }

    public int getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(int queueLength) {
        this.queueLength = queueLength;
    }

    public int getClientsServed() {
        return clientsServed;
    }

    public void setClientsServed(int clientsServed) {
        this.clientsServed = clientsServed;
    }


    //Simulating process

    public void simulate(){

        Event initialArrive = new Event(0,0);
        tableOfEvents.add(initialArrive);
        Event actualEvent;
        while(getClientsServed() < 15){
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

           setClock(actualEvent.getTime());
            //Preguntar como se mueve el reloj;

           System.out.println("Clock: " + clock );
           System.out.println("QueueLength: " +queueLength);
           System.out.println("ClientsServed: " + clientsServed);
           System.out.println("Busy: " + busy);
        }


    }
    public void processArrive(){
        if(busy < servers){
            busy++;
            ++clientsServed;
            generateDeparture();
        }else{
            queueLength++;
        }
        generateArrival();
    }
    public void processDepart(){
        if(queueLength > 0 ){
            -- queueLength;
            ++ clientsServed;
            generateDeparture();
        }else{
                --busy;
        }
    }

    public void generateDeparture(){
        randomNumber = rnd.nextDouble();

        int size = d.size();
        int counter = 0;
        double fGrande = 0.0;
        boolean found = false;
        while (counter < size && !found){
            fGrande += d.get(counter).probability ;
            if(randomNumber < fGrande){
                found = true;
            }else{
                ++counter;
            }

        }

        Event depart = new Event(d.get(counter).time+this.clock, 1); //1 --> Depart

        tableOfEvents.add(depart);
    }
    public void generateArrival(){
        int size = a.size();
        int counter = 0;
        double fGrande = 0.0;
        boolean found = false;
        while (counter < size && !found){
            fGrande += a.get(counter).probability ;
            if(randomNumber < fGrande){
                found = true;
            }else{
                ++counter;
            }

        }


        Event depart = new Event(a.get(counter).time + this.clock, 0); //1 --> Depart

        tableOfEvents.add(depart);

    }


}
