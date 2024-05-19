package Benchmarks;

import org.openjdk.jmh.annotations.*;
import src.*;
import tests.*;
import java.io.OutputStream;
import java.io.PrintStream;


public class DrainBenchmark {


    @State(Scope.Thread)
    public static class GameBenchmarkState {

        Game game;
        Source source;
        TestPipe pipe;
        Drain drain;

        /** Writes to nowhere. */
        public static class EmptyOutputStream extends OutputStream {
            @Override
            public void write(int b) {}
        }

        @Setup(Level.Trial)
        public void doBeforeEachBenchmark() {
            game = new Game(new PrintStream(new EmptyOutputStream()));
            Drain.SetGame(game);

            source = new Source();
            pipe = new TestPipe();
            drain = new Drain();

            source.ConnectPipe(pipe);
            drain.ConnectPipe(pipe);
        }

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void pullWater(GameBenchmarkState gbs) {

        gbs.source.Step();
        gbs.pipe.Step();
        gbs.drain.Step();
    }
}
