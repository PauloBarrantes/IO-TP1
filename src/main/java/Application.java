import java.util.ArrayList;

public class Application {
    public Simulator simulator;
    public Files files;
    private static final String fileNameA ="/Users/paulobarrantes/Proyectos/IO-TP1/src/main/java/files/arrivals.txt" ;
    private static final String fileNameD = "/Users/paulobarrantes/Proyectos/IO-TP1/src/main/java/files/departures.txt";
    private Application() {

    }

    private void run(){
        files = new Files();
        ArrayList<Distribution> a =  files.readDistributions(fileNameA);
        ArrayList<Distribution> d =  files.readDistributions(fileNameD);
        simulator = new Simulator(a,d);

        simulator.simulate();
    }

    public static void main(String[] args){

        Application application = new Application();
        application.run();
    }
}
