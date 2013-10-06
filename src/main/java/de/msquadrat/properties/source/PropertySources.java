package de.msquadrat.properties.source;

import java.util.LinkedList;

public class PropertySources extends LinkedList<PropertyReader> {

    private static final long serialVersionUID = 1L;

    public PropertySources() {
        super();
    }
    
    public void addFile(String filename) {
        add(new PropertyFileReader(filename));
    }
    
    public void addString(String value) {
        add(new PropertyStringReader(value));
    }
}
