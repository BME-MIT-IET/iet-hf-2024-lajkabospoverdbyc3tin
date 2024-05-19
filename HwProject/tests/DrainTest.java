package HwProject.tests;

import HwProject.tests.HelperClasses.*;
import HwProject.src.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DrainTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game(System.out);
        Drain.setGame(game);

    }

    @Test
    public void testPullWater() {
        Source source = new Source();
        TestPipe pipe = new TestPipe();
        Drain drain = new Drain();

        source.connectPipe(pipe);
        drain.connectPipe(pipe);

        source.step();
        pipe.step();
        drain.step();

        assertNotEquals(0, game.getMechanicPoint());
    }

    @Test
    public void testPickUpPump() {
        // Create a Drain instance
        Drain drain = new Drain();
        // Pick up a pump
        Pump pump = drain.pickUpPump();
        // Verify that a pump is picked up
        assertNotNull(pump);
    }

    @Test
    public void testPickUpPipe() {
        // Create a Drain instance
        Drain drain = new Drain();
        // Pick up a pipe
        Pipe pipe = drain.pickUpPipe();
        // Verify that a pipe is picked up
        assertNotNull(pipe);
    }

    @AfterEach
    public void cleanUp() {
        TestCleanup.cleanup();
    }
}
