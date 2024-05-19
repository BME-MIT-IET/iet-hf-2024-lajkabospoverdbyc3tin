package tests.HelperClasses;

import src.*;

import java.util.ArrayList;
import java.util.Collection;

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

    public boolean getSplippy() {
        return this.isSlippy;
    }

    public StickyStates getSticky() {
        return this.stickyState;
    }

    public static void setInstanceNr(int i) {
        instanceNr = i;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean getDamage() {
        return isDamaged;
    }
}
