package gui;


import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.exception.ActionFailedException;
import org.assertj.swing.fixture.FrameFixture;
import java.awt.*;

import static gui.TestingTools.findButtonByText;
import static org.junit.jupiter.api.Assertions.assertThrows;

import HwProject.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameWindowTests {
    private FrameFixture window;

    @BeforeEach
    public void setUp() {
        var gameController = new GameControllerUI(new GameModel());
        var gui = gameController.getFrame();
        window = new FrameFixture(gui);
        window.show(); // shows the frame to test
        window.button(findButtonByText("Start")).click();
    }

    @Test
    @GUITest
    public void gameHasTitle() {
        window.requireTitle("Sivatagi Vizhalozatok");
    }

    @Test()
    @GUITest
    public void GameWindowNotResizable() {
        assertThrows(ActionFailedException.class, () ->{
            var oldSize = window.target().getSize();
            var newSize = new Dimension(oldSize.width + 100, 800);
            window.resizeTo(newSize);
            window.requireSize(oldSize);
        });

    }

    @Test
    @GUITest
    public void mechanicColor() {
        window.label("Mechanic1").requireVisible().requireText("Mechanic1").foreground().requireEqualTo(Color.WHITE);
    }

    @Test
    @GUITest
    public void mechanicClickColor() {
        window.label("Mechanic1").requireVisible().requireText("Mechanic1").click().foreground().requireEqualTo(Color.RED);
    }

    @Test
    @GUITest
    public void saboteurColor() {
        window.label("Saboteur1").requireVisible().requireText("Saboteur1").foreground().requireEqualTo(Color.WHITE);
    }

    @Test
    @GUITest
    public void saboteurClickColor() {
        window.label("Saboteur1").requireVisible().requireText("Saboteur1").click().foreground().requireEqualTo(Color.RED);
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }
}
