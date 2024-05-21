package benchmark;

import benchmark.HelperClasses.TestPipe;
import org.openjdk.jmh.annotations.*;
import Game.*;
import java.io.PrintStream;


public class DrainBenchmark {


    @State(Scope.Thread)
    public static class GameBenchmarkState {

        Game game;
        Source source;
        TestPipe pipe;
        Drain drain;


        @Setup(Level.Trial)
        public void doBeforeEachBenchmark() {
            game = new Game(new PrintStream(new EmptyOutputStream()));
            Drain.setGame(game);

            source = new Source();
            pipe = new TestPipe();
            drain = new Drain();

            source.connectPipe(pipe);
            drain.connectPipe(pipe);
        }

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void simpleDrain(GameBenchmarkState gbs) {

        gbs.source.step();
        gbs.pipe.step();
        gbs.drain.step();
    }
}



