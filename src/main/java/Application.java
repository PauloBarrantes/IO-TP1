import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    private FilesReader filesReader;

    private static final String fileNameA ="/Users/paulobarrantes/Proyectos/IO-TP1/src/main/java/files/arrivals.txt" ;
    private static final String fileNameD = "/Users/paulobarrantes/Proyectos/IO-TP1/src/main/java/files/departures.txt";

    private Application() {

    }

    private void run(){
        filesReader = new FilesReader();
        String n;
        int iterations=0;
        Scanner scanner = new Scanner (System.in); //Creación de un objeto Scanner

        ArrayList<Distribution> a =  filesReader.readDistributions(fileNameA);
        ArrayList<Distribution> d =  filesReader.readDistributions(fileNameD);



        System.out.println("Digite el número de veces que desea simular el sistema:  ");
        n = scanner.nextLine();
        iterations = Integer.parseInt(n);
        System.out.printf("%-20s%-20s%-20s%-20s%-20s-\n","#","Clock","QueueLength","ClientsServed","AVG");
        for(int i=0; i<iterations;++i){
            Simulator simulator = new Simulator(a,d);
            simulator.simulate(i+1);
        }


    }

    public static void main(String[] args){

        Application application = new Application();
        application.run();
    }
}
