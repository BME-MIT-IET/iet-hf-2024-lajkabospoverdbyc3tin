import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.fixture.FrameFixture;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

import HwProject.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

public class GameWindowTests {
    private FrameFixture window;

    @BeforeEach
    public void setUp() {
        var gameController = new GameControllerUI(new GameModel());
        var gui = gameController.getFrame();
        window = new FrameFixture(gui);
        window.show(); // shows the frame to test
        window.button(TestingTools.findButtonByText("Start")).click();
    }

    @Test
    @GUITest
    public void gameHasTitle() {

    }

    @Test()
    @GUITest
    public void GameWindowNotResizable() {

    }

    @Test
    @GUITest
    public void mechanicColor() {
    }

    @Test
    @GUITest
    public void mechanicClickColor() {
    }

    @Test
    @GUITest
    public void saboteurColor() {
    }

    @Test
    @GUITest
    public void saboteurClickColor() {
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
        TestCleanup.cleanup();
    }
}
