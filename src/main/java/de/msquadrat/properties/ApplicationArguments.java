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
                .action(new SimpleArgumentAction() {
                    @Override
                    public void run(String value) throws ArgumentParserException {
                        sources.add(new PropertyFileReader(value));
                    }
                })
                .help("properties file to parse");
        parser.addArgument("-D")
                .metavar("name=value")
                .action(new SimpleArgumentAction() {
                    @Override
                    public void run(String value) throws ArgumentParserException {
                        sources.add(new PropertyStringReader(value));
                    }
                })
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
    
    
    private abstract class SimpleArgumentAction implements ArgumentAction {
        private ArgumentParser parser;
        private Argument arg;
        
        @SuppressWarnings("unused")
        protected void fail(String message) throws ArgumentParserException {
            throw new ArgumentParserException(message, parser, arg);
        }
        
        public abstract void run(String value) throws ArgumentParserException;
        
        @Override
        public void run(ArgumentParser parser, Argument arg,
                Map<String, Object> attrs, String flag, Object value)
                throws ArgumentParserException {
            this.parser = parser;
            this.arg = arg;
            
            run((String)value);
        }
        
        @Override
        public boolean consumeArgument() {
            return true;
        }

        @Override
        public void onAttach(Argument arg0) {
        }
    }
}
