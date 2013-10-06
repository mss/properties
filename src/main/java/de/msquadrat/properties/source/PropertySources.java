package de.msquadrat.properties.source;

import java.util.LinkedList;

public class PropertySources extends LinkedList<PropertySource> {

    private static final long serialVersionUID = 1L;

    public PropertySources() {
        super();
    }
    
    public void addFile(String filename) {
        add(new PropertyFileSource(filename));
    }
    
    public void addString(String value) {
        add(new PropertyStringSource(value));
    }
}
