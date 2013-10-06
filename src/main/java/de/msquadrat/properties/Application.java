package de.msquadrat.properties;

public class Application implements Runnable {
    public static void main(String[] args) throws Exception {
        new Application(args).run();
    }
    
    public static String getName() {
        return "properties";
    }
    
    
    private final ApplicationArguments args;
    
    protected Application(String[] args) throws Exception {
        this.args = new ApplicationArguments(args);
    }

    public void run() {
        
    }
    
    
}
