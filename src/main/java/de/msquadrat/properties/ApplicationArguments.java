package de.msquadrat.properties;

import java.util.Map;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.Argument;
import net.sourceforge.argparse4j.inf.ArgumentAction;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class ApplicationArguments {

    
    
    public ApplicationArguments(String[] args) {
        ArgumentParser parser = ArgumentParsers.newArgumentParser(
                Application.getName())
                .defaultHelp(true)
                .description("Read *.properties files from the command line.");
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
