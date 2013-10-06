package de.msquadrat.properties.source;

import java.io.Closeable;
import java.io.Reader;

public interface PropertySource extends Readable, Closeable, AutoCloseable {
    public Reader toReader();
}
