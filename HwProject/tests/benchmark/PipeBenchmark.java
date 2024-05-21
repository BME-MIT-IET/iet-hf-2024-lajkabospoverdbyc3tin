package benchmark;

import benchmark.HelperClasses.TestPipe;
import benchmark.HelperClasses.TestPump;
import org.openjdk.jmh.annotations.*;
import Game.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class PipeBenchmark {




    /**
     * Pumpa -> Cső -> Pumpa -> ... -> Pumpa (100000 csővel és 100001 pumpával)
     */
    @State(Scope.Thread)
    public static class AlreadyConnectedPipes {


        @Param({"1000", "10000", "1000000"})
        int iterationCount;

        Game game;
        Source source;
        ArrayList<TestPipe> pipes = new ArrayList<TestPipe>();
        ArrayList<TestPump> pumps = new ArrayList<>();
        Drain drain;


        @Setup(Level.Trial)
        public void doBeforeBenchmark() {
            game = new Game(new PrintStream(new EmptyOutputStream()));
            drain = new Drain();
            Drain.setGame(game);

            source = new Source();

            var pump = new TestPump(2);
            var lastPipe = new TestPipe();

            pump.setWaterLevel(10);
            pump.setOutput(lastPipe);

            pipes.add(lastPipe);
            pumps.add(pump);

            for (int i = 1; i < iterationCount; i++) {
                pump = new TestPump(2);
                pump.connectPipe(lastPipe);
                pump.setInput(lastPipe);

                lastPipe = new TestPipe();
                pump.connectPipe(lastPipe);
                pump.setOutput(lastPipe);

                pumps.add(pump);
                pipes.add(lastPipe);

            }

            pump = new TestPump(2);
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

        @Param({"1000", "10000", "1000000"})
        int iterationCount;

        Game game;
        Source source;
        ArrayList<TestPipe> pipes = new ArrayList<TestPipe>();
        ArrayList<TestPump> pumps = new ArrayList<>();
        Drain drain;


        @Setup(Level.Trial)
        public void doBeforeBenchmark() {
            game = new Game(new PrintStream(new EmptyOutputStream()));
            drain = new Drain();
            Drain.setGame(game);

            source = new Source();


            for (int i = 0; i < iterationCount; i++) {
                pumps.add(new TestPump(2));
                pipes.add(new TestPipe());
            }
            pumps.add(new TestPump(2));




        }

    }


    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Warmup(iterations = 1)
    @Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(value = 1)
    public void connectPipes(PipesToConnect ptc) {

        TestPump pump = ptc.pumps.get(0);
        TestPipe lastPipe = ptc.pipes.get(0);

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
