import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SourceTest {
    private static TestSource source;

    private static Game game;

    @BeforeAll
    public static void init() {
        game = new Game(System.out);
        Pipe.setGame(game); //Already two sources in the game
        source = new TestSource();
    }

    @Test
    public void testConstructor() {
        assertEquals("Source3", source.getID(), "id should be set correctly");
    }

    @Test
    public void testPushWater() {
        TestPipe pipe1 = new TestPipe();
        TestPipe pipe2 = new TestPipe();
        TestPump pump = new TestPump(10);
        source.connectPipe(pipe1);
        source.connectPipe(pipe2);
        pump.connectPipe(pipe1);
        pump.connectPipe(pipe2);
        source.step();
        assertTrue(pipe1.getWaterLevel() > 0, "Pipe1 should have received water");
        assertTrue(pipe2.getWaterLevel() > 0, "Pipe2 should have received water");
    }

    @Test
    public void testStepCallsPushWater() {
        TestSource testSource = new TestSource();
        TestPipe pipe1 = new TestPipe();
        TestPipe pipe2 = new TestPipe();
        TestPump pump = new TestPump(10);
        testSource.connectPipe(pipe1);
        testSource.connectPipe(pipe2);
        pump.connectPipe(pipe1);
        pump.connectPipe(pipe2);
        testSource.step();
        assertTrue(testSource.isPushWaterCalled(), "PushWater should be called by Step method");
        assertTrue(pipe1.getWaterLevel() > 0, "Pipe1 should have received water");
        assertTrue(pipe2.getWaterLevel() > 0, "Pipe2 should have received water");
    }

    @AfterEach
    public void cleanUp() {
        TestCleanup.cleanup();
    }
}
