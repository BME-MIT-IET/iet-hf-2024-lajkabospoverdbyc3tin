package benchmark;

import org.openjdk.jmh.annotations.*;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import HwProject.*;


public class DrainBenchmark {


    @State(Scope.Thread)
    public static class GameBenchmarkState {

        HwProject.Game game;
        Source source;
        BenchmarkTestPipe pipe;
        Drain drain;


        @Setup(Level.Trial)
        public void doBeforeEachBenchmark() {
            game = new Game(new PrintStream(new EmptyOutputStream()));
            Drain.setGame(game);

            source = new Source();
            pipe = new BenchmarkTestPipe();
            drain = new Drain();

            source.connectPipe(pipe);
            drain.connectPipe(pipe);
        }

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(value = 1)
    public void simpleDrain(GameBenchmarkState gbs) {

        gbs.source.step();
        gbs.pipe.step();
        gbs.drain.step();
    }
}



