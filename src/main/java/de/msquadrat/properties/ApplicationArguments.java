package de.msquadrat.properties;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.Argument;
import net.sourceforge.argparse4j.inf.ArgumentAction;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class ApplicationArguments {
    private final List<String> filenames;
    
    
    public ApplicationArguments(String[] args) {
        ArgumentParser parser = ArgumentParsers.newArgumentParser(
                Application.getName())
                .defaultHelp(true)
                .description("Read *.properties files from the command line.");
        parser.addArgument("filename")
                .metavar("filename...")
                .action(Arguments.append())
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
        
        this.filenames = Collections.unmodifiableList(opts.<String>getList("filename"));
    }
    
    
    public List<String> getFilenames() {
        return filenames;
    }
    
    
    private class SystemPropertyArgumentAction implements ArgumentAction {

        public boolean consumeArgument() {
            return true;
        }

        public void onAttach(Argument arg0) {
        }

        public void run(ArgumentParser parser, Argument arg,
                Map<String, Object> attrs, String flag, Object value)
                throws ArgumentParserException {
            run(parser, arg, attrs, flag, (String)value);
        }
        
        public void run(ArgumentParser parser, Argument arg,
                Map<String, Object> attrs, String flag, String value)
                throws ArgumentParserException {
            String kv[] = value.split("=", 2);
            if (kv.length != 2)
                throw new ArgumentParserException("expected name=value", parser, arg);
            System.setProperty(kv[0], kv[1]);
        }
        
    }

}
