package benchmark;

import benchmark.HelperClasses.TestPipe;
import benchmark.HelperClasses.TestPump;
import org.openjdk.jmh.annotations.*;
import Game.*;

import java.io.PrintStream;
import java.util.ArrayList;


public class PipeBenchmark {


    /**
     * Pumpa -> Cső -> Pumpa -> ... -> Pumpa (100000 csővel és 100001 pumpával)
     */
    @State(Scope.Thread)
    public static class AlreadyConnectedPipes {



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

            for (int i = 0; i < 100000; i++) {
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

        @TearDown
        public void afterBenchmark() {
            System.out.println("Utolsó pumpa waterlevel: " + pumps.get(pumps.size()-1).getWaterLevel());
        }

    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput, Mode.SampleTime})
    @Warmup(iterations = 1)
    @Measurement(iterations = 3)
    @Fork(value = 1)
    public void moveWater(AlreadyConnectedPipes acp) {

        for (int i = 0; i < acp.pipes.size(); i++) {
            acp.pumps.get(i).step();
            acp.pipes.get(i).step();
        }

    }


    @State(Scope.Thread)
    public static class PipesToConnect {


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


            for (int i = 0; i < 100000; i++) {
                pumps.add(new TestPump(2));
                pipes.add(new TestPipe());
            }
            pumps.add(new TestPump(2));




        }

    }


    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput, Mode.SampleTime})
    @Warmup(iterations = 1)
    @Measurement(iterations = 3)
    @Fork(value = 1)
    public void connectPipes(PipesToConnect ptc) {

        TestPump pump = ptc.pumps.get(0);
        TestPipe lastPipe = ptc.pipes.get(0);

        pump.setOutput(lastPipe);

        for (int i = 0; i < 100000; i++) {
            pump = ptc.pumps.get(i);
            pump.connectPipe(lastPipe);
            pump.setInput(lastPipe);

            lastPipe = ptc.pipes.get(i);
            pump.connectPipe(lastPipe);
            pump.setOutput(lastPipe);

        }

        ptc.pumps.get(100000).connectPipe(lastPipe);
        ptc.pumps.get(100000).setInput(lastPipe);

    }


}
