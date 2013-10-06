package de.msquadrat.properties.source;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PropertyFileReader extends InputStreamReader implements PropertyReader {

    public PropertyFileReader(String filename) {
        super(new LazyFileInputStream(filename), StandardCharsets.ISO_8859_1);
    }
}
