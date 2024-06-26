import HwProject.*;
import java.util.ArrayList;

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

    public boolean getSlippy() {
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

    public void setDamaged(boolean isDamaged) {
        this.isDamaged = isDamaged;
    }

    public boolean getDamaged() {
        return isDamaged;
    }
}
