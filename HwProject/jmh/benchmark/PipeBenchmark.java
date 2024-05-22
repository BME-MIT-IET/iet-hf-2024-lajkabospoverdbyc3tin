package benchmark;

import org.openjdk.jmh.annotations.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import HwProject.*;

public class PipeBenchmark {

    /**
     * Pumpa -> Cső -> Pumpa -> ... -> Pumpa (100000 csővel és 100001 pumpával)
     */
    @State(Scope.Thread)
    public static class AlreadyConnectedPipes {


        @Param({"100", "1000", "100000"})
        int iterationCount;

        Game game;
        Source source;
        ArrayList<BenchmarkTestPipe> pipes = new ArrayList<BenchmarkTestPipe>();
        ArrayList<BenchmarkTestPump> pumps = new ArrayList<>();
        Drain drain;

        @Setup(Level.Trial)
        public void doBeforeBenchmark() {
            game = new Game(new PrintStream(new EmptyOutputStream()));
            drain = new Drain();
            Drain.setGame(game);

            source = new Source();

            var pump = new BenchmarkTestPump(2);
            var lastPipe = new BenchmarkTestPipe();

            pump.setWaterLevel(10);
            pump.setOutput(lastPipe);

            pipes.add(lastPipe);
            pumps.add(pump);

            for (int i = 1; i < iterationCount; i++) {
                pump = new BenchmarkTestPump(2);
                pump.connectPipe(lastPipe);
                pump.setInput(lastPipe);

                lastPipe = new BenchmarkTestPipe();
                pump.connectPipe(lastPipe);
                pump.setOutput(lastPipe);

                pumps.add(pump);
                pipes.add(lastPipe);
            }

            pump = new BenchmarkTestPump(2);
            pump.connectPipe(lastPipe);
            pump.setInput(lastPipe);
            pumps.add(pump);
        }
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Warmup(iterations = 1)
    @Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(value = 1)
    public void moveWater(AlreadyConnectedPipes acp) {

        for (int i = 0; i < acp.pipes.size(); i++) {
            acp.pumps.get(i).step();
            acp.pipes.get(i).step();
        }
    }

    @State(Scope.Thread)
    public static class PipesToConnect {

        @Param({"100", "1000", "100000"})
        int iterationCount;

        Game game;
        Source source;
        ArrayList<BenchmarkTestPipe> pipes = new ArrayList<BenchmarkTestPipe>();
        ArrayList<BenchmarkTestPump> pumps = new ArrayList<>();
        Drain drain;

        @Setup(Level.Trial)
        public void doBeforeBenchmark() {
            game = new Game(new PrintStream(new EmptyOutputStream()));
            drain = new Drain();
            Drain.setGame(game);

            source = new Source();

            for (int i = 0; i < iterationCount; i++) {
                pumps.add(new BenchmarkTestPump(2));
                pipes.add(new BenchmarkTestPipe());
            }
            pumps.add(new BenchmarkTestPump(2));




        }
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Warmup(iterations = 1)
    @Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(value = 1)
    public void connectPipes(PipesToConnect ptc) {

        BenchmarkTestPump pump = ptc.pumps.get(0);
        BenchmarkTestPipe lastPipe = ptc.pipes.get(0);

        pump.setOutput(lastPipe);

        for (int i = 1; i < ptc.iterationCount; i++) {
            pump = ptc.pumps.get(i);
            pump.connectPipe(lastPipe);
            pump.setInput(lastPipe);

            lastPipe = ptc.pipes.get(i);
            pump.connectPipe(lastPipe);
            pump.setOutput(lastPipe);
        }

        ptc.pumps.get(ptc.iterationCount).connectPipe(lastPipe);
        ptc.pumps.get(ptc.iterationCount).setInput(lastPipe);
    }
}
