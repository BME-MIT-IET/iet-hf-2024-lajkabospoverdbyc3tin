import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class SaboteurTest {
    private Game game;

    private TestSaboteur saboteur;

    @BeforeEach
    public void setUp() {
        game = new Game(System.out);
        Pipe.setGame(game);
        Drain.setGame(game);
        saboteur = new TestSaboteur("Saboteur1", game.getFieldByID("Drain1"));
    }

    @Test
    void move() {
        saboteur.move(game.getFieldByID("Pipe12"));
        assertEquals(game.getFieldByID("Pipe12"), saboteur.getPosition());

        TestSaboteur saboteur2 = new TestSaboteur("Saboteur2", game.getFieldByID("Drain1"));
        saboteur2.move(game.getFieldByID("Pipe12"));
        assertEquals(game.getFieldByID("Drain1"), saboteur2.getPosition());
    }

    @Test
    void setPumpDirection() {
        TestPump pump = new TestPump(2);
        TestPipe pipe1 = new TestPipe();
        TestPipe pipe2 = new TestPipe();
        pump.connectPipe(pipe1);
        pump.connectPipe(pipe2);
        saboteur = new TestSaboteur("Saboteur2", pump);
        saboteur.setPumpDirection(pipe1, pipe2);

        assertEquals(pipe1, pump.getInput());
        assertEquals(pipe2, pump.getOutput());
    }

    @Test
    void damage() {
        TestPipe pipe = new TestPipe();
        saboteur = new TestSaboteur("Saboteur2", pipe);
        saboteur.damage();
        assertTrue(pipe.getDamaged());
    }

    @Test
    void makeSlippy() {
        TestPipe pipe = new TestPipe();
        saboteur = new TestSaboteur("Saboteur2", pipe);
        saboteur.makeSlippy();
        assertTrue(pipe.getSlippy());
    }

    @Test
    void makeSticky() {
        TestPipe pipe = new TestPipe();
        saboteur = new TestSaboteur("Saboteur2", pipe);
        saboteur.makeSticky();
        assertEquals(StickyStates.StickyAndOccupied, pipe.getSticky());
    }

    @AfterEach
    public void cleanUp() {
        TestCleanup.cleanup();
    }
}