package de.msquadrat.properties.source;

import java.io.StringReader;

public class PropertyStringReader extends StringReader implements
        PropertyReader {

    public PropertyStringReader(String s) {
        super(s);
        
        if (s.split("=", 2).length != 2)
            throw new IllegalArgumentException("expected name=value");
    }

}
