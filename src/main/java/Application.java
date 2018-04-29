public class Application {
    public Simulator simulator;
    private Application() {
        System.out.print(simulator.getClock());
    }

    private void run(){
        simulator.simulate();
    }

    public static void main(String[] args){
        Application application = new Application();
        application.run();
    }
}
