package de.msquadrat.properties.reader;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PropertyFileReader extends InputStreamReader implements PropertyReader {

    public PropertyFileReader(String filename) throws FileNotFoundException {
        super(new LazyFileInputStream(filename), StandardCharsets.ISO_8859_1);
    }
}
