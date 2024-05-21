
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


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
        pump1.connectPipe(testPipe);
        assertEquals(pump1, testPipe.getends().get(0));
    }

    @Test
    void getID() {
        // 13 pipes are already created in the game
        assertEquals("Pipe14", testPipe.getID());
        assertEquals("Pipe15", new TestPipe().getID());
    }

    @Test
    void makeSlippy() {
        testPipe.makeSlippy();
        assertSame(true, testPipe.getSlippy());
    }

    @Test
    void makeSticky() {
        pump1.connectPipe(testPipe);
        pump2.connectPipe(testPipe);
        testPipe.makeSticky();
        assertSame(StickyStates.StickyAndOccupied, testPipe.getSticky());
    }

    @Test
    void step() {
    }

    @Test
    void isPlayerRemoveable() {
        pump1.connectPipe(testPipe);
        pump2.connectPipe(testPipe);
        testPipe.makeSticky();

        Saboteur player = new Saboteur("Saboteur1", pump1);
        player.move(testPipe);
        testPipe.movePlayer(player);
        assertEquals(1, testPipe.getPlayers().size());
    }

    @Test
    void isPlayerAddable() {
        pump1.connectPipe(testPipe);
        pump2.connectPipe(testPipe);
        testPipe.makeSticky();

        Saboteur player1 = new Saboteur("Saboteur1", pump1);
        Saboteur player2 = new Saboteur("Saboteur2", pump1);
        player1.move(testPipe);
        testPipe.movePlayer(player1);
        assertEquals(1, testPipe.getPlayers().size());
        testPipe.movePlayer(player2);
        assertEquals(1, testPipe.getPlayers().size());
    }

    @Test
    void connectActive() {
        // pump1 is connected to testPipe
        pump1.connectPipe(testPipe);
        // pump2 is connected to testPipe
        pump2.connectPipe(testPipe);
        assertEquals(2, testPipe.getends().size());
    }

    @Test
    void disconnectActive() {
        // pump1 is connected to testPipe
        pump1.connectPipe(testPipe);
        // pump2 is connected to testPipe
        pump2.connectPipe(testPipe);
        // pump1 is disconnected from testPipe
        pump1.disconnectPipe(testPipe);
        assertEquals(1, testPipe.getends().size());
    }

    @Test
    void recieveWater() {
        pump1.connectPipe(testPipe);
        pump2.connectPipe(testPipe);

        pump1.setWaterLevel(10);
        pump1.setOutput(testPipe);
        pump2.setInput(testPipe);

        pump1.step();
        assertEquals(10, testPipe.getWaterLevel());
    }

    @Test
    void transmitWater() {
        pump1.connectPipe(testPipe);
        pump2.connectPipe(testPipe);

        pump1.setWaterLevel(10);
        pump1.setOutput(testPipe);
        pump2.setInput(testPipe);

        pump1.step();
        assertEquals(10, testPipe.getWaterLevel());
        testPipe.step();
        pump2.step();
        assertEquals(10, pump2.getWaterLevel());
        assertEquals(0, pump1.getWaterLevel());
        assertEquals(0, testPipe.getWaterLevel());
    }

    @Test
    void spillWater() {
        pump1.connectPipe(testPipe);
        pump2.connectPipe(testPipe);

        pump1.setWaterLevel(10);
        pump1.setOutput(testPipe);
        pump2.setInput(testPipe);

        pump1.step();
        assertEquals(10, testPipe.getWaterLevel());

        pump2.disconnectPipe(testPipe);

        pump1.setWaterLevel(10);
        pump1.step();

        assertEquals(0, testPipe.getWaterLevel());
    }

    @Test
    void damage() {
        testPipe.damage();
        assertTrue(testPipe.getDamaged());
    }

    @Test
    void repair() {
        testPipe.damage();
        testPipe.repair();
        assertFalse(testPipe.getDamaged());
    }

    @Test
    void placePump() {
        Drain drain = new Drain();
        Drain.setGame(game);
        pump1.connectPipe(testPipe);
        drain.connectPipe(testPipe);
        Mechanic player = new Mechanic("Mechanic1", drain);
        player.pickUpPump();
        player.move(testPipe);
        Pump placedPump = player.getPumpFromInventoryByID("Pump9");
        player.placePump(placedPump);
        assertEquals(2, testPipe.getends().size());
        assertTrue(testPipe.getends().contains(placedPump));
        assertTrue(testPipe.getends().contains(drain));
    }

    @AfterEach
    public void cleanUp() {
        TestCleanup.cleanup();
    }
}