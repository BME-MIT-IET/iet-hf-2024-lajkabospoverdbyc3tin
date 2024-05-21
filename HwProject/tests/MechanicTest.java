
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MechanicTest {
    private Game game;

    private TestMechanic mechanic;

    @BeforeEach
    public void setUp() {
        game = new Game(System.out);
        Pipe.setGame(game);
        Drain.setGame(game);
        mechanic = new TestMechanic("Mechanic1", game.getFieldByID("Drain1"));
    }

    @AfterEach
    void tearDown() {
        TestCleanup.cleanup();
    }

    @Test
    void move() {
        mechanic.move(game.getFieldByID("Pipe12"));
        assertEquals(game.getFieldByID("Pipe12"), mechanic.getPosition());

        TestMechanic mechanic2 = new TestMechanic("Mechanic2", game.getFieldByID("Drain1"));
        mechanic2.move(game.getFieldByID("Pipe12"));
        assertEquals(game.getFieldByID("Drain1"), mechanic2.getPosition());
    }

    @Test
    void setPumpDirection() {
        TestPump pump = new TestPump(2);
        TestPipe pipe1 = new TestPipe();
        TestPipe pipe2 = new TestPipe();
        pump.connectPipe(pipe1);
        pump.connectPipe(pipe2);
        mechanic = new TestMechanic("Mechanic2", pump);
        mechanic.setPumpDirection(pipe1, pipe2);

        assertEquals(pipe1, pump.getInput());
        assertEquals(pipe2, pump.getOutput());
    }

    @Test
    void repair() {
        TestPipe pipe = new TestPipe();
        pipe.setDamaged(true);
        mechanic = new TestMechanic("Mechanic2", pipe);
        mechanic.repair();
        assertFalse(pipe.getDamaged());
    }

    @Test
    void pickUpPump() {
        TestDrain drain = new TestDrain();
        mechanic = new TestMechanic("Mechanic2", drain);
        int pickedUp = mechanic.pickUpPump();
        Pump pickedUpPump = mechanic.getPumpFromInventoryByID("Pump7");
        assertEquals(0, pickedUp);
        assertNotNull(pickedUpPump);
    }

    @Test
    void placePump() {
        TestDrain drain = new TestDrain();
        TestPipe pipe = new TestPipe();
        TestPump pump = new TestPump(3);
        drain.connectPipe(pipe);
        pump.connectPipe(pipe);

        mechanic = new TestMechanic("Mechanic2", drain);
        int pickedUp = mechanic.pickUpPump();
        Pump pickedUpPump = mechanic.getPumpFromInventoryByID("Pump8");
        assertEquals(0, pickedUp);
        assertNotNull(pickedUpPump);

        mechanic.move(pipe);
        int placed = mechanic.placePump(pickedUpPump);
        assertEquals(0, placed);
        assertNull(mechanic.getPumpFromInventoryByID("Pump7"));
        assertTrue(pipe.getends().contains(pickedUpPump));
    }

    @Test
    void makeSticky() {
        TestPipe pipe = new TestPipe();
        mechanic = new TestMechanic("Mechanic2", pipe);
        mechanic.makeSticky();
        assertEquals(StickyStates.StickyAndOccupied, pipe.getSticky());
    }

    @Test
    void disconnectPipe() {
        mechanic.move(game.getFieldByID("Pipe12"));
        mechanic.move(game.getFieldByID("Pump5"));
        mechanic.disconnectPipe((Pipe)game.getFieldByID("Pipe12"));
        assertNotNull(mechanic.getPipeFromInventoryByID("Pipe12"));
    }

    @Test
    void connectPipe() {
        mechanic.move(game.getFieldByID("Pipe12"));
        mechanic.move(game.getFieldByID("Pump5"));
        mechanic.disconnectPipe((Pipe)game.getFieldByID("Pipe12"));
        assertNotNull(mechanic.getPipeFromInventoryByID("Pipe12"));

        mechanic.move(game.getFieldByID("Pipe10"));
        mechanic.move(game.getFieldByID("Pump6"));
        mechanic.connectPipe(mechanic.getPipeFromInventoryByID("Pipe12"));
        assertNull(mechanic.getPipeFromInventoryByID("Pipe12"));
        assertTrue(game.getFieldByID("Pipe12").isNeighbour(game.getFieldByID("Pump6")));
        assertTrue(game.getFieldByID("Pipe12").isNeighbour(game.getFieldByID("Drain1")));
    }

    @Test
    void pickUpPipe() {
        mechanic.pickUpPipe();
        assertNotNull(mechanic.getPipeFromInventoryByID("Pipe14"));
        mechanic.move(game.getFieldByID("Pipe12"));
        mechanic.pickUpPipe();
        assertNull(mechanic.getPipeFromInventoryByID("Pipe15"));
    }
}