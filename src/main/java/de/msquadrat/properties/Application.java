package de.msquadrat.properties;

import java.io.IOException;
import java.util.Properties;

import de.msquadrat.properties.source.PropertySource;
import de.msquadrat.properties.source.PropertySources;

public class Application implements Runnable {
    public static void main(String[] args) throws Exception {
        new Application(args).run();
    }
    
    public static String getName() {
        return "properties";
    }
    
    
    private final Properties props;
    private final ApplicationArguments args;
    
    protected Application(String[] args) throws Exception {
        this.args = new ApplicationArguments(args);
        this.props = new Properties();
    }

    public void run() {
        PropertySources sources = args.getSources();
        while (sources.peek() != null) {
            try (PropertySource source = sources.poll()) {
                props.load(source.toReader());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        
        System.err.println(props);
    }
    
    
}
