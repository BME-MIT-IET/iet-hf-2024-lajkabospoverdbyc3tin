package tests;

import org.junit.jupiter.api.*;
import src.*;
import tests.HelperClasses.*;
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
        Pipe.SetGame(game);

        pump = new TestPump(3);
        inputPipe = new TestPipe();
        outputPipe = new TestPipe();
        pump.ConnectPipe(inputPipe);
        pump.ConnectPipe(outputPipe);
        TestPump endPointOfOutput = new TestPump(3);
        endPointOfOutput.ConnectPipe(outputPipe);
    }

    @Test
    void testConstructor() {
        int instanceNrBeforeCreation = pump.getInstanceOfPump();
        pump = new TestPump(3);
        int instanceNrAfterCreation = instanceNrBeforeCreation + 1;
        assertEquals("Pump" + instanceNrAfterCreation, pump.GetID(), "id should be set correctly");
        assertEquals(0, pump.getWaterLevel(), "Initial water level should be 0");
        assertEquals(50, pump.getMaxWaterLevel(), "Max water level should be 50");
        assertEquals(3, pump.getMaxConnectedPipes(), "Max connected pipes should be set correctly");
        assertFalse(pump.isDamaged(), "Pump should not be damaged initially");
    }

    @Test
    void testPushWater() {
        pump.SetPumpDirection(inputPipe, outputPipe);
        pump.setWaterLevel(10); // Simulate some water in the pump
        pump.Step();

        assertEquals(0, pump.getWaterLevel(), "Water level should be 0 after pushing water to output pipe");
        assertEquals(10, outputPipe.getWaterLevel(), "Output pipe should receive maximum water");
    }

    @Test
    void testPullWater() {
        pump.SetPumpDirection(inputPipe, outputPipe);
        inputPipe.setWaterLevel(30); // Simulate some water in the input pipe
        pump.Step();

        assertEquals(30, pump.getWaterLevel(), "Water level should increase after pulling water from input pipe");
    }

    @Test
    void testStep() {
        pump.SetPumpDirection(inputPipe, outputPipe);
        inputPipe.setWaterLevel(30); // Simulate some water in the input pipe
        pump.setWaterLevel(30); // Simulate some water in the input pipe
        pump.Step();

        assertTrue(outputPipe.getWaterLevel() > 0, "Pump should push water to output pipe");
        assertTrue(pump.getWaterLevel() > 0, "Pump should push water to output pipe");
    }



    @Test
    void testGetDirection() {
        pump.SetPumpDirection(inputPipe, outputPipe);
        ArrayList<String> directions = pump.getdirection();

        assertEquals(inputPipe.GetID(), directions.get(0), "Input direction should be correct");
        assertEquals(outputPipe.GetID(), directions.get(1), "Output direction should be correct");
    }

    @Test
    void testTryToDamage() {
        pump.setRandomEnabled(false); // Disable randomness for predictable results
        int result = pump.TrytoDamage();

        assertTrue(pump.isDamaged(), "Pump should be damaged");
        assertEquals(0, result, "Result should indicate successful damage");
    }

    @Test
    void testSetPumpDirection() {
        pump.ConnectPipe(inputPipe);
        pump.ConnectPipe(outputPipe);
        int result = pump.SetPumpDirection(inputPipe, outputPipe);

        assertEquals(0, result, "Result should indicate successful setting of pump direction");
        assertEquals(inputPipe, pump.getInput(), "Input pipe should be set correctly");
        assertEquals(outputPipe, pump.getOutput(), "Output pipe should be set correctly");
    }

    @Test
    void testRepair() {
        pump.setRandomEnabled(false); // Disable randomness for predictable results
        pump.TrytoDamage();
        int result = pump.Repair();

        assertFalse(pump.isDamaged(), "Pump should be repaired");
        assertEquals(0, result, "Result should indicate successful repair");
    }

    @Test
    void testConnectPipe() {
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();

        int result1 = pump.ConnectPipe(pipe1);
        int result2 = pump.ConnectPipe(pipe2);

        assertEquals(0, result1, "First pipe should connect successfully");
        assertEquals(4, result2, "Second pipe should connect successfully");
    }

    @Test
    void testDisconnectPipe() {
        pump.ConnectPipe(inputPipe);
        pump.ConnectPipe(outputPipe);
        pump.SetPumpDirection(inputPipe, outputPipe);

        int result1 = pump.DisconnectPipe(inputPipe);
        int result2 = pump.DisconnectPipe(outputPipe);

        assertEquals(0, result1, "Input pipe should disconnect successfully");
        assertEquals(0, result2, "Output pipe should disconnect successfully");
        assertNull(pump.getInput(), "Input should be null after disconnection");
        assertNull(pump.getOutput(), "Output should be null after disconnection");
    }

    @AfterEach
    public void cleanUp() {
        TestPump.setInstanceNr(0);
        TestPipe.setInstanceNr(0);
        TestSource.setInstanceNr(0);
    }
}