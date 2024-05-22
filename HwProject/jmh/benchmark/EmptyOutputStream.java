package benchmark;

import java.io.OutputStream;

/** Writes to nowhere. */
public class EmptyOutputStream extends OutputStream {
    @Override
    public void write(int b) {
    }
}