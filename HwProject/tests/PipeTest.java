package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.HelperClasses.TestCleanup;

import static org.junit.jupiter.api.Assertions.*;

import src.*;
import tests.HelperClasses.*;

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
        Pipe.SetGame(game);
    }

    @Test
    void connectPipe() {
        pump1.ConnectPipe(testPipe);
        assertEquals(pump1, testPipe.getends().get(0));
    }

    @Test
    void getID() {
        // 13 pipes are already created in the game
        assertEquals("Pipe14", testPipe.GetID());
        assertEquals("Pipe15", new TestPipe().GetID());
    }

    @Test
    void makeSlippy() {
        testPipe.MakeSlippy();
        assertSame(true, testPipe.getSplippy());
    }

    @Test
    void makeSticky() {
        pump1.ConnectPipe(testPipe);
        pump2.ConnectPipe(testPipe);
        testPipe.MakeSticky();
        assertSame(Pipe.StickyStates.StickyAndOccupied, testPipe.getSticky());
    }

    @Test
    void step() {
    }

    @Test
    void isPlayerRemoveable() {
        pump1.ConnectPipe(testPipe);
        pump2.ConnectPipe(testPipe);
        testPipe.MakeSticky();

        Saboteur player = new Saboteur("Saboteur1", pump1);
        player.Move(testPipe);
        testPipe.MovePlayer(player);
        assertEquals(1, testPipe.getPlayers().size());
    }

    @Test
    void isPlayerAddable() {
        pump1.ConnectPipe(testPipe);
        pump2.ConnectPipe(testPipe);
        testPipe.MakeSticky();

        Saboteur player1 = new Saboteur("Saboteur1", pump1);
        Saboteur player2 = new Saboteur("Saboteur2", pump1);
        player1.Move(testPipe);
        testPipe.MovePlayer(player1);
        assertEquals(1, testPipe.getPlayers().size());
        testPipe.MovePlayer(player2);
        assertEquals(1, testPipe.getPlayers().size());
    }

    @Test
    void connectActive() {
        // pump1 is connected to testPipe
        pump1.ConnectPipe(testPipe);
        // pump2 is connected to testPipe
        pump2.ConnectPipe(testPipe);
        assertEquals(2, testPipe.getends().size());
    }

    @Test
    void disconnectActive() {
        // pump1 is connected to testPipe
        pump1.ConnectPipe(testPipe);
        // pump2 is connected to testPipe
        pump2.ConnectPipe(testPipe);
        // pump1 is disconnected from testPipe
        pump1.DisconnectPipe(testPipe);
        assertEquals(1, testPipe.getends().size());
    }

    @Test
    void recieveWater() {
        pump1.ConnectPipe(testPipe);
        pump2.ConnectPipe(testPipe);

        pump1.setWaterLevel(10);
        pump1.setOutput(testPipe);
        pump2.setInput(testPipe);

        pump1.Step();
        assertEquals(10, testPipe.getWaterLevel());
    }

    @Test
    void transmitWater() {
        pump1.ConnectPipe(testPipe);
        pump2.ConnectPipe(testPipe);

        pump1.setWaterLevel(10);
        pump1.setOutput(testPipe);
        pump2.setInput(testPipe);

        pump1.Step();
        assertEquals(10, testPipe.getWaterLevel());
        testPipe.Step();
        pump2.Step();
        assertEquals(10, pump2.getWaterLevel());
        assertEquals(0, pump1.getWaterLevel());
        assertEquals(0, testPipe.getWaterLevel());
    }

    @Test
    void spillWater() {
        pump1.ConnectPipe(testPipe);
        pump2.ConnectPipe(testPipe);

        pump1.setWaterLevel(10);
        pump1.setOutput(testPipe);
        pump2.setInput(testPipe);

        pump1.Step();
        assertEquals(10, testPipe.getWaterLevel());

        pump2.DisconnectPipe(testPipe);

        pump1.setWaterLevel(10);
        pump1.Step();

        assertEquals(0, testPipe.getWaterLevel());
    }

    @Test
    void damage() {
        testPipe.Damage();
        assertTrue(testPipe.getDamage());
    }

    @Test
    void repair() {
        testPipe.Damage();
        testPipe.Repair();
        assertFalse(testPipe.getDamage());
    }

    @Test
    void placePump() {
        Drain drain = new Drain();
        Drain.SetGame(game);
        pump1.ConnectPipe(testPipe);
        drain.ConnectPipe(testPipe);
        Mechanic player = new Mechanic("Mechanic1", drain);
        player.PickUpPump();
        player.Move(testPipe);
        Pump placedPump = player.GetPumpFromInventoryByID("Pump9");
        player.PlacePump(placedPump);
        assertEquals(2, testPipe.getends().size());
        assertTrue(testPipe.getends().contains(placedPump));
        assertTrue(testPipe.getends().contains(drain));
    }

    @AfterEach
    public void cleanUp() {
        TestCleanup.cleanup();
    }
}