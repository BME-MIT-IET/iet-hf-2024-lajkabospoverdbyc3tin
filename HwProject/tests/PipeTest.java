import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import HwProject.*;

class PipeTest {

    private TestPipe testPipe;
    private TestPump pump1;
    private TestPump pump2;

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game(System.out);
        testPipe = new TestPipe();
        pump1 = new TestPump(1);
        pump2 = new TestPump(1);
        Pipe.setGame(game);
    }

    @Test
    void connectPipe() {

    }

    @Test
    void getID() {
        // 13 pipes are already created in the game
        assertEquals("Pipe14", testPipe.getID());
        assertEquals("Pipe15", new TestPipe().getID());
    }

    @Test
    void makeSlippy() {

    }

    @Test
    void makeSticky() {

    }

    @Test
    void step() {
    }

    @Test
    void isPlayerRemoveable() {

    }

    @Test
    void isPlayerAddable() {

    }

    @Test
    void connectActive() {

    }

    @Test
    void disconnectActive() {

    }

    @Test
    void recieveWater() {

    }

    @Test
    void transmitWater() {

    }

    @Test
    void spillWater() {

    }

    @Test
    void damage() {
        testPipe.damage();
        assertTrue(testPipe.getDamaged());
    }

    @Test
    void repair() {

    }

    @Test
    void placePump() {

    }

    @AfterEach
    public void cleanUp() {
        TestCleanup.cleanup();
    }
}