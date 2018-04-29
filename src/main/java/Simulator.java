/*



 */
import java.util.Comparator;
import java.util.PriorityQueue;


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
    private int servers;

    //statistical variables
    private int clientsServed;
    //Constructor
    public Simulator() {
        this.setClock(0);
        this.setBusy(0);
        this.setQueueLength(0);
        this.setClientsServed(0);
        this.setServers(2);
    }

    //Getter and Setter
    int getClock() {
        return clock;
    }

    private void setClock(int clock) {
        this.clock = clock;
    }

    public int getBusy() {
        return busy;
    }

    public void setBusy(int busy) {
        this.busy = busy;
    }
    public void incrementBusy(){
        this.busy++;
    }
    public void decrementBusy(){
        this.busy--;
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

    public int getServers() {
        return servers;
    }

    public void setServers(int servers) {
        this.servers = servers;
    }
    //Simulating process

    public void simulate(){

        Event initialArrive = new Event(0,0);
        tableOfEvents.add(initialArrive);
        Event actualEvent;
        while(getClientsServed() > 15){
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

           setClock(3);
        }

    }
    public void processArrive(){
        if(getClientsServed()< getServers()){
            busy++;
            clientsServed++;
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

    }
    public void generateArrival(){

    }


}
