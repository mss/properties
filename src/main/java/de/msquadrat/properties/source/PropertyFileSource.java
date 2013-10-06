package de.msquadrat.properties.source;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class PropertyFileSource extends InputStreamReader
        implements PropertySource {

    public PropertyFileSource(String filename) {
        super(new LazyFileInputStream(filename), StandardCharsets.ISO_8859_1);
    }

    @Override
    public Reader toReader() {
        return this;
    }
}
