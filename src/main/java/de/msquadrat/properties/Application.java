package de.msquadrat.properties;

public class Application {
    public static void main(String[] args) throws Exception {
        new Application(args);
    }
    
    
    private final ApplicationArguments args;
    
    protected Application(String[] args) throws Exception {
        this.args = new ApplicationArguments(args);
    }
    
    public static String getName() {
        return "properties";
    }
}
