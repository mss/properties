package de.msquadrat.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import de.msquadrat.properties.reader.PropertyFileReader;
import de.msquadrat.properties.reader.PropertyReader;
import de.msquadrat.properties.reader.PropertyStringReader;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.Argument;
import net.sourceforge.argparse4j.inf.ArgumentAction;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class ApplicationArguments {
    private final List<PropertyReader> sources;
    
    
    public ApplicationArguments(String[] args) {
        sources = new ArrayList<>();
        
        ArgumentParser parser = ArgumentParsers.newArgumentParser(
                Application.getName())
                .defaultHelp(true)
                .description("Read *.properties files from the command line.");
        parser.addArgument("filename")
                .metavar("filename...")
                .action(new FilenameArgumentAction())
                .help("properties file to parse");
        parser.addArgument("-D")
                .metavar("name=value")
                .action(new SystemPropertyArgumentAction())
                .help("set a system property");
        
        Namespace opts = null;
        try {
            opts = parser.parseArgs(args);
        }
        catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }
    }
    
    
    public List<PropertyReader> getSources() {
        return Collections.unmodifiableList(sources);
    }
    
    
    private class FilenameArgumentAction implements ArgumentAction {

        @Override
        public boolean consumeArgument() {
            return true;
        }

        @Override
        public void onAttach(Argument arg0) {
        }

        @Override
        public void run(ArgumentParser parser, Argument arg,
                Map<String, Object> attrs, String flag, Object value)
                throws ArgumentParserException {
            ApplicationArguments.this.sources.add(new PropertyFileReader((String)value));
        }
        
    }
    
    private class SystemPropertyArgumentAction implements ArgumentAction {

        @Override
        public boolean consumeArgument() {
            return true;
        }

        @Override
        public void onAttach(Argument arg0) {
        }

        @Override
        public void run(ArgumentParser parser, Argument arg,
                Map<String, Object> attrs, String flag, Object value)
                throws ArgumentParserException {
            ApplicationArguments.this.sources.add(new PropertyStringReader((String)value));
        }
        
    }

}
