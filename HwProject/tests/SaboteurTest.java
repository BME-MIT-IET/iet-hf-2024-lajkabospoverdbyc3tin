import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import HwProject.*;


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

    }

    @Test
    void setPumpDirection() {

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

    }

    @Test
    void makeSticky() {

    }

    @AfterEach
    public void cleanUp() {
        TestCleanup.cleanup();
    }
}