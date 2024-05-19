package src;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import HwProject.tests.HelperClasses.TestCleanup;
import HwProject.tests.HelperClasses.TestSaboteur;

class SaboteurTest {
    private Game game;

    private TestSaboteur saboteur;

    @BeforeEach
    public void setUp() {
        game = new Game(System.out);
        Pipe.SetGame(game);
        Drain.SetGame(game);
        saboteur = new TestSaboteur("Saboteur1", game.GetFieldByID("Drain1"));
    }

    @Test
    void move() {
        saboteur.Move(game.GetFieldByID("Pipe12"));
        assertEquals(game.GetFieldByID("Pipe12"), saboteur.getPosition());

        TestSaboteur saboteur2 = new TestSaboteur("Saboteur2", game.GetFieldByID("Drain1"));
        saboteur2.Move(game.GetFieldByID("Pipe12"));
        assertEquals(game.GetFieldByID("Drain1"), saboteur2.getPosition());
    }

    @Test
    void setPumpDirection() {
        saboteur.Move(game.GetFieldByID("Pipe12"));
        saboteur.Move(game.GetFieldByID("Pump5"));
        saboteur.SetPumpDirection((Pipe) game.GetFieldByID("Pipe3"), (Pipe) game.GetFieldByID("Pipe10"));
        assertEquals(game.GetFieldByID("Pipe10"), ((Pump) game.GetFieldByID("Pump5")).output);
        assertEquals(game.GetFieldByID("Pipe3"), ((Pump) game.GetFieldByID("Pump5")).input);
    }

    @Test
    void damage() {
        saboteur.Move(game.GetFieldByID("Pipe12"));
        saboteur.Damage();
        assertTrue(((Pipe) game.GetFieldByID("Pipe12")).isDamaged);
    }

    @Test
    void makeSlippy() {
        saboteur.Move(game.GetFieldByID("Pipe12"));
        saboteur.MakeSlippy();
        assertTrue(((Pipe) game.GetFieldByID("Pipe12")).isSlippy);
    }

    @Test
    void makeSticky() {
    }

    @Test
    void testSetPumpDirection() {
    }

    @Test
    void testDamage() {
    }

    @Test
    void testMakeSlippy() {
    }

    @Test
    void testMakeSticky() {
    }

    @AfterEach
    public void cleanUp() {
        TestCleanup.cleanup();
    }
}