package tests.HelperClasses;

import src.Pipe;

/**
 *    Pipe subclass for testing, only added setters and getters for each private and protected field
 */
public class TestPipe extends Pipe {
    public TestPipe() {
        super();
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public static void setInstanceNr(int i) {
        instanceNr = i;
    }
}
