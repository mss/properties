package de.msquadrat.properties.source;

import java.io.Reader;
import java.io.StringReader;

public class PropertyStringSource extends StringReader implements
        PropertySource {

    public PropertyStringSource(String s) {
        super(s);
        
        if (s.split("=", 2).length != 2)
            throw new IllegalArgumentException("expected name=value");
    }

    @Override
    public Reader toReader() {
        return this;
    }

}
