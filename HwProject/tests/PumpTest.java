
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PumpTest {
    private TestPump pump;
    private TestPipe inputPipe;
    private TestPipe outputPipe;
    private Game game;


    @BeforeEach
    public void init() {
        game = new Game(System.out);
        Pipe.setGame(game);

        pump = new TestPump(3);
        inputPipe = new TestPipe();
        outputPipe = new TestPipe();
        pump.connectPipe(inputPipe);
        pump.connectPipe(outputPipe);
        TestPump endPointOfOutput = new TestPump(3);
        endPointOfOutput.connectPipe(outputPipe);
    }

    @Test
    void testConstructor() {
        int instanceNrBeforeCreation = pump.getInstanceOfPump();
        pump = new TestPump(3);
        int instanceNrAfterCreation = instanceNrBeforeCreation + 1;
        assertEquals("Pump" + instanceNrAfterCreation, pump.getID(), "id should be set correctly");
        assertEquals(0, pump.getWaterLevel(), "Initial water level should be 0");
        assertEquals(50, pump.getMaxWaterLevel(), "Max water level should be 50");
        assertEquals(3, pump.getMaxConnectedPipes(), "Max connected pipes should be set correctly");
        assertFalse(pump.isDamaged(), "Pump should not be damaged initially");
    }

    @Test
    void testPushWater() {
        pump.setPumpDirection(inputPipe, outputPipe);
        pump.setWaterLevel(10); // Simulate some water in the pump
        pump.step();

        assertEquals(0, pump.getWaterLevel(), "Water level should be 0 after pushing water to output pipe");
        assertEquals(10, outputPipe.getWaterLevel(), "Output pipe should receive maximum water");
    }

    @Test
    void testPullWater() {
        pump.setPumpDirection(inputPipe, outputPipe);
        inputPipe.setWaterLevel(30); // Simulate some water in the input pipe
        pump.step();

        assertEquals(30, pump.getWaterLevel(), "Water level should increase after pulling water from input pipe");
    }

    @Test
    void testStep() {
        pump.setPumpDirection(inputPipe, outputPipe);
        inputPipe.setWaterLevel(30); // Simulate some water in the input pipe
        pump.setWaterLevel(30); // Simulate some water in the input pipe
        pump.step();

        assertTrue(outputPipe.getWaterLevel() > 0, "Pump should push water to output pipe");
        assertTrue(pump.getWaterLevel() > 0, "Pump should push water to output pipe");
    }



    @Test
    void testGetDirection() {
        pump.setPumpDirection(inputPipe, outputPipe);
        ArrayList<String> directions = pump.getdirection();

        assertEquals(inputPipe.getID(), directions.get(0), "Input direction should be correct");
        assertEquals(outputPipe.getID(), directions.get(1), "Output direction should be correct");
    }

    @Test
    void testTryToDamage() {
        pump.setRandomEnabled(false); // Disable randomness for predictable results
        int result = pump.trytoDamage();

        assertTrue(pump.isDamaged(), "Pump should be damaged");
        assertEquals(0, result, "Result should indicate successful damage");
    }

    @Test
    void testSetPumpDirection() {
        pump.connectPipe(inputPipe);
        pump.connectPipe(outputPipe);
        int result = pump.setPumpDirection(inputPipe, outputPipe);

        assertEquals(0, result, "Result should indicate successful setting of pump direction");
        assertEquals(inputPipe, pump.getInput(), "Input pipe should be set correctly");
        assertEquals(outputPipe, pump.getOutput(), "Output pipe should be set correctly");
    }

    @Test
    void testRepair() {
        pump.setRandomEnabled(false); // Disable randomness for predictable results
        pump.trytoDamage();
        int result = pump.repair();

        assertFalse(pump.isDamaged(), "Pump should be repaired");
        assertEquals(0, result, "Result should indicate successful repair");
    }

    @Test
    void testConnectPipe() {
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();

        int result1 = pump.connectPipe(pipe1);
        int result2 = pump.connectPipe(pipe2);

        assertEquals(0, result1, "First pipe should connect successfully");
        assertEquals(4, result2, "Second pipe should connect successfully");
    }

    @Test
    void testDisconnectPipe() {
        pump.connectPipe(inputPipe);
        pump.connectPipe(outputPipe);
        pump.setPumpDirection(inputPipe, outputPipe);

        int result1 = pump.disconnectPipe(inputPipe);
        int result2 = pump.disconnectPipe(outputPipe);

        assertEquals(0, result1, "Input pipe should disconnect successfully");
        assertEquals(0, result2, "Output pipe should disconnect successfully");
        assertNull(pump.getInput(), "Input should be null after disconnection");
        assertNull(pump.getOutput(), "Output should be null after disconnection");
    }

    @AfterEach
    public void cleanUp() {
        TestCleanup.cleanup();
    }
}