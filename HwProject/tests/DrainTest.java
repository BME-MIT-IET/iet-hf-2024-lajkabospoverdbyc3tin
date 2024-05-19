package tests;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import src.*;
import tests.HelperClasses.*;

public class DrainTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game(System.out);
        Drain.SetGame(game);

    }

    @Test
    public void testGetInstanceNr() {
        int initialInstanceNr = Drain.GetInstanceNr();
        Drain drain1 = new Drain();
        assertEquals(initialInstanceNr + 1, Drain.GetInstanceNr());
    }

    @Test
    public void testPullWater() {
        Source source = new Source();
        TestPipe pipe = new TestPipe();
        Drain drain = new Drain();

        source.ConnectPipe(pipe);
        drain.ConnectPipe(pipe);

        source.Step();
        pipe.Step();
        drain.Step();

        assertNotEquals(0, game.GetMechanicPoint());
    }

    @Test
    public void testPickUpPump() {
        // Create a Drain instance
        Drain drain = new Drain();
        // Pick up a pump
        Pump pump = drain.PickUpPump();
        // Verify that a pump is picked up
        assertNotNull(pump);
    }

    @Test
    public void testPickUpPipe() {
        // Create a Drain instance
        Drain drain = new Drain();
        // Pick up a pipe
        Pipe pipe = drain.PickUpPipe();
        // Verify that a pipe is picked up
        assertNotNull(pipe);
    }

    @AfterEach
    public void cleanUp() {
        TestPump.setInstanceNr(0);
        TestPipe.setInstanceNr(0);
        TestSource.setInstanceNr(0);
    }
}
