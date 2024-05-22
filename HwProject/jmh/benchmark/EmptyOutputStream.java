import java.io.OutputStream;
import HwProject.*;

/** Writes to nowhere. */
public class EmptyOutputStream extends OutputStream {
    @Override
    public void write(int b) {
    }
}