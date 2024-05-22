package benchmark;

import HwProject.*;

/**
 *     Pump subclass for testing, only added setters and getters for each private and protected field
 */
public class BenchmarkTestPump extends Pump {
    public BenchmarkTestPump(int maxConnectedPipes) {
        super(maxConnectedPipes);
    }

    public int getInstanceOfPump() {
        return instanceNr;
    }

    public static void setInstanceNr(int i) {
        instanceNr = i;
    }
    public void setWaterLevel(int i) {
        waterLevel = i;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public int getMaxWaterLevel() {
        return maxWaterLevel;
    }

    public int getMaxConnectedPipes() {
        return maxConnectedPipes;
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public Pipe getInput() {
        return input;
    }

    public Pipe getOutput() {
        return output;
    }

    public void setRandomEnabled(boolean randomEnabled) {
        Pump.randomEnabled = randomEnabled;
    }

    public void setInput(Pipe input) {
        this.input = input;
    }

    public void setOutput(Pipe output) {
        this.output = output;
    }


}