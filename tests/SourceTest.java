package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import src.*;
import tests.HelperClasses.*;

public class SourceTest {
    private static TestSource source;

    private static Game game;

    @BeforeAll
    public static void init() {
        game = new Game(System.out);
        Pipe.SetGame(game); //Already two sources in the game
        source = new TestSource();
    }

    @Test
    public void testConstructor() {
        assertEquals("Source3", source.GetID(), "id should be set correctly");
    }

    @Test
    public void testPushWater() {
        TestPipe pipe1 = new TestPipe();
        TestPipe pipe2 = new TestPipe();
        TestPump pump = new TestPump(10);
        source.ConnectPipe(pipe1);
        source.ConnectPipe(pipe2);
        pump.ConnectPipe(pipe1);
        pump.ConnectPipe(pipe2);
        source.Step();
        assertTrue(pipe1.getWaterLevel() > 0, "Pipe1 should have received water");
        assertTrue(pipe2.getWaterLevel() > 0, "Pipe2 should have received water");
    }

    @Test
    public void testStepCallsPushWater() {
        TestSource testSource = new TestSource();
        TestPipe pipe1 = new TestPipe();
        TestPipe pipe2 = new TestPipe();
        TestPump pump = new TestPump(10);
        testSource.ConnectPipe(pipe1);
        testSource.ConnectPipe(pipe2);
        pump.ConnectPipe(pipe1);
        pump.ConnectPipe(pipe2);
        testSource.Step();
        assertTrue(testSource.isPushWaterCalled(), "PushWater should be called by Step method");
        assertTrue(pipe1.getWaterLevel() > 0, "Pipe1 should have received water");
        assertTrue(pipe2.getWaterLevel() > 0, "Pipe2 should have received water");
    }

    @AfterEach
    public void cleanUp() {
        TestPump.setInstanceNr(0);
        TestPipe.setInstanceNr(0);
        TestSource.setInstanceNr(0);
    }
}
